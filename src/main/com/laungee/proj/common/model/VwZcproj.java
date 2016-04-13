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

public class VwZcproj implements java.io.Serializable {

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
	private String detail1Title;
	private String detail2Title;
	private String detail3Title;
	private String detail4Title;
	private String detail5Title;
	private String detail6Title;
	private String detail7Title;
	private String detail8Title;
	private String detail9Title;
	private String detail10Title;
	private String detail1Content;
	private String detail2Content;
	private String detail3Content;
	private String detail4Content;
	private String detail5Content;
	private String detail6Content;
	private String detail7Content;
	private String detail8Content;
	private String detail9Content;
	private String detail10Content;
	private String detail1Publish;
	private String detail2Publish;
	private String detail3Publish;
	private String detail4Publish;
	private String detail5Publish;
	private String detail6Publish;
	private String detail7Publish;
	private String detail8Publish;
	private String detail9Publish;
	private String detail10Publish;
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

	public VwZcproj() {
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

	public String getDetail1Title() {
		return detail1Title;
	}

	public void setDetail1Title(String detail1Title) {
		this.detail1Title = detail1Title;
	}

	public String getDetail2Title() {
		return detail2Title;
	}

	public void setDetail2Title(String detail2Title) {
		this.detail2Title = detail2Title;
	}

	public String getDetail3Title() {
		return detail3Title;
	}

	public void setDetail3Title(String detail3Title) {
		this.detail3Title = detail3Title;
	}

	public String getDetail4Title() {
		return detail4Title;
	}

	public void setDetail4Title(String detail4Title) {
		this.detail4Title = detail4Title;
	}

	public String getDetail5Title() {
		return detail5Title;
	}

	public void setDetail5Title(String detail5Title) {
		this.detail5Title = detail5Title;
	}

	public String getDetail6Title() {
		return detail6Title;
	}

	public void setDetail6Title(String detail6Title) {
		this.detail6Title = detail6Title;
	}

	public String getDetail7Title() {
		return detail7Title;
	}

	public void setDetail7Title(String detail7Title) {
		this.detail7Title = detail7Title;
	}

	public String getDetail8Title() {
		return detail8Title;
	}

	public void setDetail8Title(String detail8Title) {
		this.detail8Title = detail8Title;
	}

	public String getDetail9Title() {
		return detail9Title;
	}

	public void setDetail9Title(String detail9Title) {
		this.detail9Title = detail9Title;
	}

	public String getDetail10Title() {
		return detail10Title;
	}

	public void setDetail10Title(String detail10Title) {
		this.detail10Title = detail10Title;
	}

	public String getDetail1Content() {
		return detail1Content;
	}

	public void setDetail1Content(String detail1Content) {
		this.detail1Content = detail1Content;
	}

	public String getDetail2Content() {
		return detail2Content;
	}

	public void setDetail2Content(String detail2Content) {
		this.detail2Content = detail2Content;
	}

	public String getDetail3Content() {
		return detail3Content;
	}

	public void setDetail3Content(String detail3Content) {
		this.detail3Content = detail3Content;
	}

	public String getDetail4Content() {
		return detail4Content;
	}

	public void setDetail4Content(String detail4Content) {
		this.detail4Content = detail4Content;
	}

	public String getDetail5Content() {
		return detail5Content;
	}

	public void setDetail5Content(String detail5Content) {
		this.detail5Content = detail5Content;
	}

	public String getDetail6Content() {
		return detail6Content;
	}

	public void setDetail6Content(String detail6Content) {
		this.detail6Content = detail6Content;
	}

	public String getDetail7Content() {
		return detail7Content;
	}

	public void setDetail7Content(String detail7Content) {
		this.detail7Content = detail7Content;
	}

	public String getDetail8Content() {
		return detail8Content;
	}

	public void setDetail8Content(String detail8Content) {
		this.detail8Content = detail8Content;
	}

	public String getDetail9Content() {
		return detail9Content;
	}

	public void setDetail9Content(String detail9Content) {
		this.detail9Content = detail9Content;
	}

	public String getDetail10Content() {
		return detail10Content;
	}

	public void setDetail10Content(String detail10Content) {
		this.detail10Content = detail10Content;
	}

	public String getDetail1Publish() {
		return detail1Publish;
	}

	public void setDetail1Publish(String detail1Publish) {
		this.detail1Publish = detail1Publish;
	}

	public String getDetail2Publish() {
		return detail2Publish;
	}

	public void setDetail2Publish(String detail2Publish) {
		this.detail2Publish = detail2Publish;
	}

	public String getDetail3Publish() {
		return detail3Publish;
	}

	public void setDetail3Publish(String detail3Publish) {
		this.detail3Publish = detail3Publish;
	}

	public String getDetail4Publish() {
		return detail4Publish;
	}

	public void setDetail4Publish(String detail4Publish) {
		this.detail4Publish = detail4Publish;
	}

	public String getDetail5Publish() {
		return detail5Publish;
	}

	public void setDetail5Publish(String detail5Publish) {
		this.detail5Publish = detail5Publish;
	}

	public String getDetail6Publish() {
		return detail6Publish;
	}

	public void setDetail6Publish(String detail6Publish) {
		this.detail6Publish = detail6Publish;
	}

	public String getDetail7Publish() {
		return detail7Publish;
	}

	public void setDetail7Publish(String detail7Publish) {
		this.detail7Publish = detail7Publish;
	}

	public String getDetail8Publish() {
		return detail8Publish;
	}

	public void setDetail8Publish(String detail8Publish) {
		this.detail8Publish = detail8Publish;
	}

	public String getDetail9Publish() {
		return detail9Publish;
	}

	public void setDetail9Publish(String detail9Publish) {
		this.detail9Publish = detail9Publish;
	}

	public String getDetail10Publish() {
		return detail10Publish;
	}

	public void setDetail10Publish(String detail10Publish) {
		this.detail10Publish = detail10Publish;
	}

	public BigDecimal getEndLastDay() {
		return endLastDay;
	}

	public void setEndLastDay(BigDecimal endLastDay) {
		this.endLastDay = endLastDay;
	}

}