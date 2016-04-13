package com.laungee.proj.common.model;

/**
 * VwAlumniExport entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwAlumniExport implements java.io.Serializable {
	// Fields

	private Long alumniId;
	private String nameCn;
	private String typeCid;
	private String sexCid;
	private String mailFirst;
	private String fax;
	
	private String liveFid;
	private String gradeCode;
	private String gradeName;
	private String nameEn;
	private String birthFid;
	
	private String birthDay;
	private String isSend;
	private String telephoneFirst;
	private String telephoneSecond;
	private String messageOther;
	
	private String handsetFirst;
	private String handsetSecond;
	private String handsetThree;
	private String selfMemo;
	
	private String companyName;
	private String departmentName;
	private String dutyName;
	private String dutyAlias;
	
	private String chengwei;
	private String address;
	private String postcode;
	private String telphone;
	
	private String yearJoin;
	private String yearOff;
	private String educateFid;
	private String degreeFid;
	private String studentCode;
	private String academyName;
	private String department;
	private String majorName;
	private String className;

	// Constructors

	/** default constructor */
	public VwAlumniExport() {
	}

	/** minimal constructor */
	public VwAlumniExport(Long alumniId) {
		this.alumniId = alumniId;
	}

	// Property accessors

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getTypeCid() {
		return this.typeCid;
	}

	public void setTypeCid(String typeCid) {
		this.typeCid = typeCid;
	}

	public String getSexCid() {
		return this.sexCid;
	}

	public void setSexCid(String sexCid) {
		this.sexCid = sexCid;
	}

	public String getMailFirst() {
		return this.mailFirst;
	}

	public void setMailFirst(String mailFirst) {
		this.mailFirst = mailFirst;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLiveFid() {
		return this.liveFid;
	}

	public void setLiveFid(String liveFid) {
		this.liveFid = liveFid;
	}

	public String getGradeCode() {
		return this.gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getBirthFid() {
		return this.birthFid;
	}

	public void setBirthFid(String birthFid) {
		this.birthFid = birthFid;
	}

	public String getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getIsSend() {
		return this.isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getTelephoneFirst() {
		return this.telephoneFirst;
	}

	public void setTelephoneFirst(String telephoneFirst) {
		this.telephoneFirst = telephoneFirst;
	}

	public String getTelephoneSecond() {
		return this.telephoneSecond;
	}

	public void setTelephoneSecond(String telephoneSecond) {
		this.telephoneSecond = telephoneSecond;
	}

	public String getMessageOther() {
		return this.messageOther;
	}

	public void setMessageOther(String messageOther) {
		this.messageOther = messageOther;
	}

	public String getHandsetFirst() {
		return this.handsetFirst;
	}

	public void setHandsetFirst(String handsetFirst) {
		this.handsetFirst = handsetFirst;
	}

	public String getHandsetSecond() {
		return this.handsetSecond;
	}

	public void setHandsetSecond(String handsetSecond) {
		this.handsetSecond = handsetSecond;
	}

	public String getHandsetThree() {
		return this.handsetThree;
	}

	public void setHandsetThree(String handsetThree) {
		this.handsetThree = handsetThree;
	}

	public String getSelfMemo() {
		return this.selfMemo;
	}

	public void setSelfMemo(String selfMemo) {
		this.selfMemo = selfMemo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getDutyAlias() {
		return this.dutyAlias;
	}

	public void setDutyAlias(String dutyAlias) {
		this.dutyAlias = dutyAlias;
	}

	public String getChengwei() {
		return this.chengwei;
	}

	public void setChengwei(String chengwei) {
		this.chengwei = chengwei;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getYearJoin() {
		return this.yearJoin;
	}

	public void setYearJoin(String yearJoin) {
		this.yearJoin = yearJoin;
	}

	public String getYearOff() {
		return this.yearOff;
	}

	public void setYearOff(String yearOff) {
		this.yearOff = yearOff;
	}

	public String getEducateFid() {
		return this.educateFid;
	}

	public void setEducateFid(String educateFid) {
		this.educateFid = educateFid;
	}

	public String getDegreeFid() {
		return this.degreeFid;
	}

	public void setDegreeFid(String degreeFid) {
		this.degreeFid = degreeFid;
	}

	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getAcademyName() {
		return this.academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}