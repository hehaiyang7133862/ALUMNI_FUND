package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.util.IFinalMenu;

public class MenuForwardAction extends BaseAction implements IFinalMenu {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 菜单序号
		String num=request.getParameter("num");
		request.setAttribute("num", num);
		// 编号
		String code=request.getParameter("code");
		// 菜单信息
		String hql="from TbMenu a where a.code='"+code+"'";
		TbMenu tbMenu=(TbMenu)getCommonBo().getHQLUnique(hql);
		request.setAttribute("bean_menu", tbMenu);
		// 返回
		return SUCCESS;
	}
}
