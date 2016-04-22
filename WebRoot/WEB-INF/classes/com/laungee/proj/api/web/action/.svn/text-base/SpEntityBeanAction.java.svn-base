package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.VwCommodity;
import com.laungee.proj.common.model.VwCommodityBase;

public class SpEntityBeanAction extends BaseAction {
	// 商品详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 1、true时，排除详情信息罗列
		String exceptDetail = request.getParameter("exceptDetail");
		request.setAttribute("exceptDetail", exceptDetail);
		// 商品
		Object bean = null;
		// 商品ID
		String commId = request.getParameter("id");
		if(commId!=null && !"".equals(commId)){
			if(exceptDetail!=null && ("1".equals(exceptDetail) || "true".equals(exceptDetail))){
				try{
					bean = this.getCommonBo().get(VwCommodityBase.class, new Long(commId));
				}catch(Exception e){}
			}else{
				try{
					bean = this.getCommonBo().get(VwCommodity.class, new Long(commId));
				}catch(Exception e){}
			}
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