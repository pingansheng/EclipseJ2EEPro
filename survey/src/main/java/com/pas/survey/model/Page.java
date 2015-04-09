package com.pas.survey.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Page extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2762429655364828144L;
	private Integer id;
	private String title ="页面初始名称";
	private String description;
	//一个实数值用于标示页面顺序
	private float orderno;
	//多对一关联
	//transient临时的 虚拟机不会对其进行深度复制
	private transient Survey survey;
	
	//一对多关联
	private Set<Question> questions=new HashSet<Question>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(null!=id){
			//默认页序号与id一致
			this.orderno=id;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
}
