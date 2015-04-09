package com.pas.spring.service;

import java.util.ArrayList;
import java.util.List;

public class WelcomeServiceImpl implements WelcomeService {

	public void sayHello(String name) {
		System.out.println(name);
	}

	public int getLength(String s) {
		return s.length();
	}

	public List<Student> getStudents(int n) {
		List<Student> ss=new ArrayList<Student>(n);
		for(int i=0;i<n;i++){
			Student s=new Student();
			s.setId(i+1);
			s.setName("name"+i);
			ss.add(s);
		}
		return ss;
	}

}
