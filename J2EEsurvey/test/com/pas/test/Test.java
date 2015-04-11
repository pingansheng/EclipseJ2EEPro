package com.pas.test;

import java.util.Calendar;
import java.util.Date;

import com.pas.survey.util.DataUtil;

public class Test {

	public static void main(String[] args) {
//		long a=1L;
//		for(int i=1;i<=63;i++)
//		System.out.println(a<<i);
//		System.out.println(DataUtil.sha_1("38383386"));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
		System.err.println(date.getTime());
		
	}

}
