package com.laungee.proj.api.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcprojType;

public class ZcProjListAction extends BaseAction {
	// 众筹项目列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 众筹项目距离截止时间显示天数节点
		String hqlLastDay = "select a.fieldValue from TbFields a,TbFields b where a.parentId=b.fieldId and b.code='ZCPROJ_END_TIME' and a.code='DAY'";
		String endLastViewDay = (String)this.getCommonBo().getHQLUnique(hqlLastDay);
		request.setAttribute("endLastViewDay", endLastViewDay);
		// 众筹项目距离截止时间显示时间节点
		String hqlLastHour = "select a.fieldValue from TbFields a,TbFields b where a.parentId=b.fieldId and b.code='ZCPROJ_END_TIME' and a.code='HOUR'";
		String endLastViewHour = (String)this.getCommonBo().getHQLUnique(hqlLastHour);
		request.setAttribute("endLastViewHour", endLastViewHour);
		// 构造查询
		String hql = "from VwZcprojBase a where 1=1";
		List pars = this.getList();
		// 项目分类
		String typeId = request.getParameter("type");
		if(typeId!=null && !"".equals(typeId) && !"1".equals(typeId)){
			TbZcprojType beanType = null;
			try{
				beanType = (TbZcprojType)this.getCommonBo().get(TbZcprojType.class, new Long(typeId));
			}catch(Exception e){}
			if(beanType==null){
				hql += " and 1<>1";
			}else{
				String typeIds = beanType.getTypeId().toString();
				String hqlChild = "select a.typeId from TbZcprojType a where a.parentId="+beanType.getTypeId();
				List listChild = this.getCommonBo().findHQL(hqlChild);
				while(listChild!=null && !listChild.isEmpty()){
					String ids = "";
					for(int j=0;j<listChild.size();j++){
						ids += (j>0?",":"")+listChild.get(j);
					}
					typeIds += ","+ids;
					hqlChild = "select a.typeId from TbZcprojType a where a.parentId in ("+ids+")";
					listChild = this.getCommonBo().findHQL(hqlChild);
				}
				hql += " and a.projType in ("+typeIds+")";
				request.setAttribute("type", beanType.getTypeId());
			}
		}
		// 项目上架状态
		String shelves = request.getParameter("shelves");
		if(shelves!=null && "1".equals(shelves)){
			hql += " and a.shelvesFlag = ?";
			pars.add(shelves);
			request.setAttribute("shelves", shelves);
		}else if(shelves!=null && "0".equals(shelves)){
			hql += " and (a.shelvesFlag is null or a.shelvesFlag = ?)";
			pars.add(shelves);
			request.setAttribute("shelves", shelves);
		}
		// 项目热门状态
		String hot = request.getParameter("hot");
		if(hot!=null && "1".equals(hot)){
			hql += " and a.hotFlag = ?";
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
			hql += " and (a.hotFlag is null or a.hotFlag = ?)";
			pars.add(hot);
			request.setAttribute("hot", hot);
		}
		// 项目状态 0、即将开始+众筹中 1、即将开始 2、众筹中 3、已结束
		String status = request.getParameter("status");
		if(status!=null && ("0".equals(status) || "1".equals(status) || "2".equals(status) || "3".equals(status))){
			if("0".equals(status)){
				hql += " and (a.projStatus = ? or a.projStatus = ?)";
				pars.add("1");
				pars.add("2");
			}else{
				hql += " and a.projStatus = ?";
				pars.add(status);
			}
			request.setAttribute("status", status);
		}
		// 项目完成情况
		String complete = request.getParameter("complete");
		if(complete!=null && ("0".equals(complete) || "1".equals(complete))){
			if("0".equals(complete)){
				hql += " and a.zcedPercent<100";
			}else{
				hql += " and a.zcedPercent>=100";
			}
			request.setAttribute("complete", complete);
		}
		// 项目完成后是否可以继续捐赠
		String completedJz = request.getParameter("completedJz");
		if(completedJz!=null && ("0".equals(completedJz) || "1".equals(completedJz))){
			if("1".equals(completedJz)){
				hql += " and a.completedJz=?";
				pars.add("1");
			}else{
				hql += " and (a.completedJz is null or a.completedJz<>?)";
				pars.add("1");
			}
			request.setAttribute("completedJz", completedJz);
		}
		// 项目结束后是否归为经典回顾
		String classicStatus = request.getParameter("classicStatus");
		if(classicStatus!=null && ("0".equals(classicStatus) || "1".equals(classicStatus))){
			if("1".equals(classicStatus)){
				hql += " and a.classicStatus=?";
				pars.add("1");
			}else{
				hql += " and (a.classicStatus is null or a.classicStatus<>?)";
				pars.add("1");
			}
			request.setAttribute("classicStatus", classicStatus);
		}
		// 项目众筹金额区间
		Float minAmountF = null;
		String minAmount = request.getParameter("minAmount");
		if(minAmount!=null && !"".equals(minAmount)){
			try{
				minAmountF = new Float(minAmount);
			}catch(Exception e){}
		}
		Float maxAmountF = null;
		String maxAmount = request.getParameter("maxAmount");
		if(maxAmount!=null && !"".equals(maxAmount)){
			try{
				maxAmountF = new Float(maxAmount);
			}catch(Exception e){}
		}
		if(minAmountF!=null){
			hql += " and ((a.optionOther='1' and a.minAmount>="+minAmountF+") or exists(select 1 from TbZcprojOption b where b.projId=a.projId and b.optionPublish='1' and b.optionAmount>="+minAmountF+"))";
			request.setAttribute("minAmount", minAmountF);
		}
		if(maxAmountF!=null){
			hql += " and ((a.optionOther='1' and a.minAmount<="+maxAmountF+") or exists(select 1 from TbZcprojOption b where b.projId=a.projId and b.optionPublish='1' and b.optionAmount<="+maxAmountF+"))";
			request.setAttribute("maxAmount", maxAmountF);
		}
		// 项目关键字
		String key = request.getParameter("key");
		if(key!=null && !"".equals(key)){
			hql += " and (a.projName like ? or a.projCode like ? or a.searchKey like ?)";
			pars.add("%"+key+"%");
			pars.add("%"+key+"%");
			pars.add("%"+key+"%");
			request.setAttribute("key", key);
		}
		// 项目名称
		String name = request.getParameter("name");
		if(name!=null && !"".equals(name)){
			hql += " and a.projName like ?";
			pars.add("%"+name+"%");
			request.setAttribute("name", name);
		}
		// 项目编码
		String code = request.getParameter("code");
		if(code!=null && !"".equals(code)){
			hql += " and a.projCode like ?";
			pars.add("%"+code+"%");
			request.setAttribute("code", code);
		}
		// 项目标签关键字
		String target = request.getParameter("target");
		if(target!=null && !"".equals(target)){
			hql += " and (";
			String[] targets = target.split(",");
			for(int i=0;i<targets.length;i++){
				if(i>0){
					hql += " or ";
				}
				hql += "','||a.searchKey||',' like ?";
				pars.add("%,"+targets[i]+",%");
			}
			hql += ")";
			request.setAttribute("target", target);
		}
		// 执行查询众筹项目总数
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
			hql += " order by a.shelvesTime "+orderType+",a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "begtime".equals(order)){
			// 按开始时间排序
			hql += " order by a.begTime "+orderType+",a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "endtime".equals(order)){
			// 按结束时间排序
			hql += " order by a.endTime "+orderType+",a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "targetamout".equals(order)){
			// 按目标金额排序
			hql += " order by a.targetAmout "+orderType+",a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "zcedpercent".equals(order)){
			// 按完成度排序
			hql += " order by a.zcedPercent "+orderType+",a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else{
			// 默认排序
			hql += " order by a.shelvesFlag desc,a.hotFlag desc,a.hotOrder asc,a.shelvesTime desc,a.projName asc";
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