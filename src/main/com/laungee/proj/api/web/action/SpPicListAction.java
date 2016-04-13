package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class SpPicListAction extends BaseAction {
	// ��ƷͼƬ�б�
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ѯ
		String hql = "from TbCommodityPic a where 1=1";
		List pars = this.getList();
		// ��ƷID
		Long commIdL = null;
		String commId = request.getParameter("comm");
		if(commId!=null && !"".equals(commId)){
			try{
				commIdL = new Long(commId);
			}catch(Exception e){}
		}
		if(commIdL!=null){
			hql += " and a.commId = ?";
			pars.add(commIdL);
			request.setAttribute("comm", commIdL);
		}else{
			hql += " and 1<>1";
		}
		// ��ƷͼƬ������
		String code = request.getParameter("code");
		if(code!=null && !"".equals(code)){
			hql += " and a.numOrder like ?";
			pars.add(code+"%");
			request.setAttribute("code", code);
		}
		hql += " order by a.numOrder";
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