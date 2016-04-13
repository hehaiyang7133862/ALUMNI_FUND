package com.laungee.proj.common.core;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laungee.proj.common.util.DecryptUtil;
import com.laungee.proj.common.util.IEncryptUtil;
import com.laungee.proj.common.util.PaginationDto;
import com.laungee.proj.common.util.StringUtils;

public class BaseHibernate extends HibernateDaoSupport implements IEncryptUtil {

	/**
	 * 由HQL查询分页
	 */
	public List findHQLPage(String hql,List pars) throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		// ---设置初始值
		PaginationDto dto=new PaginationDto();
		// ---初化参数
		// 当前的页数
		String scurr=request.getParameter(dto.getScurr());
		if(null!=scurr){
			dto.setCurr(Integer.parseInt(scurr));
		}
		// 当前条目数
		String ssize=request.getParameter(dto.getSsize());
		if(null!=ssize){
			dto.setSize(Integer.parseInt(ssize));
		}
		// 显示条目数
		String sitem1 =request.getParameter(dto.getSitem1());
		if(null!=sitem1){
			dto.setItem1(Integer.parseInt(sitem1));
		}
		String sitem2 =request.getParameter(dto.getSitem2());
		if(null!=sitem2){
			dto.setItem2(Integer.parseInt(sitem2));
		}
		String sitem3 =request.getParameter(dto.getSitem3());
		if(null!=sitem3){
			dto.setItem3(Integer.parseInt(sitem3));
		}
		// 排序标识符
		String or=request.getParameter(dto.getSor());
		if(null!=or){
			dto.setOr(or);
		}
		String st=request.getParameter(dto.getSst());
		if(null!=st){
			dto.setSt(st);
		}
		request.setAttribute(dto.getSor(), or);
		request.setAttribute(dto.getSst(), st);
		/* 处理排序 */
		String hql1=hql;
		if(null!=st){
			String order=DecryptUtil.decrypt(st);
			if(order.startsWith(SORT_KEY) && order.matches("[0-9A-Za-z.]*")){
				order=order.replaceFirst(SORT_KEY, "");
				if("asc".equalsIgnoreCase(dto.getOr()) || "desc".equalsIgnoreCase(dto.getOr())){
					//替换原来的排序
					if(hql1.lastIndexOf("order by")!=-1){
						hql1=hql1.substring(0,hql1.lastIndexOf("order by"));
					}
					//再拼接
					hql1+=" order by "+order+" "+dto.getOr();
				}
			}
		}
//		if(hql1.indexOf("order by")==-1){
//			if(hql1.startsWith("select")){
//				String alias= hql1.substring(7,hql1.indexOf(" ",7));
//				hql1+=" order by "+alias+".updateTime desc";
//			}
//			else{
//				hql1+=" order by updateTime desc";
//			}
//		}
		
