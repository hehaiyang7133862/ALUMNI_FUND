package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbManuscriptAlumni entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbManuscriptAlumni implements java.io.Serializable {

	// Fields

	private Long relId;
	private TbAlumniSt tbAlumni;
	private TbManuscript tbManuscript;
	private Long updateUser;
	private Date updateTime;
	private String roleCid;
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

	// Constructors

	public Long getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Long isRepeat) {
		this.isRepeat = isRepeat;
	}

	/** default constructor */
	public TbManuscriptAlumni() {
	}

	/** minimal constructor */
	public TbManuscriptAlumni(Long relId) {
		this.relId = relId;
	}

	// Property accessors
	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public TbAlumniSt getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumniSt tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public TbManuscript getTbManuscript() {
		return tbManuscript;
	}

	public void setTbManuscript(TbManuscript tbManuscript) {
		this.tbManuscript = tbManuscript;
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

	public String getRoleCid() {
		return roleCid;
	}

	public void setRoleCid(String roleCid) {
		this.roleCid = roleCid;
	}
	
}