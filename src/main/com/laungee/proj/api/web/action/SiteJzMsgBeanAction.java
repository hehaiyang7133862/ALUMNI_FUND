package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteJzMsg;

public class SiteJzMsgBeanAction extends BaseAction {
	
	// 众筹网捐赠寄语详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 捐赠寄语对象
		TbSiteJzMsg bean = null;
		// 捐赠寄语msgId
		String msgId = request.getParameter("id");
		if(msgId!=null && !"".equals(msgId)){
			try{
				bean = (TbSiteJzMsg)this.getCommonBo().get(TbSiteJzMsg.class, new Long(msgId));
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
