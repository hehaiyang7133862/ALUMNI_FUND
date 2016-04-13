package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;


public class FieldsJsonAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 属性编号
		String id=getRequest().getParameter("id");
		// 查询属性
		String hql="from TbFields a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("list_ele", eleList);
		// 返回
		return SUCCESS;
	}
	// 处理请求
	public String pre() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 菜单序号
		request.setAttribute("num", request.getAttribute("num"));
		// 属性编号
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="1";
		}
		// 查询属性
		TbFields tbField=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
		request.setAttribute("bean", tbField);
		// 返回
		return INPUT;
	}
}
