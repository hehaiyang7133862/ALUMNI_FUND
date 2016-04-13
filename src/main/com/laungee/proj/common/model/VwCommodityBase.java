package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * TbCommodity entity. @author MyEclipse Persistence Tools
 */

public class VwCommodityBase implements java.io.Serializable {

	// Fields

	private Long commId;
	//��Ʒ����
	private String commName;
	//��Ʒ���
	private String commIntro;
	//��������
	private Long commType;
	//�Ƿ����˷� 1�����˷ѣ�0���˷�
	private String isShipping;
	//�˷�
	private BigDecimal shippingFee;
	//�˷�˵��
	private String shippingMemo;
	//�Ƿ���� 1������0������
	private String isDonation;
	//�������
	private BigDecimal donationFee;
	//����˵��
	private String donationMemo;
	//�Ƿ��ϼ� 1���ϼܣ�0���¼�
	private String isShelves;
	//�Ƿ�ɾ�� 1��ɾ����0δɾ��
	private String isDelete;
	//�Ƿ�����
	private String isHot;
	//��������
	private String hotOrder;
	//��ע
	private String memo;
	//�ۼۣ���ͣ�
	private BigDecimal saleFeeMin;
	//�ۼۣ���ߣ�
	private BigDecimal saleFeeMax;
	//���
	private Long stockCount;
	//����
	private Long saledCount;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	private Set tbCommodityPics=new HashSet(0);
	private Set tbCommodityLargePics=new HashSet(0);
	private Set tbCommodityNormalPics=new HashSet(0);
	private Set tbCommoditySmallPics=new HashSet(0);
	private Set tbCommodityParams=new HashSet(0);
	private Set tbCommodityDetails=new HashSet(0);
	private Set tbCommodityPubDetails=new HashSet(0);
	
	public VwCommodityBase() {
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

	public BigDecimal getSaleFeeMin() {
		return saleFeeMin;
	}

	public void setSaleFeeMin(BigDecimal saleFeeMin) {
		this.saleFeeMin = saleFeeMin;
	}

	public BigDecimal getSaleFeeMax() {
		return saleFeeMax;
	}

	public void setSaleFeeMax(BigDecimal saleFeeMax) {
		this.saleFeeMax = saleFeeMax;
	}

	public Long getStockCount() {
		return stockCount;
	}

	public void setStockCount(Long stockCount) {
		this.stockCount = stockCount;
	}

	public Long getSaledCount() {
		return saledCount;
	}

	public void setSaledCount(Long saledCount) {
		this.saledCount = saledCount;
	}

	public Set getTbCommodityPics() {
		return tbCommodityPics;
	}

	public void setTbCommodityPics(Set tbCommodityPics) {
		this.tbCommodityPics = tbCommodityPics;
	}

	public Set getTbCommodityLargePics() {
		return tbCommodityLargePics;
	}

	public void setTbCommodityLargePics(Set tbCommodityLargePics) {
		this.tbCommodityLargePics = tbCommodityLargePics;
	}

	public Set getTbCommodityNormalPics() {
		return tbCommodityNormalPics;
	}

	public void setTbCommodityNormalPics(Set tbCommodityNormalPics) {
		this.tbCommodityNormalPics = tbCommodityNormalPics;
	}

	public Set getTbCommoditySmallPics() {
		return tbCommoditySmallPics;
	}

	public void setTbCommoditySmallPics(Set tbCommoditySmallPics) {
		this.tbCommoditySmallPics = tbCommoditySmallPics;
	}

	public Set getTbCommodityParams() {
		return tbCommodityParams;
	}

	public void setTbCommodityParams(Set tbCommodityParams) {
		this.tbCommodityParams = tbCommodityParams;
	}

	public Set getTbCommodityDetails() {
		return tbCommodityDetails;
	}

	public void setTbCommodityDetails(Set tbCommodityDetails) {
		this.tbCommodityDetails = tbCommodityDetails;
	}

	public Set getTbCommodityPubDetails() {
		return tbCommodityPubDetails;
	}

	public void setTbCommodityPubDetails(Set tbCommodityPubDetails) {
		this.tbCommodityPubDetails = tbCommodityPubDetails;
	}

}