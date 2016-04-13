package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbRole;
import com.laungee.proj.common.model.TbUser;

public class UserListAction extends BaseAction {
	// 查调
	public String execute() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 查询
		StringBuffer hql=new StringBuffer();
		hql.append("from TbUser a where ");
		List pars=this.getList();
		Long userId=(Long)request.getSession().getAttribute(USER_ID);
		TbUser tbUser=(TbUser)getCommonBo().get(TbUser.class, userId);
		if(tbUser!=null){
			if(tbUser.getUserCard()!=null&&!tbUser.getUserCard().equals("xyadmin")){
				hql.append("a.userCard!='xyadmin' and ");
			}
		}
		// 帐号
		String userCard=request.getParameter("userCard");
		if(null!=userCard && !"".equals(userCard)){
			hql.append("a.userCard like ? and ");
			pars.add("%"+userCard+"%");
		}
		// 姓名
		String userName=request.getParameter("userName");
		if(null!=userName && !"".equals(userName)){
			hql.append("a.userName like ? and ");
			pars.add("%"+userName+"%");
		}
		// 执行
		List list=getCommonBo().findHQLPage(hql.toString(),pars);
		request.setAttribute("list_user", list);
		// 返回
		return SUCCESS;
	}
	// 删除
	public String del() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 删除ID
		String delsId=request.getParameter("delsId");
		try {
			if(null==delsId||"".equals(delsId))
				return null;
			TbUser bean=(TbUser)getCommonBo().get(TbUser.class, new Long(delsId));
			getCommonBo().delete(bean);
		} catch (Exception e) {
			request.setAttribute("alert", "不能删除该用户");
		}
		// 执行
		return execute();
	}
}
