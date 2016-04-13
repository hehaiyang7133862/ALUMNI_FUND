package com.laungee.proj.manage.web.action;

import java.util.List;

import com.laungee.proj.common.core.BaseAction;

public class AttrChildAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		String id=getRequest().getParameter("id");
		String hql="from TbField a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("list_ele", eleList);
		// 返回
		return SUCCESS;
	}

}
