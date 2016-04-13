package com.laungee.proj.common.web;
import com.laungee.proj.common.core.BaseManager;

public class AcademyManager extends BaseManager{
	private static AcademyManager manager=null;
	private AcademyManager(){
	}
	public static AcademyManager getInstance(){
		if(null==manager){
			manager = new AcademyManager();
		}
		return manager;
	}
	// ≤È—ØºØ∫œ
	public boolean check(Long id) throws Exception{
		String sql="select a.member_id from VW_STUDENTMEMBER_INFO a,TB_UN_ALUMNI b where (a.YEAR_JOIN=b.study_year or a.CREATE_TIME=b.study_year or a.YEAR_off=b.study_off_year or a.leave_time=b.study_off_year) and (a.ACADEMY_NAME=b.study_academy or a.DEPARTMENT_NAME=b.study_department or a.MAJOR_NAME=b.study_major or a.CLASS_NAME=b.study_class) and a.STUDENT_NAME=b.NAME_CN and b.UN_ALUMNI_ID="+id;
		int count=getCommonBiz().findSQLCount(sql);
		if(0==count){
			return false;
		}
		else {
			return true;
		}
	}
}
