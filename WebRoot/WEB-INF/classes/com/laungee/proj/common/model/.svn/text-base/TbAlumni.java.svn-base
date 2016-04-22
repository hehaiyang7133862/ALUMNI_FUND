package com.laungee.proj.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class TbAlumni implements java.io.Serializable {

	// Fields

	private Long alumniId;
	private String userCard;
	private Long openFid;
	private String userLogin;
	private String idStar;
	private String nameCn;
	private String nameEn;
	private String isLive;
	private String cardNum;
	private String sexCid;
	private Long nationFid;
	private Long countryFid;
	private String birthDay;
	private Long birthCountryFid;
	private Long birthProvinceFid;
	private Long birthCityFid;
	private String birthDetail;
	private Long liveCountry1Fid;
	private Long liveProvince1Fid;
	private Long liveCity1Fid;
	private String liveDetail1;
	private Long liveCountry2Fid;
	private Long liveProvince2Fid;
	private Long liveCity2Fid;
	private String liveDetail2;
	private Long workFid;
	private Long industryNowFid;
	private Long industryThinkFid;
	private String telephoneFirst;
	private String telephoneSecond;
	private String handsetFirst;
	private String handsetSecond;
	private String mailFirst;
	private String mailSecond;
	private String mailSchool;
	private String messageFirst;
	private String messageOther;
	private String fax;
	private String homepage;
	private String selfResume;
	private String selfFond;
	private String selfSpeciality;
	private String selfDiscover;
	private String selfHonour;
	private String selfBase;
	private String selfMemo;
	private String photoUrl;
	private String isPhotoShow;
	private String isPartJoin;
	private String partDuty;
	private Long confidenceFid;
	private String gradeFid;
	private Long closeFid;
	private String typeFid;
	private String studyYear;
	private String liveYear;
	private String studyacademy;
	private String studydepartment;
	private String studyMajor;
	private String studyClass;
	private String studyDegree;
	private String studyYear1;
	private String liveYear1;
	private String studyacademy1;
	private String studydepartment1;
	private String studyMajor1;
	private String studyClass1;
	private String studyDegree1;
	//学位
	private String studyXuewei1;
	//学生类型
	private String studentType1;
	private Long updateUser;
	private Date updateTime;
	private String currentFid;
	private String handsetThree;
	private String isAdd;
	private String isSend;
	private String sendNote;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String knowerName;
	private Long alumniLevel;
	private String alumniOrgan;
	private String alumniBlog;
	private Long isRepeat;
	private Long cardType;		//证件类型
	private String cardsNum;	//证件号码
	private String selfFamily;	//校友家属
	private Set tbStudys=new HashSet(0);
	private Set tbVisitAlumnis = new HashSet(0);
	private Set tbTelephoneAlumnis = new HashSet(0);
	private Set tbActionAlumnis = new HashSet(0);
    private Set tbEmailApps = new HashSet(0);
	private Set tbSeeks = new HashSet(0);
	private List alumniCards=new ArrayList();
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

	public List getAlumniCards() {
		if(alumniId!=null&&!"".equals(alumniId)){
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			try {
				alumniCards=biz.findHQL("from TbAlumniCard a where a.alumniId="+alumniId+" order by updateTime desc");
			} catch (Exception e) {
			}
		}
		return alumniCards;
	}

	/** default constructor */
	public TbAlumni() {
	}

	public String getLiveYear() {
		return liveYear;
	}

	public void setLiveYear(String liveYear) {
		this.liveYear = liveYear;
	}

	public String getStudyDegree() {
		return studyDegree;
	}

	public void setStudyDegree(String studyDegree) {
		this.studyDegree = studyDegree;
	}

	/** minimal constructor */
	public TbAlumni(Long alumniId) {
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

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getIsLive() {
		return this.isLive;
	}

	public void setIsLive(String isLive) {
		this.isLive = isLive;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getSexCid() {
		return this.sexCid;
	}

	public void setSexCid(String sexCid) {
		this.sexCid = sexCid;
	}

	public Long getNationFid() {
		return this.nationFid;
	}

	public void setNationFid(Long nationFid) {
		this.nationFid = nationFid;
	}

	public Long getCountryFid() {
		return this.countryFid;
	}

	public void setCountryFid(Long countryFid) {
		this.countryFid = countryFid;
	}

	public String getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Long getBirthCountryFid() {
		return this.birthCountryFid;
	}

	public void setBirthCountryFid(Long birthCountryFid) {
		this.birthCountryFid = birthCountryFid;
	}

	public Long getBirthProvinceFid() {
		return this.birthProvinceFid;
	}
	

	public void setBirthProvinceFid(Long birthProvinceFid) {
		this.birthProvinceFid = birthProvinceFid;
	}

	public Long getBirthCityFid() {
		return this.birthCityFid;
	}

	public void setBirthCityFid(Long birthCityFid) {
		this.birthCityFid = birthCityFid;
	}

	public String getBirthDetail() {
		return this.birthDetail;
	}

	public void setBirthDetail(String birthDetail) {
		this.birthDetail = birthDetail;
	}

	public Long getLiveCountry1Fid() {
		return this.liveCountry1Fid;
	}

	public void setLiveCountry1Fid(Long liveCountry1Fid) {
		this.liveCountry1Fid = liveCountry1Fid;
	}

	public Long getLiveProvince1Fid() {
		return this.liveProvince1Fid;
	}

	public void setLiveProvince1Fid(Long liveProvince1Fid) {
		this.liveProvince1Fid = liveProvince1Fid;
	}

	public Long getLiveCity1Fid() {
		return this.liveCity1Fid;
	}

	public void setLiveCity1Fid(Long liveCity1Fid) {
		this.liveCity1Fid = liveCity1Fid;
	}

	public String getLiveDetail1() {
		return this.liveDetail1;
	}

	public void setLiveDetail1(String liveDetail1) {
		this.liveDetail1 = liveDetail1;
	}

	public Long getLiveCountry2Fid() {
		return this.liveCountry2Fid;
	}

	public void setLiveCountry2Fid(Long liveCountry2Fid) {
		this.liveCountry2Fid = liveCountry2Fid;
	}

	public Long getLiveProvince2Fid() {
		return this.liveProvince2Fid;
	}

	public void setLiveProvince2Fid(Long liveProvince2Fid) {
		this.liveProvince2Fid = liveProvince2Fid;
	}

	public Long getLiveCity2Fid() {
		return this.liveCity2Fid;
	}

	public void setLiveCity2Fid(Long liveCity2Fid) {
		this.liveCity2Fid = liveCity2Fid;
	}

	public String getLiveDetail2() {
		return this.liveDetail2;
	}

	public void setLiveDetail2(String liveDetail2) {
		this.liveDetail2 = liveDetail2;
	}

	public Long getWorkFid() {
		return workFid;
	}

	public void setWorkFid(Long workFid) {
		this.workFid = workFid;
	}

	public Long getIndustryNowFid() {
		return this.industryNowFid;
	}

	public void setIndustryNowFid(Long industryNowFid) {
		this.industryNowFid = industryNowFid;
	}

	public Long getIndustryThinkFid() {
		return this.industryThinkFid;
	}

	public void setIndustryThinkFid(Long industryThinkFid) {
		this.industryThinkFid = industryThinkFid;
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

	public String getMailFirst() {
		return this.mailFirst;
	}

	public void setMailFirst(String mailFirst) {
		this.mailFirst = mailFirst;
	}

	public String getMailSecond() {
		return this.mailSecond;
	}

	public void setMailSecond(String mailSecond) {
		this.mailSecond = mailSecond;
	}

	public String getMessageFirst() {
		return this.messageFirst;
	}

	public void setMessageFirst(String messageFirst) {
		this.messageFirst = messageFirst;
	}

	public String getMessageOther() {
		return this.messageOther;
	}

	public void setMessageOther(String messageOther) {
		this.messageOther = messageOther;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getSelfResume() {
		return this.selfResume;
	}

	public void setSelfResume(String selfResume) {
		this.selfResume = selfResume;
	}

	public String getSelfFond() {
		return this.selfFond;
	}

	public void setSelfFond(String selfFond) {
		this.selfFond = selfFond;
	}

	public String getSelfSpeciality() {
		return this.selfSpeciality;
	}

	public void setSelfSpeciality(String selfSpeciality) {
		this.selfSpeciality = selfSpeciality;
	}

	public String getSelfDiscover() {
		return this.selfDiscover;
	}

	public void setSelfDiscover(String selfDiscover) {
		this.selfDiscover = selfDiscover;
	}

	public String getSelfHonour() {
		return this.selfHonour;
	}

	public void setSelfHonour(String selfHonour) {
		this.selfHonour = selfHonour;
	}

	public String getSelfBase() {
		return this.selfBase;
	}

	public void setSelfBase(String selfBase) {
		this.selfBase = selfBase;
	}

	public String getSelfMemo() {
		return this.selfMemo;
	}

	public void setSelfMemo(String selfMemo) {
		this.selfMemo = selfMemo;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getIsPhotoShow() {
		return this.isPhotoShow;
	}

	public void setIsPhotoShow(String isPhotoShow) {
		this.isPhotoShow = isPhotoShow;
	}

	public String getIsPartJoin() {
		return this.isPartJoin;
	}

	public void setIsPartJoin(String isPartJoin) {
		this.isPartJoin = isPartJoin;
	}

	public String getPartDuty() {
		return this.partDuty;
	}

	public void setPartDuty(String partDuty) {
		this.partDuty = partDuty;
	}

	public Long getConfidenceFid() {
		return this.confidenceFid;
	}

	public void setConfidenceFid(Long confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public String getGradeFid() {
		return this.gradeFid;
	}

	public void setGradeFid(String gradeFid) {
		this.gradeFid = gradeFid;
	}

	public Long getCloseFid() {
		return this.closeFid;
	}

	public void setCloseFid(Long closeFid) {
		this.closeFid = closeFid;
	}

	public String getTypeFid() {
		return this.typeFid;
	}

	public void setTypeFid(String typeFid) {
		this.typeFid = typeFid;
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

	public Set getTbVisitAlumnis() {
		return this.tbVisitAlumnis;
	}

	public void setTbVisitAlumnis(Set tbVisitAlumnis) {
		this.tbVisitAlumnis = tbVisitAlumnis;
	}

	public Set getTbTelephoneAlumnis() {
		return this.tbTelephoneAlumnis;
	}

	public void setTbTelephoneAlumnis(Set tbTelephoneAlumnis) {
		this.tbTelephoneAlumnis = tbTelephoneAlumnis;
	}

	public Set getTbActionAlumnis() {
		return this.tbActionAlumnis;
	}

	public void setTbActionAlumnis(Set tbActionAlumnis) {
		this.tbActionAlumnis = tbActionAlumnis;
	}

	public Long getOpenFid() {
		return openFid;
	}

	public void setOpenFid(Long openFid) {
		this.openFid = openFid;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getMailSchool() {
		return mailSchool;
	}

	public void setMailSchool(String mailSchool) {
		this.mailSchool = mailSchool;
	}
	public Set getTbEmailApps() {
		return tbEmailApps;
	}

	public void setTbEmailApps(Set tbEmailApps) {
		this.tbEmailApps = tbEmailApps;
	}

	public Set getTbSeeks() {
		return tbSeeks;
	}

	public void setTbSeeks(Set tbSeeks) {
		this.tbSeeks = tbSeeks;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getIdStar() {
		return idStar;
	}

	public void setIdStar(String idStar) {
		this.idStar = idStar;
	}

	public String getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(String studyYear) {
		this.studyYear = studyYear;
	}

	public String getStudyacademy() {
		return studyacademy;
	}

	public void setStudyacademy(String studyacademy) {
		this.studyacademy = studyacademy;
	}

	public String getStudydepartment() {
		return studydepartment;
	}

	public void setStudydepartment(String studydepartment) {
		this.studydepartment = studydepartment;
	}

	public String getStudyMajor() {
		return studyMajor;
	}

	public void setStudyMajor(String studyMajor) {
		this.studyMajor = studyMajor;
	}

	public String getStudyClass() {
		return studyClass;
	}

	public void setStudyClass(String studyClass) {
		this.studyClass = studyClass;
	}

	public Set getTbStudys() {
		return tbStudys;
	}

	public void setTbStudys(Set tbStudys) {
		this.tbStudys = tbStudys;
	}

	public String getCurrentFid() {
		return currentFid;
	}

	public void setCurrentFid(String currentFid) {
		this.currentFid = currentFid;
	}

	public String getHandsetThree() {
		return handsetThree;
	}

	public void setHandsetThree(String handsetThree) {
		this.handsetThree = handsetThree;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getSendNote() {
		return sendNote;
	}

	public void setSendNote(String sendNote) {
		this.sendNote = sendNote;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getStudyYear1() {
		return studyYear1;
	}

	public void setStudyYear1(String studyYear1) {
		this.studyYear1 = studyYear1;
	}

	public String getLiveYear1() {
		return liveYear1;
	}

	public void setLiveYear1(String liveYear1) {
		this.liveYear1 = liveYear1;
	}

	public String getStudyacademy1() {
		return studyacademy1;
	}

	public void setStudyacademy1(String studyacademy1) {
		this.studyacademy1 = studyacademy1;
	}

	public String getStudydepartment1() {
		return studydepartment1;
	}

	public void setStudydepartment1(String studydepartment1) {
		this.studydepartment1 = studydepartment1;
	}

	public String getStudyMajor1() {
		return studyMajor1;
	}

	public void setStudyMajor1(String studyMajor1) {
		this.studyMajor1 = studyMajor1;
	}

	public String getStudyClass1() {
		return studyClass1;
	}

	public void setStudyClass1(String studyClass1) {
		this.studyClass1 = studyClass1;
	}

	public String getStudyDegree1() {
		return studyDegree1;
	}

	public void setStudyDegree1(String studyDegree1) {
		this.studyDegree1 = studyDegree1;
	}

	public String getKnowerName() {
		return knowerName;
	}

	public void setKnowerName(String knowerName) {
		this.knowerName = knowerName;
	}

	public Long getAlumniLevel() {
		return alumniLevel;
	}

	public void setAlumniLevel(Long alumniLevel) {
		this.alumniLevel = alumniLevel;
	}

	public String getAlumniOrgan() {
		return alumniOrgan;
	}

	public void setAlumniOrgan(String alumniOrgan) {
		this.alumniOrgan = alumniOrgan;
	}

	public String getAlumniBlog() {
		return alumniBlog;
	}

	public void setAlumniBlog(String alumniBlog) {
		this.alumniBlog = alumniBlog;
	}

	public Long getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Long isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getStudyXuewei1() {
		return studyXuewei1;
	}

	public void setStudyXuewei1(String studyXuewei1) {
		this.studyXuewei1 = studyXuewei1;
	}

	public String getStudentType1() {
		return studentType1;
	}

	public void setStudentType1(String studentType1) {
		this.studentType1 = studentType1;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public String getCardsNum() {
		return cardsNum;
	}

	public void setCardsNum(String cardsNum) {
		this.cardsNum = cardsNum;
	}

	public String getSelfFamily() {
		return selfFamily;
	}

	public void setSelfFamily(String selfFamily) {
		this.selfFamily = selfFamily;
	}
	
}