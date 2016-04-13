package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbCommlcomment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbCommlcomment implements java.io.Serializable {

	// Fields

	private Long commcommentId;
	private Long commphotodetilId;
	private String commlcommentMemo;
	private Long userid;
	private Date commcommentTime;
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
	public TbCommlcomment() {
	}

	/** full constructor */
	public TbCommlcomment(Long commphotodetilId, String commlcommentMemo,
			Long userid, Date commcommentTime, Long updateUser, Date updateTime) {
		this.commphotodetilId = commphotodetilId;
		this.commlcommentMemo = commlcommentMemo;
		this.userid = userid;
		this.commcommentTime = commcommentTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getCommcommentId() {
		return this.commcommentId;
	}

	public void setCommcommentId(Long commcommentId) {
		this.commcommentId = commcommentId;
	}

	public Long getCommphotodetilId() {
		return this.commphotodetilId;
	}

	public void setCommphotodetilId(Long commphotodetilId) {
		this.commphotodetilId = commphotodetilId;
	}

	public String getCommlcommentMemo() {
		return this.commlcommentMemo;
	}

	public void setCommlcommentMemo(String commlcommentMemo) {
		this.commlcommentMemo = commlcommentMemo;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getCommcommentTime() {
		return this.commcommentTime;
	}

	public void setCommcommentTime(Date commcommentTime) {
		this.commcommentTime = commcommentTime;
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