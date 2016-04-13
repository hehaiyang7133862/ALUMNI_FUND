package com.laungee.proj.common.model;

import java.util.Date;

/**
 * VwDonation generated by MyEclipse Persistence Tools
 */

public class VwDonation implements java.io.Serializable {

	// Fields

	private Long donationId;
	private Long alumniId;
	private Long typeFid;
	private String donationUser;
	private String donationBenefit;
	private Date donationDate;
	private String donationAddress;
	private String fundName;
	private String fundNum;
	private Date fundDate;
	private String isFund;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String donationCount;
	private String cashWay;
	private String nameCn;
	private String cashcount;
	private Long programId;
	// Constructors
	/** default constructor */
	public VwDonation() {
	}

	/** minimal constructor */
	public VwDonation(Long donationId) {
		this.donationId = donationId;
	}

	

	// Property accessors

	public Long getDonationId() {
		return this.donationId;
	}

	public void setDonationId(Long donationId) {
		this.donationId = donationId;
	}

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public Long getTypeFid() {
		return this.typeFid;
	}

	public void setTypeFid(Long typeFid) {
		this.typeFid = typeFid;
	}

	public String getDonationUser() {
		return this.donationUser;
	}

	public void setDonationUser(String donationUser) {
		this.donationUser = donationUser;
	}

	public String getDonationBenefit() {
		return this.donationBenefit;
	}

	public void setDonationBenefit(String donationBenefit) {
		this.donationBenefit = donationBenefit;
	}

	public Date getDonationDate() {
		return this.donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public String getDonationAddress() {
		return this.donationAddress;
	}

	public void setDonationAddress(String donationAddress) {
		this.donationAddress = donationAddress;
	}

	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundNum() {
		return this.fundNum;
	}

	public void setFundNum(String fundNum) {
		this.fundNum = fundNum;
	}

	public Date getFundDate() {
		return this.fundDate;
	}

	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}

	public String getIsFund() {
		return this.isFund;
	}

	public void setIsFund(String isFund) {
		this.isFund = isFund;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	

	public String getCashWay() {
		return this.cashWay;
	}

	public void setCashWay(String cashWay) {
		this.cashWay = cashWay;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getDonationCount() {
		return donationCount;
	}

	public void setDonationCount(String donationCount) {
		this.donationCount = donationCount;
	}

	public String getCashcount() {
		return cashcount;
	}

	public void setCashcount(String cashcount) {
		this.cashcount = cashcount;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	
}