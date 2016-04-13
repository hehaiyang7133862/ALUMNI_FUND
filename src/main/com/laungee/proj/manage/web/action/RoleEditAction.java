package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.sun.org.apache.bcel.internal.generic.Select;

public class RoleEditAction  extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// �������
		HttpServletRequest request=ServletActionContext.getRequest();
		// ��ɫ���
		String id=request.getParameter("id");
		// ��ɫ����
		String roleName=request.getParameter("roleName");
		
		// �鿴�ȼ�
		String openFid=request.getParameter("openFid");
		// ����Ӽ�
		String isLeaf=request.getParameter("isLeaf");
		// ��ע
		String memo=request.getParameter("memo");
		// ��ɫ��Ϣ
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(id));
		tbRole.setRoleName(roleName);
		
		tbRole.setIsLeaf(isLeaf);
		tbRole.setMemo(memo);
		// �����ɫ
		this.getCommonBo().update(tbRole);
		// ����
		return SUCCESS;
	}
	// Ԥ����
	public String pre() throws Exception {
		// �������
		HttpServletRequest request=ServletActionContext.getRequest();
		// ��ɫ���
		String id=request.getParameter("id");
		// ���ý�ɫ��Ϣ
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(id));
		this.getRequest().setAttribute("bean_role", tbRole);
		// ����
		return INPUT;
	}
}
