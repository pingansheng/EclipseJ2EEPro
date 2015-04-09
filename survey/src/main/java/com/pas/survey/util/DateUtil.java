package com.pas.survey.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 处理日期为标准格式 2011-12-12 12:12:12
	 */
	public static String getSpecialDateString(Date d) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(d);
	}

}
