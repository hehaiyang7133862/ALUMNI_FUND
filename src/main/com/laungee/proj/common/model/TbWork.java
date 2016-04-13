package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbWork entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbWork implements java.io.Serializable {

	// Fields

	private Long workId;
	private TbAlumni tbAlumni;
	private String isMain;
	private String address;
	private String companyName;
	private String departmentName;
	private String dutyName;
	private String dutyAlias;
	private String dutyContent;
	private Long industryFid;
	private Date startTime;
	private Date endTime;
	private String knowUser;
	private String memo;
	private Long confidenceFid;
	private String sourceInfo;
	private Long updateUser;
	private Date updateTime;
	private String isNew;
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

	/** default constructor */
	public TbWork() {
	}

	/** minimal constructor */
	public TbWork(Long workId) {
		this.workId = workId;
	}

	// Property accessors

	public Long getWorkId() {
		return this.workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getDutyAlias() {
		return this.dutyAlias;
	}

	public void setDutyAlias(String dutyAlias) {
		this.dutyAlias = dutyAlias;
	}

	public String getDutyContent() {
		return this.dutyContent;
	}

	public void setDutyContent(String dutyContent) {
		this.dutyContent = dutyContent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getKnowUser() {
		return this.knowUser;
	}

	public void setKnowUser(String knowUser) {
		this.knowUser = knowUser;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getConfidenceFid() {
		return this.confidenceFid;
	}

	public void setConfidenceFid(Long confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public String getSourceInfo() {
		return this.sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public Long getIndustryFid() {
		return industryFid;
	}

	public void setIndustryFid(Long industryFid) {
		this.industryFid = industryFid;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
}