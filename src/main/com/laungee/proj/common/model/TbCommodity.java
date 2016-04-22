package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	// 商品名称
	private String commName;
	// 商品简介
	private String commIntro;
	// 商品详请
	private String commDetail;
	// 所属分类
	private Long commType;
	private TbCommodityType tbCommodityType;
	// 是否免运费 1不免运费，0免运费
	private String isShipping;
	// 运费
	private BigDecimal shippingFee;
	// 运费说明
	private String shippingMemo;
	// 是否捐赠 1捐赠，0不捐赠
	private String isDonation;
	// 捐赠金额
	private BigDecimal donationFee;
	// 捐赠说明
	private String donationMemo;
	// 是否上架 1已上架，0已下架
	private String isShelves;
	// 是否删除 1已删除，0未删除
	private String isDelete;
	// 是否热门
	private String isHot;
	// 热门排序
	private String hotOrder;
	// 备注
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	private String searchKey;
	private String detailTitle1;
	private String detailContent1;
	private String detailPublish1;
	private String detailTitle2;
	private String detailContent2;
	private String detailPublish2;
	private String detailTitle3;
	private String detailContent3;
	private String detailPublish3;
	private String detailTitle4;
	private String detailContent4;
	private String detailPublish4;
	private String detailTitle5;
	private String detailContent5;
	private String detailPublish5;
	private String detailTitle6;
	private String detailContent6;
	private String detailPublish6;
	private String detailTitle7;
	private String detailContent7;
	private String detailPublish7;
	private String detailTitle8;
	private String detailContent8;
	private String detailPublish8;
	private String detailTitle9;
	private String detailContent9;
	private String detailPublish9;
	private String detailTitle10;
	private String detailContent10;
	private String detailPublish10;
	private String commStyle;

	public TbCommodity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbCommodity(Long commId, String commName, String commIntro,
			String commDetail, Long commType, TbCommodityType tbCommodityType,
			String isShipping, BigDecimal shippingFee, String shippingMemo,
			String isDonation, BigDecimal donationFee, String donationMemo,
			String isShelves, String isDelete, String memo, Long creationUser,
			String creationTime, Long updateUser, Date updateTime, String isHot) {
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

	// 库存数量
	private Long stockNum;

	public Long getStockNum() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select sum(stockNum) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="
					+ this.commId + " group by commId";
			Object sum = biz.getHQLUnique(hql);
			if (null != sum) {
				stockNum = new Long(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockNum;
	}

	private String limitNum;

	public String getLimitNum() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select a.limitNum from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="
					+ this.commId;
			List lstLimitNum = new ArrayList();
			lstLimitNum = biz.findHQL(hql);

			boolean result = false;
			for (Object obj : lstLimitNum) {
				if (obj.toString().equals("0")) {
					result = true;
				}
			}
			if (result == true) {
				limitNum = "0";
			} else {
				limitNum = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}

		return limitNum;
	}

	// 成本价
	private BigDecimal maxCostFee;
	private BigDecimal minCostFee;
	// 出售单价
	private BigDecimal maxSaleFee;
	private BigDecimal minSaleFee;

	public BigDecimal getMaxCostFee() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select max(costFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="
					+ this.commId + " group by commId";
			Object sum = biz.getHQLUnique(hql);
			if (null != sum) {
				maxCostFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxCostFee;
	}

	public BigDecimal getMinCostFee() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select min(costFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="
					+ this.commId + " group by commId";
			Object sum = biz.getHQLUnique(hql);
			if (null != sum) {
				minCostFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minCostFee;
	}

	public BigDecimal getMaxSaleFee() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select max(saleFee) from TbCommodityDetail a where a.isDelete=0 and isShelves=1 and a.commId="
					+ this.commId + " group by commId";
			Object sum = biz.getHQLUnique(hql);
			if (null != sum) {
				maxSaleFee = new BigDecimal(sum.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxSaleFee;
	}

	public BigDecimal getMinSaleFee() {
		try {
			ICommonBiz biz = (ICommonBiz) SpringUtil.getBean("commonBiz");
			String hql = "select min(saleFee) from TbCommodityDetail a where a.isDelete=0 and a.commId="
					+ this.commId + " group by commId";
			Object sum = biz.getHQLUnique(hql);
			if (null != sum) {
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

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setDetailTitle1(String detailTitle1) {
		this.detailTitle1 = detailTitle1;
	}

	public String getDetailTitle1() {
		return detailTitle1;
	}

	public void setDetailContent1(String detailContent1) {
		this.detailContent1 = detailContent1;
	}

	public String getDetailContent1() {
		return detailContent1;
	}

	public void setDetailPublish1(String detailPublish1) {
		this.detailPublish1 = detailPublish1;
	}

	public String getDetailPublish1() {
		return detailPublish1;
	}

	public void setDetailTitle2(String detailTitle2) {
		this.detailTitle2 = detailTitle2;
	}

	public String getDetailTitle2() {
		return detailTitle2;
	}

	public void setDetailContent2(String detailContent2) {
		this.detailContent2 = detailContent2;
	}

	public String getDetailContent2() {
		return detailContent2;
	}

	public void setDetailPublish2(String detailPublish2) {
		this.detailPublish2 = detailPublish2;
	}

	public String getDetailPublish2() {
		return detailPublish2;
	}

	public void setDetailTitle3(String detailTitle3) {
		this.detailTitle3 = detailTitle3;
	}

	public String getDetailTitle3() {
		return detailTitle3;
	}

	public void setDetailContent3(String detailContent3) {
		this.detailContent3 = detailContent3;
	}

	public String getDetailContent3() {
		return detailContent3;
	}

	public void setDetailPublish3(String detailPublish3) {
		this.detailPublish3 = detailPublish3;
	}

	public String getDetailPublish3() {
		return detailPublish3;
	}

	public void setDetailTitle4(String detailTitle4) {
		this.detailTitle4 = detailTitle4;
	}

	public String getDetailTitle4() {
		return detailTitle4;
	}

	public void setDetailContent4(String detailContent4) {
		this.detailContent4 = detailContent4;
	}

	public String getDetailContent4() {
		return detailContent4;
	}

	public void setDetailPublish4(String detailPublish4) {
		this.detailPublish4 = detailPublish4;
	}

	public String getDetailPublish4() {
		return detailPublish4;
	}

	public void setDetailTitle5(String detailTitle5) {
		this.detailTitle5 = detailTitle5;
	}

	public String getDetailTitle5() {
		return detailTitle5;
	}

	public void setDetailContent5(String detailContent5) {
		this.detailContent5 = detailContent5;
	}

	public String getDetailContent5() {
		return detailContent5;
	}

	public void setDetailPublish5(String detailPublish5) {
		this.detailPublish5 = detailPublish5;
	}

	public String getDetailPublish5() {
		return detailPublish5;
	}

	public void setDetailTitle6(String detailTitle6) {
		this.detailTitle6 = detailTitle6;
	}

	public String getDetailTitle6() {
		return detailTitle6;
	}

	public void setDetailContent6(String detailContent6) {
		this.detailContent6 = detailContent6;
	}

	public String getDetailContent6() {
		return detailContent6;
	}

	public void setDetailPublish6(String detailPublish6) {
		this.detailPublish6 = detailPublish6;
	}

	public String getDetailPublish6() {
		return detailPublish6;
	}

	public void setDetailTitle7(String detailTitle7) {
		this.detailTitle7 = detailTitle7;
	}

	public String getDetailTitle7() {
		return detailTitle7;
	}

	public void setDetailContent7(String detailContent7) {
		this.detailContent7 = detailContent7;
	}

	public String getDetailContent7() {
		return detailContent7;
	}

	public void setDetailPublish7(String detailPublish7) {
		this.detailPublish7 = detailPublish7;
	}

	public String getDetailPublish7() {
		return detailPublish7;
	}

	public void setDetailTitle8(String detailTitle8) {
		this.detailTitle8 = detailTitle8;
	}

	public String getDetailTitle8() {
		return detailTitle8;
	}

	public void setDetailContent8(String detailContent8) {
		this.detailContent8 = detailContent8;
	}

	public String getDetailContent8() {
		return detailContent8;
	}

	public void setDetailPublish8(String detailPublish8) {
		this.detailPublish8 = detailPublish8;
	}

	public String getDetailPublish8() {
		return detailPublish8;
	}

	public void setDetailTitle9(String detailTitle9) {
		this.detailTitle9 = detailTitle9;
	}

	public String getDetailTitle9() {
		return detailTitle9;
	}

	public void setDetailContent9(String detailContent9) {
		this.detailContent9 = detailContent9;
	}

	public String getDetailContent9() {
		return detailContent9;
	}

	public void setDetailPublish9(String detailPublish9) {
		this.detailPublish9 = detailPublish9;
	}

	public String getDetailPublish9() {
		return detailPublish9;
	}

	public void setDetailTitle10(String detailTitle10) {
		this.detailTitle10 = detailTitle10;
	}

	public String getDetailTitle10() {
		return detailTitle10;
	}

	public void setDetailContent10(String detailContent10) {
		this.detailContent10 = detailContent10;
	}

	public String getDetailContent10() {
		return detailContent10;
	}

	public void setDetailPublish10(String detailPublish10) {
		this.detailPublish10 = detailPublish10;
	}

	public String getDetailPublish10() {
		return detailPublish10;
	}

	public void setCommStyle(String commStyle) {
		this.commStyle = commStyle;
	}

	public String getCommStyle() {
		return commStyle;
	}
}