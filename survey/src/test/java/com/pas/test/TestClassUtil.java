package com.pas.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pas.survey.service.RightService;
import com.pas.survey.util.ClassUtil;

public class TestClassUtil {

	@Test
	public void test() {
		try {
			ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
			RightService rs=(RightService) ac.getBean("rightService");
			List<String> classes=ClassUtil.getAllRightString("com.pas.survey.struts2.action",rs);
			for (String string : classes) {
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
