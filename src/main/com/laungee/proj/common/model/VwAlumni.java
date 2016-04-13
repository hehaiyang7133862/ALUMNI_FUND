package com.laungee.proj.common.model;

import java.util.Date;

public class VwAlumni {
	private Long sid;
	// 基本信息
	private Long alumniId;
	private String nameCn;
	private Long nationFid;
	private Long countryFid;
	private Long industryNowFid;
	private Long industryThinkFid;
	private String telephoneFirst;
	private String selfFond;
	private String selfSpeciality;
	private Long gradeFid;
	private Long closeFid;
	// 学习经历
	private Date studyTime;
	private String schoolName;
	private TbAcademy tbAcademy;
	private TbAcademy tbDepartment;
	private TbAcademy tbMajor;
	private TbAcademy tbClass;
	private Long educationFid;
	// 工作经历
	private String companyName;
	private String departmentName;
	private String dutyName;
	private String dutyAlias;
	private String address;
	// 参与组织
	private String organName;
	private String dutyTag;
	// 捐赠信息
	private Long  donationCount;
	private Float donationMoney;
	private Float donationSum;
	// 交往信息
	private Long countTelephone;
	private Long countVisit;
	private Long countAciton;
	private Long countEmail;
	private Long countMail;
	private String currentFid;
	
	public VwAlumni(){
	}
	public VwAlumni(Long sid){
		this.sid=sid;
	}
	// 属性列表
	public Long getDonationCount() {
		return donationCount;
	}
	public void setDonationCount(Long donationCount) {
		this.donationCount = donationCount;
	}
	public Float getDonationMoney() {
		return donationMoney;
	}
	public void setDonationMoney(Float donationMoney) {
		this.donationMoney = donationMoney;
	}
	public Float getDonationSum() {
		return donationSum;
	}
	public void setDonationSum(Float donationSum) {
		this.donationSum = donationSum;
	}
	public Long getCountTelephone() {
		return countTelephone;
	}
	public void setCountTelephone(Long countTelephone) {
		this.countTelephone = countTelephone;
	}
	public Long getCountVisit() {
		return countVisit;
	}
	public void setCountVisit(Long countVisit) {
		this.countVisit = countVisit;
	}
	public Long getCountAciton() {
		return countAciton;
	}
	public void setCountAciton(Long countAciton) {
		this.countAciton = countAciton;
	}
	public Long getCountEmail() {
		return countEmail;
	}
	public void setCountEmail(Long countEmail) {
		this.countEmail = countEmail;
	}
	public Long getCountMail() {
		return countMail;
	}
	public void setCountMail(Long countMail) {
		this.countMail = countMail;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getAlumniId() {
		return alumniId;
	}
	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public Long getNationFid() {
		return nationFid;
	}
	public void setNationFid(Long nationFid) {
		this.nationFid = nationFid;
	}
	public Long getCountryFid() {
		return countryFid;
	}
	public void setCountryFid(Long countryFid) {
		this.countryFid = countryFid;
	}
	public Long getIndustryNowFid() {
		return industryNowFid;
	}
	public void setIndustryNowFid(Long industryNowFid) {
		this.industryNowFid = industryNowFid;
	}
	public Long getIndustryThinkFid() {
		return industryThinkFid;
	}
	public void setIndustryThinkFid(Long industryThinkFid) {
		this.industryThinkFid = industryThinkFid;
	}
	public String getTelephoneFirst() {
		return telephoneFirst;
	}
	public void setTelephoneFirst(String telephoneFirst) {
		this.telephoneFirst = telephoneFirst;
	}
	public String getSelfFond() {
		return selfFond;
	}
	public void setSelfFond(String selfFond) {
		this.selfFond = selfFond;
	}
	public String getSelfSpeciality() {
		return selfSpeciality;
	}
	public void setSelfSpeciality(String selfSpeciality) {
		this.selfSpeciality = selfSpeciality;
	}
	public Long getGradeFid() {
		return gradeFid;
	}
	public void setGradeFid(Long gradeFid) {
		this.gradeFid = gradeFid;
	}
	public Long getCloseFid() {
		return closeFid;
	}
	public void setCloseFid(Long closeFid) {
		this.closeFid = closeFid;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public TbAcademy getTbAcademy() {
		return tbAcademy;
	}
	public void setTbAcademy(TbAcademy tbAcademy) {
		this.tbAcademy = tbAcademy;
	}
	public TbAcademy getTbDepartment() {
		return tbDepartment;
	}
	public void setTbDepartment(TbAcademy tbDepartment) {
		this.tbDepartment = tbDepartment;
	}
	public TbAcademy getTbMajor() {
		return tbMajor;
	}
	public void setTbMajor(TbAcademy tbMajor) {
		this.tbMajor = tbMajor;
	}
	public TbAcademy getTbClass() {
		return tbClass;
	}
	public void setTbClass(TbAcademy tbClass) {
		this.tbClass = tbClass;
	}
	public Long getEducationFid() {
		return educationFid;
	}
	public void setEducationFid(Long educationFid) {
		this.educationFid = educationFid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getDutyAlias() {
		return dutyAlias;
	}
	public void setDutyAlias(String dutyAlias) {
		this.dutyAlias = dutyAlias;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getDutyTag() {
		return dutyTag;
	}
	public void setDutyTag(String dutyTag) {
		this.dutyTag = dutyTag;
	}
	public Date getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(Date studyTime) {
		this.studyTime = studyTime;
	}
	public String getCurrentFid() {
		return currentFid;
	}
	public void setCurrentFid(String currentFid) {
		this.currentFid = currentFid;
	}
}
