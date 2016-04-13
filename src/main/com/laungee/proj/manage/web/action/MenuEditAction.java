package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuEditAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// �������
		HttpServletRequest request=ServletActionContext.getRequest();
		// ������
		String menuId=request.getParameter("menuId");
		// �˵�����
		String menuName=request.getParameter("menuName");
	
		// ���ͱ�ע
		String memo=request.getParameter("memo");
		// �˵�����
		String urlLink=request.getParameter("urlLink");
		// �˵�ͼ��
		String urlIcon=request.getParameter("urlIcon");
		// ������Ϣ
		if(null==menuId||"".equals(menuId))
			return null;
		TbMenu tbMenu=(TbMenu)getCommonBo().get(TbMenu.class, new Long(menuId));
		// �˵�����
		tbMenu.setMenuName(menuName);
		// ��ע
		tbMenu.setMemo(memo);
		// �˵�����
		tbMenu.setUrlLink(urlLink);
		// �˵�ͼ��
		tbMenu.setUrlIcon(urlIcon);
		// ������Ϣ
		getCommonBo().update(tbMenu);
		// ����
		return SUCCESS;
	}
}
