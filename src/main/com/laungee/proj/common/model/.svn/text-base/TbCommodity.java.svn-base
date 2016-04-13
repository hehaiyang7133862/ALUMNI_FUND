package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * TbCommodity entity. @author MyEclipse Persistence Tools
 */

public class TbCommodity implements java.io.Serializable {

	// Fields

	private Long commId;
	//商品名称
	private String commName;
	//商品简介
	private String commIntro;
	//商品详请
	private String commDetail;
	//所属分类
	private Long commType;
	private TbCommodityType tbCommodityType;
	//是否免运费 1不免运费，0免运费
	private String isShipping;
	//运费
	private BigDecimal shippingFee;
	//运费说明
	private String shippingMemo;
	//是否捐赠 1捐赠，0不捐赠
	private String isDonation;
	//捐赠金额
	private BigDecimal donationFee;
	//捐赠说明
	private String donationMemo;
	//是否上架 1已上架，0已下架
	private String isShelves;
	//是否删除 1已删除，0未删除
	private String isDelete;
	//是否热门
	private String isHot;
	//热门排序
	private String hotOrder;
	//备注
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	
	public TbCommodity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TbCommodity(Long commId, String commName, String commIntro,
			String commDetail, Long commType, TbCommodityType tbCommodityType,
			String isShipping, BigDecimal shippingFee, String shippingMemo,
			String isDonation, BigDecimal donationFee, String donationMemo,
			String isShelves, String isDelete, String memo, Long creationUser,
			String creationTime, Long updateUser, Date updateTime,String isHot) {
		super();
		this.commId = commId;
		this.commName = commName;
		this.commIntro = commIntro;
		this.commDetail = commDetail;
		this.commType = commType;
		this.tbCommodityType = tbCommodityType;
		this.isShipping = isShipping;
		this.shippingFee = shippingFee;
		this.shippingMemo = shippingMemo;
		this.isDonation = isDonation;
		this.donationFee = donationFee;
		this.donationMemo = donationMemo;
		this.isShelves = isShelves;
		this.isDelete = isDelete;
		this.memo = memo;
		this.creationUser = creationUser;
		this.creationTime = creationTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.isHot = isHot;
	}


	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getHotOrder() {
		return hotOrder;
	}

	public void setHotOrder(String hotOrder) {
		this.hotOrder = hotOrder;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getCommIntro() {
		return commIntro;
	}

	public void setCommIntro(String commIntro) {
		this.commIntro = commIntro;
	}

	public String getCommDetail() {
		return commDetail;
	}

	public void setCommDetail(String commDetail) {
		this.commDetail = commDetail;
	}

	public Long getCommType() {
		return commType;
	}

	public void setCommType(Long commType) {
		this.commType = commType;
	}

	public String getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getShippingMemo() {
		return shippingMemo;
	}

	public void setShippingMemo(String shippingMemo) {
		this.shippingMemo = shippingMemo;
	}

	public String getIsDonation() {
		return isDonation;
	}

	public void setIsDonation(String isDonation) {
		this.isDonation = isDonation;
	}

	public BigDecimal getDonationFee() {
		return donationFee;
	}

	public void setDonationFee(BigDecimal donationFee) {
		this.donationFee = donationFee;
	}

	public String getDonationMemo() {
		return donationMemo;
	}

	public void setDonationMemo(String donationMemo) {
		this.donationMemo = donationMemo;
	}

	public String getIsShelves() {
		return isShelves;
	}

	public void setIsShelves(String isShelves) {
		this.isShelves = isShelves;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public TbCommodityType getTbCommodityType() {
		return tbCommodityType;
	}


	public void setTbCommodityType(TbCommodityType tbCommodityType) {
		this.tbCommodityType = tbCommodityType;
	}


	//库存数量
	private Long stockNum;

	public Long getStockNum() {
		try{
			ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
			String hql = "select sum(stockNum) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="+this.commId+" group by commId";
			Object sum = biz.getHQLUnique(hql);
			if(null!=sum){
				stockNum = new Long(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockNum;
	}

	//成本价
	private BigDecimal maxCostFee;
	private BigDecimal minCostFee;
	//出售单价
	private BigDecimal maxSaleFee;
	private BigDecimal minSaleFee;
	
	
	public BigDecimal getMaxCostFee() {
		try{
			ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
			String hql = "select max(costFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="+this.commId+" group by commId";
			Object sum = biz.getHQLUnique(hql);
			if(null!=sum){
				maxCostFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxCostFee;
	}
	public BigDecimal getMinCostFee() {
		try{
			ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
			String hql = "select min(costFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="+this.commId+" group by commId";
			Object sum = biz.getHQLUnique(hql);
			if(null!=sum){
				minCostFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minCostFee;
	}
	public BigDecimal getMaxSaleFee() {
		try{
			ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
			String hql = "select max(saleFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="+this.commId+" group by commId";
			Object sum = biz.getHQLUnique(hql);
			if(null!=sum){
				maxSaleFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxSaleFee;
	}
	public BigDecimal getMinSaleFee() {
		try{
			ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
			String hql = "select min(saleFee) from TbCommodityDetail a where a.isDelete=0 and a.commId="+this.commId+" group by commId";
			Object sum = biz.getHQLUnique(hql);
			if(null!=sum){
				minSaleFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minSaleFee;
	}
	
	
	
	
	public void setMinSaleFee(BigDecimal minSaleFee) {
		this.minSaleFee = minSaleFee;
	}
	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	public void setMaxCostFee(BigDecimal maxCostFee) {
		this.maxCostFee = maxCostFee;
	}
	public void setMaxSaleFee(BigDecimal maxSaleFee) {
		this.maxSaleFee = maxSaleFee;
	}
	public void setMinCostFee(BigDecimal minCostFee) {
		this.minCostFee = minCostFee;
	}

}