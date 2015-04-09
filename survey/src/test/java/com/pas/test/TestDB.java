package com.pas.test;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ≤‚ ‘ ˝æ›‘¥
 * @author pingansheng
 *
 */
public class TestDB {

	@Test
	public void testDataSource() throws SQLException {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds=(DataSource) ac.getBean("dataSource");
		System.out.println(ds.getConnection());
	}
}
