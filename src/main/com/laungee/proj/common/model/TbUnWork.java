package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbWork entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbUnWork implements java.io.Serializable {

	// Fields

	private Long workId;
	private TbUnAlumni tbUnAlumni;
	private String isMain;
	private String address;
	private String companyName;
	private String departmentName;
	private String dutyName;
	private String dutyAlias;
	private Long industryFid;
	private String startTime;
	private String endTime;
	private String knowUser;
	private String memo;
	private Long confidenceFid;
	private String sourceInfo;
	private Long updateUser;
	private Long updateTime;
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
	public TbUnWork() {
	}

	/** minimal constructor */
	public TbUnWork(Long workId) {
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

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
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

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Long getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public TbUnAlumni getTbUnAlumni() {
		return tbUnAlumni;
	}

	public void setTbUnAlumni(TbUnAlumni tbUnAlumni) {
		this.tbUnAlumni = tbUnAlumni;
	}

	public Long getIndustryFid() {
		return industryFid;
	}

	public void setIndustryFid(Long industryFid) {
		this.industryFid = industryFid;
	}
}