package com.pas.survey.struts2.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象Action
 * @author pingansheng
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable{
	
	private static final long serialVersionUID = 1L;
	protected T model;
	
	public BaseAction()
	{
		try {
			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz=(Class) type.getActualTypeArguments()[0];
			model=(T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 如果action针对每次请求都要执行一些相同的业务逻辑， 那么可以实现Preparable接口
	 */
	public void prepare() throws Exception {
		
	}
	
	public T getModel(){
		return model;
	}
	
}
