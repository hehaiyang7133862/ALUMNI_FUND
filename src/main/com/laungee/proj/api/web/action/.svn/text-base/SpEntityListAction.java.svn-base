package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityType;

public class SpEntityListAction extends BaseAction {
	// 商品列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from VwCommodityBase a where 1=1";
		List pars = this.getList();
		// 项目分类
		String typeId = request.getParameter("type");
		if(typeId!=null && !"".equals(typeId) && !"1".equals(typeId)){
			TbCommodityType beanType = null;
			try{
				beanType = (TbCommodityType)this.getCommonBo().get(TbCommodityType.class, new Long(typeId));
			}catch(Exception e){}
			if(beanType==null){
				hql += " and 1<>1";
			}else{
				String typeIds = beanType.getTypeId().toString();
				String hqlChild = "select a.typeId from TbCommodityType a where a.parentId="+beanType.getTypeId();
				List listChild = this.getCommonBo().findHQL(hqlChild);
				while(listChild!=null && !listChild.isEmpty()){
					String ids = "";
					for(int j=0;j<listChild.size();j++){
						ids += (j>0?",":"")+listChild.get(j);
					}
					typeIds += ","+ids;
					hqlChild = "select a.typeId from TbCommodityType a where a.parentId in ("+ids+")";
					listChild = this.getCommonBo().findHQL(hqlChild);
				}
				hql += " and a.commType in ("+typeIds+")";
				request.setAttribute("type", beanType.getTypeId());
			}
		}
		// 商品上架状态
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
		// 商品热门状态
		String hot = request.getParameter("hot");
		if(hot!=null && "1".equals(hot)){
			hql += " and a.isHot = ?";
			pars.add(hot);
			request.setAttribute("hot", hot);
			// 项目热门排序
			String hotOrder = request.getParameter("hotOrder");
			if(hotOrder!=null && !"".equals(hotOrder)){
				hql += " and a.hotOrder like ?";
				pars.add(hotOrder+"%");
				request.setAttribute("hotOrder", hotOrder);
			}
		}else if(hot!=null && "0".equals(hot)){
			hql += " and (a.isHot is null or a.isHot = ?)";
			pars.add(hot);
			request.setAttribute("hot", hot);
		}
		// 商品是否免运费
		String ship = request.getParameter("ship");
		if(ship!=null && "1".equals(ship)){
			hql += " and a.isShipping = ?";
			pars.add(ship);
			request.setAttribute("ship", ship);
		}else if(ship!=null && "0".equals(ship)){
			hql += " and (a.isShipping is null or a.isShipping = ?)";
			pars.add(ship);
			request.setAttribute("ship", ship);
		}
		// 商品名称
		String name = request.getParameter("name");
		if(name!=null && !"".equals(name)){
			hql += " and a.commName like ?";
			pars.add("%"+name+"%");
			request.setAttribute("name", name);
		}
		// 执行查询商品总数
		Long count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql,pars);
		request.setAttribute("count", count);
		// 排序
		String order = request.getParameter("order");
		String orderType = request.getParameter("orderType");
		if(orderType==null || !"asc".equals(orderType)){
			orderType = "desc";
		}
		if(order!=null && "createtime".equals(order)){
			// 按发布时间排序
			hql += " order by a.creationTime "+orderType+",a.isShelves desc,a.isHot desc,a.hotOrder asc,a.creationTime desc,a.commName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "stockcount".equals(order)){
			// 按库存排序
			hql += " order by a.stockCount "+orderType+",a.isShelves desc,a.isHot desc,a.hotOrder asc,a.creationTime desc,a.commName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "saledcount".equals(order)){
			// 按销量排序
			hql += " order by a.saledCount "+orderType+",a.isShelves desc,a.isHot desc,a.hotOrder asc,a.creationTime desc,a.commName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "salefee".equals(order)){
			// 按售价排序
			hql += " order by a.saleFeeMin "+orderType+",a.isShelves desc,a.isHot desc,a.hotOrder asc,a.creationTime desc,a.commName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else{
			// 默认排序
			hql += " order by a.isShelves desc,a.isHot desc,a.hotOrder asc,a.creationTime desc,a.commName asc";
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