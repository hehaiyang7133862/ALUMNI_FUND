package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbProgram entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbProgram implements java.io.Serializable {

	// Fields

	private Long programId;
	private String programName;
	private String programDis;
	private String programMemo;
	private Long updateUser;
	private Date updateTime;
	private Set tbDonations = new HashSet(0);
	private Set tbDonationoutlays = new HashSet(0);
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
	public TbProgram() {
	}

	/** minimal constructor */
	public TbProgram(Long programId) {
		this.programId = programId;
	}

	/** full constructor */
	public TbProgram(Long programId, String programName, String programDis,
			String programMemo, Long updateUser, Date updateTime,
			Set tbDonations) {
		this.programId = programId;
		this.programName = programName;
		this.programDis = programDis;
		this.programMemo = programMemo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tbDonations = tbDonations;
	}

	// Property accessors

	public Long getProgramId() {
		return this.programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramDis() {
		return this.programDis;
	}

	public void setProgramDis(String programDis) {
		this.programDis = programDis;
	}

	public String getProgramMemo() {
		return this.programMemo;
	}

	public void setProgramMemo(String programMemo) {
		this.programMemo = programMemo;
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

	public Set getTbDonations() {
		return this.tbDonations;
	}

	public void setTbDonations(Set tbDonations) {
		this.tbDonations = tbDonations;
	}

	public Set getTbDonationoutlays() {
		return tbDonationoutlays;
	}

	public void setTbDonationoutlays(Set tbDonationoutlays) {
		this.tbDonationoutlays = tbDonationoutlays;
	}
    
}