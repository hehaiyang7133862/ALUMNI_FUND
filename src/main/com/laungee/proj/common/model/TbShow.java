package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbShow generated by MyEclipse Persistence Tools
 */

public class TbShow implements java.io.Serializable {

	// Fields

	private Long showId;
	private TbCommunity tbCommunity;
	private String subject;
	private String content;
	private Date createDate;
	private Long updateUser;
	private Date updateTime;
	private String imageUrl;
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

	/** default constructor */
	public TbShow() {
	}

	/** minimal constructor */
	public TbShow(Long showId) {
		this.showId = showId;
	}

	/** full constructor */
	public TbShow(Long showId, TbCommunity tbCommunity, String subject,
			String content, Date createDate, Long updateUser, Date updateTime) {
		this.showId = showId;
		this.tbCommunity = tbCommunity;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getShowId() {
		return this.showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public TbCommunity getTbCommunity() {
		return this.tbCommunity;
	}

	public void setTbCommunity(TbCommunity tbCommunity) {
		this.tbCommunity = tbCommunity;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
}