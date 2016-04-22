package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class SpOptionListAction extends BaseAction {
	// 商品选项列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbCommodityDetail a where 1=1";
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
		// 商品选项名称
		String name = request.getParameter("name");
		if(name!=null && !"".equals(name)){
			hql += " and a.detailName like ?";
			pars.add("%"+name+"%");
			request.setAttribute("name", name);
		}
		// 项目选项是否上架
		String shelves = request.getParameter("shelves");
		if(shelves!=null && "1".equals(shelves)){
			hql += " and a.isShelves = ?";
			pars.add(shelves);
			request.setAttribute("shelves", shelves);
		}else if(shelves!=null && "0".equals(shelves)){
			hql += " and (a.isShelves is null or a.isShelves = ?)";
			pars.add(shelves);
			request.setAttribute("shelves", shelves);
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