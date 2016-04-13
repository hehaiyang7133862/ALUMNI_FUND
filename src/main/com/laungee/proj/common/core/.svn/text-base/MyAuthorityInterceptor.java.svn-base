package com.laungee.proj.common.core;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyAuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
			ActionContext ctx = arg0.getInvocationContext();
			Map session = ctx.getSession();
			// 取出名为user的session属性
//			Object user_id = session.get("user_id");
//			Object stu_id = session.get("stu_id");
			Object user_type = session.get("fund_role_open");
			if(null == user_type){
				return Action.LOGIN;
		     }else{
		        return arg0.invoke();
		      }
			
		       
			}
	

	
}