package com.laungee.proj.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.laungee.proj.common.core.BaseHibernate;
import com.laungee.proj.common.dao.ICommonDao;
import com.laungee.proj.common.model.TbLog;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbUser;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.DecryptUtil;
import com.laungee.proj.common.util.IFinalUser;
import com.laungee.proj.common.util.PaginationDto;
import com.laungee.proj.common.util.StringUtil;
import com.laungee.proj.common.util.TableManagerImpl;
import com.laungee.proj.common.util.TableMemo;

public class CommonDaoImpl extends BaseHibernate implements ICommonDao,IFinalUser {
	
	// ---��������
	/**
	 * �õ�����
	 */
	public Object get(Class clazz, Serializable serializable) throws Exception {
		if(null!=serializable){
			return getHibernateTemplate().get(clazz, serializable);
		}
		else{
			return null;
		}
	}
	
	/**
	 * �õ��������
	 * @param clazz
	 * @param serializable
	 * @return
	 * @throws Exception
	 */
	public Object getEvict(Class clazz, Serializable serializable) throws Exception {
		HibernateTemplate hibernateBean=getHibernateTemplate();
		if(null!=serializable){
			Object obj= hibernateBean.get(clazz, serializable);
			hibernateBean.evict(obj);
			return obj;
		}
		else{
			return null;
		}
	}
	/**
	 * �õ�����
	 */
	public Object getHQLUnique(String hql, List list) throws Exception {
		Object object=null;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (isRepeat=0 or isRepeat is null) ";
		String thql="";
		//�ж���û�������ǩ
		if(hql.indexOf("order by")==-1){
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=hql+" where "+repeat;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}else{
			String sql1=hql.substring(0,hql.indexOf("order by"));
			String order=hql.substring(hql.indexOf("order by"));
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}
		
		try {
			Query query = getSession().createQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			List result=query.list();
			if(result!=null&&!result.isEmpty()){
				 object=result.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					Query query = getSession().createQuery(hql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					List result=query.list();
					if(result!=null&&!result.isEmpty()){
						 object=result.get(0);
					}
				}
			}
		}
		return object;
	}
	
