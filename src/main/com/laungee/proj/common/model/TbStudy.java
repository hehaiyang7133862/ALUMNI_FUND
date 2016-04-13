package com.laungee.proj.common.model;

import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * TbStudy entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbStudy implements java.io.Serializable {

	// Fields

	private Long studyId;
	private TbAlumniSt tbAlumniSt;
	private String isMain;
	private String schoolName;
	private String startTime;
	private String endTime;
	private String yearJoin;
	private String yearOff;
	private String monthJoin;
	private String monthOff;
	private Long academyId;
	private TbAcademy tbAcademy;
	private String academyName;
	private Long departmentId;
	private TbAcademy tbDepartment;
	private String departmentName;
	private Long majorId;
	private TbAcademy tbMajor;
	private String majorName;
	private Long classId;
	private TbAcademy tbClass;
	private String className;
	private String studentCode;
	private String educationFid;
	private String degreeFid;
	private String address;
	private Long confidenceFid;
	private String sourceInfo;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String isNew;
	private String rxqdwMemo;
	private String studyType;
	private Long ssxy;
	private TbCommunity tbCommunity;
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

	public TbCommunity getTbCommunity() {
		tbCommunity=null;
		try {
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			if(ssxy!=null){
				tbCommunity=(TbCommunity)biz.get(TbCommunity.class, ssxy);
			}
		} catch (Exception e) {
		}
		return tbCommunity;
	}

	public Long getSsxy() {
		return ssxy;
	}

	public void setSsxy(Long ssxy) {
		this.ssxy = ssxy;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	/** default constructor */
	public TbStudy() {
	}

	/** minimal constructor */
	public TbStudy(Long studyId) {
		this.studyId = studyId;
	}

	// Property accessors

	public Long getStudyId() {
		return this.studyId;
	}

	public void setStudyId(Long studyId) {
		this.studyId = studyId;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(Long academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return this.academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getSourceInfo() {
		return this.sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
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

	public TbAlumniSt getTbAlumniSt() {
		return tbAlumniSt;
	}

	public void setTbAlumniSt(TbAlumniSt tbAlumniSt) {
		this.tbAlumniSt = tbAlumniSt;
	}
	
	public String getStartTime() {
		return getYearJoin();
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return getYearOff();
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMonthJoin() {
		return monthJoin;
	}

	public void setMonthJoin(String monthJoin) {
		this.monthJoin = monthJoin;
	}

	public String getMonthOff() {
		return monthOff;
	}

	public void setMonthOff(String monthOff) {
		this.monthOff = monthOff;
	}

	public String getEducationFid() {
		return educationFid;
	}

	public void setEducationFid(String educationFid) {
		this.educationFid = educationFid;
	}

	public String getDegreeFid() {
		return degreeFid;
	}

	public void setDegreeFid(String degreeFid) {
		this.degreeFid = degreeFid;
	}

	public Long getConfidenceFid() {
		return confidenceFid;
	}

	public void setConfidenceFid(Long confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getYearJoin() {
		return yearJoin;
	}

	public void setYearJoin(String yearJoin) {
		this.yearJoin = yearJoin;
	}

	public String getYearOff() {
		return yearOff;
	}

	public void setYearOff(String yearOff) {
		this.yearOff = yearOff;
	}

	public String getRxqdwMemo() {
		return rxqdwMemo;
	}

	public void setRxqdwMemo(String rxqdwMemo) {
		this.rxqdwMemo = rxqdwMemo;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}
	
}