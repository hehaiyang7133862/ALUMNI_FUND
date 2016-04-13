package com.laungee.proj.common.model;

import java.util.Date;

public class TbPersonNotice {
	
	private Long noticeId;
	private String type;
	private Long unAlumniId;
	private Long optTableId;
	private String optTableName;
	private String optFlag;
	private String updateDate;
	private Date updateTime;
	private Long creationUser;
	private String creationTime;
	private String memo;
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
	
	public TbPersonNotice(){	
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUnAlumniId() {
		return unAlumniId;
	}

	public void setUnAlumniId(Long unAlumniId) {
		this.unAlumniId = unAlumniId;
	}

	public Long getOptTableId() {
		return optTableId;
	}

	public void setOptTableId(Long optTableId) {
		this.optTableId = optTableId;
	}

	public String getOptTableName() {
		return optTableName;
	}

	public void setOptTableName(String optTableName) {
		this.optTableName = optTableName;
	}

	public String getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(String optFlag) {
		this.optFlag = optFlag;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
