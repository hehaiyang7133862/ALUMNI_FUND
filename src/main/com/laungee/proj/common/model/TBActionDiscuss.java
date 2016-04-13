package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TBActionDiscuss implements java.io.Serializable {
	
	private Long discussId;
	private Long actionId;
	private TbAction tbAction;
	private String discussContent;
	private Long sendId;
	private TbUnAlumni sendUser;
	private Long receiveId;
	private TbUnAlumni receiveUser;
	private Long parentId;
	private Long floorNum;
	private Long numOrder;
	private Long hasReceieve;
	private Long disType;
	private Date updateTime;
	private Set children = new HashSet(0);
	
	public TBActionDiscuss() {
	}
	
	public TBActionDiscuss(Long discussId, Long actionId, TbAction tbAction,
			String discussContent, Long sendId, TbUnAlumni sendUser,
			Long receiveId, TbUnAlumni receiveUser, Long parentId,
			Long floorNum, Long numOrder, Long hasReceieve, Long disType,
			Date updateTime, Set children) {
		super();
		this.discussId = discussId;
		this.actionId = actionId;
		this.tbAction = tbAction;
		this.discussContent = discussContent;
		this.sendId = sendId;
		this.sendUser = sendUser;
		this.receiveId = receiveId;
		this.receiveUser = receiveUser;
		this.parentId = parentId;
		this.floorNum = floorNum;
		this.numOrder = numOrder;
		this.hasReceieve = hasReceieve;
		this.disType = disType;
		this.updateTime = updateTime;
		this.children = children;
	}

	public Long getDiscussId() {
		return discussId;
	}

	public void setDiscussId(Long discussId) {
		this.discussId = discussId;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public TbAction getTbAction() {
		return tbAction;
	}

	public void setTbAction(TbAction tbAction) {
		this.tbAction = tbAction;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public TbUnAlumni getSendUser() {
		return sendUser;
	}

	public void setSendUser(TbUnAlumni sendUser) {
		this.sendUser = sendUser;
	}

	public Long getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	public TbUnAlumni getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(TbUnAlumni receiveUser) {
		this.receiveUser = receiveUser;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Long floorNum) {
		this.floorNum = floorNum;
	}

	public Long getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getHasReceieve() {
		return hasReceieve;
	}

	public void setHasReceieve(Long hasReceieve) {
		this.hasReceieve = hasReceieve;
	}

	public Long getDisType() {
		return disType;
	}

	public void setDisType(Long disType) {
		this.disType = disType;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
	
}
