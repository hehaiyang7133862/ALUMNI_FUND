package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbStayaction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbStayaction implements java.io.Serializable {

	// Fields

	private Long stayId;
	private TbAlumni tbAlumni;
	private String startTime;
	private String endTime;
	private String departmentName;
	private String dutyName;
	private String remark;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String isMain;
	private String isNew;
	private String confidenceFid;
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

	public String getConfidenceFid() {
		return confidenceFid;
	}

	public void setConfidenceFid(String confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	/** default constructor */
	public TbStayaction() {
	}

	/** full constructor */
	public TbStayaction(TbAlumni tbAlumni, String startTime, String endTime,
			String departmentName, String dutyName, String remark, String memo,
			Long updateUser, Date updateTime) {
		this.tbAlumni = tbAlumni;
		this.startTime = startTime;
		this.endTime = endTime;
		this.departmentName = departmentName;
		this.dutyName = dutyName;
		this.remark = remark;
		this.memo = memo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getStayId() {
		return this.stayId;
	}

	public void setStayId(Long stayId) {
		this.stayId = stayId;
	}

	public TbAlumni getTbAlumni() {
		return this.tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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