package com.laungee.proj.foundation.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFoundType;
import com.laungee.proj.common.util.DateUtil;

public class FoundTypeAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 属性编号
		String id=getRequest().getParameter("id");
		// 查询属性
		String hql="from TbFoundType a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("list_ele", eleList);
		// 返回
		return "list";
	}
	// 处理请求
	public String pre() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 菜单序号
		request.setAttribute("num", request.getAttribute("num"));
		// 属性编号
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="1";
		}
		// 查询属性
		TbFoundType bean=(TbFoundType)getCommonBo().get(TbFoundType.class, new Long(id));
		request.setAttribute("bean", bean);
		// 返回
		return INPUT;
	}
	// 处理请求
	public String doAdd() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 当前用户
		Object userObj = request.getSession().getAttribute(USER_ID);
		Long userId = null;
		if(userObj!=null){
			try {
				userId = new Long(userObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 编号
		String code=request.getParameter("typeCode");
		// 名称
		String typeName=request.getParameter("typeName");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbFoundType tb=new TbFoundType();
		tb.setTypeCode(code);
		tb.setTypeName(typeName);
		tb.setCreationUser(userId);
		tb.setCreationTime(DateUtil.format(dateNow, "yyyy-MM-dd HH:mm:ss"));
		tb.setMemo(memo);
		tb.setParentId(new Long(parentId));
		// 排序号
		String numHql="select max(a.numOrder) from TbFoundType a where a.parentId="+parentId;
		int num=getCommonBo().findHQLCount(numHql);
		tb.setNumOrder(new Long(num+1));
		// 保存
		getCommonBo().save(tb);
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String add() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 设置父类编号
		this.getRequest().setAttribute("parentId", parentId);
		// 返回
		return "add";
	}
	// 处理请求
	public String doEdit() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 当前用户
		Object userObj = request.getSession().getAttribute(USER_ID);
		Long userId = null;
		if(userObj!=null){
			try {
				userId = new Long(userObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// id
		String typeId=request.getParameter("typeId");
		// 编号
		String typeCode=request.getParameter("typeCode");
		// 名称
		String typeName=request.getParameter("typeName");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbFoundType tb=(TbFoundType)getCommonBo().get(TbFoundType.class, new Long(typeId));
		
		tb.setTypeName(typeName);
		tb.setMemo(memo);
		tb.setUpdateTime(dateNow);
		tb.setUpdateUser(userId);
		// 保存
		getCommonBo().update(tb);
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String edit() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 编号
		String id=request.getParameter("id");
		// 属性信息
		TbFoundType bean=(TbFoundType)getCommonBo().get(TbFoundType.class, new Long(id));
		getRequest().setAttribute("bean", bean);
		// 返回
		return "edit";
	}

}
