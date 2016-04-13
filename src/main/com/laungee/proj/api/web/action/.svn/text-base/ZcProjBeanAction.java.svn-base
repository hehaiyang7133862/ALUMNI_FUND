package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.VwZcproj;
import com.laungee.proj.common.model.VwZcprojBase;

public class ZcProjBeanAction extends BaseAction {
	// 众筹项目详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 众筹项目距离截止时间显示天数节点
		String hqlLastDay = "select a.fieldValue from TbFields a,TbFields b where a.parentId=b.fieldId and b.code='ZCPROJ_END_TIME' and a.code='DAY'";
		String endLastViewDay = (String)this.getCommonBo().getHQLUnique(hqlLastDay);
		request.setAttribute("endLastViewDay", endLastViewDay);
		// 众筹项目距离截止时间显示时间节点
		String hqlLastHour = "select a.fieldValue from TbFields a,TbFields b where a.parentId=b.fieldId and b.code='ZCPROJ_END_TIME' and a.code='HOUR'";
		String endLastViewHour = (String)this.getCommonBo().getHQLUnique(hqlLastHour);
		request.setAttribute("endLastViewHour", endLastViewHour);
		// 1、true时，排除详情信息罗列
		String exceptDetail = request.getParameter("exceptDetail");
		request.setAttribute("exceptDetail", exceptDetail);
		// 众筹项目
		Object bean = null;
		// 众筹项目ID
		String projId = request.getParameter("id");
		if(projId!=null && !"".equals(projId)){
			if(exceptDetail!=null && ("1".equals(exceptDetail) || "true".equals(exceptDetail))){
				try{
					bean = this.getCommonBo().get(VwZcprojBase.class, new Long(projId));
				}catch(Exception e){}
			}else{
				try{
					bean = this.getCommonBo().get(VwZcproj.class, new Long(projId));
				}catch(Exception e){}
			}
		}
		if(bean==null){
			// 参数错误
			request.setAttribute("result", "error");
			// 返回
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// 返回
		return SUCCESS;
	}
}