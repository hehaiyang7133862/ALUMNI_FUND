package com.laungee.proj.api.web.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.util.EncryptUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Interceptor extends AbstractInterceptor {

	ICommonBiz biz = null;
	
	private ICommonBiz getBiz(){
		if(biz==null){
			biz = (ICommonBiz) SpringUtil.getBean(SpringUtil.COMMONBIZ);
		}
		return biz;
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 验证参数值正确性
		Map map = request.getParameterMap();
		if(map==null || map.isEmpty()){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
			return Action.ERROR;
		}
		// 随机数
		String randomKey = request.getParameter("randomKey");
		if(randomKey==null || "".equals(randomKey)){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
			return Action.ERROR;
		}
		// 签名
		String sign = request.getParameter("sign");
		if(sign==null || "".equals(sign)){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
			return Action.ERROR;
		}

		// API访问地址
		String fromURI = request.getHeader("Referer");
		// API访问域名
		String fromDomain = null;
		if(fromURI!=null && !"".equals(fromURI)){
			fromDomain = fromURI;
			int index = fromDomain.indexOf("://");
			if(index!=-1){
				fromDomain = fromDomain.substring(index+3); 
			}
			index = fromDomain.indexOf("/");
			if(index!=-1){
				fromDomain = fromDomain.substring(0,index); 
			}
		}
		// 验证该访问域名是否得到授权
		TbFields beanFieldSite = null;
		if(fromDomain!=null && !"".equals(fromDomain)){
			try{
				String hql = "select a from TbFields a,TbFields b where a.parentId=b.fieldId and b.code=? and (a.code=? or a.fieldValue=?)";
				List pars = new ArrayList();
				pars.add("JJHMGTAPIWITHSITE");
				pars.add(fromDomain);
				pars.add(fromDomain);
				beanFieldSite = (TbFields)this.getBiz().getHQLUnique(hql,pars);
			}catch(Exception e){}
		}
		if(beanFieldSite==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
			return Action.ERROR;
		}
		// 签名秘钥KEY值
		String key = null;
		try{
			String hql = "select a.fieldValue from TbFields a,TbFields b where a.parentId=b.fieldId and b.fieldId=? and a.code=?";
			List pars = new ArrayList();
			pars.add(beanFieldSite.getFieldId());
			pars.add("SIGNKEY");
			key = (String)this.getBiz().getHQLUnique(hql,pars);
		}catch(Exception e){}
		if(key==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
			// 返回错误页面
			return Action.ERROR;
		}
		// 重构参数
		List data = new ArrayList();
		for(Iterator iterator=map.keySet().iterator(); iterator.hasNext();){
			String name = (String)iterator.next();
			if("randomKey".equals(name) || "sign".equals(name)){
				continue;
			}
			String[] values = (String[])map.get(name);
			for(int i=0; i<values.length; i++){
				data.add(new NameValuePair(name, values[i]));
			}
		}
		// 按照name+value从小到大排序
		Collections.sort(data, new Comparator(){
			public int compare(Object o1, Object o2){
				NameValuePair p1 = (NameValuePair)o1;
				NameValuePair p2 = (NameValuePair)o2;
		        return (p1.getName()+p1.getValue()).compareTo(p2.getName()+p2.getValue());
			}
		});
		// 拼接键值对（签名、值为空时不参与），生成签名值
		String signTemp = "";
		for(int i=0;i<data.size();i++){
			NameValuePair obj = (NameValuePair)data.get(i);
			if(obj.getValue()!=null && !"".equals(obj.getValue())){
				signTemp += obj.getName()+"="+obj.getValue()+"&";
			}
		}
		signTemp += "randomKey="+randomKey+"&key="+key;
		// md5生成签名
		signTemp = EncryptUtil.md5(signTemp,"utf-8");
		// 签名正确性验证
		if(!sign.equals(signTemp)){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
			return Action.ERROR;
		}
		// 返回
        return arg0.invoke();
	}   
}