package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 父类编号
		String menuId=request.getParameter("menuId");
		// 菜单名称
		String menuName=request.getParameter("menuName");
	
		// 类型备注
		String memo=request.getParameter("memo");
		// 菜单链接
		String urlLink=request.getParameter("urlLink");
		// 菜单图标
		String urlIcon=request.getParameter("urlIcon");
		// 类型信息
		if(null==menuId||"".equals(menuId))
			return null;
		TbMenu tbMenu=(TbMenu)getCommonBo().get(TbMenu.class, new Long(menuId));
		// 菜单名称
		tbMenu.setMenuName(menuName);
		// 备注
		tbMenu.setMemo(memo);
		// 菜单链接
		tbMenu.setUrlLink(urlLink);
		// 菜单图标
		tbMenu.setUrlIcon(urlIcon);
		// 保存信息
		getCommonBo().update(tbMenu);
		// 返回
		return SUCCESS;
	}
}
