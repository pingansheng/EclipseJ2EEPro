package com.pas.survey.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.junit.runners.Parameterized;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.service.BaseService;

/**
 * 抽象BaseService实现(常用于单体操作)
 * @author pingansheng
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	
	Class<T> clazz;
	
	public BaseServiceImpl(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	//不指定注入哪个按照 名称、类型顺序注入，否则异常
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);		
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void batchEntityByHql(String hql, Object... objects) {
		dao.batchEntityByHql(hql, objects);
	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHql(String hql, Object... objects) {
		return dao.findEntityByHql(hql, objects);
	}
	
	public List<T> executeQueryByPage(String hql, int pageNow, int pageSize,
			Object... params) {
		return dao.executeQueryByPage(hql, pageNow, pageSize, params);
	}

	public List<T> findAllEntities() {
		String hql="from "+clazz.getSimpleName();
		return this.findEntityByHql(hql);
	}
	
	public Object getUniqueResult(String hql, Object... objects) {
		return dao.getUniqueResult(hql, objects);
	}
	
	public List<T> executeSqlQuery(String sql, Class<T> clazz,Object... objects) {
		return dao.executeSqlQuery(sql, clazz, objects);
	}
	
	@Override
	public void executeSQL(String sql, Object... objects) {
		dao.executeSQL(sql, objects);
	}
	
	@Override
	public List executeSqlQuery(String sql, Object... objects) {
		return dao.executeSqlQuery(sql, objects);
	}
}
