package com.laungee.proj.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * TbCommunity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbCommunity implements java.io.Serializable {

	// Fields

	private Long communityId;
	private String name;
	private String urlImage;
	private String declaration;
	private String createTime;
	private String emailMaster;
	private String masterTel;
	private String masterUser;
	private String masterDate;
	private String academy;	//院系
//	private String department;
	private String grade;
	private String className;
	private Long organType;
	private String organCreate;
	private String organAddress;
	private Long activeFid;
	private String responseName;
	private String responseTel;
	private String responseEmail;
	private String relationName;
	private String relationTel;
	private String relationEmail;
	private String organDis;
	private Long numVisit;
	private Long organLevel;
	private Long isOrgan;
	private Long isOrganflag;
	private Long isCommflag;
	private String isLeaf;
	private Long numOrder;
	private Long parentId;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private Long isType;	//是否为类型
//	private Set tbOrganMembers = new HashSet(0);
	private Set tbEvents = new HashSet(0);
	private Set tbShows = new HashSet(0);
	private Set tbMessages = new HashSet(0);
	private Set tbAnnounces = new HashSet(0);
	private Set tbCommunityApps = new HashSet(0);
	private Long creationUser;
	private String creationTime;
	private String isForeign;//是否海外
	private Long addrCountryFid;
	private Long addrProvinceFid;
	private Long addrCityFid;
	private Long industryFid;
	private String feature;
	// Constructors

	public String getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}

	public Long getAddrCountryFid() {
		return addrCountryFid;
	}

	public void setAddrCountryFid(Long addrCountryFid) {
		this.addrCountryFid = addrCountryFid;
	}

	public Long getAddrProvinceFid() {
		return addrProvinceFid;
	}

	public void setAddrProvinceFid(Long addrProvinceFid) {
		this.addrProvinceFid = addrProvinceFid;
	}

	public Long getAddrCityFid() {
		return addrCityFid;
	}

	public void setAddrCityFid(Long addrCityFid) {
		this.addrCityFid = addrCityFid;
	}

	public Long getIndustryFid() {
		return industryFid;
	}

	public void setIndustryFid(Long industryFid) {
		this.industryFid = industryFid;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public void setCommunityList(List communityList) {
		this.communityList = communityList;
	}

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
	
	//得到子机构，权限控制,只到下一级
	private List communityList=new ArrayList();
	
	public List getCommunityList(){
		ActionContext ct= ActionContext.getContext();
		HttpServletRequest request=(HttpServletRequest)ct.get(ServletActionContext.HTTP_REQUEST );
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		//得到用户ID
		StringBuffer sb = new StringBuffer(
		"select a from TbCommunity a where 1=1");
		List pars = new ArrayList();
		if (communityId != null && !communityId.equals("")) {
			sb.append(" and a.parentId=?");
			pars.add(new Long(communityId));
		}
		// 所属院系
		TbUser tbUser = (TbUser) request.getAttribute("user");
		//权限控制
		String alumniOrgan=(String) request.getAttribute("alumniOrgan");
		if(null!=alumniOrgan&&!"".equals(alumniOrgan)){
			if(alumniOrgan.length()-1==alumniOrgan.lastIndexOf(",")){
				alumniOrgan=alumniOrgan.substring(0,alumniOrgan.lastIndexOf(","));
			}
			sb.append(" and a.communityId in ("+alumniOrgan+")");
		}		
		sb.append(" order by a.numOrder");
		try {
			communityList=biz.findHQL(sb.toString(),pars);
		} catch (Exception e) {
		}
		return communityList;
	}
	
	
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEmailMaster() {
		return emailMaster;
	}
	public void setEmailMaster(String emailMaster) {
		this.emailMaster = emailMaster;
	}
	public String getMasterTel() {
		return masterTel;
	}
	public void setMasterTel(String masterTel) {
		this.masterTel = masterTel;
	}
	public String getMasterUser() {
		return masterUser;
	}
	public void setMasterUser(String masterUser) {
		this.masterUser = masterUser;
	}
	public String getMasterDate() {
		return masterDate;
	}
	public void setMasterDate(String masterDate) {
		this.masterDate = masterDate;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
//	public String getDepartment() {
//		return department;
//	}
//	public void setDepartment(String department) {
//		this.department = department;
//	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getOrganType() {
		return organType;
	}
	public void setOrganType(Long organType) {
		this.organType = organType;
	}
	public String getOrganCreate() {
		return organCreate;
	}
	public void setOrganCreate(String organCreate) {
		this.organCreate = organCreate;
	}
	public String getOrganAddress() {
		return organAddress;
	}
	public void setOrganAddress(String organAddress) {
		this.organAddress = organAddress;
	}
	public Long getActiveFid() {
		return activeFid;
	}
	public void setActiveFid(Long activeFid) {
		this.activeFid = activeFid;
	}
	public String getResponseName() {
		return responseName;
	}
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	public String getResponseTel() {
		return responseTel;
	}
	public void setResponseTel(String responseTel) {
		this.responseTel = responseTel;
	}
	public String getResponseEmail() {
		return responseEmail;
	}
	public void setResponseEmail(String responseEmail) {
		this.responseEmail = responseEmail;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getRelationTel() {
		return relationTel;
	}
	public void setRelationTel(String relationTel) {
		this.relationTel = relationTel;
	}
	public String getRelationEmail() {
		return relationEmail;
	}
	public void setRelationEmail(String relationEmail) {
		this.relationEmail = relationEmail;
	}
	public String getOrganDis() {
		return organDis;
	}
	public void setOrganDis(String organDis) {
		this.organDis = organDis;
	}
	public Long getNumVisit() {
		return numVisit;
	}
	public void setNumVisit(Long numVisit) {
		this.numVisit = numVisit;
	}
	public Long getOrganLevel() {
		return organLevel;
	}
	public void setOrganLevel(Long organLevel) {
		this.organLevel = organLevel;
	}
	public Long getIsOrgan() {
		return isOrgan;
	}
	public void setIsOrgan(Long isOrgan) {
		this.isOrgan = isOrgan;
	}
	public Long getIsOrganflag() {
		return isOrganflag;
	}
	public void setIsOrganflag(Long isOrganflag) {
		this.isOrganflag = isOrganflag;
	}
	public Long getIsCommflag() {
		return isCommflag;
	}
	public void setIsCommflag(Long isCommflag) {
		this.isCommflag = isCommflag;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Long getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
//	public Set getTbOrganMembers() {
//		return tbOrganMembers;
//	}
//	public void setTbOrganMembers(Set tbOrganMembers) {
//		this.tbOrganMembers = tbOrganMembers;
//	}
	public Set getTbEvents() {
		return tbEvents;
	}
	public void setTbEvents(Set tbEvents) {
		this.tbEvents = tbEvents;
	}
	public Set getTbShows() {
		return tbShows;
	}
	public void setTbShows(Set tbShows) {
		this.tbShows = tbShows;
	}
	public Set getTbMessages() {
		return tbMessages;
	}
	public void setTbMessages(Set tbMessages) {
		this.tbMessages = tbMessages;
	}
	public Set getTbAnnounces() {
		return tbAnnounces;
	}
	public void setTbAnnounces(Set tbAnnounces) {
		this.tbAnnounces = tbAnnounces;
	}
	public Set getTbCommunityApps() {
		return tbCommunityApps;
	}
	public void setTbCommunityApps(Set tbCommunityApps) {
		this.tbCommunityApps = tbCommunityApps;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getIsType() {
		return isType;
	}
	public void setIsType(Long isType) {
		this.isType = isType;
	}
}