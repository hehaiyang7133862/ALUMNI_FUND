package com.laungee.proj.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.IFinalUser;
import com.laungee.proj.common.util.PropertyUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements IFinalUser {
	/**
	 * 业务层
	 */
	private ICommonBiz commonBiz;
	
	/**
	 * Spring容器
	 */
	public static ApplicationContext ctx = null;

	/**
	 * 设置业务层对象
	 */
	public void setCommonBiz(ICommonBiz commonBiz) {
		this.commonBiz = commonBiz;
	}
	
	/**
	 * 得到业务层对象
	 */
	public ICommonBiz getCommonBo(){
		if (null==commonBiz){
			commonBiz=(ICommonBiz)getBean("commonBiz");
		}
		return commonBiz;
	}

	/**
	 * 得到Spring的Bean
	 */
	public Object getBean(String name) {
		if (null==ctx){
			ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		}
		return ctx.getBean(name);
	}
	/**
	 * 
	 * @return
	 */
	public List getList(){
		return new ArrayList();
	}

	/*
	 * 得到请求对象
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/*
	 * 得到响应对象
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 向客户端发出文本内容
	 * 
	 * @param <HttpServletResponse>response
	 *            响应对象
	 * @param <String>responseText
	 *            输出文本
	 * @return void
	 */
	public void sendResponse(HttpServletResponse response, String responseText)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0L);
		response.getWriter().println(responseText);
	}
	
	/**
	 * 将单值转换成Set
	 */
	public Set listToSet(List v,Object clzz,String value){
		Set set=new HashSet();
		if(null!=v){
			for(int i=0;i<v.size();i++){
				clzz = v.get(i);
				Object val1 = PropertyUtil.getPropertyValue(clzz, value);
				set.add(val1);
			}
		}
		return set;
	}
	/**
	 * 将单值转换成Set
	 */
	public Set listToSet(List v){
		Set set=new HashSet();
		if(null!=v){
			for(int i=0;i<v.size();i++){
				set.add(v.get(i).toString());
			}
		}
		return set;
	}
	/**
	 * 将单值转换成List
	 */
	public Set arrayToSet(Object[] v){
		Set set=new HashSet();
		if(null!=v){
			for(int i=0;i<v.length;i++){
				set.add(v[i]);
			}
		}
		return set;
	}
	/**
	 * 将键值转换成Map
	 */
	public Map listToMap(List v,Object clzz,String key,String value){
		Map map=new HashMap();
		for(int i=0;i<v.size();i++){
			clzz = v.get(i);
			Object key1 = PropertyUtil.getPropertyValue(clzz, key);
			Object value1 = PropertyUtil.getPropertyValue(clzz, value);
			map.put(key1,value1);
		}
		return map;
	}

	/**
	 * 将键值转换成XML
	 */
	public String toXml(List v, Object clzz, String id, String name) {
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		buf.append("<root>");
		if(null!=v){
			for (int i = 0; i < v.size(); i++) {
				clzz = v.get(i);
				Object id1 = PropertyUtil.getPropertyValue(clzz, id);
				Object name1 = PropertyUtil.getPropertyValue(clzz, name);
				buf.append("<fields>");
				buf.append("<id>" + id1 + "</id>");
				buf.append("<name>" + name1 + "</name>");
				buf.append("</fields>");
			}
		}
		buf.append("</root>");
		return buf.toString();
	}

	/**
	 * 将集合转换成XML
	 */
	public String toXml(List v) {
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		buf.append("<root>");
		Object[] objects;
		for (int i = 0; i < v.size(); i++) {
			objects = (Object[]) v.get(i);
			for (int j = 0; j < objects.length; j++) {
				buf.append("<name" + j + ">" + null != objects[j] ? objects[j]
						.toString() : "" + "</id>");
			}
		}
		buf.append("</root>");
		return buf.toString();
	}
}
