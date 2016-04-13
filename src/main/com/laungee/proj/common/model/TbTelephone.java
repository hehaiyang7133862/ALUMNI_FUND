package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbTelephone generated by MyEclipse Persistence Tools
 */

public class TbTelephone implements java.io.Serializable {

	// Fields

	private Long telId;
	private Date telDate;
	private String telNum;
	private String subject;
	private String memo;
	private String isTrack;
	private Long updateUser;
	private Date updateTime;
	private String relation;
	private Long jtrFid;
	private Set tbTelephoneAlumnis = new HashSet(0);
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
	public TbTelephone() {
	}

	/** minimal constructor */
	public TbTelephone(Long telId) {
		this.telId = telId;
	}

	/** full constructor */
	public TbTelephone(Long telId, Date telDate, String telNum, String subject,
			String memo, String isTrack, Long updateUser, Date updateTime,
			Set tbTelephoneAlumnis) {
		this.telId = telId;
		this.telDate = telDate;
		this.telNum = telNum;
		this.subject = subject;
		this.memo = memo;
		this.isTrack = isTrack;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tbTelephoneAlumnis = tbTelephoneAlumnis;
	}

	// Property accessors

	public Long getTelId() {
		return this.telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
	}

	public Date getTelDate() {
		return this.telDate;
	}

	public void setTelDate(Date telDate) {
		this.telDate = telDate;
	}

	public String getTelNum() {
		return this.telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsTrack() {
		return this.isTrack;
	}

	public void setIsTrack(String isTrack) {
		this.isTrack = isTrack;
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

	public Set getTbTelephoneAlumnis() {
		return this.tbTelephoneAlumnis;
	}

	public void setTbTelephoneAlumnis(Set tbTelephoneAlumnis) {
		this.tbTelephoneAlumnis = tbTelephoneAlumnis;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getJtrFid() {
		return jtrFid;
	}

	public void setJtrFid(Long jtrFid) {
		this.jtrFid = jtrFid;
	}
    
}