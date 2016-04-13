package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbSiteContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbSiteContent implements java.io.Serializable {

	// Fields

	private Long contentId;
	private Long itemId;
	private String contentLogo;
	private String contentTitle;
	private String contentIntro;
	private String contentType;
	private String linkUrl;
	private String contentCnt;
	private String publishFlag;
	private Date publishTime;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbSiteContent() {
	}

	// Property accessors

	public Long getContentId() {
		return this.contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getContentLogo() {
		return this.contentLogo;
	}

	public void setContentLogo(String contentLogo) {
		this.contentLogo = contentLogo;
	}

	public String getContentTitle() {
		return this.contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentIntro() {
		return this.contentIntro;
	}

	public void setContentIntro(String contentIntro) {
		this.contentIntro = contentIntro;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getContentCnt() {
		return this.contentCnt;
	}

	public void setContentCnt(String contentCnt) {
		this.contentCnt = contentCnt;
	}

	public String getPublishFlag() {
		return this.publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
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

}