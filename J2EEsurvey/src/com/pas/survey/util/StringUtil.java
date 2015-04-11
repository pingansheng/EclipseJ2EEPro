package com.pas.survey.util;

public class StringUtil {

	/**
	 * 拆分字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String[] stringToArr(String s, String reg) {
		if (ValidateUtil.isValid(s)) {
			return s.split(reg);
		}
		return null;
	}

	/**
	 * 得到项目绝对路径
	 * 
	 * @return
	 */
	public static String getProPath() {
		String path;
		java.net.URL u = StringUtil.class.getResource("");
		path = u.toString();
		path = path.replaceAll("%20", " ");
		int off = path.indexOf("WEB-INF");
		path = path.substring(6, off-1);
		return path;
	}

	/**
	 * 数组转换为字符串逗号隔开
	 * @param value
	 * @return
	 */
	public static String arrTostr(Object[] value) {
		if(ValidateUtil.isValid(value)){
			StringBuffer sb=new StringBuffer();
			for (Object string : value) {
				sb.append(string).append(",");
			}
			return sb.replace(sb.length()-1, sb.length(), "").toString();
		}
		return null;
	}

	/**
	 * 得到描述性字符串
	 * @param s
	 */
	public static String getDescString(String s){
		if(s!=null && s.trim().length()>30){
			return s.substring(0, 30);
		}
		return s;
	}
}
