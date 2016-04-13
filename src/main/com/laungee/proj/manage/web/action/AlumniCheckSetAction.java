package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbPars;

public class AlumniCheckSetAction extends BaseAction {
	// 注册校友审核配置页面
	public String pre() throws Exception {
		HttpServletRequest request = this.getRequest();
		String tabNum = request.getParameter("num");
		TbPars tbPars = null;
		String hql = "";
		try{
			request.setAttribute("num", tabNum);
			//自由注册
			hql = "from TbPars a where a.parType='1' and a.parName='freeCheck'";
			tbPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbPars!=null){
				request.setAttribute("freeCheck", tbPars.getParValue());
			}
			//智能审核
			hql = "from TbPars a where a.parType='1' and a.parName='autoCheck'";
			tbPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbPars!=null){
				request.setAttribute("autoCheck", tbPars.getParValue());
			}
			//院系审核
			hql = "from TbPars a where a.parType='1' and a.parName='academyCheck'";
			tbPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbPars!=null){
				request.setAttribute("academyCheck", tbPars.getParValue());
			}
			//校友总会审核
			hql = "from TbPars a where a.parType='1' and a.parName='schoolCheck'";
			tbPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbPars!=null){
				request.setAttribute("schoolCheck", tbPars.getParValue());
			}
		}catch(Exception e){
		}
		return INPUT;
	}
	// 注册校友审核配置更新
	public String upload() throws Exception {
		HttpServletRequest request = this.getRequest();
		String tabNum = request.getParameter("num");
		String freeCheck = request.getParameter("freeCheck");
		String autoCheck = request.getParameter("autoCheck");
		String academyCheck = request.getParameter("academyCheck");
		String schoolCheck = request.getParameter("schoolCheck");
		String hql = "";
		try{
			request.setAttribute("num", tabNum);
			request.setAttribute("freeCheck", freeCheck);
			request.setAttribute("autoCheck", autoCheck);
			request.setAttribute("academyCheck", academyCheck);
			request.setAttribute("schoolCheck", schoolCheck);
			//自由注册
			hql = "from TbPars a where a.parType='1' and a.parName='freeCheck'";
			TbPars tbFreePars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbFreePars==null){
				tbFreePars = new TbPars();
				tbFreePars.setParName("freeCheck");
				tbFreePars.setParType("1");
			}
			if("1".equals(autoCheck)){
				tbFreePars.setParValue("1");
			}else{
				tbFreePars.setParValue("0");
			}
			this.getCommonBo().saveOrUpdate(tbFreePars);
			//智能审核
			hql = "from TbPars a where a.parType='1' and a.parName='autoCheck'";
			TbPars tbAutoPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbAutoPars==null){
				tbAutoPars = new TbPars();
				tbAutoPars.setParName("autoCheck");
				tbAutoPars.setParType("1");
			}
			if("1".equals(autoCheck)){
				tbAutoPars.setParValue("1");
			}else{
				tbAutoPars.setParValue("0");
			}
			this.getCommonBo().saveOrUpdate(tbAutoPars);
			//院系审核
			hql = "from TbPars a where a.parType='1' and a.parName='academyCheck'";
			TbPars tbAcademyPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbAcademyPars==null){
				tbAcademyPars = new TbPars();
				tbAcademyPars.setParName("academyCheck");
				tbAcademyPars.setParType("1");
			}
			if("1".equals(academyCheck)){
				tbAcademyPars.setParValue("1");
			}else{
				tbAcademyPars.setParValue("0");
			}
			this.getCommonBo().saveOrUpdate(tbAcademyPars);
			//校友总会审核
			hql = "from TbPars a where a.parType='1' and a.parName='schoolCheck'";
			TbPars tbSchoolPars = (TbPars)this.getCommonBo().getHQLUnique(hql);
			if(tbSchoolPars==null){
				tbSchoolPars = new TbPars();
				tbSchoolPars.setParName("schoolCheck");
				tbSchoolPars.setParType("1");
			}
			if("1".equals(schoolCheck)){
				tbSchoolPars.setParValue("1");
			}else{
				tbSchoolPars.setParValue("0");
			}
			this.getCommonBo().saveOrUpdate(tbSchoolPars);
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}
}