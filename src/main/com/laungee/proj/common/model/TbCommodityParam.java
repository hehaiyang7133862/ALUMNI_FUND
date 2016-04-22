package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbCommodityParam entity. @author MyEclipse Persistence Tools
 */

public class TbCommodityParam implements java.io.Serializable {

	// Fields

	private Long paramId;
	private String paramName;
	private String paramValue;
	private Long commId;
	private TbCommodity tbCommodity;
	private String memo;
	private String numOrder;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	private String paramPublish;

	public TbCommodityParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbCommodityParam(Long paramId, String paramName, String paramValue,
			Long commId, TbCommodity tbCommodity, String memo, String numOrder,
			Long creationUser, String creationTime, Long updateUser,
			Date updateTime) {
		super();
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.commId = commId;
		this.tbCommodity = tbCommodity;
		this.memo = memo;
		this.numOrder = numOrder;
		this.creationUser = creationUser;
		this.creationTime = creationTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public Long getParamId() {
		return paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public TbCommodity getTbCommodity() {
		return tbCommodity;
	}

	public void setTbCommodity(TbCommodity tbCommodity) {
		this.tbCommodity = tbCommodity;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(String numOrder) {
		this.numOrder = numOrder;
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

	public void setParamPublish(String paramPublish) {
		this.paramPublish = paramPublish;
	}

	public String getParamPublish() {
		return paramPublish;
	}

}