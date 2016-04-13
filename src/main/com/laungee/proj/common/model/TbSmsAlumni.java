package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbSmsAlumni entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbSmsAlumni implements java.io.Serializable {

	// Fields

	private Long relId;
	private TbAlumni tbAlumni;
	private TbSmsGroup tbSmsGroup;
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

	/** default constructor */
	public TbSmsAlumni() {
	}

	/** full constructor */
	public TbSmsAlumni(TbAlumni tbAlumni, TbSmsGroup tbSmsGroup,
			Long updateUser, Date updateTime) {
		this.tbAlumni = tbAlumni;
		this.tbSmsGroup = tbSmsGroup;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public TbAlumni getTbAlumni() {
		return this.tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public TbSmsGroup getTbSmsGroup() {
		return this.tbSmsGroup;
	}

	public void setTbSmsGroup(TbSmsGroup tbSmsGroup) {
		this.tbSmsGroup = tbSmsGroup;
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