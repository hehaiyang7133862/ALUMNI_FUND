package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * TbCommodityOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbCommodityOrder implements java.io.Serializable {

	// Fields

	private Long orderId;
	private String orderNo;
	private String orderSource;
	private String orderType;
	private String orderStatus;
	private BigDecimal orderFee;
	private Date orderTime;
	private Date orderOkTime;
	private String orderMemo;
	private TbCommodity tbCommodity;
	private Long commId;
	private String commName;
	private String commPic;
	private TbCommodityDetail tbCommodityDetail;
	private Long commDetailId;
	private String commDetailName;
	private BigDecimal commCostfee;
	private BigDecimal commSalefee;
	private Long commNum;
	private String shippingType;
	private BigDecimal shippingFee;
	private BigDecimal shippingCurfee;
	private BigDecimal donationFee;
	private String buyerName;
	private String niMing;
	private String buyerIp;
	private String buyerSex;
	private String buyerEmail;
	private String buyerMobile;
	private String buyerPhone;
	private String buyerZipcode;
	private String buyerSheng;
	private String buyerShi;
	private String buyerQu;
	private String buyerAddress;
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
	private Long starNum;
	private String starMemo;
	private Date starTime;
	private String receipt;
	private String mark;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbCommodityOrder() {
	}

	// Property accessors

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
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

	public BigDecimal getOrderFee() {
		return this.orderFee;
	}

	public void setOrderFee(BigDecimal orderFee) {
		this.orderFee = orderFee;
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

	public String getOrderMemo() {
		return this.orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public TbCommodity getTbCommodity() {
		return tbCommodity;
	}

	public void setTbCommodity(TbCommodity tbCommodity) {
		this.tbCommodity = tbCommodity;
	}

	public Long getCommId() {
		return this.commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public String getCommName() {
		return this.commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getCommPic() {
		return commPic;
	}

	public void setCommPic(String commPic) {
		this.commPic = commPic;
	}

	public TbCommodityDetail getTbCommodityDetail() {
		return tbCommodityDetail;
	}

	public void setTbCommodityDetail(TbCommodityDetail tbCommodityDetail) {
		this.tbCommodityDetail = tbCommodityDetail;
	}

	public Long getCommDetailId() {
		return this.commDetailId;
	}

	public void setCommDetailId(Long commDetailId) {
		this.commDetailId = commDetailId;
	}

	public String getCommDetailName() {
		return this.commDetailName;
	}

	public void setCommDetailName(String commDetailName) {
		this.commDetailName = commDetailName;
	}

	public BigDecimal getCommCostfee() {
		return this.commCostfee;
	}

	public void setCommCostfee(BigDecimal commCostfee) {
		this.commCostfee = commCostfee;
	}

	public BigDecimal getCommSalefee() {
		return this.commSalefee;
	}

	public void setCommSalefee(BigDecimal commSalefee) {
		this.commSalefee = commSalefee;
	}

	public Long getCommNum() {
		return this.commNum;
	}

	public void setCommNum(Long commNum) {
		this.commNum = commNum;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public BigDecimal getShippingFee() {
		return this.shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public BigDecimal getShippingCurfee() {
		return this.shippingCurfee;
	}

	public void setShippingCurfee(BigDecimal shippingCurfee) {
		this.shippingCurfee = shippingCurfee;
	}

	public BigDecimal getDonationFee() {
		return this.donationFee;
	}

	public void setDonationFee(BigDecimal donationFee) {
		this.donationFee = donationFee;
	}

	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getNiMing() {
		return niMing;
	}

	public void setNiMing(String niMing) {
		this.niMing = niMing;
	}

	public String getBuyerIp() {
		return this.buyerIp;
	}

	public void setBuyerIp(String buyerIp) {
		this.buyerIp = buyerIp;
	}

	public String getBuyerSex() {
		return this.buyerSex;
	}

	public void setBuyerSex(String buyerSex) {
		this.buyerSex = buyerSex;
	}

	public String getBuyerEmail() {
		return this.buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerMobile() {
		return this.buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public String getBuyerPhone() {
		return this.buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerZipcode() {
		return this.buyerZipcode;
	}

	public void setBuyerZipcode(String buyerZipcode) {
		this.buyerZipcode = buyerZipcode;
	}

	public String getBuyerSheng() {
		return this.buyerSheng;
	}

	public void setBuyerSheng(String buyerSheng) {
		this.buyerSheng = buyerSheng;
	}

	public String getBuyerShi() {
		return this.buyerShi;
	}

	public void setBuyerShi(String buyerShi) {
		this.buyerShi = buyerShi;
	}

	public String getBuyerQu() {
		return this.buyerQu;
	}

	public void setBuyerQu(String buyerQu) {
		this.buyerQu = buyerQu;
	}

	public String getBuyerAddress() {
		return this.buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
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

	public Long getStarNum() {
		return this.starNum;
	}

	public void setStarNum(Long starNum) {
		this.starNum = starNum;
	}

	public String getStarMemo() {
		return this.starMemo;
	}

	public void setStarMemo(String starMemo) {
		this.starMemo = starMemo;
	}

	public Date getStarTime() {
		return this.starTime;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
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
					String hql = "select count(*) from TbCommodityOrder a where a.commId=? and a.orderStatus=? and a.orderOkTime is null and a.orderId<?";
					List pars = new ArrayList();
					pars.add(commId);
					pars.add("1");
					pars.add(orderId);
					// 执行查询
					index = biz.getHQLUnique(hql, pars);
				}else{
					String hql = "select count(*) from TbCommodityOrder a where a.commId=? and a.orderStatus=? and (a.orderOkTime is null or a.orderOkTime<? or (a.orderOkTime=? and a.orderId<?))";
					List pars = new ArrayList();
					pars.add(commId);
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