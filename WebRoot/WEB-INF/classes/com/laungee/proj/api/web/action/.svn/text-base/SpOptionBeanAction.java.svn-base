package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityDetail;

public class SpOptionBeanAction extends BaseAction {
	// 商品选项详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品选项
		TbCommodityDetail bean = null;
		// 商品选项ID
		String detailId = request.getParameter("id");
		if(detailId!=null && !"".equals(detailId)){
			try{
				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
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