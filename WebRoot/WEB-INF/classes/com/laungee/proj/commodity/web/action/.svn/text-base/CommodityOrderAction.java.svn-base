package com.laungee.proj.commodity.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityOrder;

public class CommodityOrderAction extends BaseAction {
	// 商品订单列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbCommodityOrder a where 1=1";
		List pars = this.getList();
		// 商品名称
		String commName = request.getParameter("commName");
		if(commName!=null && !"".equals(commName)){
			hql += " and (a.tbCommodity.commName like ? or a.commName like ?)";
			pars.add("%"+commName+"%");
			pars.add("%"+commName+"%");
		}
		// 商品订单号
		String orderNo = request.getParameter("orderNo");
		if(orderNo!=null && !"".equals(orderNo)){
			hql += " and a.orderNum like ?";
			pars.add("%"+orderNo+"%");
		}
		// 商品订单支付方式
		String orderType = request.getParameter("orderType");
		if(orderType!=null && !"".equals(orderType)){
			hql += " and a.orderType like ?";
			pars.add("%"+orderType+"%");
		}
		// 商品订单支付状态
		String orderStatus = request.getParameter("orderStatus");
		if(orderStatus!=null && "1".equals(orderStatus)){
			hql += " and a.orderStatus = ?";
			pars.add("1");
		}else if(orderStatus!=null && !"".equals(orderStatus)){
			hql += " and a.orderStatus <> ?";
			pars.add("1");
		}
		// 标记
		String markFlag = request.getParameter("markFlag");
		if(markFlag!=null && "1".equals(markFlag)){
			hql += " and a.mark is not null";
			String mark = request.getParameter("mark");
			if(mark!=null && !"".equals(mark)){
				hql += " and a.mark like ?";
				pars.add("%"+mark+"%");
			}
		}else if(markFlag!=null && !"".equals(markFlag)){
			hql += " and a.mark is null";
		}
		hql += " order by a.orderTime desc";
		// 执行查询
		List orderList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("orderList", orderList);
		// 返回
		return SUCCESS;
	}
	
	// 商品订单详情页面
	public String view() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "view";
	}
	
	// 商品订单标记页面
	public String mark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "mark";
	}
	
	// 众筹项目进展标记
	public String markUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// 标记信息
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// 实用运费
			BigDecimal shippingCurfeeD = null;
			String shippingCurfee = request.getParameter("shippingCurfee");
			if(shippingCurfee!=null && !"".equals(shippingCurfee)){
				try{
					shippingCurfeeD = new BigDecimal(shippingCurfee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setShippingCurfee(shippingCurfeeD);
			// 回执信息
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				return "mark";
			}
		}
		// 返回
		return "winSuccess";
	}

	// 商品订单删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return execute();
	}

}