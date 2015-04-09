package com.pas.spring.service;

import java.util.List;

public interface WelcomeService {
	void sayHello(String name);
	int getLength(String s);
	List<Student> getStudents(int n);
}
