package com.laungee.proj.common.biz.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.dao.ICommonDao;

public class CommonBizImpl implements ICommonBiz {
	protected ICommonDao commonDao;
	
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	/* 基本操作 ---------------------------------------------- */
	// ---操作对象
	/**
	 * 得到对象
	 */
	public Object get(Class clazz, Serializable serializable) throws Exception{
		return commonDao.get(clazz, serializable);
	}
	
	/**
	 * 得到游离对象
	 */
	public Object getEvict(Class clazz, Serializable serializable) throws Exception{
		return commonDao.getEvict(clazz, serializable);
	}

	/**
	 * 得到对象
	 */
	public Object getHQLUnique(String hql, List list) throws Exception{
		return commonDao.getHQLUnique(hql, list);
	}

	/**
	 * 得到对象
	 */
	public Object getHQLUnique(String hql) throws Exception{
		return commonDao.getHQLUnique(hql, null);
	}

	/**
	 * 得到对象
	 */
	public Object getSQLUnique(String sql, List list)
			throws Exception{
		return commonDao.getSQLUnique(sql, list);
	}

	/**
	 * 得到对象
	 */
	public Object getSQLUnique(String sql) throws Exception{
		return commonDao.getSQLUnique(sql, null);
	}

	/**
	 * 获取服务器时间
	 */
	public Date getSysDate()
			throws Exception {
		return commonDao.getSysDate();
	}
	
	/**
	 * 得到数字对象
	 */
	public int getHQLNum(String hql) throws Exception{
		Object obj = commonDao.getHQLUnique(hql, null);
		if(null!=obj){
			return Integer.parseInt(obj.toString());
		}
		else{
			return 0;
		}
	}
	
	/**
	 * 得到数字对象
	 */
	public int getSQLNum(String sql) throws Exception{
		Object obj=commonDao.getSQLUnique(sql, null);
		if(null!=obj){
			return Integer.parseInt(obj.toString());
		}
		else{
			return 0;
		}
	}

	/**
	 * 保存对象
	 */
	public Object save(Object entity) throws Exception{
		return commonDao.save(entity);
	}

	/**
	 * 更新对象
	 */
	public Object update(Object entity) throws Exception{
		return commonDao.update(entity);
	}

	/**
	 * 更新对象
	 */
	public Object merge(Object entity) throws Exception{
		return commonDao.merge(entity);
	}
	/**
	 * 保存或更新对象
	 */
	public Object saveOrUpdate(Object entity) throws Exception{
		return commonDao.saveOrUpdate(entity);
	}

	/**
	 * 删除对象
	 */
	public void delete(Object entity) throws Exception{
		commonDao.delete(entity);
	}

	/**
	 * 删除集合对象
	 */
	public void deleteAll(Collection entities) throws Exception{
		commonDao.deleteAll(entities);
	}

	// ---查询分页
	/**
	 * 由HQL查询分页
	 */
	public List findHQLPage(String hql, List list) throws Exception{
		return commonDao.findHQLPage(hql, list);
	}
	/**
	 * 由HQL查询分页
	 */
	public List findHQLPage(String hql, List list, int size, int curr) throws Exception{
		return commonDao.findHQLPage(hql, list, size,curr);
	}

	/**
	 * 由HQL查询分页
	 */
	public List findHQLPage(String hql) throws Exception{
		return commonDao.findHQLPage(hql, null);
	}

	/**
	 * 由SQL查询分页
	 */
	public List findSQLPage(String hql, List list) throws Exception{
		return commonDao.findSQLPage(hql, list);
	}

	/**
	 * 由SQL查询分页
	 */
	public List findSQLPage(String hql) throws Exception{
		return commonDao.findSQLPage(hql, null);
	}

	// ---查询全部
	/**
	 * 由HQL查询全部
	 */
	public List findHQL(String hql, List list, int start,int size)
			throws Exception{
		return commonDao.findHQL(hql, list, start,size);
	}
	
	/**
	 * 由HQL查询全部
	 */
	public List findHQL(String hql, List list)
			throws Exception{
		return commonDao.findHQL(hql, list, 0,0);
	}

	/**
	 * 由HQL查询全部
	 */
	public List findHQL(String hql) throws Exception{
		return commonDao.findHQL(hql, null, 0,0);
	}

	/**
	 * 由SQL查询全部
	 */
	public List findSQL(String sql, List list, int start, int size)
			throws Exception{
		return commonDao.findSQL(sql, list, start,size);
	}
	
	/**
	 * 由SQL查询全部
	 */
	public List findSQL(String sql, List list)
			throws Exception{
		return commonDao.findSQL(sql, list, 0,0);
	}

	/**
	 * 由SQL查询全部
	 */
	public List findSQL(String sql) throws Exception{
		return commonDao.findSQL(sql, null, 0,0);
	}

	// ---查询总数
	/**
	 * 由HQL查询总数
	 */
	public int findHQlCount(String hql, List list)
			throws Exception{
		return commonDao.findHQlCount(hql, list);
	}

	/**
	 * 由HQL查询总数
	 */
	public int findHQLCount(String hql) throws Exception{
		return commonDao.findHQlCount(hql, null);
	}

	/**
	 * 由SQL查询总数
	 */
	public int findSQLCount(String hql, List list)
			throws Exception{
		return commonDao.findSQLCount(hql, list);
	}

	/**
	 * 由SQL查询总数
	 */
	public int findSQLCount(String hql) throws Exception{
		return commonDao.findSQLCount(hql, null);
	}
	
	public List findName(String name) throws Exception{
		return commonDao.findName(name, null);
	}

	public List findName(String name,List pars) throws Exception{
		return commonDao.findName(name, pars);
	}
	
	public List fieldList(String code) throws Exception{
		String hql="select a from TbField a where a.tbField.code='"+code+"'";
		return findHQL(hql);
	}
	public int execute(String sql) {
		return commonDao.execute(sql);
	}

	public int execute(String sql,List pars){
		return commonDao.execute(sql,pars);
	}
	
	public int executeHql(String hql){
		return commonDao.executeHql(hql);
	}
}
