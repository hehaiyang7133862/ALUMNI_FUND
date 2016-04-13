package com.laungee.proj.common.web;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.laungee.proj.common.core.BaseManager;

public class UserManager extends BaseManager {
	private UserManager(){
	}
	public static UserManager getInstance(){
		return new UserManager();
	}
	public String findName(Long arg0) throws Exception{
		String hql="select a.userName from TbUser a where a.userId="+arg0;
		Object obj=getCommonBo().getHQLUnique(hql);
		if(obj==null){
			hql="select a.nameCn from TbUnAlumni a where a.unAlumniId="+arg0;
			obj=getCommonBo().getHQLUnique(hql);
		}
		// ·µ»Ø
		return null!=obj?obj.toString():"";
	}
}
