package com.laungee.proj.common.model;

import java.io.Serializable;
import java.util.Date;

public class TbRelationInfo implements Serializable {

	private Long relId;
	private String relName;
	private String relLeft;
	private String relRight;
	private String relLevel;
	private Long parentId;
	private Long numOrder;
	private Long numLevel;
	private String isLeaf;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private TbRelationInfo tbRelationInfo;
	private String relLevelLeft;
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
	
	public String getRelLevelLeft() {
		return relLevelLeft;
	}
	public void setRelLevelLeft(String relLevelLeft) {
		this.relLevelLeft = relLevelLeft;
	}
	public TbRelationInfo getTbRelationInfo() {
		return tbRelationInfo;
	}
	public void setTbRelationInfo(TbRelationInfo tbRelationInfo) {
		this.tbRelationInfo = tbRelationInfo;
	}
	public Long getRelId() {
		return relId;
	}
	public void setRelId(Long relId) {
		this.relId = relId;
	}
	public String getRelName() {
		return relName;
	}
	public void setRelName(String relName) {
		this.relName = relName;
	}
	public String getRelLeft() {
		return relLeft;
	}
	public void setRelLeft(String relLeft) {
		this.relLeft = relLeft;
	}
	public String getRelRight() {
		return relRight;
	}
	public void setRelRight(String relRight) {
		this.relRight = relRight;
	}
	public String getRelLevel() {
		return relLevel;
	}
	public void setRelLevel(String relLevel) {
		this.relLevel = relLevel;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}
	public Long getNumLevel() {
		return numLevel;
	}
	public void setNumLevel(Long numLevel) {
		this.numLevel = numLevel;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
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
	
}
