package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class FieldAjaxAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ������
		String ajax = "";
		String id = request.getParameter("id");
		if (id.matches("[0-9]+")) {
			// ��ѯ�Ӽ�
			String hql = "from TbField a where a.parentId=" + id+" order by a.numOrder asc";
			List list = getCommonBo().findHQL(hql);
			ajax = this.toXml(list, TbField.class, "fieldId",
					"fieldName");
		}
		this.sendResponse(getResponse(), ajax);
		// ����
		return null;
	}
	
	// ����������
	public String fundExecute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ������
		String ajax = "";
		String id = request.getParameter("id");
		if (id.matches("[0-9]+")) {
			// ��ѯ�Ӽ�
			String hql = "from TbFields a where a.parentId=" + id+" order by a.numOrder asc";
			List list = getCommonBo().findHQL(hql);
			ajax = this.toXml(list, TbField.class, "fieldId",
					"fieldName");
		}
		this.sendResponse(getResponse(), ajax);
		// ����
		return null;
	}
}