	/**
	 * �õ�����
	 */
	public Object getSQLUnique(String sql, List list)
			throws Exception {
		Object object=null;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (is_repeat=0 or is_repeat is null) ";
		String thql="";
		//�ж���û�������ǩ
		if(sql.indexOf("order by")==-1){
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql+" where "+repeat;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}else{
			String sql1=sql.substring(0,sql.indexOf("order by"));
			String order=sql.substring(sql.indexOf("order by"));
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}
		try {
			// ��ѯ���
			Query query = getSession().createSQLQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			List result=query.list();
			if(result!=null&&!result.isEmpty()){
				 object=result.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					// ��ѯ���
					Query query = getSession().createSQLQuery(sql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					List result=query.list();
					if(result!=null&&!result.isEmpty()){
						 object=result.get(0);
					}
				}
			}
		}
		
		return object;
	}
	/**
	 * ��ȡ������ʱ��
	 */
	public Date getSysDate()
			throws Exception {
		// ��ѯ���
		Query query = getSession().createSQLQuery("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual");
		Object object = query.uniqueResult();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(object.toString());
		return date;
	}	
	/**
	 * �������
	 */
	public Object save(Object entity) throws Exception {
		try {
			// ������
			Field field1=entity.getClass().getDeclaredField("updateUser");
			field1.setAccessible(true);
	        //������
			Field fieldCreattion=entity.getClass().getDeclaredField("creationUser");
			fieldCreattion.setAccessible(true);
			HttpServletRequest request=ServletActionContext.getRequest();
			if(null!=request.getSession().getAttribute(USER_ID)){
				field1.set(entity, request.getSession().getAttribute(USER_ID));
				fieldCreattion.set(entity, request.getSession().getAttribute(USER_ID));
			}
			else if(null!=request.getSession().getAttribute(STU_ID)){
				field1.set(entity, request.getSession().getAttribute(STU_ID));
				fieldCreattion.set(entity, request.getSession().getAttribute(STU_ID));
			}
	        // ����ʱ��
	        Field field2=entity.getClass().getDeclaredField("updateTime");
			field2.setAccessible(true);
			//����ʱ��
	        Field field2Creattion=entity.getClass().getDeclaredField("creationTime");
	        field2Creattion.setAccessible(true);
			Date date=this.getSysDate();
	        field2.set(entity, date);
	        field2Creattion.set(entity, DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"));
	        //�����־
	        this.addSaveLog(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getHibernateTemplate().save(entity);
		// ˢ�»���
		getHibernateTemplate().flush();
		//������ʱ��
		trigger(entity);
		return entity;
	}

	/**
	 * ���¶���
	 */
	public Object update(Object entity) throws Exception {
		try {
			// ������
			Field field1=entity.getClass().getDeclaredField("updateUser");
			field1.setAccessible(true);
			HttpServletRequest request=ServletActionContext.getRequest();
			if(null!=request.getSession().getAttribute(USER_ID)){
				field1.set(entity, request.getSession().getAttribute(USER_ID));
			}
			else if(null!=request.getSession().getAttribute(STU_ID)){
				field1.set(entity, request.getSession().getAttribute(STU_ID));
			}
	        // ����ʱ��
	        Field field2=entity.getClass().getDeclaredField("updateTime");
			field2.setAccessible(true);
	        field2.set(entity, this.getSysDate());
	        //�����־
	        this.addUpdateLog(entity);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getHibernateTemplate().update(entity);
		// ˢ�»���
		getHibernateTemplate().flush();
		
		//������ʱ��
		trigger(entity);
		return entity;
	}
	
	/**
	 * ���¶���
	 */
	public Object merge(Object entity) throws Exception {
		try {
			// ������
			Field field1=entity.getClass().getDeclaredField("updateUser");
			field1.setAccessible(true);
	        //������
			Field fieldCreattion=entity.getClass().getDeclaredField("creationUser");
			fieldCreattion.setAccessible(true);
			try {
				HttpServletRequest request=ServletActionContext.getRequest();
				if(null!=request.getSession().getAttribute(USER_ID)){
					field1.set(entity, request.getSession().getAttribute(USER_ID));
					fieldCreattion.set(entity, request.getSession().getAttribute(USER_ID));
				}
				else if(null!=request.getSession().getAttribute(STU_ID)){
					field1.set(entity, request.getSession().getAttribute(STU_ID));
					fieldCreattion.set(entity, request.getSession().getAttribute(STU_ID));
				}
			} catch (Exception e) {	}
	        // ����ʱ��
	        Field field2=entity.getClass().getDeclaredField("updateTime");
			field2.setAccessible(true);
			//����ʱ��
	        Field field2Creattion=entity.getClass().getDeclaredField("creationTime");
	        field2Creattion.setAccessible(true);
			Date date=this.getSysDate();
	        field2.set(entity, date);
	        field2Creattion.set(entity, DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.getHibernateTemplate().merge(entity);
		// ˢ�»���
		getHibernateTemplate().flush();
		//������ʱ��
		trigger(entity);
		return entity;
	}

	/**
	 * ������߸��¶���
	 */
	public Object saveOrUpdate(Object entity) throws Exception {
		try {
			// ������
			Field field1=entity.getClass().getDeclaredField("updateUser");
			field1.setAccessible(true);
	        //������
			Field fieldCreattion=entity.getClass().getDeclaredField("creationUser");
			fieldCreattion.setAccessible(true);
			HttpServletRequest request=ServletActionContext.getRequest();
			if(null!=request.getSession().getAttribute(USER_ID)){
				field1.set(entity, request.getSession().getAttribute(USER_ID));
				if(fieldCreattion.get(entity)==null||"".equals(fieldCreattion.get(entity))){
					fieldCreattion.set(entity, request.getSession().getAttribute(USER_ID));
				}
			}
			else if(null!=request.getSession().getAttribute(STU_ID)){
				field1.set(entity, request.getSession().getAttribute(STU_ID));
				if(fieldCreattion.get(entity)==null||"".equals(fieldCreattion.get(entity))){
					fieldCreattion.set(entity, request.getSession().getAttribute(STU_ID));
				}
			}
	        // ����ʱ��
			Date date=this.getSysDate();
	        Field field2=entity.getClass().getDeclaredField("updateTime");
			field2.setAccessible(true);
	        field2.set(entity, date);
			//����ʱ��
	        Field field2Creattion=entity.getClass().getDeclaredField("creationTime");
	        field2Creattion.setAccessible(true);
	        if(field2Creattion.get(entity)==null||"".equals(field2Creattion.get(entity))){
	        	field2Creattion.set(entity, DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		getHibernateTemplate().saveOrUpdate(entity);
		getHibernateTemplate().flush();
		//�����־
//		Object obj=entity.
		// ˢ�»���
		getHibernateTemplate().flush();
		//������ʱ��
		trigger(entity);
		return entity;
	}

	/**
	 * ɾ������
	 */
	public void delete(Object entity) throws Exception {
		if(null!=entity){
			getHibernateTemplate().delete(entity);
			//�����־
	        this.addDeleteLog(entity);
		}
		// ˢ�»���
		getHibernateTemplate().flush();
	}

	/**
	 * ɾ�����϶���
	 */
	public void deleteAll(Collection entities) throws Exception {
		if(null!=entities &&entities.size()>0){
			getHibernateTemplate().deleteAll(entities);
			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				Object entity = iterator.next();
				//�����־
		        this.addDeleteLog(entity);
			}
		}
		// ˢ�»���
		getHibernateTemplate().flush();
	}

	// ---��ѯ��ҳ
	/**
	 * ��HQL��ѯ��ҳ
	 */
	public List findHQLPage(String hql, List list) throws Exception {
		return super.findHQLPage(StringUtil.toQuery(hql), list);
	}
	public List findHQLPage(String hql, List list,int size,int curr) throws Exception {
		return super.findHQLPage(hql, list, size, curr);
	}


	/**
	 * ��SQL��ѯ��ҳ
	 */
	public List findSQLPage(String sql, List list) throws Exception {
		return super.findSQLPage(sql, list);
	}

	// ---��ѯȫ��
	/**
	 * ��HQL��ѯȫ��
	 */
	public List findHQL(String hql, List list, int start, int size) throws Exception {
		Session session=getSession();
		List temp=null;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (isRepeat=0 or isRepeat is null) ";
		String thql="";
		
		/* �������� */
		PaginationDto dto=new PaginationDto();
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			String st=request.getParameter(dto.getSst());
			if(null!=st){
				dto.setSt(st);
			}
			String or=request.getParameter(dto.getSor());
			if(null!=or){
				dto.setOr(or);
			}
	//		String hql1=hql;
			if(null!=st){
				String order=DecryptUtil.decrypt(st);
				if(order.startsWith(SORT_KEY) && order.matches("[0-9A-Za-z.]*")){
					order=order.replaceFirst(SORT_KEY, "");
					if("asc".equalsIgnoreCase(dto.getOr()) || "desc".equalsIgnoreCase(dto.getOr())){
						//�滻ԭ��������
						if(hql.lastIndexOf("order")!=-1){
							hql=hql.substring(0,hql.lastIndexOf("order"));
							//��ƴ��
							hql+=" order by "+order+" "+dto.getOr();
						}
					}
				}
			}
		}catch(Exception e){}
		//�ж���û�������ǩ
		if(hql.indexOf("order by")==-1){
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=hql+" where "+repeat;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}else{
			String sql1=hql.substring(0,hql.indexOf("order by"));
			String order=hql.substring(hql.indexOf("order by"));
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}
		
		try {
			System.out.print("thql:"+thql);
			Query query = session.createQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			if(start!=0){
				query.setFirstResult(start);
			}
			if(size!=0){
				query.setMaxResults(size);
			}
			temp=query.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			e.printStackTrace();
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					// ��ѯ���
					Query query = session.createQuery(hql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					if(start!=0){
						query.setFirstResult(start);
					}
					if(size!=0){
						query.setMaxResults(size);
					}
					temp=query.list();
				}
			}
		}
		releaseSession(session);
		return temp;
	}

	/**
	 * ��SQL��ѯȫ��
	 */
	public List findSQL(String sql, List list, int start, int size) throws Exception {
		List temp=null;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (is_repeat=0 or is_repeat is null) ";
		String thql="";
		//�ж���û�������ǩ
		if(sql.indexOf("order by")==-1){
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql+" where "+repeat;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}else{
			String sql1=sql.substring(0,sql.indexOf("order by"));
			String order=sql.substring(sql.indexOf("order by"));
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}
		
		try {
			Query query = getSession().createSQLQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			if(start!=0){
				query.setFirstResult(start);
			}
			if(size!=0){
				query.setMaxResults(size);
			}
			temp=this.getSession().createSQLQuery(sql).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					// ��ѯ���
					Query query = getSession().createSQLQuery(sql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					if(start!=0){
						query.setFirstResult(start);
					}
					if(size!=0){
						query.setMaxResults(size);
					}
					temp=this.getSession().createSQLQuery(sql).list();
				}
			}
		}
		
		return temp;
	}

	// ---��ѯ����
	/**
	 * ��HQL��ѯ����
	 */
	public int findHQlCount(String hql, List list) throws Exception {
		int count=0;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (isRepeat=0 or isRepeat is null) ";
		String thql="";
		//�ж���û�������ǩ
		if(hql.indexOf("order by")==-1){
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=hql+" where "+repeat;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}else{
			String sql1=hql.substring(0,hql.indexOf("order by"));
			String order=hql.substring(hql.indexOf("order by"));
			//���û������where
			if(hql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=hql.substring(0,hql.indexOf(" where ")+7)+repeat+" and "+hql.substring(hql.indexOf(" where ")+7);
			}
		}
		
		try {
			Query query = getSession().createQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			Object obj = query.uniqueResult();
			if(null!=obj){
				count=Integer.parseInt(obj.toString());
			}
			else{
				count=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					Query query = getSession().createQuery(hql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					Object obj = query.uniqueResult();
					if(null!=obj){
						count=Integer.parseInt(obj.toString());
					}
					else{
						count=0;
					}
				}
			}
		}
		return count;
	}

	/**
	 * ��SQL��ѯ����
	 */
	public int findSQLCount(String sql, List list) throws Exception {
		int count=0;
		//ƴ��repeat is null�����غϲ����Ķ���
		String repeat=" (is_repeat=0 or is_repeat is null) ";
		String thql="";
		//�ж���û�������ǩ
		if(sql.indexOf("order by")==-1){
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql+" where "+repeat;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}else{
			String sql1=sql.substring(0,sql.indexOf("order by"));
			String order=sql.substring(sql.indexOf("order by"));
			//���û������where
			if(sql.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
			}
		}
		
		try {
			Query query = getSession().createSQLQuery(thql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
			}
			Object obj = query.uniqueResult();
			if(null!=obj){
				count=Integer.parseInt(obj.toString());
			}
			else{
				count=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����");
			//�����ʾ�ַ���Ч
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					Query query = getSession().createSQLQuery(sql);
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							query.setParameter(i, list.get(i));
						}
					}
					Object obj = query.uniqueResult();
					if(null!=obj){
						count=Integer.parseInt(obj.toString());
					}
					else{
						count=0;
					}
				}
			}
		}
		
		return count;
	}
	
	/**
	 * ��SQL��ѯȫ��
	 */
	public List findName(String name,List pars) throws Exception{
		Query query = getSession().getNamedQuery(name);
		if (pars != null) {
			for (int i = 0; i < pars.size(); i++) {
				query.setParameter(i, pars.get(i));
			}
		}
		return query.list();
	}
	private void addSaveLog(Object entity) throws NumberFormatException, Exception{
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			//�����־
	        TbLog tbLog=new TbLog();
	        String className=entity.getClass().getSimpleName().toLowerCase();
	        if(!className.equalsIgnoreCase("tblog")){
	        	String userId="";
	        	if(null!=request.getSession().getAttribute(USER_ID)){
					userId=request.getSession().getAttribute(USER_ID).toString();
		        	TbUser user=(TbUser) this.get(TbUser.class, new Long(userId));
		        	tbLog.setLoginName(user.getUserName());
				}
				else if(null!=request.getSession().getAttribute(STU_ID)){
					userId=request.getSession().getAttribute(STU_ID).toString();
					TbUnAlumni unAlumni=(TbUnAlumni)this.get(TbUnAlumni.class, new Long(userId));
					String name=unAlumni.getNameCn();
					if(name==null||name.equals("")){
						name=unAlumni.getNameEn();
					}
					tbLog.setLoginName(name);
				}
	        	TableManagerImpl ti=new TableManagerImpl();
	        	Hashtable<String, TableMemo> tables=ti.getTableMemos();
	        	if(tables.get(className)==null){
					return;
				}
		        tbLog.setLoginIp(request.getRemoteAddr());
				tbLog.setUserId(new Long(userId));
				tbLog.setHandType("����");
				tbLog.setHandSign(tables.get(className).getMessage());
				this.save(tbLog);
	        }
        }catch(Exception e){
        	
        }
	}
	
	private void addUpdateLog(Object entity) throws NumberFormatException, Exception{
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			//�����־
	        TbLog tbLog=new TbLog();
	        String className=entity.getClass().getSimpleName().toLowerCase();
	        if(!className.equalsIgnoreCase("tblog")){
	        	String userId="";
	        	if(null!=request.getSession().getAttribute(USER_ID)){
					userId=request.getSession().getAttribute(USER_ID).toString();
		        	TbUser user=(TbUser) this.get(TbUser.class, new Long(userId));
		        	tbLog.setLoginName(user.getUserName());
				}
				else if(null!=request.getSession().getAttribute(STU_ID)){
					userId=request.getSession().getAttribute(STU_ID).toString();
					TbUnAlumni unAlumni=(TbUnAlumni)this.get(TbUnAlumni.class, new Long(userId));
					String name=unAlumni.getNameCn();
					if(name==null||name.equals("")){
						name=unAlumni.getNameEn();
					}
					tbLog.setLoginName(name);
				}
	        	TableManagerImpl ti=new TableManagerImpl();
	        	Hashtable<String, TableMemo> tables=ti.getTableMemos();
	        	if(tables.get(className)==null){
					return;
				}
		        tbLog.setLoginIp(request.getRemoteAddr());
				tbLog.setUserId(new Long(userId));
				tbLog.setHandType("����");
				tbLog.setHandSign(tables.get(className).getMessage());
				this.save(tbLog);
	        }
        }catch(Exception e){
        	
        }
        
	}
	
