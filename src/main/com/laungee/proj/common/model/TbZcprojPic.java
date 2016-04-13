package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbZcprojPic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbZcprojPic implements java.io.Serializable {

	// Fields

	private Long picId;
	private Long projId;
	private String picPath;
	private String picAlt;
	private String numOrder;
	private String picPublish;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbZcprojPic() {
	}

	// Property accessors

	public Long getPicId() {
		return this.picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Long getProjId() {
		return this.projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPicAlt() {
		return this.picAlt;
	}

	public void setPicAlt(String picAlt) {
		this.picAlt = picAlt;
	}

	public String getNumOrder() {
		return this.numOrder;
	}

	public void setNumOrder(String numOrder) {
		this.numOrder = numOrder;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPicPublish() {
		return picPublish;
	}

	public void setPicPublish(String picPublish) {
		this.picPublish = picPublish;
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