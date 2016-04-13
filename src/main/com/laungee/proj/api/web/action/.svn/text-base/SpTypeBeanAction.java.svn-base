package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityType;

public class SpTypeBeanAction extends BaseAction {
	// 商品分类详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品分类
		TbCommodityType bean = null;
		// 商品分类ID
		String typeId = request.getParameter("id");
		if(typeId!=null && !"".equals(typeId)){
			try{
				bean = (TbCommodityType)this.getCommonBo().get(TbCommodityType.class, new Long(typeId));
			}catch(Exception e){}
		}
		if(bean==null){
			// 参数错误
			request.setAttribute("result", "error");
			// 返回
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// 加载标识（all、加载全部树形结构）
		String flag = request.getParameter("flag");
		if(flag==null || !"all".equals(flag)){
			flag = "single";
		}
		request.setAttribute("flag", flag);
		// 返回
		return SUCCESS;
	}
}