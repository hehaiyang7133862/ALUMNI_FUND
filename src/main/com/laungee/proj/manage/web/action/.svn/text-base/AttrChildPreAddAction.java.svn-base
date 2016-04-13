package com.laungee.proj.manage.web.action;

import java.util.List;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;

public class AttrChildPreAddAction extends BaseAction {
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
