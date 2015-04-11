package com.pas.survey.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pas.survey.dao.BaseDao;

/**
 * 抽象类，实现BaseDao接口
 * 
 * @author pingansheng
 * 
 * @param <T>
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sf;

	private Class<T> clazz;

	// 此构造函数实际为子类实例
	public BaseDaoImpl() {
		// 得到泛型化超类
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}

	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}

	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}

	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}

	public void batchEntityByHql(String hql, Object... objects) {
		Query query = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	public List<T> findEntityByHql(String hql, Object... objects) {
		Query query = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	public List<T> executeQueryByPage(String hql, int pageNow, int pageSize,
			Object... params) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		q.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
		return q.list();
	}

	public Object getUniqueResult(String hql, Object... objects) {
		Query query = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> executeSqlQuery(String sql, Class<T> clazz,Object... objects) {
		SQLQuery query=sf.getCurrentSession().createSQLQuery(sql);
		
		if(clazz!=null){
			query.addEntity(clazz);
		}
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}
	
	@Override
	public void executeSQL(String sql, Object... objects) {
		SQLQuery query=sf.getCurrentSession().createSQLQuery(sql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	
	@Override
	public List executeSqlQuery(String sql, Object... objects) {
		return this.executeSqlQuery(sql, null, objects);
	}
}
