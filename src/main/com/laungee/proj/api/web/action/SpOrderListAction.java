package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;

public class SpOrderListAction extends BaseAction {
	// ��Ʒ�����б�
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ѯ
		String hql = "from TbCommodityOrder a where 1=1";
		List pars = this.getList();
		// ��ƷID
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
		// У���Թ���ϢID
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
		// ����
		String donation = request.getParameter("donation");
		if(donation!=null && "1".equals(donation)){
			hql += " and a.donationFee is not null and a.donationFee>0";
			request.setAttribute("donation", donation);
		}else if(donation!=null && "0".equals(donation)){
			hql += " and (a.donationFee is null or a.donationFee<=0)";
			request.setAttribute("donation", donation);
		}
		// ����״̬
		String orderStatus = request.getParameter("orderStatus");
		if(orderStatus!=null && ("1".equals(orderStatus) || "0".equals(orderStatus))){
			hql += " and a.orderStatus=?";
			pars.add(orderStatus);
			request.setAttribute("orderStatus", orderStatus);
		}
		// ִ�в�ѯ��Ʒ��������
		Long count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql,pars);
		request.setAttribute("count", count);
		// ����
		String order = request.getParameter("order");
		String orderType = request.getParameter("orderType");
		if(orderType==null || !"asc".equals(orderType)){
			orderType = "desc";
		}
		if(order!=null && "orderamount".equals(order)){
			// �������������
			hql += " order by a.orderAmount "+orderType;
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else if(order!=null && "ordertime".equals(order)){
			// �������µ�ʱ������
			hql += " order by a.orderTime "+orderType;
			request.setAttribute("order", order);
			request.setAttribute("orderType", orderType);
		}else{
			// ����Ʒʱ������
			hql += " order by a.orderOkTime "+orderType;
			request.setAttribute("order", "ordertime");
			request.setAttribute("orderType", orderType);
		}
		// ��ҳ��ʶ
		String page = request.getParameter("page");
		if(page==null || !"1".equals(page)){
			page = "0";
		}
		if("1".equals(page)){
			// ��ҳ
			request.setAttribute("page", page);
			// ÿҳ����
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
			// ��ҳ����
			int pageCount = (int) Math.floor(count / pageSizeInt);
			if(count%pageSizeInt>0){
				pageCount += 1;
			}
			request.setAttribute("pageCount", pageCount);
			// ��ǰ���ڷ�ҳ
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
			// ִ�в�ѯ
			List beanList = this.getCommonBo().findHQL(hql, pars, (pageNumInt - 1) * pageSizeInt, pageSizeInt);
			request.setAttribute("beanList", beanList);
		}else{
			// ����ҳ
			request.setAttribute("page", page);
			// ��ѯ��ʼ��
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
			// ��ѯ����
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
			// ִ�в�ѯ
			List beanList = this.getCommonBo().findHQL(hql, pars, begInt, sizeInt);
			request.setAttribute("beanList", beanList);
		}
		// ����
		return SUCCESS;
	}
}