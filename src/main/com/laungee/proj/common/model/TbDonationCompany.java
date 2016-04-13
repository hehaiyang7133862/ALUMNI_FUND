package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbDonationCompany entity.¾èÔùµ¥Î»±í
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbDonationCompany implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long comId;
	private String comName;
	private String comBusi;
	private String comType;
	private String industryFid;
	private String comAddress;
	private String responseName;
	private String responseTel;
	private String responseMail;
	private Long updateUser;
	private Date updateTime;
	private String creationTime;
	private Long creationUser;

	// Constructors

	/** default constructor */
	public TbDonationCompany() {
	}

	/** full constructor */
	public TbDonationCompany(String comName, String comBusi, String comType,
			String industryFid, String comAddress, String responseName,
			String responseTel, String responseMail, Long updateUser,
			Date updateTime, String creationTime, Long creationUser) {
		this.comName = comName;
		this.comBusi = comBusi;
		this.comType = comType;
		this.industryFid = industryFid;
		this.comAddress = comAddress;
		this.responseName = responseName;
		this.responseTel = responseTel;
		this.responseMail = responseMail;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.creationTime = creationTime;
		this.creationUser = creationUser;
	}

	// Property accessors

	public Long getComId() {
		return this.comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComBusi() {
		return this.comBusi;
	}

	public void setComBusi(String comBusi) {
		this.comBusi = comBusi;
	}

	public String getComType() {
		return this.comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public String getIndustryFid() {
		return this.industryFid;
	}

	public void setIndustryFid(String industryFid) {
		this.industryFid = industryFid;
	}

	public String getComAddress() {
		return this.comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getResponseName() {
		return this.responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public String getResponseTel() {
		return this.responseTel;
	}

	public void setResponseTel(String responseTel) {
		this.responseTel = responseTel;
	}

	public String getResponseMail() {
		return this.responseMail;
	}

	public void setResponseMail(String responseMail) {
		this.responseMail = responseMail;
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

}