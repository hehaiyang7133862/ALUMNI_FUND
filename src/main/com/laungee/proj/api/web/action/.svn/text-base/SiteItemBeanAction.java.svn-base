package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteItem;

public class SiteItemBeanAction extends BaseAction {
	
	// 众筹网栏目详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 栏目对象
		TbSiteItem bean = null;
		// 栏目对象itemId
		String itemId = request.getParameter("id");
		if(itemId!=null && !"".equals(itemId)){
			try{
				bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
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
