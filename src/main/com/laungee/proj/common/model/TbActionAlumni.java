package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbActionAlumni generated by MyEclipse Persistence Tools
 */

public class TbActionAlumni implements java.io.Serializable {

	// Fields

	private Long relId;
	private TbAlumniSt tbAlumni;
	private TbAction tbAction;
	private Long communityId;
	private TbCommunity tbCommunity;
	private String roleCid;
	private Long updateUser;
	private Date updateTime;
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
	public TbActionAlumni() {
	}

	/** minimal constructor */
	public TbActionAlumni(Long relId) {
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

	public TbAction getTbAction() {
		return this.tbAction;
	}

	public void setTbAction(TbAction tbAction) {
		this.tbAction = tbAction;
	}

	public String getRoleCid() {
		return this.roleCid;
	}

	public void setRoleCid(String roleCid) {
		this.roleCid = roleCid;
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

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public TbCommunity getTbCommunity() {
		return tbCommunity;
	}

	public void setTbCommunity(TbCommunity tbCommunity) {
		this.tbCommunity = tbCommunity;
	}
	
}