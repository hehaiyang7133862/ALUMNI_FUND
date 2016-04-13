package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbDuty entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbDuty implements java.io.Serializable {

	// Fields

	private Long dutyId;
	private Long alumniId;
	private TbAlumni tbAlumni;
	private String dutyName;
	private String organName;
	private Date startTime;
	private Date endTime;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String isMain;
	private String isNew;
	private Long isRepeat;
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

	public Long getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Long isRepeat) {
		this.isRepeat = isRepeat;
	}

	/** default constructor */
	public TbDuty() {
	}

	/** minimal constructor */
	public TbDuty(Long dutyId) {
		this.dutyId = dutyId;
	}

	// Property accessors
	public Long getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		if(tbAlumni==null){
			this.setAlumniId(null);
		}else{
			this.setAlumniId(tbAlumni.getAlumniId());
		}
		this.tbAlumni = tbAlumni;
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

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
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
}