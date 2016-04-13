package com.laungee.proj.common.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;

public class PhotoCountManager extends BaseManager{
	private static PhotoCountManager manager=null;
	private PhotoCountManager(){
	}
	public static PhotoCountManager getInstance(){
		if(null==manager){
			manager = new PhotoCountManager();
		}
		return manager;
	}
	// ÕÕÆ¬ÊýÁ¿
	public int photoCount(String pid) throws Exception{
		int count=0;
		if(pid!=null&&!"".equals(pid)){
			String hql="from TbPersonphotodetil a where a.tbPersonphoto.personphotoId="+pid;
			List pList=getCommonBo().findHQL(hql);
			if(pList!=null){
				count=pList.size();
			}
		}
		return count;
	}
}
