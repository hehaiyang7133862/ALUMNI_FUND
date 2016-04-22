package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityPic;

public class SpPicBeanAction extends BaseAction {
	// 商品图片详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品图片
		TbCommodityPic bean = null;
		// 商品图片ID
		String picId = request.getParameter("id");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
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