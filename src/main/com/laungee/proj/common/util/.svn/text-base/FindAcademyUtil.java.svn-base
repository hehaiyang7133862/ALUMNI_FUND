package com.laungee.proj.common.util;

import java.util.ArrayList;
import java.util.List;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbAcademy;


public class FindAcademyUtil extends BaseAction{
	  private List plist=new ArrayList();
		//返回一个指标体系集合
		public  List findAcademyData(Long parentid) throws Exception{
			if(parentid.equals(null)){
				
			}else{
					String hql="from TbAcademy a where  a.parentId="+parentid;
					List sublist=this.getCommonBo().findHQL(hql);
					for(int i=0;i<sublist.size();i++){
						TbAcademy tbAcademy=(TbAcademy) sublist.get(i);
						plist.add(tbAcademy);
					    findAcademyData(tbAcademy.getNodeId());
					}
			}
			return plist;
		}
}
