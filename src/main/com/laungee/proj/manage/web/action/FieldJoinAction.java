package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class FieldJoinAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 属性编号
		String id=getRequest().getParameter("id");
		// 查询属性
		String hql="from TbField a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("list_ele", eleList);
		// 返回
		return SUCCESS;
	}
	// 处理请求
	public String pre() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 菜单序号
		String num=request.getParameter("num");
		request.setAttribute("num", num);
		// 属性编号
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="-1";
		}
		// 查询属性
		if(null==id||"".equals(id))
			return null;
		TbField tbField=(TbField)getCommonBo().get(TbField.class, new Long(id));
		request.setAttribute("bean", tbField);
		// 返回
		return INPUT;
	}
}
