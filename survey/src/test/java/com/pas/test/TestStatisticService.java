package com.pas.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.model.User;
import com.pas.survey.model.statistic.OptionStatisticModel;
import com.pas.survey.model.statistic.QuestionStatisticModel;
import com.pas.survey.service.StatisticService;
import com.pas.survey.service.UserService;
import com.pas.survey.struts2.action.SurveyAction;

/**
 * ≤‚ ‘UserService
 * @author pingansheng
 *
 */
public class TestStatisticService {

	private static StatisticService us;
	private static SurveyAction surveyAction;
	//≤‚ ‘«∞‘À––
	@BeforeClass
	public static void initStatisticService()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		us=(StatisticService) ac.getBean("statisticService");
		surveyAction=(SurveyAction) ac.getBean("surveyaction");
	}
	
	@Test
	public void testStatistics() {
		QuestionStatisticModel qsm=us.statistic(21);
		System.out.println(qsm.getCount());
		List<OptionStatisticModel> ops=qsm.getOpSMS();
		for (int i = 0; i < ops.size(); i++) {
			System.out.print(ops.get(i).getMatrixRowIndex()+"_"+ops.get(i).getMatrixColIndex());
			System.out.println(" "+ops.get(i).getCount());
		}
	}
	
	@Test
	public void testAnaJson() {
		surveyAction.doAnalyze();
	}
	
}
