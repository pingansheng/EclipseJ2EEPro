package com.pas.survey.datasource;

import com.pas.survey.model.Survey;

/**
 * 令牌
 * action和router之间实现消息传递
 * @author pingansheng
 *
 */
public class SurveyToken{
	
	//线程本地化对象
	private static ThreadLocal<SurveyToken> token=new ThreadLocal<SurveyToken>();
	
	private Survey survey;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	 
	/**
	 * 指定的令牌绑定到当前线程
	 * @param t
	 */
	public static void bindToken(SurveyToken t){
		token.set(t);
	}
	
	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unBindToken(){
		token.remove();
	}
	
	/**
	 * 从当前线程获得绑定的线程
	 * @return
	 */
	public static SurveyToken getSurveyToken(){
		return token.get();
	}
}
