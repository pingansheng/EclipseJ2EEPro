package com.pas.survey.service;

import java.util.List;

public interface BaseService<T> {
	// д����
	public void saveEntity(T t);

	public void saveOrUpdateEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void batchEntityByHql(String hql, Object... objects);

	public void executeSQL(String sql,Object... objects);
	
	// ������
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHql(String hql, Object... objects);
	
	public List<T> executeQueryByPage(String hql,int pageNow,int pageSize,Object... params);
	
	public List<T> executeSqlQuery(String sql,Class<T> clazz,Object... objects);

	public List executeSqlQuery(String sql,Object... objects);
	/**
	 * ��ѯ����ʵ��
	 */
	public List<T> findAllEntities();
	
	//��ֵ����
	public Object getUniqueResult(String hql, Object... objects);
}
