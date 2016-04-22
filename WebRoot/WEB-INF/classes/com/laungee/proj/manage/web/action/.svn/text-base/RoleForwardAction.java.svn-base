package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;

public class RoleForwardAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 父类编号
		String userId=request.getSession().getAttribute(USER_ID).toString();
		// 角色编号
		String roleId=request.getParameter("roleId");
		// 角色集合
		String hql="select distinct b from TbRoleUser a left join a.tbRole b where b.isValid=1 and a.tbUser.userId="+userId+" order by b.roleId";
		List list=this.getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("list_role", list);
		// 最高的角色
		TbRole tbRole=null;
		if(null!=roleId && !roleId.equals("")){
			tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(roleId));
		}
		else if(null!=list && list.size()>=1){
			tbRole=(TbRole)list.get(0);
		}
		this.getRequest().setAttribute("bean_role", tbRole);
		// 返回
		return SUCCESS;
	}
}
