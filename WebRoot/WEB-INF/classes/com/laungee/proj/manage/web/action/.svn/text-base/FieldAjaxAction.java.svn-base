package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class FieldAjaxAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 请求编号
		String ajax = "";
		String id = request.getParameter("id");
		if (id.matches("[0-9]+")) {
			// 查询子级
			String hql = "from TbField a where a.parentId=" + id+" order by a.numOrder asc";
			List list = getCommonBo().findHQL(hql);
			ajax = this.toXml(list, TbField.class, "fieldId",
					"fieldName");
		}
		this.sendResponse(getResponse(), ajax);
		// 返回
		return null;
	}
	
	// 基金处理请求
	public String fundExecute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 请求编号
		String ajax = "";
		String id = request.getParameter("id");
		if (id.matches("[0-9]+")) {
			// 查询子级
			String hql = "from TbFields a where a.parentId=" + id+" order by a.numOrder asc";
			List list = getCommonBo().findHQL(hql);
			ajax = this.toXml(list, TbField.class, "fieldId",
					"fieldName");
		}
		this.sendResponse(getResponse(), ajax);
		// 返回
		return null;
	}
}
