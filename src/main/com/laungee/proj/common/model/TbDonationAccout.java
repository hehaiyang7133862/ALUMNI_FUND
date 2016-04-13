package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbDonationAccout entity.æË‘˘’Àªßπ‹¿Ì
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbDonationAccout implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String accountName;
	private String accountBank;
	private String accountNum;
	private String currency;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String creationTime;
	private Long creationUser;

	// Constructors

	/** default constructor */
	public TbDonationAccout() {
	}

	/** full constructor */
	public TbDonationAccout(String accountName, String accountBank,
			String accountNum, String currency, String memo, Long updateUser,
			Date updateTime, String creationTime, Long creationUser) {
		this.accountName = accountName;
		this.accountBank = accountBank;
		this.accountNum = accountNum;
		this.currency = currency;
		this.memo = memo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.creationTime = creationTime;
		this.creationUser = creationUser;
	}

	// Property accessors

	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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