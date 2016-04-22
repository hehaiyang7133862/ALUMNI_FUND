package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuPreEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 编号
		String id=request.getParameter("id");
		// 查询文档信息
		if(null==id||"".equals(id))
			return null;
		TbMenu tbMenu=(TbMenu)getCommonBo().get(TbMenu.class, new Long(id));
		// 文档信息
		request.setAttribute("bean_menu", tbMenu);
		// 返回
		return SUCCESS;
	}
}
