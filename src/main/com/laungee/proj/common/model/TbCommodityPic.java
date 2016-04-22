package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbCommodityPic entity. @author MyEclipse Persistence Tools
 */

public class TbCommodityPic implements java.io.Serializable {

	// Fields

	private Long picId;
	private String picName;
	private String picExt;
	private String picPath;
	private String picAlt;
	private Long commId;
	private TbCommodity tbCommodity;
	private String memo;
	private String numOrder;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	private String picPublish;

	public TbCommodityPic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbCommodityPic(Long picId, String picName, String picExt,
			String picPath, String picAlt, Long commId,
			TbCommodity tbCommodity, String memo, String numOrder,
			Long creationUser, String creationTime, Long updateUser,
			Date updateTime) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picExt = picExt;
		this.picPath = picPath;
		this.picAlt = picAlt;
		this.commId = commId;
		this.tbCommodity = tbCommodity;
		this.memo = memo;
		this.numOrder = numOrder;
		this.creationUser = creationUser;
		this.creationTime = creationTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public TbCommodity getTbCommodity() {
		return tbCommodity;
	}

	public void setTbCommodity(TbCommodity tbCommodity) {
		this.tbCommodity = tbCommodity;
	}

	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicExt() {
		return picExt;
	}
	public void setPicExt(String picExt) {
		this.picExt = picExt;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPicAlt() {
		return picAlt;
	}
	public void setPicAlt(String picAlt) {
		this.picAlt = picAlt;
	}
	public Long getCommId() {
		return commId;
	}
	public void setCommId(Long commId) {
		this.commId = commId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(String numOrder) {
		this.numOrder = numOrder;
	}
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

	public void setPicPublish(String picPublish) {
		this.picPublish = picPublish;
	}

	public String getPicPublish() {
		return picPublish;
	}
}