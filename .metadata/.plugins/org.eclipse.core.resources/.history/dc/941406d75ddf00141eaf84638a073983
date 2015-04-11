package com.pas.survey.service;

import java.util.List;

import com.pas.survey.model.Answer;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.model.Survey;
import com.pas.survey.model.User;

public interface SurveyService{

	/**
	 * 查询调查列表
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * 添加调查
	 */
	public Survey newSurvey(User user);

	/**
	 * 获取查询调查
	 */
	public Survey getSuvey(int sid);
	
	/**
	 * 获取查询调查，并且强行初始化其子对象
	 * 这样就可以不使用OpenSessionInView过滤器，其损失性能
	 */
	public Survey getSuveyWithChildren(int sid);

	/**
	 * 更新调查
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * 保存&更新Page
	 * @param model
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * 获取某个Page对象
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);
	
	/**
	 * 保存问题
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 得到问题model
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);
	
	/*
	 * 删除问题
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 删除Page
	 * @param pid
	 */
	public void deletePage(Integer pid);
	
	/**
	 * 删除调查
	 * @param pid
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * 清空调查的答案
	 * @param sid
	 */
	public void clearAnswer(Integer sid);

	/**
	 * 切换问卷状态
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * 更新logo路径
	 * @param sid
	 * @param string
	 */
	public void updateLogoPhotoPath(Integer sid, String string);

	/**
	 * 获取用户相关的调查列表（强制加载Page对象）
	 * @param user
	 * @return
	 */
	public List<Survey> getSuveysWithPages(User user);

	/**
	 * 移动或复制页
	 * @param srcpid 源pageid
	 * @param targetpid 目标pageid
	 * @param moveType 移动类型 0之前 1之后
	 */
	public void moveOrCopyPage(int srcpid, int targetpid, int moveType);

	/**
	 * 查询所有开放的调查
	 * @return
	 */
	public List<Survey> getAllOpenedSurveys();

	/**
	 * 查询Survey的首页
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * 得到上一页
	 * @param currentPid
	 * @return
	 */
	public Page getPrePage(Integer currentPid);

	/**
	 * 得到下一页
	 * @param currentPid
	 * @return
	 */
	public Page getNextPage(Integer currentPid);

	/**
	 *保存本调查的答案集合
	 * @param storeAnswers
	 */
	public void saveAnswers(List<Answer> storeAnswers);
	
	/**
	 * 查询指定调查的所有问题
	 */
	public List<Question> getQuestions(Integer sid);
	
	/**
	 * 查询指定调查的所有答案
	 */
	public List<Answer> getAnswers(Integer sid);
}
