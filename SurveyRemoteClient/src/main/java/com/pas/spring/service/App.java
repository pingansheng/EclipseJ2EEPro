package com.pas.spring.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.model.statistic.OptionStatisticModel;
import com.pas.survey.model.statistic.QuestionStatisticModel;
import com.pas.survey.service.StatisticService;

public class App {
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("client.xml");
		StatisticService ss= (StatisticService) ac.getBean("ssClient");
		QuestionStatisticModel qsm=ss.statistic(16);
		System.out.println(qsm.getCount());
		for (OptionStatisticModel osm : qsm.getOpSMS()) {
			System.out.println(osm.getOptionLabel()+" "+osm.getCount());
		}
	}
}
