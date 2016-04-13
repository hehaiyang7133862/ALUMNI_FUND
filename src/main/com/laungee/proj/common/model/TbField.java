package com.laungee.proj.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * TbMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbField implements java.io.Serializable {

	// Fields

	private Long fieldId;
	private String code;
	private String fieldIcon;
	private String fieldName;
	private String fieldValue;
	public String memo;
	private String isLeaf;
	private Long numOrder;
	private Long parentId;
	private Long updateUser;
	private Date updateTime;
	private TbField tbField;
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

	// Constructors
	/** default constructor */
	public TbField() {
	}

	/** minimal constructor */
	public TbField(Long fieldId) {
		this.fieldId = fieldId;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public TbField getTbField() {
		return tbField;
	}

	public void setTbField(TbField tbField) {
		this.tbField = tbField;
	}

	public String getFieldIcon() {
		return fieldIcon;
	}

	public void setFieldIcon(String fieldIcon) {
		this.fieldIcon = fieldIcon;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}