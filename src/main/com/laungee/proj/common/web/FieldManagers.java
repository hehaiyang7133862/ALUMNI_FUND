package com.laungee.proj.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbFields;


public class FieldManagers extends BaseManager{
//	// 属性集合
//	private static Map map=new HashMap();
//	static{
//		map=new HashMap();
//	}
	private FieldManagers(){
	}
	public static FieldManagers getInstance(){
		return new FieldManagers();
	}
	// 查询集合
	public List findList(String code) throws Exception{
		/*
		if(!map.keySet().contains(code)){
			String hql="from TbFields a where a.tbField.code='"+code+"' order by a.numOrder";
			List list=getCommonBo().findHQL(hql);
			map.put(code, list);
		}
		// 返回
		return (List)map.get(code);*/
		String hql="from TbFields a where a.tbField.code='"+code+"'";
		List list=getCommonBo().findHQL(hql);
		// 返回
		return list;
	}
//	// 设置集合
//	public void setList(String code) throws Exception{
//		String hql="from TbFields a where a.tbField.code='"+code+"' order by a.numOrder";
//		List list=getCommonBo().findHQL(hql);
//		map.put(code, list);
//	}
	// 根据代码查询
	public TbFields getByCode(String code) throws Exception{
		String hql="from TbFields a where rownum=1 and a.code='"+code+"'";
		TbFields tbField=(TbFields)getCommonBo().getHQLUnique(hql);
		return tbField;
	}
	// 根据编号查询
	public TbFields getById(Long id) throws Exception{
		TbFields tbField=(TbFields)getCommonBo().get(TbFields.class, id);
		return tbField;
	}

	// 根据父编号查询
	public List getByParentId(Long id) throws Exception{
		String hql="from TbFields a where a.parentId = "+id;
		List tempList=this.getCommonBiz().findHQL(hql);
		return tempList;
	}
}
