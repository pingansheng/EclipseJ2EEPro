package com.pas.survey.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.pas.survey.util.StringUtil;

/**
 * �Զ���key������
 * @author pingansheng
 *
 */
public class SurveyKeyGenerator implements KeyGenerator{

	/**
	 * Ŀ����󣬷����������б�
	 */
	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String className=arg0.getClass().getSimpleName();
		String mname=arg1.getName();
		String params=StringUtil.arrTostr(arg2);
		String key=className+"."+mname+"("+params+")";
		System.out.println(key);
		return key;
	}

}
