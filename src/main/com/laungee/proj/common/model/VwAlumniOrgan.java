package com.laungee.proj.common.model;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * VwAlumniOrgan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwAlumniOrgan implements java.io.Serializable {

	// Fields

	private Long relId;
	private TbAlumni tbAlumni;
	private TbUnAlumni tbUnAlumni;
	private Long communityId;
	private TbCommunity tbCommunity;
	private Long dutyFid;
	private String dutyName;
	private String gradeName;
	private String nameCn;
	private Long sexCid;
	private String sex;
	private String mail;
	private String handset;
	private String telephone;
	private String birthDay;
	private String studyAcademy;
	private String studyDepartment;
	private String studyMajor;
	private String studyClass;
	private String studyYear;
	private String companyName;
	private String comDutyName;
	private String memo;
	private Long comAlumniId;
	private Long zong;
	private TbField tbField;
	private Long isNewAdd;
	private Long isJihuo;
	private Long alumniLevel;

	// Constructors

	public Long getIsNewAdd() {
		return isNewAdd;
	}

	public void setIsNewAdd(Long isNewAdd) {
		this.isNewAdd = isNewAdd;
	}

	public Long getIsJihuo() {
		return isJihuo;
	}

	public void setIsJihuo(Long isJihuo) {
		this.isJihuo = isJihuo;
	}

	public TbField getTbField() {
		return tbField;
	}

	public void setTbField(TbField tbField) {
		this.tbField = tbField;
	}

	public Long getZong() {
		return zong;
	}

	public void setZong(Long zong) {
		this.zong = zong;
	}

	public Long getComAlumniId() {
		return comAlumniId;
	}

	public void setComAlumniId(Long comAlumniId) {
		this.comAlumniId = comAlumniId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHandset() {
		return this.handset;
	}

	public void setHandset(String handset) {
		this.handset = handset;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getStudyAcademy() {
		return this.studyAcademy;
	}

	public void setStudyAcademy(String studyAcademy) {
		this.studyAcademy = studyAcademy;
	}

	public String getStudyDepartment() {
		return this.studyDepartment;
	}

	public void setStudyDepartment(String studyDepartment) {
		this.studyDepartment = studyDepartment;
	}

	public String getStudyMajor() {
		return this.studyMajor;
	}

	public void setStudyMajor(String studyMajor) {
		this.studyMajor = studyMajor;
	}

	public String getStudyClass() {
		return this.studyClass;
	}

	public void setStudyClass(String studyClass) {
		this.studyClass = studyClass;
	}

	public String getStudyYear() {
		return this.studyYear;
	}

	public void setStudyYear(String studyYear) {
		this.studyYear = studyYear;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getComDutyName() {
		return this.comDutyName;
	}

	public void setComDutyName(String comDutyName) {
		this.comDutyName = comDutyName;
	}

	public TbAlumni getTbAlumni() {
		return tbAlumni;
	}

	public void setTbAlumni(TbAlumni tbAlumni) {
		this.tbAlumni = tbAlumni;
	}

	public Long getDutyFid() {
		return dutyFid;
	}

	public void setDutyFid(Long dutyFid) {
		this.dutyFid = dutyFid;
	}

	public Long getSexCid() {
		return sexCid;
	}

	public void setSexCid(Long sexCid) {
		this.sexCid = sexCid;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public TbCommunity getTbCommunity() {
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		tbCommunity=null;
		if(communityId!=null&&!"".equals(communityId)){
			try {
				tbCommunity=(TbCommunity)biz.get(TbCommunity.class, communityId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tbCommunity;
	}

	public void setTbCommunity(TbCommunity tbCommunity) {
		this.tbCommunity = tbCommunity;
	}

	public Long getAlumniLevel() {
		return alumniLevel;
	}

	public void setAlumniLevel(Long alumniLevel) {
		this.alumniLevel = alumniLevel;
	}

	public TbUnAlumni getTbUnAlumni() {
		return tbUnAlumni;
	}

	public void setTbUnAlumni(TbUnAlumni tbUnAlumni) {
		this.tbUnAlumni = tbUnAlumni;
	}

}