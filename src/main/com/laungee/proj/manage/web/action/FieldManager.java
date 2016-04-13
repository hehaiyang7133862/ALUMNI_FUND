package com.laungee.proj.manage.web.action;

import java.util.ArrayList;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbRole;

public class FieldManager  extends BaseManager{
	// ��ѯ�ӽڵ�
	public boolean findNode(int id){
		try{
			// ��ѯ�Ӽ�����
			String hql="select count(*) from TbField a where a.parentId="+id;
			int count=getCommonBiz().findHQLCount(hql);
			// �Ӽ�Ϊ0
			if(count==0){
				return true;
			}
			// �Ӽ���0
			else{
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// ɾ���ڵ�
	public boolean removeNode(int id){
		try {
			// ��ѯ��ɫ
			TbField tbField=(TbField)this.getCommonBiz().get(TbField.class, new Long(id));
			// ɾ����ɫ
			getCommonBiz().delete(tbField);
			// ��������
			String hql="from TbField a where a.parentId="+tbField.getParentId()+" order by a.numOrder";
			List list=getCommonBiz().findHQL(hql);
			update(list);
			// ����
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// �ƶ��ڵ�
	public boolean moveNode(int id, int oldParentId, int newParentId, int nodeIndex){
		try {
			if(oldParentId==newParentId){
				// �������б�
				String newHql="from TbField a where a.parentId="+newParentId+" and a.fieldId!="+id+" order by a.numOrder";
				List newList=getCommonBiz().findHQL(newHql);
				if(null==newList){
					newList=new ArrayList();
				}
				// ���������
				TbField TbField=(TbField)getCommonBiz().get(TbField.class, new Long(id));
				TbField.setParentId(new Long(newParentId));
				// ����������
				newList.add(nodeIndex, TbField);
				update(newList);
				// ����
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
	// ��������
	private void update(List list) throws Exception{
		if(null!=list){
			TbField temp=null;
			for (int i = 0; i < list.size(); i++) {
				temp=(TbField)list.get(i);
				temp.setNumOrder(new Long(i+1));
				getCommonBiz().merge(temp);
			}
		}
	}
}