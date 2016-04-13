package com.laungee.proj.manage.web.action;

import java.util.List;

import com.laungee.proj.common.core.BaseAction;

public class MenuListAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 父类编号
		String id=this.getRequest().getParameter("id");
		// 父类子集
		List eleList=this.getCommonBo().findHQL("from TbMenu a where a.parentId="+id+" order by a.numOrder");
		this.getRequest().setAttribute("list_ele",eleList);
		// 返回
		return SUCCESS;
	}
}
