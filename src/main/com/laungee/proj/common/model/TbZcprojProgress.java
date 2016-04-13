package com.laungee.proj.common.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * TbZcprojProgress entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbZcprojProgress implements java.io.Serializable {

	// Fields

	private Long gressId;
	private TbZcproj tbZcproj;
	private Long projId;
	private Date gressTime;
	private String gressContent;
	private String memo;
	private Long creationUser;
	private String creationTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	public TbZcprojProgress() {
	}

	// Property accessors

	public Long getGressId() {
		return this.gressId;
	}

	public void setGressId(Long gressId) {
		this.gressId = gressId;
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

	public Date getGressTime() {
		return this.gressTime;
	}

	public void setGressTime(Date gressTime) {
		this.gressTime = gressTime;
	}

	public String getGressContent() {
		return this.gressContent;
	}

	public void setGressContent(String gressContent) {
		this.gressContent = gressContent;
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

}