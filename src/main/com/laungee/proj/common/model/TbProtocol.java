package com.laungee.proj.common.model;

import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * TbProtocol entity.基金项目协议表
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbProtocol implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835381825379287087L;
	private Long proId;
	private Long foundId;
	//基金项目
	private TbFoundation tbFoundation;
	private String proCode;
	private String proName;
	private String proStatus;
	private String jzfxz;
	private String jzfObject;
	private String jzType;
	private String szfObject;
	private String jzSource;
	private String syfObject;
	private String dealDate;
	private String startTime;
	private String endTime;
	private String proAmount;
	private String toAmount;
	private String proContent;
	//是否上架 1已上架，0已下架
	private String isShelves;
	//是否删除 1已删除，0未删除
	private String isDelete;
	private Long updateUser;
	private Date updateTime;
	private String creationTime;
	private Long creationUser;

	private String strSyfObject;
	private String strJzfObject;
	//类型 1协议，2：项目众筹
	private String proType;
	//简介
	private String proIntro;
	//最低捐款金额
	private String donationMin;
	//是否热门 1是，0否
	private String isHot;
	// Constructors


	// Property accessors

	public void setStrSyfObject(String strSyfObject) {
		this.strSyfObject = strSyfObject;
	}

	public String getIsShelves() {
		return isShelves;
	}

	public void setIsShelves(String isShelves) {
		this.isShelves = isShelves;
	}

	public void setStrJzfObject(String strJzfObject) {
		this.strJzfObject = strJzfObject;
	}

	public Long getProId() {
		return this.proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long getFoundId() {
		return this.foundId;
	}

	public void setFoundId(Long foundId) {
		this.foundId = foundId;
	}

	public String getProCode() {
		return this.proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProIntro() {
		return proIntro;
	}

	public void setProIntro(String proIntro) {
		this.proIntro = proIntro;
	}

	public String getDonationMin() {
		return donationMin;
	}

	public void setDonationMin(String donationMin) {
		this.donationMin = donationMin;
	}

	public String getProStatus() {
		return this.proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getJzfxz() {
		return this.jzfxz;
	}

	public void setJzfxz(String jzfxz) {
		this.jzfxz = jzfxz;
	}

	public String getJzfObject() {
		return this.jzfObject;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public void setJzfObject(String jzfObject) {
		this.jzfObject = jzfObject;
	}

	public String getJzType() {
		return this.jzType;
	}

	public void setJzType(String jzType) {
		this.jzType = jzType;
	}

	public String getSzfObject() {
		return this.szfObject;
	}

	public void setSzfObject(String szfObject) {
		this.szfObject = szfObject;
	}

	public String getJzSource() {
		return this.jzSource;
	}

	public void setJzSource(String jzSource) {
		this.jzSource = jzSource;
	}

	public TbFoundation getTbFoundation() {
		return tbFoundation;
	}

	public void setTbFoundation(TbFoundation tbFoundation) {
		this.tbFoundation = tbFoundation;
	}
	public String getSyfObject() {
		return this.syfObject;
	}
	
	public String getStrSyfObject(){
		try {
			if(syfObject!=null&&!"".equals(syfObject)){
				ICommonBiz biz=(ICommonBiz)SpringUtil.getBean(SpringUtil.COMMONBIZ);
//				String sql="select gettype(a.syf_object,'com_name','com_id','tb_accept_company') from tb_protocol a where a.pro_id="+proId;
				String sql="select concatstr(a.com_name) from tb_accept_company a where a.com_id in ("+syfObject+")";
				this.strSyfObject=biz.getSQLUnique(sql)+"";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.strSyfObject;
	}
	
	public String getStrJzfObject(){
		try {
			if(jzfObject!=null&&!"".equals(jzfObject)){
				ICommonBiz biz=(ICommonBiz)SpringUtil.getBean(SpringUtil.COMMONBIZ);
				if("1".equals(jzfxz)){
					//单位
					TbDonationCompany bean=(TbDonationCompany) biz.get(TbDonationCompany.class, new Long(jzfObject));
					this.strJzfObject=bean.getComName();
				}else if("2".equals(jzfxz)){
					//个人
					TbDonationor bean=(TbDonationor) biz.get(TbDonationor.class, new Long(jzfObject));
					if(bean!=null&&bean.getTbAlumni()!=null){
						this.strJzfObject=bean.getTbAlumni().getNameCn();
					}
					if(strJzfObject==null||"".equals(strJzfObject)){
						this.strJzfObject=bean.getNameCn();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.strJzfObject;
	}

	public void setSyfObject(String syfObject) {
		this.syfObject = syfObject;
	}

	public String getDealDate() {
		return this.dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProAmount() {
		return this.proAmount;
	}

	public void setProAmount(String proAmount) {
		this.proAmount = proAmount;
	}

	public String getToAmount() {
		return this.toAmount;
	}

	public void setToAmount(String toAmount) {
		this.toAmount = toAmount;
	}

	public String getProContent() {
		return this.proContent;
	}

	public void setProContent(String proContent) {
		this.proContent = proContent;
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

	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public Long getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

}