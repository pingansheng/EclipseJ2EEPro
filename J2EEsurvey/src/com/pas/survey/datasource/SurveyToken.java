package com.pas.survey.datasource;

import com.pas.survey.model.Survey;

/**
 * ����
 * action��router֮��ʵ����Ϣ����
 * @author pingansheng
 *
 */
public class SurveyToken{
	
	//�̱߳��ػ�����
	private static ThreadLocal<SurveyToken> token=new ThreadLocal<SurveyToken>();
	
	private Survey survey;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	 
	/**
	 * ָ�������ư󶨵���ǰ�߳�
	 * @param t
	 */
	public static void bindToken(SurveyToken t){
		token.set(t);
	}
	
	/**
	 * �����ǰ�̰߳󶨵�����
	 */
	public static void unBindToken(){
		token.remove();
	}
	
	/**
	 * �ӵ�ǰ�̻߳�ð󶨵��߳�
	 * @return
	 */
	public static SurveyToken getSurveyToken(){
		return token.get();
	}
}
