package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class VwOrder implements java.io.Serializable {

	// Fields

	private String orderType;
	private Long orderId;
	private String orderNum;
	private String orderProj;
	private Long unAlumniId;
	private BigDecimal orderAmount;
	private String orderStatus;
	private Date orderTime;
	private Date orderOkTime;
	private Object tbOrder;
	
	// Constructors

	public VwOrder() {
	}

	// Property accessors
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderProj() {
		return orderProj;
	}

	public void setOrderProj(String orderProj) {
		this.orderProj = orderProj;
	}

	public Long getUnAlumniId() {
		return unAlumniId;
	}

	public void setUnAlumniId(Long unAlumniId) {
		this.unAlumniId = unAlumniId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderOkTime() {
		return orderOkTime;
	}

	public void setOrderOkTime(Date orderOkTime) {
		this.orderOkTime = orderOkTime;
	}

	private boolean tbOrderFlag = false;

	private void setTbOrder() {
		try{
			if(!tbOrderFlag){
				tbOrderFlag = true;
				ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
				if("proj".equals(orderType)){
					tbOrder = biz.get(TbZcprojOrder.class, orderId);
				}else if("other".equals(orderType)){
					tbOrder = biz.get(TbZcotherOrder.class, orderId);
				}else if("comm".equals(orderType)){
					tbOrder = biz.get(TbCommodityOrder.class, orderId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object getTbOrder() {
		setTbOrder();
		return tbOrder;
	}
	
}