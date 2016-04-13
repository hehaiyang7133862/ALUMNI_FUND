package com.laungee.proj.common.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;

public class PersonPhotoManager extends BaseManager{
	private static PersonPhotoManager manager=null;
	private PersonPhotoManager(){
	}
	public static PersonPhotoManager getInstance(){
		if(null==manager){
			manager = new PersonPhotoManager();
		}
		return manager;
	}
	// 时间差
	public int calcDate(String nowDate,String createDate) throws Exception{
		int count=0;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=format.parse(nowDate);
		Date d2=format.parse(createDate);
		count=d1.getDate()-d2.getDate();
		return count;
	}// 照片数量
	public int photoCount(String pid) throws Exception{
		int count=0;
		String hql="from TbPersonphotodetil a where a.tbPersonphoto.personphotoId="+pid;
		List pList=getCommonBo().findHQL(hql);
		if(pList!=null){
			count=pList.size();
		}
		return count;
	}
}
