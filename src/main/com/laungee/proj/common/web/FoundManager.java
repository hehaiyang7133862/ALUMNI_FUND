package com.laungee.proj.common.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.util.DateUtil;

public class FoundManager extends BaseManager {
	private FoundManager(){
	}
	public static FoundManager getInstance(){
		return new FoundManager();
	}
	public String findName(String table,String idName,Long arg0) throws Exception{
		String result="";
		try {
			String hql="select creationUser,creationTime,updateUser,updateTime from "+table+" a where "+idName+"="+arg0;
			Object[] objs=(Object[])getCommonBo().getHQLUnique(hql);
			if(objs!=null){
				int creationUser=Integer.parseInt(objs[0]+"");
				String creationTime=objs[1]+"";
				int updateUser=Integer.parseInt(objs[2]+"");
				Date updateTime=(Date) objs[3];
				
				//得到创建人姓名
				hql="select userName from TbUser a where a.userId=?";
				List pars=new ArrayList();
				pars.add(new Long(creationUser));
				Object cuserName=getCommonBo().getHQLUnique(hql, pars);
				//得到修改人姓名
				pars.clear();
				pars.add(new Long(updateUser));
				Object upuserName=getCommonBo().getHQLUnique(hql, pars);
				result+="<table class=\"lyt_view\">" +
						"<tr>" +
							"<td class=\"lyt_view_note\">创建人</td>" +
							"<td>"+cuserName+"</td>" +
							"<td class=\"lyt_view_note\">创建时间</td>" +
							"<td>"+creationTime+"</td>" +
						"</tr>"+
						"<tr>" +
							"<td class=\"lyt_view_note\">修改人</td>" +
							"<td>"+upuserName+"</td>" +
							"<td class=\"lyt_view_note\">最近修改</td>" +
							"<td>"+DateUtil.format(updateTime, "yyyy-MM-dd HH:mm:ss")+"</td>" +
						"</tr>"+
						"</table>";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
