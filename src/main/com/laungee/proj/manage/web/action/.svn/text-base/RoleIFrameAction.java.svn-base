package com.laungee.proj.manage.web.action;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;

public class RoleIFrameAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 父类编号
		String id=this.getRequest().getParameter("id");
		// 父类信息
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)this.getCommonBo().get(TbRole.class, new Long(id));
		this.getRequest().setAttribute("bean_role", tbRole);
		// 祖级编号
		String grandId=this.getRequest().getParameter("grandId");
		// 祖级编号
		this.getRequest().setAttribute("grandId", grandId);
		// 返回
		return SUCCESS;
	}
}
