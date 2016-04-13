package com.laungee.proj.manage.web.action;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;

public class RoleIFrameAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// ������
		String id=this.getRequest().getParameter("id");
		// ������Ϣ
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)this.getCommonBo().get(TbRole.class, new Long(id));
		this.getRequest().setAttribute("bean_role", tbRole);
		// �漶���
		String grandId=this.getRequest().getParameter("grandId");
		// �漶���
		this.getRequest().setAttribute("grandId", grandId);
		// ����
		return SUCCESS;
	}
}