		//拼接repeat is null，查重合并过的对象
		String repeat=" (isRepeat=0 or isRepeat is null) ";
		String thql="";
		//判断有没有排序标签
		if(hql1.indexOf("order by")==-1){
			//如果没有条件where
			if(hql1.indexOf("where")==-1){
				thql=hql1+" where "+repeat;
			}else{
				thql=hql1.substring(0,hql1.indexOf(" where ")+7)+repeat+" and "+hql1.substring(hql1.indexOf(" where ")+7);
			}
		}else{
			String sql1=hql1.substring(0,hql1.indexOf("order by"));
			String order=hql1.substring(hql1.indexOf("order by"));
			//如果没有条件where
			if(hql1.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=hql1.substring(0,hql1.indexOf(" where ")+7)+repeat+" and "+hql1.substring(hql1.indexOf(" where ")+7);
			}
		}
		List list =null;
		try {
			/* 查询分页集合 */
			Query query = this.getSession().createQuery(thql);
			if (pars != null) {
				for (int i = 0; i < pars.size(); i++) {
					query.setParameter(i, pars.get(i));
				}
			}
			query.setFirstResult((dto.getCurr() - 1) * dto.getSize());
			query.setMaxResults(dto.getSize());
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("已做异常处理。");
			//如果提示字符无效
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					/* 查询分页集合 */
					Query query = this.getSession().createQuery(hql1);
					if (pars != null) {
						for (int i = 0; i < pars.size(); i++) {
							query.setParameter(i, pars.get(i));
						}
					}
					query.setFirstResult((dto.getCurr() - 1) * dto.getSize());
					query.setMaxResults(dto.getSize());
					list = query.list();
				}
			}
		}
		
		
		/* 查询总页数 */
		int ifrom = (" "+hql.toLowerCase()).indexOf(" from ");
		String countObj="*";
		if(hql.indexOf("select ")==0){
			countObj=hql.substring(hql.indexOf("select ")+"select ".length(),hql.indexOf(" from"));
			if(countObj.contains(",")){
				countObj=countObj.substring(0,countObj.indexOf(","));
			}
		}
		String hql2= "select count("+countObj+") " + hql.substring(ifrom);
		Query query2=this.getSession().createQuery(hql2);
		if (pars != null) {
			for (int i = 0; i < pars.size(); i++) {
				query2.setParameter(i, pars.get(i));
			}
		}
		int total =Integer.parseInt(query2.uniqueResult().toString());
		dto.setTotal(total);
		int count = (int) Math.ceil(total/ (dto.getSize()+0.0));
		dto.setCount(count);
		/* 请求参数 */
		StringBuffer part = new StringBuffer();
		String parName;
		String[] parValues;
		// 循环获取参数
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			// 参数名称
			parName = e.nextElement().toString();
			if (!dto.getRemove().contains(parName) && !parName.startsWith("dels")){
				try {
					parValues = request.getParameterValues(parName);
					if(null!=parValues && parValues.length == 1){
						request.setAttribute(parName, parValues[0]);
					}
					if(null!=parValues){
						request.setAttribute(parName+"s", parValues);
					}
					for (int i = 0; i < parValues.length; i++) {
						
						part.append(parName + "=" + URLEncoder.encode(parValues[i], "UTF-8")+ "&");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		// 部分参数
		
		dto.setPart(new StringUtils().subpagepar("&"+part.toString()));
		// 全部参数
		String Pars=dto.getPart();
		// 排序
		if(null!=dto.getSt() && !dto.equals("")){
			Pars+="&"+dto.getSst()+"="+dto.getSt()+"&"+dto.getSor()+"="+dto.getOr()+"&";
		}
		Pars+=dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+dto.getCurr();
		dto.setPars(Pars);
		// 设置属性
		request.setAttribute(dto.getName(), dto);
		// 返回
		return list;
	}
	public List findSQLPage(String hql,List pars) throws Exception{
		return null;
	}
	
	public List findHQLPage(String hql,List pars,int size,int curr) throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		// ---设置初始值
		PaginationDto dto=new PaginationDto();
		// ---初化参数
		// 当前的页数
		dto.setCurr(curr);
		
		//设置默认页面条目数
		dto.setSize(size);
		// 排序标识符
		String or=request.getParameter(dto.getSor());
		if(null!=or){
			dto.setOr(or);
		}
		String st=request.getParameter(dto.getSst());
		if(null!=st){
			dto.setSt(st);
		}
		request.setAttribute(dto.getSor(), or);
		request.setAttribute(dto.getSst(), st);
		/* 处理排序 */
		String hql1=hql;
		if(null!=st){
			String order=DecryptUtil.decrypt(st);
			if(order.startsWith(SORT_KEY) && order.matches("[0-9A-Za-z.]*")){
				order=order.replaceFirst(SORT_KEY, "");
				if("asc".equalsIgnoreCase(dto.getOr()) || "desc".equalsIgnoreCase(dto.getOr())){
					//替换原来的排序
					hql1=hql1.substring(0,hql1.lastIndexOf("order"));
					//再拼接
					hql1+=" order by "+order+" "+dto.getOr();
				}
			}
		}
		
		//拼接repeat is null，查重合并过的对象
		String repeat=" (isRepeat=0 or isRepeat is null) ";
		String thql="";
		//判断有没有排序标签
		if(hql1.indexOf("order by")==-1){
			//如果没有条件where
			if(hql1.indexOf("where")==-1){
				thql=hql1+" where "+repeat;
			}else{
				thql=hql1.substring(0,hql1.indexOf(" where ")+7)+repeat+" and "+hql1.substring(hql1.indexOf(" where ")+7);
			}
		}else{
			String sql1=hql1.substring(0,hql1.indexOf("order by"));
			String order=hql1.substring(hql1.indexOf("order by"));
			//如果没有条件where
			if(hql1.indexOf("where")==-1){
				thql=sql1+" where "+repeat+" "+order;
			}else{
				thql=hql1.substring(0,hql1.indexOf(" where ")+7)+repeat+" and "+hql1.substring(hql1.indexOf(" where ")+7);
			}
		}
		List list =null;
		try {
			/* 查询分页集合 */
			Query query = this.getSession().createQuery(thql);
			if (pars != null) {
				for (int i = 0; i < pars.size(); i++) {
					query.setParameter(i, pars.get(i));
				}
			}
			query.setFirstResult((dto.getCurr() - 1) * dto.getSize());
			query.setMaxResults(dto.getSize());
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("已做异常处理。");
			//如果提示字符无效
			if(e!=null&&e.getCause()!=null&&e.getCause().getMessage()!=null){
				if(e.getCause().getMessage().toUpperCase().indexOf("ORA-00904")!=-1){
					/* 查询分页集合 */
					Query query = this.getSession().createQuery(hql1);
					if (pars != null) {
						for (int i = 0; i < pars.size(); i++) {
							query.setParameter(i, pars.get(i));
						}
					}
					query.setFirstResult((dto.getCurr() - 1) * dto.getSize());
					query.setMaxResults(dto.getSize());
					list = query.list();
				}
			}
		}
		
		
		/* 查询总页数 */
		int ifrom = (" "+hql.toLowerCase()).indexOf(" from ");
		String countObj="*";
		if(hql.indexOf("select ")==0){
			countObj=hql.substring(hql.indexOf("select ")+"select ".length(),hql.indexOf(" from"));
			if(countObj.contains(",")){
				countObj=countObj.substring(0,countObj.indexOf(","));
			}
		}
		String hql2= "select count("+countObj+") " + hql.substring(ifrom);
		Query query2=this.getSession().createQuery(hql2);
		if (pars != null) {
			for (int i = 0; i < pars.size(); i++) {
				query2.setParameter(i, pars.get(i));
			}
		}
		int total =Integer.parseInt(query2.uniqueResult().toString());
		dto.setTotal(total);
		int count = (int) Math.ceil(total/ (dto.getSize()+0.0));
		dto.setCount(count);
		/* 请求参数 */
		StringBuffer part = new StringBuffer();
		String parName;
		String[] parValues;
		// 循环获取参数
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			// 参数名称
			parName = e.nextElement().toString();
			if (!dto.getRemove().contains(parName) && !parName.startsWith("dels")){
				try {
					parValues = request.getParameterValues(parName);
					if(null!=parValues && parValues.length == 1){
						request.setAttribute(parName, parValues[0]);
					}
					if(null!=parValues){
						request.setAttribute(parName+"s", parValues);
					}
					for (int i = 0; i < parValues.length; i++) {
						
						part.append(parName + "=" + URLEncoder.encode(parValues[i], "UTF-8")+ "&");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		// 部分参数
		
		dto.setPart(new StringUtils().subpagepar("&"+part.toString()));
		// 全部参数
		String Pars=dto.getPart();
		// 排序
		if(null!=dto.getSt() && !dto.equals("")){
			Pars+="&"+dto.getSst()+"="+dto.getSt()+"&"+dto.getSor()+"="+dto.getOr()+"&";
		}
		Pars+=dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+dto.getCurr();
		dto.setPars(Pars);
		// 设置属性
		request.setAttribute(dto.getName(), dto);
		// 返回
		return list;
	}
}