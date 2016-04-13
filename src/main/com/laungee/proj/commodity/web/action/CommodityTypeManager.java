package com.laungee.proj.commodity.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbCommodityType;


public class CommodityTypeManager  extends BaseManager{
	// ��ѯ�ӽڵ�
	public boolean findNode(int id){
		try{
			// ��ѯ�Ӽ�����
			String hql="select count(*) from TbCommodityType a where a.parentId="+id;
			int count=getCommonBo().findHQLCount(hql);
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
			TbCommodityType bean=(TbCommodityType)this.getCommonBo().get(TbCommodityType.class, new Long(id));
			// ɾ����ɫ
			getCommonBo().delete(bean);
			// ��������
			String hql="from TbCommodityType a where a.parentId="+bean.getParentId()+" order by a.numOrder";
			List list=getCommonBo().findHQL(hql);
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
				String newHql="from TbCommodityType a where a.parentId="+newParentId+" and a.typeId!="+id+" order by a.numOrder";
				List newList=getCommonBo().findHQL(newHql);
				if(null==newList){
					newList=new ArrayList();
				}
				// ���������
				TbCommodityType bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
				bean.setParentId(new Long(newParentId));
				// ����������
				newList.add(nodeIndex, bean);
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
			TbCommodityType temp=null;
			for (int i = 0; i < list.size(); i++) {
				temp=(TbCommodityType)list.get(i);
				temp.setNumOrder(new Long(i+1));
				getCommonBo().merge(temp);
			}
		}
	}
}