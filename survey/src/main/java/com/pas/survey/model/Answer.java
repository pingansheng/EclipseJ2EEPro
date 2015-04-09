package com.pas.survey.model;

import java.io.Serializable;
import java.util.Date;

public class Answer extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3466199912824996660L;
	private Integer id;
	private Integer questionid;
	//冗余的关联字段
	private Integer surveyid;
	
	//多选则为逗号分割
	private String answer;
	private String otherAnswer;
	
	private Date answerTime;
	
	//批次 记录每个答案实体的批次 用以标示不同用户的作答
	private String uuid;
	
	public Integer getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

	public Integer getSurveyid() {
		return surveyid;
	}

	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOtherAnswer() {
		return otherAnswer;
	}

	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
