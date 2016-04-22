package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.sun.org.apache.bcel.internal.generic.Select;

public class RoleEditAction  extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 角色编号
		String id=request.getParameter("id");
		// 角色名称
		String roleName=request.getParameter("roleName");
		
		// 查看等级
		String openFid=request.getParameter("openFid");
		// 添加子级
		String isLeaf=request.getParameter("isLeaf");
		// 备注
		String memo=request.getParameter("memo");
		// 角色信息
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(id));
		tbRole.setRoleName(roleName);
		
		tbRole.setIsLeaf(isLeaf);
		tbRole.setMemo(memo);
		// 保存角色
		this.getCommonBo().update(tbRole);
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String pre() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 角色编号
		String id=request.getParameter("id");
		// 设置角色信息
		if(null==id||"".equals(id))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(id));
		this.getRequest().setAttribute("bean_role", tbRole);
		// 返回
		return INPUT;
	}
}
