package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbFoundationtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbFoundationtype implements java.io.Serializable {

	// Fields
	private Long foundationtypeId;
	private String foundationtypeCode;
	private String foundationtypeName;
	private String foundationtypeDis;
	private String foundationtypeObject;
	private Long foundationtypeLevel;
	private String foundationtypeMemo;
	private String imageUrl;
	private Long updateUser;
	private Date updateTime;
	private Set tbFoundationprograms = new HashSet(0);
	private Set tbFoundationoutlaies = new HashSet(0);
	private Set tbDocations = new HashSet(0);
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
	public TbFoundationtype() {
	}

	/** minimal constructor */
	public TbFoundationtype(Long foundationtypeId) {
		this.foundationtypeId = foundationtypeId;
	}

	/** full constructor */
	public TbFoundationtype(Long foundationtypeId, String foundationtypeCode,
			String foundationtypeName, String foundationtypeDis,
			String foundationtypeObject, Long foundationtypeLevel,
			String foundationtypeMemo, String imageUrl, Long updateUser,
			Date updateTime, Set tbFoundationprograms,
			Set tbFoundationoutlaies, Set tbDocations) {
		this.foundationtypeId = foundationtypeId;
		this.foundationtypeCode = foundationtypeCode;
		this.foundationtypeName = foundationtypeName;
		this.foundationtypeDis = foundationtypeDis;
		this.foundationtypeObject = foundationtypeObject;
		this.foundationtypeLevel = foundationtypeLevel;
		this.foundationtypeMemo = foundationtypeMemo;
		this.imageUrl = imageUrl;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tbFoundationprograms = tbFoundationprograms;
		this.tbFoundationoutlaies = tbFoundationoutlaies;
		this.tbDocations = tbDocations;
	}

	// Property accessors

	public Long getFoundationtypeId() {
		return this.foundationtypeId;
	}

	public void setFoundationtypeId(Long foundationtypeId) {
		this.foundationtypeId = foundationtypeId;
	}

	public String getFoundationtypeCode() {
		return this.foundationtypeCode;
	}

	public void setFoundationtypeCode(String foundationtypeCode) {
		this.foundationtypeCode = foundationtypeCode;
	}

	public String getFoundationtypeName() {
		return this.foundationtypeName;
	}

	public void setFoundationtypeName(String foundationtypeName) {
		this.foundationtypeName = foundationtypeName;
	}

	public String getFoundationtypeDis() {
		return this.foundationtypeDis;
	}

	public void setFoundationtypeDis(String foundationtypeDis) {
		this.foundationtypeDis = foundationtypeDis;
	}

	public String getFoundationtypeObject() {
		return this.foundationtypeObject;
	}

	public void setFoundationtypeObject(String foundationtypeObject) {
		this.foundationtypeObject = foundationtypeObject;
	}

	public Long getFoundationtypeLevel() {
		return this.foundationtypeLevel;
	}

	public void setFoundationtypeLevel(Long foundationtypeLevel) {
		this.foundationtypeLevel = foundationtypeLevel;
	}

	public String getFoundationtypeMemo() {
		return this.foundationtypeMemo;
	}

	public void setFoundationtypeMemo(String foundationtypeMemo) {
		this.foundationtypeMemo = foundationtypeMemo;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Set getTbFoundationprograms() {
		return this.tbFoundationprograms;
	}

	public void setTbFoundationprograms(Set tbFoundationprograms) {
		this.tbFoundationprograms = tbFoundationprograms;
	}

	public Set getTbFoundationoutlaies() {
		return this.tbFoundationoutlaies;
	}

	public void setTbFoundationoutlaies(Set tbFoundationoutlaies) {
		this.tbFoundationoutlaies = tbFoundationoutlaies;
	}

	public Set getTbDocations() {
		return this.tbDocations;
	}

	public void setTbDocations(Set tbDocations) {
		this.tbDocations = tbDocations;
	}

}