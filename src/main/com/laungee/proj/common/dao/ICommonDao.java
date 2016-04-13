package com.laungee.proj.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ICommonDao {
	// ---操作对象
	/**
	 * 得到对象
	 */
	Object get(Class clazz, Serializable serializable) throws Exception;
	/**
	 * 得到游离对象
	 */
	Object getEvict(Class clazz, Serializable serializable) throws Exception;
	/**
	 * 得到对象
	 */
	Object getHQLUnique(String hql,List list) throws Exception;
	/**
	 * 得到对象
	 */
	Object getSQLUnique(String sql,List list) throws Exception;
	/**
	 * 获取服务器时间
	 */
	Date getSysDate() throws Exception;
	/**
	 * 保存对象
	 */
	Object save(Object entity) throws Exception;
	/**
	 * 更新对象
	 */
	Object update(Object entity) throws Exception;
	/**
	 * 更新对象
	 */
	Object merge(Object entity) throws Exception;
	/**
	 * 保存或者更新对象
	 */
	Object saveOrUpdate(Object entity) throws Exception;
	/**
	 * 删除对象
	 */
	void delete(Object entity) throws Exception;
	/**
	 * 删除集合对象
	 */
	void deleteAll(Collection entities) throws Exception;
	// ---查询分页
	/**
	 * 由HQL查询分页
	 */
	List findHQLPage(String hql,List list) throws Exception;
	List findHQLPage(String hql,List list,int size,int curr) throws Exception;
	/**
	 * 由SQL查询分页
	 */
	List findSQLPage(String hql,List list) throws Exception;
	// ---查询全部
	/**
	 * 由HQL查询全部
	 */
	List findHQL(String hql,List list,int start,int size) throws Exception;
	/**
	 * 由HQL查询全部
	 */
	List findSQL(String sql,List list,int start,int size) throws Exception;
	// ---查询总数
	/**
	 * 由HQL查询总数
	 */
	int findHQlCount(String hql,List list) throws Exception;
	/**
	 * 由SQL查询总数
	 */
	int findSQLCount(String hql,List list) throws Exception;
	
	List findName(String name,List pars) throws Exception;
	
	/**
	 * 执行SQL语句，非select（delete,update无ResultSet值的情况）
	 */
	int execute(String sql);
	int execute(String sql,List pars);
	
	/**
	 * 执行HQL语句，非select（delete,update无ResultSet值的情况）
	 * @param hql
	 * @return
	 */
	int executeHql(String hql);
}
