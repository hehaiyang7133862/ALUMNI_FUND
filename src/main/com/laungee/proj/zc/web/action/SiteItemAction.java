package com.laungee.proj.zc.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.model.TbSiteItem;

public class SiteItemAction extends BaseAction {
	// 众筹网栏目管理
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 返回
		return "index";
	}
	
	// 众筹网栏目树JSON
	public String json() throws Exception {
		HttpServletRequest request = this.getRequest();
		// parentId
		String parentId = request.getParameter("id");
		// 栏目分组集合
		if(parentId==null || "".equals(parentId) || "0".equals(parentId)){
			String hql = "select a from TbFields a,TbFields b where b.fieldId=a.parentId and b.parentId=1 and b.code='ZCSITE_ITEM_GROUP' order by a.numOrder";
			List groupList = this.getCommonBo().findHQL(hql);
			request.setAttribute("type", "group");
			request.setAttribute("list", groupList);
			// 分组中栏目数量
			List itemCountList = this.getList();
			if(groupList!=null && !groupList.isEmpty()){
				for(int i=0;i<groupList.size();i++){
					TbFields beanFields = (TbFields)groupList.get(i);
					hql = "select count(*) from TbSiteItem a where a.mgtRange='FundMGT' and a.groupCode=? order by a.groupItemOrder";
					List pars = this.getList();
					pars.add(beanFields.getCode());
					Long count = (Long)this.getCommonBo().getHQLUnique(hql, pars);
					itemCountList.add(count);
				}
			}
			request.setAttribute("countList", itemCountList);
		}
		// 栏目集合
		else if(parentId.startsWith("group")){
			TbFields beanFields = null;
			try{
				beanFields = (TbFields)this.getCommonBo().get(TbFields.class, new Long(parentId.substring(5)));
			}catch(Exception e){}
			if(beanFields!=null){
				String hql = "select a from TbSiteItem a where a.mgtRange='FundMGT' and a.groupCode=? order by a.groupItemOrder";
				List pars = this.getList();
				pars.add(beanFields.getCode());
				List itemList = this.getCommonBo().findHQL(hql,pars);
				request.setAttribute("type", "item");
				request.setAttribute("list", itemList);
			}
		}
		// 返回
		return "json";
	}

	// 众筹网栏目新建、编辑
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 栏目对象
		TbSiteItem bean = null;
		// 栏目对象itemId
		String itemId = request.getParameter("id");
		if(itemId!=null && !"".equals(itemId)){
			try{
				bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteItem();
			// 分组
			TbFields beanFields = null;
			// 分组fieldId
			String fieldId = request.getParameter("group");
			try{
				beanFields = (TbFields)this.getCommonBo().get(TbFields.class, new Long(fieldId));
			}catch(Exception e){}
			if(beanFields!=null){
				bean.setGroupCode(beanFields.getCode());
			}
		}
		request.setAttribute("bean", bean);
		// 分组集合
		String hql = "select a from TbFields a,TbFields b where b.fieldId=a.parentId and b.parentId=1 and b.code='ZCSITE_ITEM_GROUP' order by a.numOrder";
		List groupList = this.getCommonBo().findHQL(hql);
		request.setAttribute("groupList", groupList);
		// 返回
		return INPUT;
	}

	// 众筹网栏目新建、编辑保存
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 栏目对象
		TbSiteItem bean = null;
		// 栏目对象itemId
		String itemId = request.getParameter("itemId");
		if(itemId!=null && !"".equals(itemId)){
			try{
				bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteItem();
			// 管理应用范围
			bean.setMgtRange("FundMGT");
			// 分组
			TbFields beanFields = null;
			// 分组fieldId
			String fieldId = request.getParameter("group");
			try{
				beanFields = (TbFields)this.getCommonBo().get(TbFields.class, new Long(fieldId));
			}catch(Exception e){}
			if(beanFields!=null){
				bean.setGroupCode(beanFields.getCode());
				try{
					String hql = "select count(*) from TbSiteItem a where a.mgtRange='FundMGT' and a.groupCode=?";
					List pars = this.getList();
					pars.add(beanFields.getCode());
					Long count = (Long)this.getCommonBo().getHQLUnique(hql, pars);
					bean.setGroupItemOrder(new Long(count+1));
				}catch(Exception e){}
			}
		}
		// 栏目名称
		String itemName = request.getParameter("itemName");
		bean.setItemName(itemName);
		// 栏目类型
		String itemType = request.getParameter("itemType");
		bean.setItemType(itemType);
		// 链接地址
		String linkUrl = request.getParameter("linkUrl");
		bean.setLinkUrl(linkUrl);
		// 对外发布
		String publishFlag = request.getParameter("publishFlag");
		if(publishFlag!=null && "1".equals(publishFlag)){
			bean.setPublishFlag("1");
		}else{
			bean.setPublishFlag("0");
		}
		// 备注
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// 保存
		try{
			this.getCommonBo().saveOrUpdate(bean);
		}catch(Exception e){
			request.setAttribute("bean", bean);
			// 分组集合
			String hql = "select a from TbFields a,TbFields b where b.fieldId=a.parentId and b.parentId=1 and b.code='ZCSITE_ITEM_GROUP' order by a.numOrder";
			List groupList = this.getCommonBo().findHQL(hql);
			request.setAttribute("groupList", groupList);
			return INPUT;
		}
		// 返回
		return SUCCESS;
	}
	
	

}
