package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbProPay entity.项目支出管理表
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbProPay implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880642229644886758L;
	private Long payId;
	private Long foundId;
	//基金项目
	private TbFoundation tbFoundation;
	private String foundType;
	//类型表
	private TbFields tbFields;
	private String payAmount;
	private String payDate;
	private String payAccount;
	//账户表
	private TbDonationAccout tbDonationAccout;
	private String jsdwObject;
	private String jsdwAccount;
	//接受单位表
	private TbAcceptCompany tbAcceptCompany;
	private String payMemo;
	private Long updateUser;
	private Date updateTime;
	private String creationTime;
	private Long creationUser;

	// Constructors

	/** default constructor */
	public TbProPay() {
	}

	/** full constructor */
	public TbProPay(Long foundId, String foundType, String payAmount,
			String payDate, String payAccount, String jsdwObject,
			String jsdwAccount, String payMemo, Long updateUser,
			Date updateTime, String creationTime, Long creationUser) {
		this.foundId = foundId;
		this.foundType = foundType;
		this.payAmount = payAmount;
		this.payDate = payDate;
		this.payAccount = payAccount;
		this.jsdwObject = jsdwObject;
		this.jsdwAccount = jsdwAccount;
		this.payMemo = payMemo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.creationTime = creationTime;
		this.creationUser = creationUser;
	}

	// Property accessors

	public Long getPayId() {
		return this.payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Long getFoundId() {
		return this.foundId;
	}

	public void setFoundId(Long foundId) {
		this.foundId = foundId;
	}

	public String getFoundType() {
		return this.foundType;
	}

	public void setFoundType(String foundType) {
		this.foundType = foundType;
	}

	public String getPayAmount() {
		if(payAmount==null||"".equals(payAmount)){
			payAmount="0";
		}
		return this.payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayDate() {
		return this.payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayAccount() {
		return this.payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getJsdwObject() {
		return this.jsdwObject;
	}

	public void setJsdwObject(String jsdwObject) {
		this.jsdwObject = jsdwObject;
	}

	public String getJsdwAccount() {
		return this.jsdwAccount;
	}

	public void setJsdwAccount(String jsdwAccount) {
		this.jsdwAccount = jsdwAccount;
	}

	public String getPayMemo() {
		return this.payMemo;
	}

	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public TbFields getTbFields() {
		return tbFields;
	}

	public void setTbFields(TbFields tbFields) {
		this.tbFields = tbFields;
	}

	public TbDonationAccout getTbDonationAccout() {
		return tbDonationAccout;
	}

	public void setTbDonationAccout(TbDonationAccout tbDonationAccout) {
		this.tbDonationAccout = tbDonationAccout;
	}

	public TbAcceptCompany getTbAcceptCompany() {
		return tbAcceptCompany;
	}

	public void setTbAcceptCompany(TbAcceptCompany tbAcceptCompany) {
		this.tbAcceptCompany = tbAcceptCompany;
	}

	public TbFoundation getTbFoundation() {
		return tbFoundation;
	}

	public void setTbFoundation(TbFoundation tbFoundation) {
		this.tbFoundation = tbFoundation;
	}

}