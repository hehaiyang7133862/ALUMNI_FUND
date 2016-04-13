package com.laungee.proj.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.util.PropertyUtil;

public class FieldManager extends BaseManager{
	// 属性集合
	//private static Map map;
	//static{
	//	map=new HashMap();
	//}
	private FieldManager(){
	}
	public static FieldManager getInstance(){
		return new FieldManager();
	}
	// 查询集合
	public List findList(String code) throws Exception{
		//if(!map.keySet().contains(code)){
			String hql="from TbField a where a.tbField.code='"+code+"' order by a.numOrder";
			List list=getCommonBo().findHQL(hql);
		//	map.put(code, list);
		//}
		// 返回
		//return (List)map.get(code);
			return list;
	}
	// 设置集合
	//public void setList(String code) throws Exception{
	//	String hql="from TbField a where a.tbField.code='"+code+"' order by a.numOrder";
	//	List list=getCommonBo().findHQL(hql);
	//	map.put(code, list);
	//}
	// 根据代码查询
	public TbField getByCode(String code) throws Exception{
		String hql="from TbField a where rownum=1 and a.code='"+code+"'";
		TbField tbField=(TbField)getCommonBo().getHQLUnique(hql);
		return tbField;
	}
	// 根据编号查询
	public TbField getById(Long id) throws Exception{
		TbField tbField=(TbField)getCommonBo().get(TbField.class, id);
		return tbField;
	}
	// 查询子类集合
	public Map findValue(String code) throws Exception{
		List list=this.findList(code);
		Map map=listToMap(list, TbField.class, "code", "fieldValue");
		return map;
	}
	// 查询所以节点
	public Map findNode() throws Exception{
		String hql="from TbField a";
		List list=getCommonBiz().findHQL(hql);
		Map map=listToMap(list, TbField.class, "fieldId", "code");
		return map;
	}
	private Map listToMap(List v,Object clzz,String key,String value){
		Map map=new HashMap();
		for(int i=0;i<v.size();i++){
			clzz = v.get(i);
			Object key1 = PropertyUtil.getPropertyValue(clzz, key);
			Object value1 = PropertyUtil.getPropertyValue(clzz, value);
			map.put(key1,value1);
		}
		return map;
	}
	// 根据父编号查询
	public List getByParentId(Long id) throws Exception{
		String hql="from TbField a where a.parentId = "+id;
		List tempList=this.getCommonBiz().findHQL(hql);
		return tempList;
	}
}
