package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbFile implements java.io.Serializable {

	// Fields

	private Long   fileId;
	private String fileName;
	private Long   fileSize;
	private String fileType;
	private String urlFile;
	private Long updateUser;
	private Date updateTime;
	private Set tbFileTab = new HashSet(0);
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
	public TbFile() {
	}

	/** minimal constructor */
	public TbFile(Long fileId) {
		this.fileId = fileId;
	}
	// Property accessors

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUrlFile() {
		return urlFile;
	}

	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
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

	public Set getTbFileTab() {
		return tbFileTab;
	}

	public void setTbFileTab(Set tbFileTab) {
		this.tbFileTab = tbFileTab;
	}
}