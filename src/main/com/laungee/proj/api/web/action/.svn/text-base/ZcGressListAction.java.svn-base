package com.laungee.proj.api.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.util.DateUtil;

public class ZcGressListAction extends BaseAction {
	// 众筹项目进展列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbZcprojProgress a where 1=1";
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
		// 进展时间Beg
		Date begTimeDate = null;
		String begTime = request.getParameter("begTime");
		if(begTime!=null && !"".equals(begTime)){
			try{
				begTimeDate = DateUtil.toDate(begTime,"yyyy-MM-dd");
			}catch(Exception e){}
		}
		if(begTimeDate!=null){
			hql += " and a.gressTime >= ?";
			pars.add(begTimeDate);
			request.setAttribute("begTime", begTimeDate);
		}
		// 进展时间End
		Date endTimeDate = null;
		String endTime = request.getParameter("endTime");
		if(endTime!=null && !"".equals(endTime)){
			try{
				endTimeDate = DateUtil.toDate(endTime,"yyyy-MM-dd");
			}catch(Exception e){}
		}
		if(endTimeDate!=null){
			hql += " and a.gressTime <= ?";
			pars.add(endTimeDate);
			request.setAttribute("endTime", endTimeDate);
		}
		// 执行查询众筹项目进展总数
		Long count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql,pars);
		request.setAttribute("count", count);
		// 按进展时间排序
		String orderType = request.getParameter("orderType");
		if(orderType==null || !"asc".equals(orderType)){
			orderType = "desc";
		}
		request.setAttribute("orderType", orderType);
		// 默认排序
		hql += " order by a.gressTime "+orderType;
		// 分页标识
		String page = request.getParameter("page");
		if(page==null || !"1".equals(page)){
			page = "0";
		}
		if("1".equals(page)){
			// 分页
			request.setAttribute("page", page);
			// 每页条数
			Integer pageSizeInt = null;
			String pageSize = request.getParameter("pageSize");
			if(pageSize!=null && !"".equals(pageSize)){
				try{
					pageSizeInt = new Integer(pageSize);
				}catch(Exception e){}
			}
			if(pageSizeInt==null){
				pageSizeInt = 20;
			}
			request.setAttribute("pageSize", pageSizeInt);
			// 分页总数
			int pageCount = (int) Math.floor(count / pageSizeInt);
			if(count%pageSizeInt>0){
				pageCount += 1;
			}
			request.setAttribute("pageCount", pageCount);
			// 当前所在分页
			Integer pageNumInt = null;
			String pageNum = request.getParameter("pageNum");
			if(pageNum!=null && !"".equals(pageNum)){
				try{
					pageNumInt = new Integer(pageNum);
				}catch(Exception e){}
			}
			if(pageNumInt==null){
				pageNumInt = 1;
			}
			if(pageNumInt>pageCount){
				pageNumInt = pageCount;
			}
			request.setAttribute("pageNum", pageNumInt);
			// 执行查询
			List beanList = this.getCommonBo().findHQL(hql, pars, (pageNumInt - 1) * pageSizeInt, pageSizeInt);
			request.setAttribute("beanList", beanList);
		}else{
			// 不分页
			request.setAttribute("page", page);
			// 查询开始行
			Integer begInt = null;
			String begnum = request.getParameter("num");
			if(begnum!=null && !"".equals(begnum)){
				try{
					begInt = new Integer(begnum);
				}catch(Exception e){}
			}
			if(begInt==null){
				begInt = 0;
			}
			request.setAttribute("num", begInt);
			// 查询条数
			Integer sizeInt = null;
			String size = request.getParameter("size");
			if(size!=null && !"".equals(size)){
				try{
					sizeInt = new Integer(size);
				}catch(Exception e){}
			}
			if(sizeInt==null){
				sizeInt = 0;
			}
			request.setAttribute("size", sizeInt);
			// 执行查询
			List beanList = this.getCommonBo().findHQL(hql, pars, begInt, sizeInt);
			request.setAttribute("beanList", beanList);
		}
		// 返回
		return SUCCESS;
	}
}