package com.pas.survey.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import com.pas.survey.service.RightService;

public class ClassUtil {

	/**
	 * 从包中找出类
	 * 
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	private static List<Class<?>> getRightActions(String packageName)
			throws Exception {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		List<Class<?>> actionClasses = new ArrayList<Class<?>>();
		// 包名转物理地址
		String path = packageName.replace('.', '/');
		URL url = classLoader.getResource(path);
		File actionDir = new File(url.toURI());
		File[] classes = actionDir.listFiles();

		String fname = "";
		for (File file : classes) {
			fname = file.getName();
			if (fname.startsWith("Base") || !fname.endsWith("class")) {
				continue;
			}
			Class<?> clazz = Class.forName(packageName + "."
					+ fname.substring(0, file.getName().length() - 6));
			actionClasses.add(clazz);
		}
		return actionClasses;
	}

	/**
	 * 得到所有的权限字符串
	 * 
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllRightString(String packageName,RightService rightService)
			throws Exception {
		List<String> rightStrings = new ArrayList<String>();
		List<Class<?>> actionClasses = getRightActions(packageName);
		for (Class<?> clazz : actionClasses) {
			String actionName = clazz.getSimpleName();
			if (actionName.endsWith("Action")) {
				Method[] methods = clazz.getDeclaredMethods();
				Class<?> returnType = null;
				String methodName = null;
				Class<?>[] paramTypes = null;
				String rightUrl=null;
				
				for (Method method : methods) {
					returnType = method.getReturnType();
					methodName = method.getName();
					paramTypes = method.getParameterTypes();
					if (returnType == String.class
							&& !ValidateUtil.isValid(paramTypes)
							&& Modifier.isPublic(method.getModifiers())) {
						
						
						if ("execute".equals(methodName)) {
							rightUrl="/" + actionName;
						} else {
							rightUrl="/" + actionName + "_"
									+ methodName;
						}
						rightStrings.add(rightUrl);
					}
				}
			}
		}
		return rightStrings;
	}
}
