package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbSiteContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbSiteJzMsg implements java.io.Serializable {

	// Fields

	private Long msgId;
	private String msgCnt;
	private String msgFrom;
	private Date msgTime;
	private String publishFlag;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbSiteJzMsg() {
	}

	// Property accessors

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgCnt() {
		return msgCnt;
	}

	public void setMsgCnt(String msgCnt) {
		this.msgCnt = msgCnt;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
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

}