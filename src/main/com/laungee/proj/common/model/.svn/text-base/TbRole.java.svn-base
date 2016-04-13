package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbRole implements java.io.Serializable {

	// Fields
	private Long roleId;
	private String code;
	private Long openFid;
	private TbField tbOpen;
	private String roleName;
	private String isLeaf;
	private String isOrgan;
	private Long parentId;
	private TbRole tbRole;
	private Long numOrder;
	private String memo;
	private String isValid;
	private String organCode;
	private String createUser;
	private String createAddress;
	private Date startTime;
	private Date endTime;
	private Long activeFid;
	private Long typeFid;
	private String organMemo;
	private Long updateUser;
	private Date updateTime;
	private Set tbMenuRoles = new HashSet(0);
	private Set tbRoleUsers = new HashSet(0);
	private Long usercount;
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

	/** default constructor */
	public TbRole() {
	}

	/** minimal constructor */
	public TbRole(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set getTbMenuRoles() {
		return tbMenuRoles;
	}

	public void setTbMenuRoles(Set tbMenuRoles) {
		this.tbMenuRoles = tbMenuRoles;
	}

	public Set getTbRoleUsers() {
		return tbRoleUsers;
	}

	public void setTbRoleUsers(Set tbRoleUsers) {
		this.tbRoleUsers = tbRoleUsers;
	}

	public String getIsOrgan() {
		return isOrgan;
	}

	public void setIsOrgan(String isOrgan) {
		this.isOrgan = isOrgan;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getActiveFid() {
		return activeFid;
	}

	public void setActiveFid(Long activeFid) {
		this.activeFid = activeFid;
	}

	public String getOrganMemo() {
		return organMemo;
	}

	public void setOrganMemo(String organMemo) {
		this.organMemo = organMemo;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCreateAddress() {
		return createAddress;
	}

	public void setCreateAddress(String createAddress) {
		this.createAddress = createAddress;
	}

	public Long getOpenFid() {
		return openFid;
	}

	public void setOpenFid(Long openFid) {
		this.openFid = openFid;
	}

	public Long getTypeFid() {
		return typeFid;
	}

	public void setTypeFid(Long typeFid) {
		this.typeFid = typeFid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TbRole getTbRole() {
		return tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public TbField getTbOpen() {
		return tbOpen;
	}

	public void setTbOpen(TbField tbOpen) {
		this.tbOpen = tbOpen;
	}
	public Long getUsercount() {
		if(tbRoleUsers!=null){
			usercount=new Long(tbRoleUsers.size());
		}
		
		return usercount;
	}

	public void setUsercount(Long usercount) {
		this.usercount = usercount;
	}
}