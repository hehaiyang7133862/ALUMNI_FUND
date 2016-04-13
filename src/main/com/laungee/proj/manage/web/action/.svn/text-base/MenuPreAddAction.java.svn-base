package com.laungee.proj.manage.web.action;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuPreAddAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 父类编号
		String parentId=this.getRequest().getParameter("parentId");
		// 设置父类编号
		this.getRequest().setAttribute("parentId", parentId);
		// 返回
		return SUCCESS;
	}
}
