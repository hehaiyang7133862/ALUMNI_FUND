package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteContent;

public class SiteItemCntBeanAction extends BaseAction {
	
	// 众筹网栏目内容详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 栏目内容对象
		TbSiteContent bean = null;
		// 栏目内容对象contentId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			// 参数错误
			request.setAttribute("result", "error");
			// 返回
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// 返回
		return SUCCESS;
	}
}
