package com.laungee.proj.manage.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.laungee.proj.common.model.TbRoleUser;
import com.laungee.proj.common.model.TbUser;

public class UserUnListAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// ��ɫ��Ϣ
		String roleId=request.getParameter("roleId");
		if(null==roleId||"".equals(roleId))
			return null;
		TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class, new Long(roleId));
		request.setAttribute("bean_role", tbRole);
		// ������ѯ
		StringBuffer hql=new StringBuffer();
		List pars=this.getList();
		hql.append("select a from TbUser a where not exists (select 1 from TbRoleUser b where b.tbUser.userId=a.userId and b.tbRole.roleId="+roleId+") and ");
		Long userId=(Long)request.getSession().getAttribute(USER_ID);
		TbUser tbUser=(TbUser)getCommonBo().get(TbUser.class, userId);
		if(tbUser!=null){
			if(tbUser.getUserCard()!=null&&!tbUser.getUserCard().equals("xyadmin")){
				hql.append("a.userCard!='xyadmin' and ");
			}
		}
		// ����
		String userName=request.getParameter("userName");
		if(null!=userName && !userName.equals("")){
			hql.append("a.userName like ? and ");
			pars.add("%"+userName+"%");
		}
		// �ʺ�
		String userCard=request.getParameter("userCard");
		if(null!=userCard && !userCard.equals("")){
			hql.append("a.userCard like ? and ");
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
		try {

			// ��Ա
			String delsId=request.getParameter("delsId");
			if(null==delsId||"".equals(delsId))
				return null;
			//��ɾ�����û������˵���Ϣ
			String hql="delete from TbRoleUser a where a.tbUser.userId="+new Long(delsId);
			this.getCommonBo().executeHql(hql);
			//�ٽ�������Ϣ������û�
			// �û���Ϣ
			TbRoleUser tb=new TbRoleUser();
			// ��ɫ
			String roleId=request.getParameter("roleId");
			if(null==roleId||"".equals(roleId))
				return null;
			TbRole tbRole=(TbRole)getCommonBo().get(TbRole.class,new Long(roleId));
			tb.setTbRole(tbRole);
			TbUser tbUser=(TbUser)getCommonBo().get(TbUser.class, new Long(delsId));
			tb.setTbUser(tbUser);
			// ִ��
			getCommonBo().save(tb);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// ����
		return execute();
	}
}
