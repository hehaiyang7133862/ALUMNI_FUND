package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcprojProgress;

public class ZcGressBeanAction extends BaseAction {
	// 众筹项目进展详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 众筹项目进展
		TbZcprojProgress bean = null;
		// 众筹项目进展ID
		String gressId = request.getParameter("id");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
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