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
 * TbMailGroup generated by MyEclipse Persistence Tools
 */

public class TbMailGroup implements java.io.Serializable {

	// Fields

	private Long groupId;
	private String groupName;
	private String memo;
	private Long updateUser;
	private Date updateTime;
	private String organRole;
	private Set tbMailAlumnis = new HashSet(0);
	private Set tbMailHistories = new HashSet(0);
	
	private List mailAlumniList=new ArrayList();
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
	public TbMailGroup() {
	}

	/** minimal constructor */
	public TbMailGroup(Long groupId) {
		this.groupId = groupId;
	}

	/** full constructor */
	public TbMailGroup(Long groupId, String groupName, String memo,
			Long updateUser, Date updateTime, Set tbMailAlumnis) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.memo = memo;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tbMailAlumnis = tbMailAlumnis;
	}

	public List getMailAlumniList(){
		ActionContext ct= ActionContext.getContext();
		HttpServletRequest request=(HttpServletRequest)ct.get(ServletActionContext.HTTP_REQUEST );
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		//得到用户ID
		StringBuffer sb = new StringBuffer(
		"select a from TbMailAlumni a  left join a.tbAlumni b  left join a.tbMailGroup c where (b.isRepeat=0 or b.isRepeat is null) ");
		List pars = new ArrayList();
		if (groupId != null && !groupId.equals("")) {
			sb.append(" and c.groupId=?");
			pars.add(new Long(groupId));
		}
		// 所属院系
		TbUser tbUser = (TbUser) request.getAttribute("user");
		if (null != tbUser) {
			if (null != tbUser) {
				// 所属机构
				if (null != tbUser.getAlumniOrgan()
						&& !tbUser.getAlumniOrgan().equals("")) {
					String str[] = tbUser.getAlumniOrgan().split(",");

					TbCommunity tbOrgan = null;
					try {
						tbOrgan = (TbCommunity) biz.get(TbCommunity.class,
								new Long(str[0].toString()));
					} catch (NumberFormatException e) {
					} catch (Exception e) {
					}
					if (tbOrgan != null
							&& !tbOrgan.getOrganType().equals(new Long(1))) {
						if (str.length == 1) {
							sb.append(" and b.alumniOrgan like ?");
							pars.add("%" + str[0] + "%");
						} else {
							for (int i = 0; i < str.length; i++) {
								if (i == 0) {
									sb.append(" and (b.alumniOrgan like ? or ");
									pars.add("%" + str[i] + "%");
								} else if (i == str.length - 1) {
									sb.append("b.alumniOrgan like ? )");
									pars.add("%" + str[i] + "%");
								} else {
									sb.append("b.alumniOrgan like ?  or ");
									pars.add("%" + str[i] + "%");
								}
							}
						}
					}
				}
				// 密级
				if (null != tbUser.getAlumniLevel()) {
					sb.append(" and b.alumniLevel<=?");
					pars.add(tbUser.getAlumniLevel());
				}
			}
		}
		try {
			mailAlumniList=biz.findHQL(sb.toString(),pars);
		} catch (Exception e) {
		}
		return mailAlumniList;
	}
	// Property accessors

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public Set getTbMailAlumnis() {
		return this.tbMailAlumnis;
	}

	public void setTbMailAlumnis(Set tbMailAlumnis) {
		this.tbMailAlumnis = tbMailAlumnis;
	}

	public Set getTbMailHistories() {
		return tbMailHistories;
	}

	public void setTbMailHistories(Set tbMailHistories) {
		this.tbMailHistories = tbMailHistories;
	}

	public String getOrganRole() {
		return organRole;
	}

	public void setOrganRole(String organRole) {
		this.organRole = organRole;
	}
     
}