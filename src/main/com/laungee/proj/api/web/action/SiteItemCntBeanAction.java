package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteContent;

public class SiteItemCntBeanAction extends BaseAction {
	
	// �ڳ�����Ŀ��������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ���ݶ���
		TbSiteContent bean = null;
		// ��Ŀ���ݶ���contentId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			// ��������
			request.setAttribute("result", "error");
			// ����
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// ����
		return SUCCESS;
	}
}
