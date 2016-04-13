package com.laungee.proj.manage.web.action;

import java.util.ArrayList;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbRole;

public class RoleManager extends BaseManager{
	// 查询是否有子节点
	public boolean ajaxFindNode(int id){
		try{
			// 查询子级个数
			String hql="select count(*) from TbRole a where a.parentId="+id;
			int count=getCommonBiz().findHQLCount(hql);
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
	// 移除节点
	public boolean ajaxMoveNode(int id, int oldParentId, int newParentId, int nodeIndex){
		try {
			// 判断
			if (oldParentId == newParentId) {
				// 新列表
				String newHql = "from TbRole a where a.parentId=" + newParentId
								+ " and a.roleId!=" + id + " order by a.numOrder";
				List newList = getCommonBiz().findHQL(newHql);
				if (null == newList) {
					newList = new ArrayList();
				}
				// 更新新列表
				TbRole tbMenu = (TbRole) getCommonBiz().get(TbRole.class,new Long(id));
				// 新类型排序
				newList.add(nodeIndex,tbMenu);
				update(newList);
				// 返回
				return true;
			}
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
	public boolean ajaxRemoveNode(int id){
		try {
			// 查询角色
			TbRole tbRole=(TbRole)this.getCommonBiz().get(TbRole.class, new Long(id));
			if(tbRole!=null){
				//先删除菜单权限
				String sql="delete from Tb_Menu_Role a where a.role_id="+tbRole.getRoleId();
				this.getCommonBiz().execute(sql);
				// 然后删除角色
				getCommonBiz().delete(tbRole);
				
			}
			
			// 重新排序
			String hql = "from TbRole a where a.parentId="
					+ tbRole.getParentId() + " order by a.numOrder";
			List list = getCommonBiz().findHQL(hql);
			update(list);
			// 返回
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 更新排序
	private void update(List list) throws Exception {
		if (null != list) {
			TbRole temp = null;
			for (int i = 0; i < list.size(); i++) {
				temp = (TbRole) list.get(i);
				temp.setNumOrder(new Long(i + 1));
				getCommonBiz().merge(temp);
			}
		}
	}
}
