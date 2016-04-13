package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbDocation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbDocation implements java.io.Serializable {

	// Fields

	private Long docationId;
	private TbFoundationprogram tbFoundationprogram;
	private TbFoundationtype tbFoundationtype;
	private Date docationDate;
	private Date docationLaydate;
	private Long docationWay;
	private Long isOpen;
	private String docationUnit;
	private Long docationType;
	private String docationCount;
	private String docationDis;
	private String docationMsg;
	private String docationPepole;
	private String relationTel;
	private String relationMoile;
	private String relationMail;
	private String relationAddress;
	private String postCode;
	private String docationAddress;
	private String benefitUnit;
	private String receiptNumber;
	private String docationAcademy;
	private String docationDep;
	private String docationMajor;
	private String docationClass;
	private String startYear;
	private String leaveYear;
	private String docationMemo;
	private String imageUrl;
	private Long updateUser;
	private Date updateTime;
	private Long docationPepoleId;
	private Long unalumniId;
	private Long industryId;
	private Long pepoleType;
	private Long chengwei;
	private Long onlineStatus;
	private String onlineIp;
	private Long creationUser;
	private String creationTime;
	// Constructors

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

	/** default constructor */
	public TbDocation() {
	}

	/** minimal constructor */
	public TbDocation(Long docationId) {
		this.docationId = docationId;
	}

	/** full constructor */
	public TbDocation(Long docationId, TbFoundationprogram tbFoundationprogram,
			TbFoundationtype tbFoundationtype, Date docationDate,
			Date docationLaydate, Long docationWay, Long isOpen,
			String docationUnit, Long docationType, String docationCount,
			String docationDis, String docationMsg, String docationPepole,
			String relationTel, String relationMoile, String relationMail,
			String relationAddress, String postCode, String docationAddress,
			String benefitUnit, String receiptNumber, String docationAcademy,
			String docationDep, String docationMajor, String docationClass,
			String startYear, String leaveYear, String docationMemo,
			String imageUrl, Long updateUser, Date updateTime) {
		this.docationId = docationId;
		this.tbFoundationprogram = tbFoundationprogram;
		this.tbFoundationtype = tbFoundationtype;
		this.docationDate = docationDate;
		this.docationLaydate = docationLaydate;
		this.docationWay = docationWay;
		this.isOpen = isOpen;
		this.docationUnit = docationUnit;
		this.docationType = docationType;
		this.docationCount = docationCount;
		this.docationDis = docationDis;
		this.docationMsg = docationMsg;
		this.docationPepole = docationPepole;
		this.relationTel = relationTel;
		this.relationMoile = relationMoile;
		this.relationMail = relationMail;
		this.relationAddress = relationAddress;
		this.postCode = postCode;
		this.docationAddress = docationAddress;
		this.benefitUnit = benefitUnit;
		this.receiptNumber = receiptNumber;
		this.docationAcademy = docationAcademy;
		this.docationDep = docationDep;
		this.docationMajor = docationMajor;
		this.docationClass = docationClass;
		this.startYear = startYear;
		this.leaveYear = leaveYear;
		this.docationMemo = docationMemo;
		this.imageUrl = imageUrl;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getDocationId() {
		return this.docationId;
	}

	public void setDocationId(Long docationId) {
		this.docationId = docationId;
	}

	public TbFoundationprogram getTbFoundationprogram() {
		return this.tbFoundationprogram;
	}

	public void setTbFoundationprogram(TbFoundationprogram tbFoundationprogram) {
		this.tbFoundationprogram = tbFoundationprogram;
	}

	public TbFoundationtype getTbFoundationtype() {
		return this.tbFoundationtype;
	}

	public void setTbFoundationtype(TbFoundationtype tbFoundationtype) {
		this.tbFoundationtype = tbFoundationtype;
	}

	public Date getDocationDate() {
		return this.docationDate;
	}

	public void setDocationDate(Date docationDate) {
		this.docationDate = docationDate;
	}

	public Date getDocationLaydate() {
		return this.docationLaydate;
	}

	public void setDocationLaydate(Date docationLaydate) {
		this.docationLaydate = docationLaydate;
	}

	public Long getDocationWay() {
		return this.docationWay;
	}

	public void setDocationWay(Long docationWay) {
		this.docationWay = docationWay;
	}

	public Long getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Long isOpen) {
		this.isOpen = isOpen;
	}

	public String getDocationUnit() {
		return this.docationUnit;
	}

	public void setDocationUnit(String docationUnit) {
		this.docationUnit = docationUnit;
	}

	public Long getDocationType() {
		return this.docationType;
	}

	public void setDocationType(Long docationType) {
		this.docationType = docationType;
	}

	public String getDocationCount() {
		return this.docationCount;
	}

	public void setDocationCount(String docationCount) {
		this.docationCount = docationCount;
	}

	public String getDocationDis() {
		return this.docationDis;
	}

	public void setDocationDis(String docationDis) {
		this.docationDis = docationDis;
	}

	public String getDocationMsg() {
		return this.docationMsg;
	}

	public void setDocationMsg(String docationMsg) {
		this.docationMsg = docationMsg;
	}

	public String getDocationPepole() {
		return this.docationPepole;
	}

	public void setDocationPepole(String docationPepole) {
		this.docationPepole = docationPepole;
	}

	public String getRelationTel() {
		return this.relationTel;
	}

	public void setRelationTel(String relationTel) {
		this.relationTel = relationTel;
	}

	public String getRelationMoile() {
		return this.relationMoile;
	}

	public void setRelationMoile(String relationMoile) {
		this.relationMoile = relationMoile;
	}

	public String getRelationMail() {
		return this.relationMail;
	}

	public void setRelationMail(String relationMail) {
		this.relationMail = relationMail;
	}

	public String getRelationAddress() {
		return this.relationAddress;
	}

	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDocationAddress() {
		return this.docationAddress;
	}

	public void setDocationAddress(String docationAddress) {
		this.docationAddress = docationAddress;
	}

	public String getBenefitUnit() {
		return this.benefitUnit;
	}

	public void setBenefitUnit(String benefitUnit) {
		this.benefitUnit = benefitUnit;
	}

	public String getReceiptNumber() {
		return this.receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getDocationAcademy() {
		return this.docationAcademy;
	}

	public void setDocationAcademy(String docationAcademy) {
		this.docationAcademy = docationAcademy;
	}

	public String getDocationDep() {
		return this.docationDep;
	}

	public void setDocationDep(String docationDep) {
		this.docationDep = docationDep;
	}

	public String getDocationMajor() {
		return this.docationMajor;
	}

	public void setDocationMajor(String docationMajor) {
		this.docationMajor = docationMajor;
	}

	public String getDocationClass() {
		return this.docationClass;
	}

	public void setDocationClass(String docationClass) {
		this.docationClass = docationClass;
	}

	public String getStartYear() {
		return this.startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getLeaveYear() {
		return this.leaveYear;
	}

	public void setLeaveYear(String leaveYear) {
		this.leaveYear = leaveYear;
	}

	public String getDocationMemo() {
		return this.docationMemo;
	}

	public void setDocationMemo(String docationMemo) {
		this.docationMemo = docationMemo;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Long getDocationPepoleId() {
		return docationPepoleId;
	}

	public void setDocationPepoleId(Long docationPepoleId) {
		this.docationPepoleId = docationPepoleId;
	}

	public Long getUnalumniId() {
		return unalumniId;
	}

	public void setUnalumniId(Long unalumniId) {
		this.unalumniId = unalumniId;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Long getPepoleType() {
		return pepoleType;
	}

	public void setPepoleType(Long pepoleType) {
		this.pepoleType = pepoleType;
	}

	public Long getChengwei() {
		return chengwei;
	}

	public void setChengwei(Long chengwei) {
		this.chengwei = chengwei;
	}

	public Long getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Long onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getOnlineIp() {
		return onlineIp;
	}

	public void setOnlineIp(String onlineIp) {
		this.onlineIp = onlineIp;
	}
     
}