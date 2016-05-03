package com.laungee.proj.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbFields;


public class FieldManagers extends BaseManager{
//	// ���Լ���
//	private static Map map=new HashMap();
//	static{
//		map=new HashMap();
//	}
	private FieldManagers(){
	}
	public static FieldManagers getInstance(){
		return new FieldManagers();
	}
	// ��ѯ����
	public List findList(String code) throws Exception{
		/*
		if(!map.keySet().contains(code)){
			String hql="from TbFields a where a.tbField.code='"+code+"' order by a.numOrder";
			List list=getCommonBo().findHQL(hql);
			map.put(code, list);
		}
		// ����
		return (List)map.get(code);*/
		String hql="from TbFields a where a.tbField.code='"+code+"'";
		List list=getCommonBo().findHQL(hql);
		// ����
		return list;
	}
//	// ���ü���
//	public void setList(String code) throws Exception{
//		String hql="from TbFields a where a.tbField.code='"+code+"' order by a.numOrder";
//		List list=getCommonBo().findHQL(hql);
//		map.put(code, list);
//	}
	// ���ݴ����ѯ
	public TbFields getByCode(String code) throws Exception{
		String hql="from TbFields a where rownum=1 and a.code='"+code+"'";
		TbFields tbField=(TbFields)getCommonBo().getHQLUnique(hql);
		return tbField;
	}
	// ���ݱ�Ų�ѯ
	public TbFields getById(Long id) throws Exception{
		TbFields tbField=(TbFields)getCommonBo().get(TbFields.class, id);
		return tbField;
	}

	// ���ݸ���Ų�ѯ
	public List getByParentId(Long id) throws Exception{
		String hql="from TbFields a where a.parentId = "+id;
		List tempList=this.getCommonBiz().findHQL(hql);
		return tempList;
	}
}
