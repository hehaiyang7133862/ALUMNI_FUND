package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbUnRelation implements java.io.Serializable {

	// Fields

	private Long relationId;
	private TbUnAlumni tbUnAlumni;
	private String isMain;
	private String relationName;
	private String telphone;
	private String fax;
	private String address;
	private String postcode;
	private Long typeFid;
	private String memo;
	private Long confidenceFid;
	private String sourceInfo;
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
	public TbUnRelation() {
	}

	/** minimal constructor */
	public TbUnRelation(Long relationId) {
		this.relationId = relationId;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Long getTypeFid() {
		return typeFid;
	}

	public void setTypeFid(Long typeFid) {
		this.typeFid = typeFid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getConfidenceFid() {
		return confidenceFid;
	}

	public void setConfidenceFid(Long confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public String getSourceInfo() {
		return sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
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

	public TbUnAlumni getTbUnAlumni() {
		return tbUnAlumni;
	}

	public void setTbUnAlumni(TbUnAlumni tbUnAlumni) {
		this.tbUnAlumni = tbUnAlumni;
	}
}