package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class SpPicListAction extends BaseAction {
	// 商品图片列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbCommodityPic a where 1=1";
		List pars = this.getList();
		// 商品ID
		Long commIdL = null;
		String commId = request.getParameter("comm");
		if(commId!=null && !"".equals(commId)){
			try{
				commIdL = new Long(commId);
			}catch(Exception e){}
		}
		if(commIdL!=null){
			hql += " and a.commId = ?";
			pars.add(commIdL);
			request.setAttribute("comm", commIdL);
		}else{
			hql += " and 1<>1";
		}
		// 商品图片排序码
		String code = request.getParameter("code");
		if(code!=null && !"".equals(code)){
			hql += " and a.numOrder like ?";
			pars.add(code+"%");
			request.setAttribute("code", code);
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