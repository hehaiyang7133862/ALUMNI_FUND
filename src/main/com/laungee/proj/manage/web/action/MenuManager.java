package com.laungee.proj.manage.web.action;

import java.util.ArrayList;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbMenuRole;

public class MenuManager extends BaseManager {
	// 查询子节点
	public boolean ajaxFindNode(int id) throws Exception {
		try {
			// 查询子级个数
			String hql = "select count(*) from TbMenu a where a.parentId=" + id;
			int count = getCommonBiz().findHQLCount(hql);
			// 子级为0,可删除
			if (count == 0) {
				return true;
			}
			// 子级非0,不可删除
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 移除节点
	public boolean ajaxMoveNode(int id, int oldParentId, int newParentId,
			int nodeIndex) throws Exception {
		try {
			// 旧父编号
			TbMenu oldMenu = (TbMenu) getCommonBiz().get(TbMenu.class,new Long(oldParentId));
			Long oldGrandId = oldMenu.getParentId();
			// 新父编号
			TbMenu newMenu = (TbMenu) getCommonBiz().get(TbMenu.class,new Long(newParentId));
			Long newGrandId = newMenu.getParentId();
			// 判断
			if (oldParentId == newParentId || oldGrandId.equals(newGrandId)) {
				// 新列表
				String newHql = "from TbMenu a where a.parentId=" + newParentId
						+ " and a.menuId!=" + id + " order by a.numOrder";
				List newList = getCommonBiz().findHQL(newHql);
				if (null == newList) {
					newList = new ArrayList();
				}
				// 更新新列表
				TbMenu tbMenu = (TbMenu) getCommonBiz().get(TbMenu.class,new Long(id));
				tbMenu.setParentId(new Long(newParentId));
				// 新类型排序
				newList.add(nodeIndex, tbMenu);
				update(newList);
				// 原类型排序
				if (oldParentId != newParentId) {
					String oldHql = "from TbMenu a where a.parentId="
							+ oldParentId + " order by a.numOrder";
					List oldList = getCommonBiz().findHQL(oldHql);
					update(oldList);
				}
				// 返回
				return true;
			}
			// 平移失败
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 称动节点
	public boolean ajaxRemoveNode(int id) throws Exception {
		try {
			// 查询角色
			TbMenu tbMenu = (TbMenu) this.getCommonBiz().get(TbMenu.class,
					new Long(id));
			if(tbMenu!=null){
				//先删除菜单权限
				String sql="delete from Tb_Menu_Role a where a.menu_Id="+tbMenu.getMenuId();
				this.getCommonBiz().execute(sql);
				// 然后删除角色
				getCommonBiz().delete(tbMenu);
				
			}
			// 重新排序
			String hql = "from TbMenu a where a.parentId="
					+ tbMenu.getParentId() + " order by a.numOrder";
			List list = getCommonBiz().findHQL(hql);
			update(list);
			// 返回
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 更新排序
	private void update(List list) throws Exception {
		if (null != list) {
			TbMenu temp = null;
			for (int i = 0; i < list.size(); i++) {
				temp = (TbMenu) list.get(i);
				temp.setNumOrder(new Long(i + 1));
				getCommonBiz().merge(temp);
			}
		}
	}
}