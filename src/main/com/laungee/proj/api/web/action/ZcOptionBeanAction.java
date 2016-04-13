package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcprojOption;

public class ZcOptionBeanAction extends BaseAction {
	// 众筹项目选项详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 众筹项目选项
		TbZcprojOption bean = null;
		// 众筹项目选项ID
		String optionId = request.getParameter("id");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
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