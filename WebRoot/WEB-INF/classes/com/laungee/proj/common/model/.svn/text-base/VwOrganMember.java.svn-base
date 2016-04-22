package com.laungee.proj.common.model;

import java.util.List;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * VwAlumniOrgan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwOrganMember implements java.io.Serializable {

	// Fields
	private Long id;
	private TbUnAlumni tbUnAlumni;
	private String memo;
	private List memberList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TbUnAlumni getTbUnAlumni() {
		return tbUnAlumni;
	}
	public void setTbUnAlumni(TbUnAlumni tbUnAlumni) {
		this.tbUnAlumni = tbUnAlumni;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List getMemberList() {
		memberList=null;
		try {
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			if(tbUnAlumni!=null){
				String hql="from TbOrganMember a where a.tbUnAlumni.unAlumniId="+tbUnAlumni.getUnAlumniId();
				memberList=biz.findHQL(hql);
			}
		} catch (Exception e) {
		}
		return memberList;
	}
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}
	
}