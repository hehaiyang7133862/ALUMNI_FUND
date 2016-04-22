package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class SpOrderListAction extends BaseAction {
	// 商品订单列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbCommodityOrder a where 1=1";
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
			hql += " and a.commId=?";
			pars.add(commIdL);
			request.setAttribute("comm", commIdL);
		}
		// 校友自管信息ID
		Long alumniUnL = null;
		String alumniUn = request.getParameter("alumniUn");
		if(alumniUn!=null && !"".equals(alumniUn)){
			try{
				alumniUnL = new Long(alumniUn);
			}catch(Exception e){}
		}
		if(alumniUnL!=null){
			hql += " and a.unAlumniId=?";
			pars.add(alumniUnL);
			request.setAttribute("alumniUn", alumniUnL);
		}
		// 捐赠
		String donation = request.getParameter("donation");
		if(donation!=null && "1".equals(donation)){
			hql += " and a.donationFee is not null and a.donationFee>0";
			request.setAttribute("donation", donation);
		}else if(donation!=null && "0".equals(donation)){
			hql += " and (a.donationFee is null or a.donationFee<=0)";
			request.setAttribute("donation", donation);
		}
		// 订单状态
		String orderStatus = request.getParameter("orderStatus");
		if(orderStatus!=null && ("1".equals(orderStatus) || "0".equals(orderStatus))){
			hql += " and a.orderStatus=?";
			pars.add(orderStatus);
			request.setAttribute("orderStatus", orderStatus);
		}
		// 执行查询商品订单总数
		Long count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql,pars);
		request.setAttribute("count", count);
		// 排序
		String order = request.getParameter("order");
		String orderType = request.getParameter("orderType");
		if(orderType==null || !"asc".equals(orderType)){
			orderType = "desc";
		}
		if(order!=null && "orderamount".equals(order)){
			// 按订单金额排序
			hql += " order by a.orderAmount "+orderType;
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "ordertime".equals(order)){
			// 按捐赠下单时间排序
			hql += " order by a.orderTime "+orderType;
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else{
			// 按商品时间排序
			hql += " order by a.orderOkTime "+orderType;
			request.setAttribute("order", "ordertime");
			request.setAttribute("orderType", orderType);
		}
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