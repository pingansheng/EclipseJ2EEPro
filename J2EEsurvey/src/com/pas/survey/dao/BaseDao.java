package com.pas.survey.dao;

import java.util.List;

import org.hibernate.Session;

/**
 * BaseDao接口
 * 
 * @author pingansheng
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	// 写操作
	public void saveEntity(T t);

	public void saveOrUpdateEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void batchEntityByHql(String hql, Object... objects);

	public void executeSQL(String sql,Object... objects);
	
	// 读操作
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHql(String hql, Object... objects);
	
	public List<T> executeQueryByPage(String hql,int pageNow,int pageSize,Object... params);
	
	public List<T> executeSqlQuery(String sql,Class<T> clazz,Object... objects);
	
	public List executeSqlQuery(String sql,Object... objects);
	
	//查询
	//单值检索
	public Object getUniqueResult(String hql, Object... objects);
}
