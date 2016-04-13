package com.laungee.proj.common.model;

import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * TbSmsHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbSmsHistory implements java.io.Serializable {

	// Fields

	private Long historyId;
	private TbAlumni tbAlumni;
	private TbSmsGroup tbSmsGroup;
	private Date sendDate;
	private String sendContent;
	private String memo;
	private Long updateUser;
	private Date updateTime;
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

	// Constructors



	// Property accessors

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public TbSmsGroup getTbSmsGroup() {
		return tbSmsGroup;
	}

	public void setTbSmsGroup(TbSmsGroup tbSmsGroup) {
		this.tbSmsGroup = tbSmsGroup;
	}

	public Long getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}


	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendContent() {
		return this.sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
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

}