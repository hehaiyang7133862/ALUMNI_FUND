package com.laungee.proj.manage.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbFields;


public class FieldsManager  extends BaseManager{
	// 查询子节点
	public boolean findNode(int id){
		try{
			// 查询子级个数
			String hql="select count(*) from TbFields a where a.parentId="+id;
			int count=getCommonBo().findHQLCount(hql);
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
			// 查询角色
			TbFields tbField=(TbFields)this.getCommonBo().get(TbFields.class, new Long(id));
			// 删除角色
			getCommonBo().delete(tbField);
			// 重新排序
			String hql="from TbFields a where a.parentId="+tbField.getParentId()+" order by a.numOrder";
			List list=getCommonBo().findHQL(hql);
			update(list);
			// 返回
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// 称动节点
	public boolean moveNode(int id, int oldParentId, int newParentId, int nodeIndex){
		try {
			if(oldParentId==newParentId){
				// 新类型列表
				String newHql="from TbFields a where a.parentId="+newParentId+" and a.fieldId!="+id+" order by a.numOrder";
				List newList=getCommonBo().findHQL(newHql);
				if(null==newList){
					newList=new ArrayList();
				}
				// 插入的类型
				TbFields TbField=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
				TbField.setParentId(new Long(newParentId));
				// 新类型排序
				newList.add(nodeIndex, TbField);
				update(newList);
				// 返回
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// 更新排序
	private void update(List list) throws Exception{
		if(null!=list){
			TbFields temp=null;
			for (int i = 0; i < list.size(); i++) {
				temp=(TbFields)list.get(i);
				temp.setNumOrder(new Long(i+1));
				getCommonBo().merge(temp);
			}
		}
	}
}