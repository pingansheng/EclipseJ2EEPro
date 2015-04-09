package com.pas.survey.util;

import java.text.DecimalFormat;
import java.util.Calendar;

public class LogUtil {

	/**
	 * 生成日志表名称 log_2014_8
	 * @param offset 偏移量 按月份
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		//0-11
		int month=c.get(Calendar.MONTH)+1+offset;
		//跨年处理
		if(month>12){
			year++;
			month-=12;
		}
		
		//偏移量为负数情况
		if(month<1){
			year--;
			month+=12;
		}
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("00");
		return "log_"+year+"_"+df.format(month);
	}

}
