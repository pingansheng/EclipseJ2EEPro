package com.pas.survey.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.dao.impl.QuestionDaoImpl;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.service.SurveyService;

/**
 * ����Action
 * 
 * @author pingansheng
 * 
 */
@Controller("questionaction")
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = -248452419923511379L;

	@Resource(name = "surveyService")
	private SurveyService surveyService;
	private Integer sid;
	private Integer pid;
	// ����qid
	private Integer qid;

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * ����ѡ������ҳ��
	 * 
	 * @return
	 */
	public String toSelectQuestionType() {

		return "selectQuestionTypePage";
	}

	/**
	 * �����������ҳ��
	 * 
	 * @return
	 */
	public String toDesignQuestionPage() {

		return model.getQuestionType() + "";
	}

	/**
	 * ����&������Ŀ
	 * 
	 * @return
	 */
	public String saveOrUpdateQuestion() {
		Page p = new Page();
		p.setId(pid);
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}

	/**
	 * ɾ����Ŀ
	 * 
	 * @return
	 */
	public String deleteQuestion() {
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	/**
	 * �༭��Ŀ
	 * @return
	 */
	public String editQuestion() {
		this.model=surveyService.getQuestion(qid);
		return model.getQuestionType() + "";
	}
}
