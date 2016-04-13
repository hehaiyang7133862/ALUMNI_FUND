package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbAcademy generated by MyEclipse Persistence Tools
 */

public class TbAcademy implements java.io.Serializable {

	// Fields

	private Long nodeId;
	private String nodeCode;
	private String nodeName;
	private String nodeAlias;
	private String createTime;
	private String memo;
	private Long numOrder;
	private Long parentId;
	private String isLeaf;
	private Long numLevel;
	private Long yearFid;
	private Long degreeFid;
	private Set tbMembersForMajorId = new HashSet(0);
	private Set tbMembersForNodeId = new HashSet(0);
	private TbAcademy tbAcademy;
	private Long updateUser;
	private Date updateTime;
	//2013-06-28����
	private String leaveTime;	//��У��
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

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
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

	/** default constructor */
	public TbAcademy() {
	}

	/** minimal constructor */
	public TbAcademy(Long nodeId) {
		this.nodeId = nodeId;
	}

	/** full constructor */
	public TbAcademy(Long nodeId, String nodeCode, String nodeName,
			String nodeAlias, String createTime, String memo, Long numOrder,
			Long parentId, String isLeaf, Long numLevel, Long yearFid,
			Long degreeFid, Set tbMembersForMajorId, Set tbMembersForNodeId) {
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.createTime = createTime;
		this.memo = memo;
		this.numOrder = numOrder;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.numLevel = numLevel;
		this.yearFid = yearFid;
		this.degreeFid = degreeFid;
		this.tbMembersForMajorId = tbMembersForMajorId;
		this.tbMembersForNodeId = tbMembersForNodeId;
	}

	// Property accessors

	public Long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeCode() {
		return this.nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeAlias() {
		return this.nodeAlias;
	}

	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getNumOrder() {
		return this.numOrder;
	}

	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getNumLevel() {
		return this.numLevel;
	}

	public void setNumLevel(Long numLevel) {
		this.numLevel = numLevel;
	}

	public Long getYearFid() {
		return this.yearFid;
	}

	public void setYearFid(Long yearFid) {
		this.yearFid = yearFid;
	}

	public Long getDegreeFid() {
		return this.degreeFid;
	}

	public void setDegreeFid(Long degreeFid) {
		this.degreeFid = degreeFid;
	}

	public Set getTbMembersForMajorId() {
		return this.tbMembersForMajorId;
	}

	public void setTbMembersForMajorId(Set tbMembersForMajorId) {
		this.tbMembersForMajorId = tbMembersForMajorId;
	}

	public Set getTbMembersForNodeId() {
		return this.tbMembersForNodeId;
	}

	public void setTbMembersForNodeId(Set tbMembersForNodeId) {
		this.tbMembersForNodeId = tbMembersForNodeId;
	}

	public TbAcademy getTbAcademy() {
		return tbAcademy;
	}

	public void setTbAcademy(TbAcademy tbAcademy) {
		this.tbAcademy = tbAcademy;
	}
    
}