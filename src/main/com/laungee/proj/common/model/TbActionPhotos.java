package com.laungee.proj.common.model;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class TbActionPhotos implements java.io.Serializable {
	private Long actionPhotosId;
	private Long actionId;
	private TbAction tbAction;
	private Long unAlumniId;
	private TbUnAlumni unAlumni;
	private String photosTitle;
	private Long visitNum;
	private Long photosType;
	private Long updateUser;
	private Date updateTime;
	private String memo;
	
	public TbActionPhotos() {
	}

	public TbActionPhotos(Long actionPhotosId, Long actionId,
			TbAction tbAction, Long unAlumniId, TbUnAlumni unAlumni,
			String photosTitle, Long visitNum, Long photosType,
			Long updateUser, Date updateTime, String memo) {
		super();
		this.actionPhotosId = actionPhotosId;
		this.actionId = actionId;
		this.tbAction = tbAction;
		this.unAlumniId = unAlumniId;
		this.unAlumni = unAlumni;
		this.photosTitle = photosTitle;
		this.visitNum = visitNum;
		this.photosType = photosType;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.memo = memo;
	}

	public Long getActionPhotosId() {
		return actionPhotosId;
	}

	public void setActionPhotosId(Long actionPhotosId) {
		this.actionPhotosId = actionPhotosId;
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

	public Long getUnAlumniId() {
		return unAlumniId;
	}

	public void setUnAlumniId(Long unAlumniId) {
		this.unAlumniId = unAlumniId;
	}

	public TbUnAlumni getUnAlumni() {
		return unAlumni;
	}

	public void setUnAlumni(TbUnAlumni unAlumni) {
		this.unAlumni = unAlumni;
	}

	public String getPhotosTitle() {
		return photosTitle;
	}

	public void setPhotosTitle(String photosTitle) {
		this.photosTitle = photosTitle;
	}

	public Long getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Long visitNum) {
		this.visitNum = visitNum;
	}

	public Long getPhotosType() {
		return photosType;
	}

	public void setPhotosType(Long photosType) {
		this.photosType = photosType;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
