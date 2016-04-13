package com.laungee.proj.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * TbZcproj entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwZcprojBase implements java.io.Serializable {

	// Fields

	private Long projId;
	private Long projType;
	private String projCode;
	private String projName;
	private String projIntro;
	private String shelvesFlag;
	private Date shelvesTime;
	private BigDecimal targetAmout;
	private Date begTime;
	private Date endTime;
	private String hotFlag;
	private String hotOrder;
	private String completedJz;
	private String classicStatus;
	private String optionOther;
	private String optionOtherName;
	private String optionOtherMemo;
	private BigDecimal minAmount;
	private String searchKey;
	private String memo;
	// 众筹状态
	private String projStatus;
	// 距离众筹结束时间剩余天数
	private BigDecimal endLastDay;
	// 已筹数量
	private Long zcedCount;
	// 已筹支持数量（支持人数）
	private Long zcedPersonCount;
	// 已筹总金额
	private BigDecimal zcedAmout;
	// 众筹进度百分比
	private Float zcedPercent;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;
	private Set tbZcprojPics=new HashSet(0);
	private Set tbZcprojPubPics=new HashSet(0);
	private Set tbZcprojPubLargePics=new HashSet(0);
	private Set tbZcprojPubNormalPics=new HashSet(0);
	private Set tbZcprojPubSmallPics=new HashSet(0);
	private Set tbZcprojOptions=new HashSet(0);
	private Set tbZcprojPubOptions=new HashSet(0);

	// Constructors

	public VwZcprojBase() {
	}

	// Property accessors

	public Long getProjId() {
		return this.projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getProjType() {
		return projType;
	}

	public void setProjType(Long projType) {
		this.projType = projType;
	}

	public String getProjCode() {
		return this.projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjIntro() {
		return this.projIntro;
	}

	public void setProjIntro(String projIntro) {
		this.projIntro = projIntro;
	}

	public String getShelvesFlag() {
		return this.shelvesFlag;
	}

	public void setShelvesFlag(String shelvesFlag) {
		this.shelvesFlag = shelvesFlag;
	}

	public Date getShelvesTime() {
		return this.shelvesTime;
	}

	public void setShelvesTime(Date shelvesTime) {
		this.shelvesTime = shelvesTime;
	}

	public BigDecimal getTargetAmout() {
		return this.targetAmout;
	}

	public void setTargetAmout(BigDecimal targetAmout) {
		this.targetAmout = targetAmout;
	}

	public Date getBegTime() {
		return this.begTime;
	}

	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getHotFlag() {
		return this.hotFlag;
	}

	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag;
	}

	public String getHotOrder() {
		return this.hotOrder;
	}

	public void setHotOrder(String hotOrder) {
		this.hotOrder = hotOrder;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public Long getZcedCount() {
		return zcedCount;
	}

	public void setZcedCount(Long zcedCount) {
		this.zcedCount = zcedCount;
	}

	public Long getZcedPersonCount() {
		return zcedPersonCount;
	}

	public void setZcedPersonCount(Long zcedPersonCount) {
		this.zcedPersonCount = zcedPersonCount;
	}

	public BigDecimal getZcedAmout() {
		return zcedAmout;
	}

	public void setZcedAmout(BigDecimal zcedAmout) {
		this.zcedAmout = zcedAmout;
	}

	public Float getZcedPercent() {
		return zcedPercent;
	}

	public void setZcedPercent(Float zcedPercent) {
		this.zcedPercent = zcedPercent;
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

	public Set getTbZcprojPics() {
		return tbZcprojPics;
	}

	public void setTbZcprojPics(Set tbZcprojPics) {
		this.tbZcprojPics = tbZcprojPics;
	}

	public Set getTbZcprojPubPics() {
		return tbZcprojPubPics;
	}

	public void setTbZcprojPubPics(Set tbZcprojPubPics) {
		this.tbZcprojPubPics = tbZcprojPubPics;
	}

	public Set getTbZcprojPubLargePics() {
		return tbZcprojPubLargePics;
	}

	public void setTbZcprojPubLargePics(Set tbZcprojPubLargePics) {
		this.tbZcprojPubLargePics = tbZcprojPubLargePics;
	}

	public Set getTbZcprojPubNormalPics() {
		return tbZcprojPubNormalPics;
	}

	public void setTbZcprojPubNormalPics(Set tbZcprojPubNormalPics) {
		this.tbZcprojPubNormalPics = tbZcprojPubNormalPics;
	}

	public Set getTbZcprojPubSmallPics() {
		return tbZcprojPubSmallPics;
	}

	public void setTbZcprojPubSmallPics(Set tbZcprojPubSmallPics) {
		this.tbZcprojPubSmallPics = tbZcprojPubSmallPics;
	}

	public Set getTbZcprojPubOptions() {
		return tbZcprojPubOptions;
	}

	public void setTbZcprojPubOptions(Set tbZcprojPubOptions) {
		this.tbZcprojPubOptions = tbZcprojPubOptions;
	}

	public Set getTbZcprojOptions() {
		return tbZcprojOptions;
	}

	public void setTbZcprojOptions(Set tbZcprojOptions) {
		this.tbZcprojOptions = tbZcprojOptions;
	}

	public String getCompletedJz() {
		return completedJz;
	}

	public void setCompletedJz(String completedJz) {
		this.completedJz = completedJz;
	}

	public String getClassicStatus() {
		return classicStatus;
	}

	public void setClassicStatus(String classicStatus) {
		this.classicStatus = classicStatus;
	}

	public String getOptionOther() {
		return optionOther;
	}

	public void setOptionOther(String optionOther) {
		this.optionOther = optionOther;
	}

	public String getOptionOtherName() {
		return optionOtherName;
	}

	public void setOptionOtherName(String optionOtherName) {
		this.optionOtherName = optionOtherName;
	}

	public String getOptionOtherMemo() {
		return optionOtherMemo;
	}

	public void setOptionOtherMemo(String optionOtherMemo) {
		this.optionOtherMemo = optionOtherMemo;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public BigDecimal getEndLastDay() {
		return endLastDay;
	}

	public void setEndLastDay(BigDecimal endLastDay) {
		this.endLastDay = endLastDay;
	}

}