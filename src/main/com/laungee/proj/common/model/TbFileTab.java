package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbResOther entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbFileTab implements java.io.Serializable {

	// Fields

	private Long relId;
	private TbFile tbFile;
	private String tableName;
	private Long tableId;
	private String typeCid;
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
	public TbFileTab() {
	}

	/** minimal constructor */
	public TbFileTab(Long relId) {
		this.relId = relId;
	}

	// Property accessors

	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public TbFile getTbFile() {
		return tbFile;
	}

	public void setTbFile(TbFile tbFile) {
		this.tbFile = tbFile;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getTableId() {
		return this.tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
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

	public String getTypeCid() {
		return typeCid;
	}

	public void setTypeCid(String typeCid) {
		this.typeCid = typeCid;
	}
}