package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbOrgan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbOrgan implements java.io.Serializable {

	// Fields

	 private Long organId;
	 private String organName;
     private String organDis;
     private Long organType;
     private String organCreate;
     private String organAddress;
     private Date startTime;
     private String responseName;
     private Long activeFid;
     private String memo;
     private Long updateUser;
     private Date updateTime;
     private String responseTel;
     private String responseEmail;
     private String relationTel;
     private String relationEmail;
     private String relationName;
	private Set tbOrganMembers = new HashSet(0);
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
	public TbOrgan() {
	}

	/** minimal constructor */
	public TbOrgan(Long organId) {
		this.organId = organId;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganDis() {
		return organDis;
	}

	public void setOrganDis(String organDis) {
		this.organDis = organDis;
	}

	public Long getOrganType() {
		return organType;
	}

	public void setOrganType(Long organType) {
		this.organType = organType;
	}

	public String getOrganCreate() {
		return organCreate;
	}

	public void setOrganCreate(String organCreate) {
		this.organCreate = organCreate;
	}

	public String getOrganAddress() {
		return organAddress;
	}

	public void setOrganAddress(String organAddress) {
		this.organAddress = organAddress;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public Long getActiveFid() {
		return activeFid;
	}

	public void setActiveFid(Long activeFid) {
		this.activeFid = activeFid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getResponseTel() {
		return responseTel;
	}

	public void setResponseTel(String responseTel) {
		this.responseTel = responseTel;
	}

	public String getResponseEmail() {
		return responseEmail;
	}

	public void setResponseEmail(String responseEmail) {
		this.responseEmail = responseEmail;
	}

	public String getRelationTel() {
		return relationTel;
	}

	public void setRelationTel(String relationTel) {
		this.relationTel = relationTel;
	}

	public String getRelationEmail() {
		return relationEmail;
	}

	public void setRelationEmail(String relationEmail) {
		this.relationEmail = relationEmail;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Set getTbOrganMembers() {
		return tbOrganMembers;
	}

	public void setTbOrganMembers(Set tbOrganMembers) {
		this.tbOrganMembers = tbOrganMembers;
	}

	

}