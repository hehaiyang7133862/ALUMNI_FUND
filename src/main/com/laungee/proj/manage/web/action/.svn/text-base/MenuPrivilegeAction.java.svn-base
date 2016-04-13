package com.laungee.proj.manage.web.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbMenuRole;
import com.laungee.proj.common.model.TbRole;
import com.laungee.proj.common.model.TbRoleUser;
import com.laungee.proj.common.model.TbUser;
import com.sun.org.apache.bcel.internal.generic.Select;

public class MenuPrivilegeAction  extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 角色编号
		String roleId=request.getParameter("roleId");
		// 菜单编号
		String[] menuIds=request.getParameterValues("menuId");
		// 原有菜单编号
		String roleHql="select a.tbMenu.menuId from TbMenuRole a where a.tbMenu.numLevel is not null and a.tbRole.roleId="+roleId;
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
		return pre();
	}
	// 处理请求
	public String pre() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 代码
		String code=request.getParameter("code");
		request.setAttribute("code",code);
		// 祖级编号
		String grandId=request.getParameter("grandId");
		request.setAttribute("grandId", grandId);
		TbRole tbRootRole=(TbRole)getCommonBo().get(TbRole.class, new Long(grandId));
		// 角色编号
		String roleId=request.getParameter("roleId");
		// 角色信息
		if(null==roleId||"".equals(roleId))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(roleId));
		request.setAttribute("bean_role", tbRole);
		// 父级编号
		Long parentId=tbRole.getParentId();
		// 菜单
		if(null!=tbRole.getParentId()){
				String hql="select distinct b from TbMenuRole a left join a.tbMenu b where";
				if(tbRootRole.equals(tbRole)){
					hql += " a.tbRole.roleId="+tbRole.getRoleId();
				}else{
					hql += " a.tbRole.roleId="+tbRole.getParentId();
				}
				List list=getCommonBo().findHQL(hql+" order by b.numOrder");
				request.setAttribute("list_menu", list);
		}else{
				String hql="select a from TbMenu a where a.parentId is not null";
				List list=getCommonBo().findHQL(hql+" order by a.numOrder");
				request.setAttribute("list_menu", list);
		}
	
		// 已选子菜单
		String hql2="select b.menuId from TbMenuRole a left join a.tbMenu b " +
		"where a.tbRole.roleId="+roleId;
		List selfList=getCommonBo().findHQL(hql2+" order by b.numOrder");
		Set set=new HashSet(selfList);
		request.setAttribute("list_sel", set);
		// 返回
		return SUCCESS;
	}
	
}
