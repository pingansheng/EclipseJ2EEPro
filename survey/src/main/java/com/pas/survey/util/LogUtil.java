package com.pas.survey.util;

import java.text.DecimalFormat;
import java.util.Calendar;

public class LogUtil {

	/**
	 * ������־������ log_2014_8
	 * @param offset ƫ���� ���·�
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		//0-11
		int month=c.get(Calendar.MONTH)+1+offset;
		//���괦��
		if(month>12){
			year++;
			month-=12;
		}
		
		//ƫ����Ϊ�������
		if(month<1){
			year--;
			month+=12;
		}
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("00");
		return "log_"+year+"_"+df.format(month);
	}

}
