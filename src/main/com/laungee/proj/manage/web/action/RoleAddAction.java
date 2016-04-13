package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.sun.org.apache.bcel.internal.generic.Select;

public class RoleAddAction  extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 角色名称
		String roleName=request.getParameter("roleName");
		// 编号
		String code=request.getParameter("code");
		// 查看等级
		String openFid=request.getParameter("openFid");
		// 添加子级
		String isLeaf=request.getParameter("isLeaf");
		// 备注
		String memo=request.getParameter("memo");
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 排序编号
		String numHql="select max(a.numOrder) from TbRole a where a.parentId="+parentId;
		int numOrder=this.getCommonBo().findHQLCount(numHql);
		// 角色信息
		TbRole tbRole=new TbRole();
		tbRole.setRoleName(roleName);
		tbRole.setCode(code);
		
		tbRole.setIsLeaf(isLeaf);
		tbRole.setMemo(memo);
		if(null!=parentId&&!"".equals(parentId)){
			tbRole.setParentId(new Long(parentId));
		}
		tbRole.setNumOrder(new Long(numOrder+1));
		// 保存角色
		this.getCommonBo().save(tbRole);
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String pre() throws Exception {
		// 父类编号
		String parentId=this.getRequest().getParameter("parentId");
		// 设置父类编号
		this.getRequest().setAttribute("parentId", parentId);
		// 返回
		return INPUT;
	}
}
