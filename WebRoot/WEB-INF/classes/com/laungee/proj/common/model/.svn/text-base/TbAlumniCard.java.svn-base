package com.laungee.proj.common.model;

import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;


/**
 * TbAlumniCardId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbAlumniCard implements java.io.Serializable {

	// Fields

	private Long cardId;
	private String cardName;
	private String cardType;
	private String cardUrl;
	private String memo;
	private Long alumniId;
	private TbAlumni tbAlumni;
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
	public TbAlumniCard() {
	}

	/** full constructor */
	public TbAlumniCard(Long cardId, String cardName, String cardType,
			String cardUrl, String memo, Long alumniId, Long updateUser,
			Date updateTime) {
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardUrl = cardUrl;
		this.memo = memo;
		this.alumniId = alumniId;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardUrl() {
		return this.cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
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

	public TbAlumni getTbAlumni() {
		tbAlumni=null;
		if(alumniId!=null&&!"".equals(alumniId)){
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			try {
				tbAlumni=(TbAlumni) biz.get(TbAlumni.class, alumniId);
			} catch (Exception e) {
			}
		}
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}
}