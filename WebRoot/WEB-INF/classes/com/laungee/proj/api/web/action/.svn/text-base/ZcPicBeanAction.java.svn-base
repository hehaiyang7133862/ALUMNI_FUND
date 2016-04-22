package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcprojPic;

public class ZcPicBeanAction extends BaseAction {
	// 众筹项目图片详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 众筹项目图片
		TbZcprojPic bean = null;
		// 众筹项目图片ID
		String picId = request.getParameter("id");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
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