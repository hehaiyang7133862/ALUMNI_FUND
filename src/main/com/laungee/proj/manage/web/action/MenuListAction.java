package com.laungee.proj.manage.web.action;

import java.util.List;

import com.laungee.proj.common.core.BaseAction;

public class MenuListAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// ������
		String id=this.getRequest().getParameter("id");
		// �����Ӽ�
		List eleList=this.getCommonBo().findHQL("from TbMenu a where a.parentId="+id+" order by a.numOrder");
		this.getRequest().setAttribute("list_ele",eleList);
		// ����
		return SUCCESS;
	}
}
