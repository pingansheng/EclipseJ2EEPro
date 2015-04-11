package com.pas.test;

import com.pas.survey.util.LogUtil;

public class TestLogUtil {

	public static void main(String[] args) {
		
		for(int i=-8;i<=12;i++){
			System.out.println(i+"_"+LogUtil.generateLogTableName(i));
		}
	}
	
}
