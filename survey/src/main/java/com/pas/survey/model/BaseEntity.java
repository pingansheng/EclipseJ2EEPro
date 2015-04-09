package com.pas.survey.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 抽象实体，用于继承
 * 
 * @author pingansheng
 * 
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5745255752746243194L;

	public abstract Integer getId();

	public abstract void setId(Integer id);

	@Override
	/**
	 * 覆盖tostring方法提供友好Json格式
	 */
	public String toString() {

		try {
			StringBuffer sb = new StringBuffer();
			Class clazz = this.getClass();
			String simplename = clazz.getSimpleName();
			sb.append(simplename).append("{");
			Field[] fs = clazz.getDeclaredFields();
			Class ftype = null;
			String fname = null;
			Object fvalue = null;
			for (Field f : fs) {
				ftype = f.getType();
				fname = f.getName();
				f.setAccessible(true);
				fvalue = f.get(this);
				// 是否是基本类型
				if ((ftype.isPrimitive() || ftype == Integer.class
						|| ftype == Float.class || ftype == Double.class
						|| ftype == String.class || ftype == Boolean.class
						|| ftype == Character.class || ftype == Float.class
						|| ftype == Long.class) && !Modifier.isStatic(f.getModifiers())) {
					sb.append(fname).append(":").append(fvalue).append(",");
				}
			}
			sb.replace(sb.length()-1, sb.length(), "");
			sb.append("}");
			return sb.toString();
		} catch (Exception e) {
		}
		return null;
	}
}
