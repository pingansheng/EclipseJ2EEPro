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
	private String title ="ҳ���ʼ����";
	private String description;
	//һ��ʵ��ֵ���ڱ�ʾҳ��˳��
	private float orderno;
	//���һ����
	//transient��ʱ�� �����������������ȸ���
	private transient Survey survey;
	
	//һ�Զ����
	private Set<Question> questions=new HashSet<Question>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(null!=id){
			//Ĭ��ҳ�����idһ��
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
