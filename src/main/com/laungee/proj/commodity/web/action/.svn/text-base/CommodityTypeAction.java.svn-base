package com.laungee.proj.commodity.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityType;
import com.laungee.proj.common.util.DateUtil;

public class CommodityTypeAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 属性编号
		String id=getRequest().getParameter("id");
		// 查询属性
		String hql="from TbCommodityType a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		request.setAttribute("list_ele", eleList);
		// 所属分类子目录项目总数
		List countList=getList();
		if(eleList!=null && !eleList.isEmpty()){
			for(int i=0;i<eleList.size();i++){
				TbCommodityType obj = (TbCommodityType)eleList.get(i);
				String objIds = obj.getTypeId()+"";
				List childList = this.getList();
				getAllCommodityTypes(childList,obj);
				if(null!=childList&&!childList.isEmpty()){
					for (int j=0;j<childList.size();j++){
						TbCommodityType temp = (TbCommodityType)childList.get(j);
						objIds += ","+temp.getTypeId();
					}
				}
				String hqlCount = "select count(*) from TbCommodity a where a.commType in ("+objIds+")";
				countList.add(getCommonBo().getHQLNum(hqlCount));
			}
		}
		request.setAttribute("list_count", countList);
		// 返回
		return "list";
	}
	private void getAllCommodityTypes(List beanList,TbCommodityType bean)throws Exception{
		String hql = "from TbCommodityType a where a.parentId="+bean.getTypeId()+" order by a.numOrder";
		List list = this.getCommonBo().findHQL(hql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				TbCommodityType obj = (TbCommodityType)list.get(i);
				if(obj!=null){
					TbCommodityType temp = new TbCommodityType();
					temp.setTypeName(bean.getTypeName()+"  >  "+obj.getTypeName());
					temp.setTypeId(obj.getTypeId());
					beanList.add(temp);
					getAllCommodityTypes(beanList,obj);
				}
			}
		}
	}
	// 处理请求
	public String json() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 属性编号
		String id=getRequest().getParameter("id");
		// 查询属性
		String hql="from TbCommodityType a where a.parentId="+id+" order by a.numOrder";
		List eleList=getCommonBo().findHQL(hql);
		request.setAttribute("list_ele", eleList);
		// 返回
		return "json";
	}
	// 处理请求
	public String index() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 菜单序号
		request.setAttribute("num", request.getAttribute("num"));
		// 属性编号
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="1";
		}
		// 查询属性
		TbCommodityType bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
		request.setAttribute("bean", bean);
		// 返回
		return "index";
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
		TbCommodityType bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
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
		TbCommodityType tb=new TbCommodityType();
		tb.setTypeCode(code);
		tb.setTypeName(typeName);
		tb.setCreationUser(userId);
		tb.setCreationTime(DateUtil.format(dateNow, "yyyy-MM-dd HH:mm:ss"));
		tb.setMemo(memo);
		tb.setParentId(new Long(parentId));
		// 排序号
		String numHql="select max(a.numOrder) from TbCommodityType a where a.parentId="+parentId;
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
		TbCommodityType tb=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(typeId));
		
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
		TbCommodityType bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
		getRequest().setAttribute("bean", bean);
		// 返回
		return "edit";
	}

}
