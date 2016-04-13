package com.laungee.proj.common.model;

import java.util.Date;

public class TbAdvise implements java.io.Serializable {

	private Long adviseId;
	private String adviseTitle;
	private String adviseContent;
	private String isTop;
	private String isView;
	private Date addTime;
	private Date loseTime;
	private Long numVisit;
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

	public TbAdvise() {
	}

	public Long getAdviseId() {
		return adviseId;
	}

	public void setAdviseId(Long adviseId) {
		this.adviseId = adviseId;
	}

	public String getAdviseTitle() {
		return adviseTitle;
	}

	public void setAdviseTitle(String adviseTitle) {
		this.adviseTitle = adviseTitle;
	}

	public String getAdviseContent() {
		return adviseContent;
	}

	public void setAdviseContent(String adviseContent) {
		this.adviseContent = adviseContent;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLoseTime() {
		return loseTime;
	}

	public void setLoseTime(Date loseTime) {
		this.loseTime = loseTime;
	}

	public Long getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(Long numVisit) {
		this.numVisit = numVisit;
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
}