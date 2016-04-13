package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbDonationor entity.�����˱�
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbDonationor implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2593142794890734094L;
	private Long memId;
	private Long alumniId;
	//����
	private TbAlumni tbAlumni;
	private Long workId;
	//��������
	private TbWork tbWork;
	private String nameCn;
	private String dutyName;
	private String company;
	private String industryFid;
	private String telephone;
	private String handset;
	private String mail;
	private String sexCid;
	private String isStu;
	private String gradeFid;
	//���Զ���
	private TbField tbField;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String creationTime;
	private Long creationUser;

	// Constructors

	/** default constructor */
	public TbDonationor() {
	}

	/** full constructor */
	public TbDonationor(String nameCn, String dutyName, String company,
			String industryFid, String telephone, String handset, String mail,
			String sexCid, String isStu, String gradeFid, String memo,
			Long updateUser, Date updateTime, String creationTime,
			Long creationUser) {
		this.nameCn = nameCn;
		this.dutyName = dutyName;
		this.company = company;
		this.industryFid = industryFid;
		this.telephone = telephone;
		this.handset = handset;
		this.mail = mail;
		this.sexCid = sexCid;
		this.isStu = isStu;
		this.gradeFid = gradeFid;
		this.memo = memo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.creationTime = creationTime;
		this.creationUser = creationUser;
	}

	// Property accessors

	public Long getMemId() {
		return this.memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustryFid() {
		return this.industryFid;
	}

	public void setIndustryFid(String industryFid) {
		this.industryFid = industryFid;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHandset() {
		return this.handset;
	}

	public void setHandset(String handset) {
		this.handset = handset;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSexCid() {
		return this.sexCid;
	}

	public void setSexCid(String sexCid) {
		this.sexCid = sexCid;
	}

	public String getIsStu() {
		return this.isStu;
	}

	public void setIsStu(String isStu) {
		this.isStu = isStu;
	}

	public String getGradeFid() {
		return this.gradeFid;
	}

	public void setGradeFid(String gradeFid) {
		this.gradeFid = gradeFid;
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

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public Long getAlumniId() {
		return alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public TbField getTbField() {
		return tbField;
	}

	public void setTbField(TbField tbField) {
		this.tbField = tbField;
	}

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public TbWork getTbWork() {
		return tbWork;
	}

	public void setTbWork(TbWork tbWork) {
		this.tbWork = tbWork;
	}

}