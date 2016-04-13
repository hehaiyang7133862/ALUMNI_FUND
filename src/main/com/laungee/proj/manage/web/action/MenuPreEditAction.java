package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuPreEditAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// �������
		HttpServletRequest request=ServletActionContext.getRequest();
		// ���
		String id=request.getParameter("id");
		// ��ѯ�ĵ���Ϣ
		if(null==id||"".equals(id))
			return null;
		TbMenu tbMenu=(TbMenu)getCommonBo().get(TbMenu.class, new Long(id));
		// �ĵ���Ϣ
		request.setAttribute("bean_menu", tbMenu);
		// ����
		return SUCCESS;
	}
}
