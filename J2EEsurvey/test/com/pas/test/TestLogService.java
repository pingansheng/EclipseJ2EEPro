package com.pas.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.model.User;
import com.pas.survey.service.LogService;
import com.pas.survey.service.UserService;

/**
 * ≤‚ ‘LogService
 * @author pingansheng
 *
 */
public class TestLogService {

	private static LogService ls;
	
	//≤‚ ‘«∞‘À––
	@BeforeClass
	public static void initUserService()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		ls=(LogService) ac.getBean("logService");
	}
	
	@Test
	public void test() {
		ls.findHistoryLogsByMonth();
	}
}
