package com.laungee.proj.manage.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.laungee.proj.common.model.TbRoleUser;
import com.laungee.proj.common.model.TbUser;

public class UserInListAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// ��ɫ��Ϣ
		String roleId=request.getParameter("roleId");
		if(null==roleId||"".equals(roleId))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(roleId));
		request.setAttribute("bean_role", tbRole);
		// ����
		StringBuffer hql=new StringBuffer();
		hql.append("select a from TbRoleUser a where ");
		List pars=this.getList();
		Long userId=(Long)request.getSession().getAttribute(USER_ID);
		TbUser tbUser=(TbUser)getCommonBo().get(TbUser.class, userId);
		if(tbUser!=null){
			if(tbUser.getUserCard()!=null&&!tbUser.getUserCard().equals("xyadmin")){
				hql.append("a.tbUser.userCard!='xyadmin' and ");
			}
		}
		hql.append("tbRole.roleId=? and ");
		pars.add(new Long(roleId));
		// ����
		String userName=request.getParameter("userName");
		if(null!=userName && !"".equals(userName)){
			hql.append("a.tbUser.userName like ? and ");
			pars.add("%"+userName+"%");
		}
		// �ʺ�
		String userCard=request.getParameter("userCard");
		if(null!=userCard && !"".equals(userCard)){
			hql.append("a.tbUser.userCard like ? and ");
			pars.add("%"+userCard+"%");
		}
		// ��ѯ
		List userList=getCommonBo().findHQLPage(hql.toString(),pars);
		request.setAttribute("list_user", userList);
		// �˵����
		request.setAttribute("num", "2");
		// ����
		return SUCCESS;

	}
	public String del() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ɾ�����
		String delsId=request.getParameter("delsId");
		try {
			if(null==delsId||"".equals(delsId))
				return null;
			TbRoleUser tbRoleUser=(TbRoleUser)getCommonBo().get(TbRoleUser.class, new Long(delsId));
			getCommonBo().delete(tbRoleUser);
		} catch (Exception e) {
			request.setAttribute("alert", "ɾ��ʧ��!");
		}
		// ����
		return execute();
	}
}
