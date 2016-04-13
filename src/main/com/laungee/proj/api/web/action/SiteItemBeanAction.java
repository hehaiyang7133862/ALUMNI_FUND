package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteItem;

public class SiteItemBeanAction extends BaseAction {
	
	// �ڳ�����Ŀ����
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbSiteItem bean = null;
		// ��Ŀ����itemId
		String itemId = request.getParameter("id");
		if(itemId!=null && !"".equals(itemId)){
			try{
				bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
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
