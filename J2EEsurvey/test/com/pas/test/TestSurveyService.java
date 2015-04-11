package com.pas.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.model.Survey;
import com.pas.survey.model.User;
import com.pas.survey.service.SurveyService;
import com.pas.survey.service.UserService;

public class TestSurveyService {

	private static SurveyService ss;

	// ≤‚ ‘«∞‘À––
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (SurveyService) ac.getBean("surveyService");
	}

	@Test
	public void test() {
		
		Survey survey=ss.getSuvey(1);
		
	}
}
