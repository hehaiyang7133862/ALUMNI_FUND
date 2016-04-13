package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbEmailForm2 generated by MyEclipse Persistence Tools
 */

public class TbEmailForm2 implements java.io.Serializable {

	// Fields

	private Long formId;
	private String formName;
	private String subject;
	private String content;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private Set tbEmaillHistories = new HashSet(0);

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
	public TbEmailForm2() {
	}

	/** minimal constructor */
	public TbEmailForm2(Long formId) {
		this.formId = formId;
	}

	/** full constructor */
	public TbEmailForm2(Long formId, String formName, String subject,
			String content, String memo, Long updateUser, Date updateTime,
			Set tbEmaillHistories) {
		this.formId = formId;
		this.formName = formName;
		this.subject = subject;
		this.content = content;
		this.memo = memo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tbEmaillHistories = tbEmaillHistories;
	}

	// Property accessors

	public Long getFormId() {
		return this.formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
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

	public Set getTbEmaillHistories() {
		return this.tbEmaillHistories;
	}

	public void setTbEmaillHistories(Set tbEmaillHistories) {
		this.tbEmaillHistories = tbEmaillHistories;
	}

}