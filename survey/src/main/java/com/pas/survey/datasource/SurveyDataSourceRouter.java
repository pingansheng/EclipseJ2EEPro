package com.pas.survey.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class SurveyDataSourceRouter extends AbstractRoutingDataSource{

	/**
	 * ��⵱ǰkey
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		//�õ���ǰ����
		SurveyToken t=SurveyToken.getSurveyToken();
		if(t!=null){
			Integer id=t.getSurvey().getId();
			//������ư�
			SurveyToken.unBindToken();
			return (id % 2==0?"DATASOURCE_EVEN":"DATASOURCE_ODD");
		}
		return null;
	}

}
