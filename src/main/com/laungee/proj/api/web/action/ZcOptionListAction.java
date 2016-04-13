package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class ZcOptionListAction extends BaseAction {
	// �ڳ���Ŀѡ���б�
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ѯ
		String hql = "from TbZcprojOption a where 1=1";
		List pars = this.getList();
		// �ڳ���ĿID
		Long projIdL = null;
		String projId = request.getParameter("proj");
		if(projId!=null && !"".equals(projId)){
			try{
				projIdL = new Long(projId);
			}catch(Exception e){}
		}
		if(projIdL!=null){
			hql += " and a.projId = ?";
			pars.add(projIdL);
			request.setAttribute("proj", projIdL);
		}else{
			hql += " and 1<>1";
		}
		// ��Ŀѡ������
		String name = request.getParameter("name");
		if(name!=null && !"".equals(name)){
			hql += " and a.optionName like ?";
			pars.add("%"+name+"%");
			request.setAttribute("name", name);
		}
		// ��Ŀѡ�����
		String code = request.getParameter("code");
		if(code!=null && !"".equals(code)){
			hql += " and a.optionCode like ?";
			pars.add("%"+code+"%");
			request.setAttribute("code", code);
		}
		// ��Ŀѡ���Ƿ������ʾ
		String publish = request.getParameter("publish");
		if(publish!=null && "1".equals(publish)){
			hql += " and a.optionPublish = ?";
			pars.add(publish);
			request.setAttribute("publish", publish);
		}else if(publish!=null && "0".equals(publish)){
			hql += " and (a.optionPublish is null or a.optionPublish = ?)";
			pars.add(publish);
			request.setAttribute("publish", publish);
		}
		hql += " order by a.optionOrder";
		// ִ�в�ѯ
		try{
			List beanList = this.getCommonBo().findHQL(hql,pars);
			request.setAttribute("beanList", beanList);
		}catch(Exception e){
			// ��������
			request.setAttribute("result", "error");
			// ����
			return ERROR;
		}
		// ����
		return SUCCESS;
	}
}