package com.laungee.proj.common.model;

import java.util.Date;

public class TbPars {
	private Long parId;
	private String parName;
	private String parValue;
	private String parType;
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
	
	public TbPars(){
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public String getParName() {
		return parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	public String getParValue() {
		return parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

	public String getParType() {
		return parType;
	}

	public void setParType(String parType) {
		this.parType = parType;
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
