package com.laungee.proj.common.model;

import java.util.Date;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

/**
 * AbstractVwOperatelogId entity provides the base persistence definition of the
 * VwOperatelogId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwOperatelog implements java.io.Serializable {

	// Fields

	private Long logId;
	private Long userId;
	private Long alumniId;
	private String nameCn;
	private String content;
	private Date operateTime;
	private String alumniOrgan;
	private String yearJoin;
	private String yearOff;
	private Long academyId;
	private String academyName;
	private Long majorId;
	private String majorName;
	private Long classId;
	private String className;
	private String organStr;

	// Constructors

	// Property accessors

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getAlumniOrgan() {
		return this.alumniOrgan;
	}

	public void setAlumniOrgan(String alumniOrgan) {
		this.alumniOrgan = alumniOrgan;
	}

	public String getYearJoin() {
		return this.yearJoin;
	}

	public void setYearJoin(String yearJoin) {
		this.yearJoin = yearJoin;
	}

	public String getYearOff() {
		return this.yearOff;
	}

	public void setYearOff(String yearOff) {
		this.yearOff = yearOff;
	}

	public Long getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(Long academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return this.academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public Long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOrganStr() {
		organStr="";
		if(this.getAlumniOrgan()!=null&&!"".equals(this.getAlumniOrgan())){
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			String[] strs=this.getAlumniOrgan().split(",");
			if(strs!=null&&strs.length!=0){
				for (int i = 0; i < strs.length; i++) {
					String id=strs[i];
					if(id!=null&&!"".equals(id)){
						String sql="select a.name from tb_community a where a.community_id="+id;
						try {
							Object obj=biz.getSQLUnique(sql);
							if(obj!=null&&!"".equals(obj)){
								if(i+1==strs.length){
									organStr+=obj;
								}else{
									organStr+=obj+";";
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return organStr;
	}

	public void setOrganStr(String organStr) {
		this.organStr = organStr;
	}

}