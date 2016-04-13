package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbRelation implements java.io.Serializable {

	// Fields

	private Long relationId;
	private TbAlumni tbAlumni;
	private String isMain;
	private String relationName;
	private String chengWei;
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
	public TbRelation() {
	}

	/** minimal constructor */
	public TbRelation(Long relationId) {
		this.relationId = relationId;
	}

	// Property accessors

	public Long getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public Long getTypeFid() {
		return typeFid;
	}

	public void setTypeFid(Long typeFid) {
		this.typeFid = typeFid;
	}

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public String getChengWei() {
		return chengWei;
	}

	public void setChengWei(String chengWei) {
		this.chengWei = chengWei;
	}
}