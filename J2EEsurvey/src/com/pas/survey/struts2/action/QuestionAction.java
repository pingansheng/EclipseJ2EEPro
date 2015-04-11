package com.pas.survey.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.dao.impl.QuestionDaoImpl;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.service.SurveyService;

/**
 * 题型Action
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
	// 接收qid
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
	 * 到达选择题型页面
	 * 
	 * @return
	 */
	public String toSelectQuestionType() {

		return "selectQuestionTypePage";
	}

	/**
	 * 到达题型设计页面
	 * 
	 * @return
	 */
	public String toDesignQuestionPage() {

		return model.getQuestionType() + "";
	}

	/**
	 * 保存&更新题目
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
	 * 删除题目
	 * 
	 * @return
	 */
	public String deleteQuestion() {
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	/**
	 * 编辑题目
	 * @return
	 */
	public String editQuestion() {
		this.model=surveyService.getQuestion(qid);
		return model.getQuestionType() + "";
	}
}
