package com.laungee.proj.manage.web.action;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class NavAction  extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 参数列表
		String code=this.getRequest().getParameter("code");
		String url=this.getRequest().getParameter("url");
		// 查询菜单
		if(null==code||"".equals(code))
			return null;
		TbMenu tbMenu=(TbMenu)this.getCommonBo().get(TbMenu.class, new Long(code));
		String nav=tbMenu.getTbMenu().getMenuName()+" > "+tbMenu.getMenuName();
		this.getRequest().getSession().setAttribute("NAV", nav);
		// 跳转处理
		this.getResponse().sendRedirect(url);
		// 返回
		return null;
	}

}
