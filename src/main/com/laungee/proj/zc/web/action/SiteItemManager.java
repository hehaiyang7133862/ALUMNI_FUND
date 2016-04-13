package com.laungee.proj.zc.web.action;

import java.util.ArrayList;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbSiteItem;

public class SiteItemManager extends BaseManager{
	// 查询子节点
	public boolean findNode(int id){
		try{
			// 查询栏目内容个数
			String hql = "select count(*) from TbSiteContent a where a.itemId="+id;
			int count = getCommonBo().findHQLCount(hql);
			// 子级为0
			if(count==0){
				return true;
			}
			// 子级非0
			else{
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// 删除节点
	public boolean removeNode(int id){
		try {
			// 查询栏目
			TbSiteItem bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(id));
			// 删除栏目
			getCommonBo().delete(bean);
			// 重新排序
			String hql="from TbSiteItem a where a.mgtRange='FundMGT' and a.groupCode=? order by a.groupItemOrder";
			List pars = new ArrayList();
			pars.add(bean.getGroupCode());
			List list = getCommonBo().findHQL(hql);
			update(list);
			// 删除栏目内容
			hql = "delete from TbSiteContent a where a.itemId="+id;
			getCommonBo().executeHql(hql);
			// 返回
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// 称动节点
	public boolean moveNode(int id, int nodeIndex){
		try {
			// 查询栏目
			TbSiteItem bean = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(id));
			// 新类型列表
			String newHql="from TbSiteItem a where a.mgtRange='FundMGT' and a.groupCode=? and a.itemId!=? order by a.groupItemOrder";
			List pars = new ArrayList();
			pars.add(bean.getGroupCode());
			pars.add(bean.getItemId());
			List newList=getCommonBo().findHQL(newHql,pars);
			if(null==newList){
				newList=new ArrayList();
			}
			// 新类型排序
			newList.add(nodeIndex, bean);
			update(newList);
			// 返回
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// 更新排序
	private void update(List list) throws Exception{
		if(null!=list){
			TbSiteItem temp=null;
			for (int i = 0; i < list.size(); i++) {
				temp=(TbSiteItem)list.get(i);
				temp.setGroupItemOrder(new Long(i+1));
				getCommonBo().merge(temp);
			}
		}
	}
}