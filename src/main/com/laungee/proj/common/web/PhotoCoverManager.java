package com.laungee.proj.common.web;

import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbPersonphotodetil;

public class PhotoCoverManager extends BaseManager {
	private static PhotoCoverManager manager=null;
	private PhotoCoverManager(){
		
	}
	public static PhotoCoverManager getInstance(){
		if(null==manager){
			manager = new PhotoCoverManager();
		}
		return manager;
	}
	//得到封面照片
	public TbPersonphotodetil getCoverPhoto(String pid){
		TbPersonphotodetil pd=null;
		try {
			if(null!=pid&&!"".equals(pid)){
				//相册封面
				String hql="from TbPersonphotodetil a where a.photocover=1 and a.tbPersonphoto.personphotoId="+new Long(pid);
				pd=(TbPersonphotodetil) this.getCommonBo().getHQLUnique(hql);
				if(pd==null){
					//无封面照片，得到最新上传的图片
					hql="from TbPersonphotodetil a where a.tbPersonphoto.personphotoId="+new Long(pid)+" order by a.uploadTime desc";
					List tempList=this.getCommonBo().findHQL(hql);
					if(tempList!=null&&tempList.size()!=0){
						pd=(TbPersonphotodetil) tempList.get(0);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("******取相册封面照片时报错******");
		}
		return pd;
	}
}