	private void addDeleteLog(Object entity) throws NumberFormatException, Exception{
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			//�����־
	        TbLog tbLog=new TbLog();
	        String className=entity.getClass().getSimpleName().toLowerCase();
	        if(!className.equalsIgnoreCase("tblog")){
	        	String userId="";
	        	if(null!=request.getSession().getAttribute(USER_ID)){
					userId=request.getSession().getAttribute(USER_ID).toString();
		        	TbUser user=(TbUser) this.get(TbUser.class, new Long(userId));
		        	tbLog.setLoginName(user.getUserName());
				}
				else if(null!=request.getSession().getAttribute(STU_ID)){
					userId=request.getSession().getAttribute(STU_ID).toString();
					TbUnAlumni unAlumni=(TbUnAlumni)this.get(TbUnAlumni.class, new Long(userId));
					String name=unAlumni.getNameCn();
					if(name==null||name.equals("")){
						name=unAlumni.getNameEn();
					}
					tbLog.setLoginName(name);
				}
	        	TableManagerImpl ti=new TableManagerImpl();
	        	Hashtable<String, TableMemo> tables=ti.getTableMemos();
	        	if(tables.get(className)==null){
					return;
				}
		        tbLog.setLoginIp(request.getRemoteAddr());
				tbLog.setUserId(new Long(userId));
				tbLog.setHandType("ɾ��");
				tbLog.setHandSign(tables.get(className).getMessage());
				this.save(tbLog);
	        }
		}catch(Exception e){
        	
        }
	}
	
	/**
	 * //������ʱ��
	 * @param entity
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void trigger(Object entity) throws IllegalAccessException,
			InvocationTargetException {
		//������ʱ��
		//��ȡalumniId�ֶ�
		//�õ�entity�����з���
		boolean tagBreak=false;
		Method[] methods=entity.getClass().getMethods();
		if(methods!=null&&methods.length!=0){
			for (int i = 0; i < methods.length; i++) {
				Method method=methods[i];
				String methodName=method.getName();
				if("getAlumniId".equalsIgnoreCase(methodName)){
					Object alumniId=method.invoke(entity, null);
					if(alumniId!=null&&!"".equals(alumniId)){
						//������ʱ����������
						String sql="update up_message_info a set a.message_status="+alumniId+" where a.system_seq='1'";
						this.execute(sql);
						break;
					}
				}else if("getTbAlumniSt".equalsIgnoreCase(methodName)
						||"getTbAlumni".equalsIgnoreCase(methodName)){
					Object alumniBean=method.invoke(entity, null);
					if(alumniBean!=null){
						Method[] _methods=alumniBean.getClass().getMethods();
						if(_methods!=null&&_methods.length!=0){
							for (int j = 0; j < _methods.length; j++) {
								Method _method=_methods[j];
								String _methodName=_method.getName();
								if("getAlumniId".equalsIgnoreCase(_methodName)){
									Object alumniId=_method.invoke(alumniBean, null);
									if(alumniId!=null&&!"".equals(alumniId)){
										//������ʱ����������
										String sql="update up_message_info a set a.message_status="+alumniId+" where a.system_seq='1'";
										this.execute(sql);
										tagBreak=true;
										break;
									}
								}
							}
							if(tagBreak){
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * ִ��SQL��䣬��select��delete,update��ResultSetֵ�������
	 */
	public int execute(final String sql){
		Object obj=this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				Query query=session.createSQLQuery(sql);
			return query.executeUpdate();
		}});
		return new Integer(obj+"");
	}
	
	public int execute(final String sql,final List pars){
		Object obj=this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				Query query=session.createSQLQuery(sql);
				if(pars!=null&&!pars.isEmpty()){
					for (int i = 0; i < pars.size(); i++) {
						query.setParameter(i, pars.get(i));
					}
				}
			return query.executeUpdate();
		}});
		return new Integer(obj+"");
	}
	
	/**
	 * ִ��HQL��䣬��select��delete,update��ResultSetֵ�������
	 */
	public int executeHql(final String hql){
		Object obj=this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
			return query.executeUpdate();
		}});
		return new Integer(obj+"");
	}
}
