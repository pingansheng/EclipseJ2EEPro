package com.pas.survey.model.statistic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pas.survey.model.Question;

/**
 * 问题统计模型
 * @author pingansheng
 *
 */
public class QuestionStatisticModel implements Serializable{
	
	private static final long serialVersionUID = -8277993470489385981L;
	private Question question;
	private int count;//回答人数
	private List<OptionStatisticModel> opSMS=new ArrayList<OptionStatisticModel>();
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<OptionStatisticModel> getOpSMS() {
		return opSMS;
	}
	public void setOpSMS(List<OptionStatisticModel> opSMS) {
		this.opSMS = opSMS;
	}
	
	
}
