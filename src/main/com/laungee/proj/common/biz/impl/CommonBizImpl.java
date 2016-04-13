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
	/* �������� ---------------------------------------------- */
	// ---��������
	/**
	 * �õ�����
	 */
	public Object get(Class clazz, Serializable serializable) throws Exception{
		return commonDao.get(clazz, serializable);
	}
	
	/**
	 * �õ��������
	 */
	public Object getEvict(Class clazz, Serializable serializable) throws Exception{
		return commonDao.getEvict(clazz, serializable);
	}

	/**
	 * �õ�����
	 */
	public Object getHQLUnique(String hql, List list) throws Exception{
		return commonDao.getHQLUnique(hql, list);
	}

	/**
	 * �õ�����
	 */
	public Object getHQLUnique(String hql) throws Exception{
		return commonDao.getHQLUnique(hql, null);
	}

	/**
	 * �õ�����
	 */
	public Object getSQLUnique(String sql, List list)
			throws Exception{
		return commonDao.getSQLUnique(sql, list);
	}

	/**
	 * �õ�����
	 */
	public Object getSQLUnique(String sql) throws Exception{
		return commonDao.getSQLUnique(sql, null);
	}

	/**
	 * ��ȡ������ʱ��
	 */
	public Date getSysDate()
			throws Exception {
		return commonDao.getSysDate();
	}
	
	/**
	 * �õ����ֶ���
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
	 * �õ����ֶ���
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
	 * �������
	 */
	public Object save(Object entity) throws Exception{
		return commonDao.save(entity);
	}

	/**
	 * ���¶���
	 */
	public Object update(Object entity) throws Exception{
		return commonDao.update(entity);
	}

	/**
	 * ���¶���
	 */
	public Object merge(Object entity) throws Exception{
		return commonDao.merge(entity);
	}
	/**
	 * �������¶���
	 */
	public Object saveOrUpdate(Object entity) throws Exception{
		return commonDao.saveOrUpdate(entity);
	}

	/**
	 * ɾ������
	 */
	public void delete(Object entity) throws Exception{
		commonDao.delete(entity);
	}

	/**
	 * ɾ�����϶���
	 */
	public void deleteAll(Collection entities) throws Exception{
		commonDao.deleteAll(entities);
	}

	// ---��ѯ��ҳ
	/**
	 * ��HQL��ѯ��ҳ
	 */
	public List findHQLPage(String hql, List list) throws Exception{
		return commonDao.findHQLPage(hql, list);
	}
	/**
	 * ��HQL��ѯ��ҳ
	 */
	public List findHQLPage(String hql, List list, int size, int curr) throws Exception{
		return commonDao.findHQLPage(hql, list, size,curr);
	}

	/**
	 * ��HQL��ѯ��ҳ
	 */
	public List findHQLPage(String hql) throws Exception{
		return commonDao.findHQLPage(hql, null);
	}

	/**
	 * ��SQL��ѯ��ҳ
	 */
	public List findSQLPage(String hql, List list) throws Exception{
		return commonDao.findSQLPage(hql, list);
	}

	/**
	 * ��SQL��ѯ��ҳ
	 */
	public List findSQLPage(String hql) throws Exception{
		return commonDao.findSQLPage(hql, null);
	}

	// ---��ѯȫ��
	/**
	 * ��HQL��ѯȫ��
	 */
	public List findHQL(String hql, List list, int start,int size)
			throws Exception{
		return commonDao.findHQL(hql, list, start,size);
	}
	
	/**
	 * ��HQL��ѯȫ��
	 */
	public List findHQL(String hql, List list)
			throws Exception{
		return commonDao.findHQL(hql, list, 0,0);
	}

	/**
	 * ��HQL��ѯȫ��
	 */
	public List findHQL(String hql) throws Exception{
		return commonDao.findHQL(hql, null, 0,0);
	}

	/**
	 * ��SQL��ѯȫ��
	 */
	public List findSQL(String sql, List list, int start, int size)
			throws Exception{
		return commonDao.findSQL(sql, list, start,size);
	}
	
	/**
	 * ��SQL��ѯȫ��
	 */
	public List findSQL(String sql, List list)
			throws Exception{
		return commonDao.findSQL(sql, list, 0,0);
	}

	/**
	 * ��SQL��ѯȫ��
	 */
	public List findSQL(String sql) throws Exception{
		return commonDao.findSQL(sql, null, 0,0);
	}

	// ---��ѯ����
	/**
	 * ��HQL��ѯ����
	 */
	public int findHQlCount(String hql, List list)
			throws Exception{
		return commonDao.findHQlCount(hql, list);
	}

	/**
	 * ��HQL��ѯ����
	 */
	public int findHQLCount(String hql) throws Exception{
		return commonDao.findHQlCount(hql, null);
	}

	/**
	 * ��SQL��ѯ����
	 */
	public int findSQLCount(String hql, List list)
			throws Exception{
		return commonDao.findSQLCount(hql, list);
	}

	/**
	 * ��SQL��ѯ����
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
