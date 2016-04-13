package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbUnimportorder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbUnimportorder implements java.io.Serializable {

	// Fields

	private Long unimportorderId;
	private Long alumniOrder;
	private Long successCount;
	private Long errorCount;
	private Long userId;
	private Date updateTime;
	private Long updateUser;
	private String fileName;
	private String fileUrl;
	private TbUser tbUser;
	private Set tbUnimportalumnis = new HashSet(0);
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
	public TbUnimportorder() {
	}

	/** minimal constructor */
	public TbUnimportorder(Long unimportorderId) {
		this.unimportorderId = unimportorderId;
	}

	/** full constructor */
	public TbUnimportorder(Long unimportorderId, Long alumniOrder,
			Long successCount, Long errorCount, Long userId, Date updateTime,
			Set tbUnimportalumnis) {
		this.unimportorderId = unimportorderId;
		this.alumniOrder = alumniOrder;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.userId = userId;
		this.updateTime = updateTime;
		this.tbUnimportalumnis = tbUnimportalumnis;
	}

	// Property accessors

	public Long getUnimportorderId() {
		return this.unimportorderId;
	}

	public void setUnimportorderId(Long unimportorderId) {
		this.unimportorderId = unimportorderId;
	}

	public Long getAlumniOrder() {
		return this.alumniOrder;
	}

	public void setAlumniOrder(Long alumniOrder) {
		this.alumniOrder = alumniOrder;
	}

	public Long getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}

	public Long getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set getTbUnimportalumnis() {
		return this.tbUnimportalumnis;
	}

	public void setTbUnimportalumnis(Set tbUnimportalumnis) {
		this.tbUnimportalumnis = tbUnimportalumnis;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TbUser getTbUser() {
		return tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
    
}