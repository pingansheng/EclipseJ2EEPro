package com.pas.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.model.User;
import com.pas.survey.service.UserService;

/**
 * ≤‚ ‘UserService
 * @author pingansheng
 *
 */
public class TestUserService {

	private static UserService us;
	
	//≤‚ ‘«∞‘À––
	@BeforeClass
	public static void initUserService()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		us=(UserService) ac.getBean("userService");
	}
	
	@Test
	public void testInsert() {
		
		User u=new User();
		u.setEmail("ping@qw.com");
		u.setPassword("123456");
		u.setName("asd");
		us.saveEntity(u);
	}
}
