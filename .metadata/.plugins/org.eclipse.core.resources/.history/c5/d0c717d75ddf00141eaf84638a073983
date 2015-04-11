package com.pas.survey.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class SurveyDataSourceRouter extends AbstractRoutingDataSource{

	/**
	 * 检测当前key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		//得到当前令牌
		SurveyToken t=SurveyToken.getSurveyToken();
		if(t!=null){
			Integer id=t.getSurvey().getId();
			//解除令牌绑定
			SurveyToken.unBindToken();
			return (id % 2==0?"DATASOURCE_EVEN":"DATASOURCE_ODD");
		}
		return null;
	}

}
