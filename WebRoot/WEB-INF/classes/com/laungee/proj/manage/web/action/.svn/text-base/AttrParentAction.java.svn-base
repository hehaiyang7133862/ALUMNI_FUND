package com.laungee.proj.manage.web.action;

import java.util.List;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;

public class AttrParentAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 父类属性
		String id=getRequest().getParameter("id");
		// 为父类时
		if("0".equals(id)){
			// 查询属性
			String hql="from TbField a where a.parentId=0 order by a.numOrder";
			List eleList=getCommonBo().findHQL(hql);
			this.getRequest().setAttribute("list_ele", eleList);
		}
		// 返回
		return SUCCESS;
	}

}
