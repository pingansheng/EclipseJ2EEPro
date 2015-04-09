package com.pas.spring.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("client.xml");
		WelcomeService ws=(WelcomeService) ac.getBean("wsClient");
		ws.sayHello("Jame");
		System.out.println(ws.getLength("11111"));
		List<Student> ss=ws.getStudents(10);
		for (Student s : ss) {
			System.out.println("id:"+s.getId()+",name:"+s.getName());
		}
	}
}
