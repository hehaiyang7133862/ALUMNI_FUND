package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TbZcprojOption entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbZcprojOption implements java.io.Serializable {

	// Fields

	private Long optionId;
	private Long projId;
	private String optionCode;
	private String optionName;
	private BigDecimal optionAmount;
	private String limitCount;
	private Long optionCount;
	private String unitName;
	private String optionPublish;
	private String optionOrder;
	private String optionMemo;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbZcprojOption() {
	}

	// Property accessors

	public Long getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getProjId() {
		return this.projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getOptionCode() {
		return this.optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public BigDecimal getOptionAmount() {
		return this.optionAmount;
	}

	public void setOptionAmount(BigDecimal optionAmount) {
		this.optionAmount = optionAmount;
	}

	public String getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}

	public Long getOptionCount() {
		return optionCount;
	}

	public void setOptionCount(Long optionCount) {
		this.optionCount = optionCount;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getOptionPublish() {
		return optionPublish;
	}

	public void setOptionPublish(String optionPublish) {
		this.optionPublish = optionPublish;
	}

	public String getOptionOrder() {
		return optionOrder;
	}

	public void setOptionOrder(String optionOrder) {
		this.optionOrder = optionOrder;
	}

	public String getOptionMemo() {
		return this.optionMemo;
	}

	public void setOptionMemo(String optionMemo) {
		this.optionMemo = optionMemo;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
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

}