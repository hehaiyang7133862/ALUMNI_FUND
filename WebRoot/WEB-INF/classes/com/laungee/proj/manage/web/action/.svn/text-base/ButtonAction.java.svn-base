package com.laungee.proj.manage.web.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbMenuRole;
import com.laungee.proj.common.model.TbRole;
import com.sun.org.apache.bcel.internal.generic.Select;

public class ButtonAction  extends BaseAction {
	
	// 处理请求
	public String prebutton() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 代码
		String code=request.getParameter("code");
		request.setAttribute("code",code);
		// 祖级编号
		String grandId=request.getParameter("grandId");
		request.setAttribute("grandId", grandId);
		// 角色编号
		String roleId=request.getParameter("roleId");
		// 角色信息
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(roleId));
		request.setAttribute("bean_role", tbRole);
		// 父级编号
		Long parentId=tbRole.getParentId();
		
//			// 非超级管理员
//			if(null!=parentId){
//				// 父级主菜单
//				String menuMainHql="select distinct c from TbMenuRole a left join a.tbMenu b left join b.tbMenu c " +
//					"where a.tbRole.roleId="+parentId+" and c.numLevel is null and c.code!='button' order by c.numOrder";
//				List menuMainList=getCommonBo().findHQL(menuMainHql);
//				request.setAttribute("list_main", menuMainList);
//				// 父级子菜单
//				String menuSubHql="select distinct b from TbMenuRole a left join a.tbMenu b left join b.tbMenu c " +
//					"where a.tbRole.roleId="+parentId+" and  c.numLevel is null and c.code!='button' order by b.numOrder";
//				List menuSubList=getCommonBo().findHQL(menuSubHql);
//				request.setAttribute("list_sub", menuSubList);
//			}
			// 超级管理员
//			else{
				// 父级主菜单
				String menuMainHql="from TbMenu a where  a.numLevel is null and a.code!='button' order by a.numOrder";
				List menuMainList=getCommonBo().findHQL(menuMainHql);
				request.setAttribute("list_main", menuMainList);
				// 父级子菜单
				String menuSubHql="from TbMenu a where  a.numLevel is null and a.code!='button' order by a.numOrder";
				List menuSubList=getCommonBo().findHQL(menuSubHql);
				request.setAttribute("list_sub", menuSubList);
//			}
		
		// 已选子菜单
		String selfHql="select distinct a.tbMenu from TbMenuRole a where a.tbMenu.isLeaf=1  and a.tbRole.roleId="+roleId;
		List selfList=getCommonBo().findHQL(selfHql);
		Set set=listToSet(selfList, TbMenu.class, "menuId");
		request.setAttribute("list_self", set);
		// 返回
		return "prebutton";
	}
	// 处理请求
	public String buttonEdit() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 角色编号
		String roleId=request.getParameter("roleId");
		// 菜单编号
		String[] menuIds=request.getParameterValues("menuId");
		// 原有菜单编号
		String roleHql="select a.tbMenu.menuId from TbMenuRole a where a.tbMenu.numLevel is null and  a.tbRole.roleId="+roleId;
		List roleList=getCommonBo().findHQL(roleHql);
		Set roleSet=listToSet(roleList);
		// 添加的List
		Set addSet=arrayToSet(menuIds);
		addSet.removeAll(roleSet);
		for (Iterator iterator=addSet.iterator();iterator.hasNext();) {
			TbMenuRole tbMenuRole=new TbMenuRole();
			Object obj=iterator.next();
			if(null!=iterator&&null!=obj&&!"".equals(obj)){
				tbMenuRole.setTbMenu(new TbMenu(new Long(obj.toString())));
			}
			if(null!=roleId&&!"".equals(roleId)){
				tbMenuRole.setTbRole(new TbRole(new Long(roleId)));
			}
			getCommonBo().save(tbMenuRole);
		}
		// 删除的List
		roleSet.removeAll(arrayToSet(menuIds));
		for (Iterator iterator=roleSet.iterator();iterator.hasNext();) {
			String delHql="select a from TbMenuRole a where a.tbMenu.menuId="+iterator.next()+" and a.tbRole.roleId="+roleId;
			List temp =getCommonBo().findHQL(delHql);
			getCommonBo().deleteAll(temp);
		}
		// 提示信息
		request.setAttribute("alert", "更新成功");
		// 返回
		return prebutton();
	}
	
}
