package com.laungee.proj.common.model;

import java.util.Date;

/**
 * TbAdlist entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbAdlist implements java.io.Serializable {

	// Fields

	private Long adId;
	private String nameCn;
	private String telephone;
	private String studentCode;
	private String email;
	private String address;
	private String qtalk;
	private String birthday;
	private String company;
	private String industryNow;
	private String industryThink;
	private String photo;
	private String memo;
	private String academy;
	private Long academyId;
	private String department;
	private Long departmentId;
	private String major;
	private Long majorId;
	private String clazz;
	private Long classId;
	private String yearJoin;
	private String yearOff;
	private String remark7;
	private String remark6;
	private String remark5;
	private String remark4;
	private String remark3;
	private String remark1;
	private String remark13;
	private String remark12;
	private String remark11;
	private String remark10;
	private String remark9;
	private String remark2;
	private String remark8;
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
	public TbAdlist() {
	}

	/** full constructor */
	public TbAdlist(String nameCn, String telephone, String studentCode,
			String email, String address, String qtalk, String birthday,
			String company, String industryNow, String industryThink,
			String photo, String memo, String academy, Long academyId,
			String department, Long departmentId, String major, Long majorId,
			String clazz, Long classId, String yearJoin, String yearOff,
			String remark7, String remark6, String remark5, String remark4,
			String remark3, String remark1, String remark13, String remark12,
			String remark11, String remark10, String remark9, String remark2,
			String remark8, Long updateUser, Date updateTime) {
		this.nameCn = nameCn;
		this.telephone = telephone;
		this.studentCode = studentCode;
		this.email = email;
		this.address = address;
		this.qtalk = qtalk;
		this.birthday = birthday;
		this.company = company;
		this.industryNow = industryNow;
		this.industryThink = industryThink;
		this.photo = photo;
		this.memo = memo;
		this.academy = academy;
		this.academyId = academyId;
		this.department = department;
		this.departmentId = departmentId;
		this.major = major;
		this.majorId = majorId;
		this.clazz = clazz;
		this.classId = classId;
		this.yearJoin = yearJoin;
		this.yearOff = yearOff;
		this.remark7 = remark7;
		this.remark6 = remark6;
		this.remark5 = remark5;
		this.remark4 = remark4;
		this.remark3 = remark3;
		this.remark1 = remark1;
		this.remark13 = remark13;
		this.remark12 = remark12;
		this.remark11 = remark11;
		this.remark10 = remark10;
		this.remark9 = remark9;
		this.remark2 = remark2;
		this.remark8 = remark8;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getAdId() {
		return this.adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQtalk() {
		return this.qtalk;
	}

	public void setQtalk(String qtalk) {
		this.qtalk = qtalk;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustryNow() {
		return this.industryNow;
	}

	public void setIndustryNow(String industryNow) {
		this.industryNow = industryNow;
	}

	public String getIndustryThink() {
		return this.industryThink;
	}

	public void setIndustryThink(String industryThink) {
		this.industryThink = industryThink;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Long getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(Long academyId) {
		this.academyId = academyId;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getClazz() {
		return this.clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
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

	public String getRemark7() {
		return this.remark7;
	}

	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}

	public String getRemark6() {
		return this.remark6;
	}

	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}

	public String getRemark5() {
		return this.remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getRemark4() {
		return this.remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark13() {
		return this.remark13;
	}

	public void setRemark13(String remark13) {
		this.remark13 = remark13;
	}

	public String getRemark12() {
		return this.remark12;
	}

	public void setRemark12(String remark12) {
		this.remark12 = remark12;
	}

	public String getRemark11() {
		return this.remark11;
	}

	public void setRemark11(String remark11) {
		this.remark11 = remark11;
	}

	public String getRemark10() {
		return this.remark10;
	}

	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}

	public String getRemark9() {
		return this.remark9;
	}

	public void setRemark9(String remark9) {
		this.remark9 = remark9;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark8() {
		return this.remark8;
	}

	public void setRemark8(String remark8) {
		this.remark8 = remark8;
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