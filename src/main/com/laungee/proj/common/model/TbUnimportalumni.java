package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbUnimportalumni entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbUnimportalumni implements java.io.Serializable {

	// Fields

	private Long unimportalumniId;
	private TbUnimportorder tbUnimportorder;
	private Long alumniId;
	private String alumniName;
	private Long alumniOrder;
	private String unImportfiled;
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
	public TbUnimportalumni() {
	}

	/** minimal constructor */
	public TbUnimportalumni(Long unimportalumniId) {
		this.unimportalumniId = unimportalumniId;
	}

	/** full constructor */
	public TbUnimportalumni(Long unimportalumniId,
			TbUnimportorder tbUnimportorder, Long alumniId, String alumniName,
			Long alumniOrder, String unImportfiled) {
		this.unimportalumniId = unimportalumniId;
		this.tbUnimportorder = tbUnimportorder;
		this.alumniId = alumniId;
		this.alumniName = alumniName;
		this.alumniOrder = alumniOrder;
		this.unImportfiled = unImportfiled;
	}

	// Property accessors

	public Long getUnimportalumniId() {
		return this.unimportalumniId;
	}

	public void setUnimportalumniId(Long unimportalumniId) {
		this.unimportalumniId = unimportalumniId;
	}

	public TbUnimportorder getTbUnimportorder() {
		return this.tbUnimportorder;
	}

	public void setTbUnimportorder(TbUnimportorder tbUnimportorder) {
		this.tbUnimportorder = tbUnimportorder;
	}

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public String getAlumniName() {
		return this.alumniName;
	}

	public void setAlumniName(String alumniName) {
		this.alumniName = alumniName;
	}

	public Long getAlumniOrder() {
		return this.alumniOrder;
	}

	public void setAlumniOrder(Long alumniOrder) {
		this.alumniOrder = alumniOrder;
	}

	public String getUnImportfiled() {
		return this.unImportfiled;
	}

	public void setUnImportfiled(String unImportfiled) {
		this.unImportfiled = unImportfiled;
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