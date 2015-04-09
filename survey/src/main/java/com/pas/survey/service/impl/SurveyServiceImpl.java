package com.pas.survey.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.Answer;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.model.Survey;
import com.pas.survey.model.User;
import com.pas.survey.service.SurveyService;
import com.pas.survey.util.DataUtil;
import com.pas.survey.util.StringUtil;
import com.pas.survey.util.ValidateUtil;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name = "surveyDao")
	private BaseDao<Survey> surveyDao;
	@Resource(name = "pageDao")
	private BaseDao<Page> pageDao;
	@Resource(name = "questionDao")
	private BaseDao<Question> questionDao;
	@Resource(name = "answerDao")
	private BaseDao<Answer> answerDao;
	
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id=?";
		return surveyDao.findEntityByHql(hql, user.getId());
	}

	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}

	public Survey getSuvey(int sid) {
		return surveyDao.getEntity(sid);
	}

	public Survey getSuveyWithChildren(int sid) {
		Survey s=this.getSuvey(sid);
		
		//触发懒加载，强行加载Page集合与问题集合
		for (Page p : s.getPages()) {
			p.getQuestions().size();
		}
		return s;
	}

	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}

	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	public void deleteQuestion(Integer qid) {
		String hql_delete_a="delete from Answer a where a.questionid=?";
		answerDao.batchEntityByHql(hql_delete_a, qid);
		String hql_delete_q="delete from Question q where q.id=?";
		questionDao.batchEntityByHql(hql_delete_q, qid);
	}

	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}

	public void deletePage(Integer pid) {
		String hql_delete_a="delete from Answer a where a.questionid in (select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHql(hql_delete_a, pid);
		String hql_delete_q="delete from Question q where q.page.id=?";
		questionDao.batchEntityByHql(hql_delete_q, pid);
		String hql_delete_p="delete from Page p where p.id=?";
		pageDao.batchEntityByHql(hql_delete_p, pid);
	}

	public void deleteSurvey(Integer sid) {
		
		//删除答案
		String hql_delete_a="delete from Answer a where a.surveyid=?";
		answerDao.batchEntityByHql(hql_delete_a, sid);
		/**
		 * 写操作不允许两级以上连接
		 */
		//删除问题
		String hql_delete_q="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id=?)";
		questionDao.batchEntityByHql(hql_delete_q, sid);
		
		String hql_delete_p="delete from Page p where p.survey.id=?";
		pageDao.batchEntityByHql(hql_delete_p, sid);
		
		String hql_delete_s="delete from Survey s where s.id=?";
		surveyDao.batchEntityByHql(hql_delete_s, sid);
	}

	public void clearAnswer(Integer sid) {
		String hql="delete from Answer a where a.surveyid=?";
		answerDao.batchEntityByHql(hql, sid);
	}

	public void toggleStatus(Integer sid) {
		boolean closed=this.getSuvey(sid).getClosed();
		String hql="update Survey s set s.closed=? where s.id=?";
		surveyDao.batchEntityByHql(hql,!closed,sid);
	}

	public void updateLogoPhotoPath(Integer sid, String string) {
		Survey survey=this.getSuvey(sid);
		String oldpath=survey.getLogoPhotoPath();
		String hql="update Survey s set s.logoPhotoPath=? where s.id=?";
		surveyDao.batchEntityByHql(hql, string,sid);
		if(null!=oldpath){
			File f=new File(StringUtil.getProPath()+oldpath);
			f.delete();
		}
	}

	public List<Survey> getSuveysWithPages(User user) {
		String hql="from Survey s where s.user.id=?";
		List<Survey> surveys=surveyDao.findEntityByHql(hql, user.getId());
		for (Survey survey : surveys) {
			//强制加载Pages
			survey.getPages().size();
		}
		return surveys;
	}

	public void moveOrCopyPage(int srcpid, int targetpid, int moveType) {
		
		//源页面id->源页面->源调查
		Page srcPage=this.getPage(srcpid);
		Survey srcSurvey=srcPage.getSurvey();
		
		///目标页面id->目标页面->目标调查
		Page targetPage=this.getPage(targetpid);
		Survey targetSurvey=targetPage.getSurvey();
		
		if(srcSurvey.getId().equals(targetSurvey.getId())){
			//移动
			setOrderNo(srcPage,targetPage,moveType);
		}else{
			//复制页面
			//强制获取问题集合
			srcPage.getQuestions().size();
			//深度复制页面
			Page copyPage=(Page) DataUtil.deepCopy(srcPage);
			copyPage.setSurvey(targetSurvey);
			
			pageDao.saveEntity(copyPage);
			//保存问题集合
			for(Question q:copyPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			
			//设置页序
			setOrderNo(copyPage,targetPage,moveType);
			
		}
		
	}
	
	/**
	 * 设置页面序号
	 * @param srcPage
	 * @param targetPage
	 * @param moveType
	 */
	private void setOrderNo(Page srcPage, Page targetPage, int moveType) {
		if(moveType==0){
			//之前
			if(isFirstPage(targetPage)){
				srcPage.setOrderno(targetPage.getOrderno()-0.01f);
			}else{
				//取得目标页前一页
				Page prePage=getPrePage(targetPage);
				srcPage.setOrderno((prePage.getOrderno()+targetPage.getOrderno())/2);
			}
		}else{
			//之后
			if(isLastPage(targetPage)){
				srcPage.setOrderno(targetPage.getOrderno()+0.01f);
			}else{
				//取得目标页前一页
				Page nextPage=getNextPage(targetPage);
				srcPage.setOrderno((nextPage.getOrderno()+targetPage.getOrderno())/2);
			}
		}
	}


	/**
	 * 是否为首页
	 * @param targetPage
	 * @return
	 */
	private boolean isFirstPage(Page targetPage) {
		String hql="select count(*) from Page p where p.survey.id=? and p.orderno<?";
		long count=(Long) pageDao.getUniqueResult(hql, targetPage.getSurvey().getId(),targetPage.getOrderno());
		return count==0;
	}

	/**
	 * 是否为尾页
	 * @param targetPage
	 * @return
	 */
	private boolean isLastPage(Page targetPage) {
		String hql="select count(*) from Page p where p.survey.id=? and p.orderno>?";
		Long count=(Long) pageDao.getUniqueResult(hql, targetPage.getSurvey().getId(),targetPage.getOrderno());
		return count==0;
	}

	/**
	 * 获得页面的前一页
	 * @param targetPage
	 * @return
	 */
	private Page getPrePage(Page targetPage) {
		String hql="from Page p where p.survey.id=? and p.orderno<? order by p.orderno desc";
		List<Page> pages=pageDao.findEntityByHql(hql, targetPage.getSurvey().getId(),targetPage.getOrderno());
		Page p=null;
		if(pages.size()>0){
			p=pages.get(0);
		}
		return p;
	}
	
	/**
	 * 获得页面的下一页
	 * @param targetPage
	 * @return
	 */
	private Page getNextPage(Page targetPage) {
		String hql="from Page p where p.survey.id=? and p.orderno>? order by p.orderno asc";
		List<Page> pages=pageDao.findEntityByHql(hql, targetPage.getSurvey().getId(),targetPage.getOrderno());
		Page p=null;
		if(pages.size()>0){
			p=pages.get(0);
		}
		return p;
	}

	/**
	 * 得到所有的开放调查
	 */
	public List<Survey> getAllOpenedSurveys() {
		String hql="from Survey s where s.closed=?";
		return surveyDao.findEntityByHql(hql, false);
	}

	/**
	 * 得到首个Page对象
	 */
	public Page getFirstPage(Integer sid) {
		String hql="from Page p where p.survey.id=? order by p.orderno asc";
		List<Page> pages=this.pageDao.findEntityByHql(hql, sid);
		Page p=null;
		if(pages.size()>0){
			p=pages.get(0);
			p.getQuestions().size();
		}
		return p;
	}

	public Page getPrePage(Integer currentPid) {
		Page p=this.getPage(currentPid);
		Page target_page= this.getPrePage(p);
		if(null!=target_page){
			int quesCount=target_page.getQuestions().size();
			if(quesCount<1){
				//当前取出的页没有Question
				return getPrePage(target_page.getId());
			}
			return target_page;
		}
		return null;
	}

	public Page getNextPage(Integer currentPid) {
		Page p=this.getPage(currentPid);
		Page target_page= this.getNextPage(p);
		if(null!=target_page){
			int quesCount=target_page.getQuestions().size();
			if(quesCount<1){
				//当前取出的页没有问题
				return getNextPage(target_page.getId());
			}
			return target_page;
		}
		return null;
	}

	public void saveAnswers(List<Answer> storeAnswers) {
		if(ValidateUtil.isValid(storeAnswers)){
			java.util.Date answerDate=new java.util.Date();
			String uuid=UUID.randomUUID().toString();
			for (Answer answer : storeAnswers) {
				answer.setUuid(uuid);
				answer.setAnswerTime(answerDate);
				answerDao.saveEntity(answer);
			}
		}
	}
	
	public List<Question> getQuestions(Integer sid){
		String hql = "from Question q where q.page.survey.id = ?" ;
		return questionDao.findEntityByHql(hql, sid);
	}

	public List<Answer> getAnswers(Integer sid) {
		String hql = "from Answer a where a.surveyid = ? order by uuid" ;
		return answerDao.findEntityByHql(hql, sid);
	}

}
