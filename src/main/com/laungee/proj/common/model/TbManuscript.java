package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbManuscript entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbManuscript implements java.io.Serializable {

	// Fields
	private Long manuscriptId;
	private TbPublication tbPublication;
	private String titleMain;
	private String titleSub;
	private String content;
	private String author;
	private String isAdopt;
	private Date printTime;
	private Long stateFid;
	private String memo;
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
	public TbManuscript() {
	}

	/** minimal constructor */
	public TbManuscript(Long manuscriptId) {
		this.manuscriptId = manuscriptId;
	}

	// Property accessors
	public Long getManuscriptId() {
		return this.manuscriptId;
	}

	public void setManuscriptId(Long manuscriptId) {
		this.manuscriptId = manuscriptId;
	}

	public TbPublication getTbPublication() {
		return tbPublication;
	}

	public void setTbPublication(TbPublication tbPublication) {
		this.tbPublication = tbPublication;
	}

	public String getTitleMain() {
		return this.titleMain;
	}

	public void setTitleMain(String titleMain) {
		this.titleMain = titleMain;
	}

	public String getTitleSub() {
		return this.titleSub;
	}

	public void setTitleSub(String titleSub) {
		this.titleSub = titleSub;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsAdopt() {
		return this.isAdopt;
	}

	public void setIsAdopt(String isAdopt) {
		this.isAdopt = isAdopt;
	}

	public Date getPrintTime() {
		return this.printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	public Long getStateFid() {
		return this.stateFid;
	}

	public void setStateFid(Long stateFid) {
		this.stateFid = stateFid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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