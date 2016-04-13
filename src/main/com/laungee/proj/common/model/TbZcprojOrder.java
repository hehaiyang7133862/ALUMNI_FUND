package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * TbZcprojOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbZcprojOrder implements java.io.Serializable {

	// Fields

	private Long orderId;
	private String orderNum;
	private TbZcproj tbZcproj;
	private Long projId;
	private String projName;
	private String projPic;
	private TbZcprojOption tbZcprojOption;
	private Long optionId;
	private String optionName;
	private Long optionCount;
	private BigDecimal orderAmount;
	private String orderAmountType;
	private BigDecimal orderAmountView;
	private String orderAmountUnit;
	private String orderType;
	private String orderStatus;
	private Date orderTime;
	private Date orderOkTime;
	private String needZhengshu;
	private String needFapiao;
	private String personType;
	private Long personCount;
	private String personName;
	private String niMing;
	private String personIp;
	private String personSex;
	private String personEmail;
	private String personPhone;
	private String personTel;
	private String addressZip;
	private String addressSheng;
	private String addressShi;
	private String addressQu;
	private String addressDetail;
	private String alumniFlag;
	private Long unAlumniId;
	private String studyYearin;
	private String studyYearover;
	private String studyAcademy;
	private String studyMajor;
	private String studyClass;
	private String studyNum;
	private String studyDegree;
	private String workCompany;
	private String workDepart;
	private String workDuty;
	private String orderMemo;
	private String receipt;
	private String mark;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	
	// Constructors

	public TbZcprojOrder() {
	}

	// Property accessors

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public TbZcproj getTbZcproj() {
		return tbZcproj;
	}

	public void setTbZcproj(TbZcproj tbZcproj) {
		this.tbZcproj = tbZcproj;
	}

	public Long getProjId() {
		return this.projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjPic() {
		return projPic;
	}

	public void setProjPic(String projPic) {
		this.projPic = projPic;
	}

	public TbZcprojOption getTbZcprojOption() {
		return tbZcprojOption;
	}

	public void setTbZcprojOption(TbZcprojOption tbZcprojOption) {
		this.tbZcprojOption = tbZcprojOption;
	}

	public Long getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Long getOptionCount() {
		return this.optionCount;
	}

	public void setOptionCount(Long optionCount) {
		this.optionCount = optionCount;
	}

	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderAmountType() {
		return orderAmountType;
	}

	public void setOrderAmountType(String orderAmountType) {
		this.orderAmountType = orderAmountType;
	}

	public BigDecimal getOrderAmountView() {
		return orderAmountView;
	}

	public void setOrderAmountView(BigDecimal orderAmountView) {
		this.orderAmountView = orderAmountView;
	}

	public String getOrderAmountUnit() {
		return orderAmountUnit;
	}

	public void setOrderAmountUnit(String orderAmountUnit) {
		this.orderAmountUnit = orderAmountUnit;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderOkTime() {
		return this.orderOkTime;
	}

	public void setOrderOkTime(Date orderOkTime) {
		this.orderOkTime = orderOkTime;
	}

	public String getNeedZhengshu() {
		return this.needZhengshu;
	}

	public void setNeedZhengshu(String needZhengshu) {
		this.needZhengshu = needZhengshu;
	}

	public String getNeedFapiao() {
		return this.needFapiao;
	}

	public void setNeedFapiao(String needFapiao) {
		this.needFapiao = needFapiao;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Long getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Long personCount) {
		this.personCount = personCount;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getNiMing() {
		return niMing;
	}

	public void setNiMing(String niMing) {
		this.niMing = niMing;
	}

	public String getPersonIp() {
		return this.personIp;
	}

	public void setPersonIp(String personIp) {
		this.personIp = personIp;
	}

	public String getPersonSex() {
		return this.personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}

	public String getPersonEmail() {
		return this.personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonPhone() {
		return this.personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonTel() {
		return this.personTel;
	}

	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}

	public String getAddressZip() {
		return this.addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public String getAddressSheng() {
		return this.addressSheng;
	}

	public void setAddressSheng(String addressSheng) {
		this.addressSheng = addressSheng;
	}

	public String getAddressShi() {
		return this.addressShi;
	}

	public void setAddressShi(String addressShi) {
		this.addressShi = addressShi;
	}

	public String getAddressQu() {
		return this.addressQu;
	}

	public void setAddressQu(String addressQu) {
		this.addressQu = addressQu;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAlumniFlag() {
		return this.alumniFlag;
	}

	public void setAlumniFlag(String alumniFlag) {
		this.alumniFlag = alumniFlag;
	}

	public Long getUnAlumniId() {
		return unAlumniId;
	}

	public void setUnAlumniId(Long unAlumniId) {
		this.unAlumniId = unAlumniId;
	}

	public String getStudyYearin() {
		return this.studyYearin;
	}

	public void setStudyYearin(String studyYearin) {
		this.studyYearin = studyYearin;
	}

	public String getStudyYearover() {
		return this.studyYearover;
	}

	public void setStudyYearover(String studyYearover) {
		this.studyYearover = studyYearover;
	}

	public String getStudyAcademy() {
		return this.studyAcademy;
	}

	public void setStudyAcademy(String studyAcademy) {
		this.studyAcademy = studyAcademy;
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

	public String getStudyNum() {
		return this.studyNum;
	}

	public void setStudyNum(String studyNum) {
		this.studyNum = studyNum;
	}

	public String getStudyDegree() {
		return this.studyDegree;
	}

	public void setStudyDegree(String studyDegree) {
		this.studyDegree = studyDegree;
	}

	public String getWorkCompany() {
		return this.workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkDepart() {
		return this.workDepart;
	}

	public void setWorkDepart(String workDepart) {
		this.workDepart = workDepart;
	}

	public String getWorkDuty() {
		return this.workDuty;
	}

	public void setWorkDuty(String workDuty) {
		this.workDuty = workDuty;
	}

	public String getOrderMemo() {
		return this.orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
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

	// 支持排名数（同项目第几个支持）
	private Long suportIndex;
	private Boolean suportIndexFlag = false;

	public Long getSuportIndex(){
		setSuportIndex();
		return this.suportIndex;
	}
	
	private void setSuportIndex(){
		if(orderStatus!=null && "1".equals(orderStatus) && !suportIndexFlag){
			try{
				ICommonBiz biz = (ICommonBiz)SpringUtil.getBean("commonBiz");
				// 支持排名数
				Object index = null;
				if(orderOkTime==null){
					String hql = "select count(*) from TbZcprojOrder a where a.projId=? and a.orderStatus=? and a.orderOkTime is null and a.orderId<?";
					List pars = new ArrayList();
					pars.add(projId);
					pars.add("1");
					pars.add(orderId);
					// 执行查询
					index = biz.getHQLUnique(hql, pars);
				}else{
					String hql = "select count(*) from TbZcprojOrder a where a.projId=? and a.orderStatus=? and (a.orderOkTime is null or a.orderOkTime<? or (a.orderOkTime=? and a.orderId<?))";
					List pars = new ArrayList();
					pars.add(projId);
					pars.add("1");
					pars.add(orderOkTime);
					pars.add(orderOkTime);
					pars.add(orderId);
					// 执行查询
					index = biz.getHQLUnique(hql, pars);
				}
				if(index!=null){
					suportIndex = new Long(index.toString()) + 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		suportIndexFlag = true;
	}
	
}