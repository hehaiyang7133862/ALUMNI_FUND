package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class ZcPicListAction extends BaseAction {
	// 众筹项目图片列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbZcprojPic a where 1=1";
		List pars = this.getList();
		// 众筹项目ID
		Long projIdL = null;
		String projId = request.getParameter("proj");
		if(projId!=null && !"".equals(projId)){
			try{
				projIdL = new Long(projId);
			}catch(Exception e){}
		}
		if(projIdL!=null){
			hql += " and a.projId = ?";
			pars.add(projIdL);
			request.setAttribute("proj", projIdL);
		}else{
			hql += " and 1<>1";
		}
		// 项目图片排序码
		String code = request.getParameter("code");
		if(code!=null && !"".equals(code)){
			hql += " and a.numOrder like ?";
			pars.add(code+"%");
			request.setAttribute("code", code);
		}
		// 项目图片是否对外显示
		String publish = request.getParameter("publish");
		if(publish!=null && "1".equals(publish)){
			hql += " and a.picPublish = ?";
			pars.add(publish);
			request.setAttribute("publish", publish);
		}else if(publish!=null && "0".equals(publish)){
			hql += " and (a.picPublish is null or a.picPublish = ?)";
			pars.add(publish);
			request.setAttribute("publish", publish);
		}
		hql += " order by a.numOrder";
		// 执行查询
		try{
			List beanList = this.getCommonBo().findHQL(hql,pars);
			request.setAttribute("beanList", beanList);
		}catch(Exception e){
			// 参数错误
			request.setAttribute("result", "error");
			// 返回
			return ERROR;
		}
		// 返回
		return SUCCESS;
	}
}