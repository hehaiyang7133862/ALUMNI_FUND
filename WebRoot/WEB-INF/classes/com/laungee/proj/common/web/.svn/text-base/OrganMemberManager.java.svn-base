package com.laungee.proj.common.web;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbCommunity;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.model.TbOrgan;
import com.laungee.proj.common.model.TbOrganMember;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbUser;

public class OrganMemberManager extends BaseManager {
	private static OrganMemberManager manager=null;
	private OrganMemberManager(){
		
	}
	public static OrganMemberManager getInstance(){
		if(manager==null){
			manager= new OrganMemberManager();
		}
		return manager;
	}
	public String memberCount(String organId) throws Exception {
		String text="";
		if(organId!=null&&!"".equals(organId)){
			TbCommunity commOrgan=(TbCommunity)getCommonBiz().get(TbCommunity.class, new Long(organId));
			if(commOrgan!=null){
				if(commOrgan.getOrganType()==1){
					String hql="select count (*) from vw_alumni_organ";
					Object obj=this.getCommonBo().getSQLUnique(hql);
					hql="select count(*) from tb_un_alumni a where a.is_jihuo = 1";
					Object unobj=this.getCommonBo().getSQLUnique(hql);
					if(obj!=null&&unobj!=null){
						text=unobj+"|"+obj;
					}else {
						text="0|0";
					}
				}else {
					

//					//得到所有成员
//					List memSet=this.getCommonBo().findHQL("from TbOrganMember a where a.tbCommunity.communityId="+community.getCommunityId());
					//密级
					
					int jihuo=0;
					int total=0;
					//总人数
					String sql="select count(*) from tb_organ_member a where a.community_id="+commOrgan.getCommunityId()+" and (a.is_repeat is null or a.is_repeat=0)";
					Object count=this.getCommonBo().getSQLUnique(sql);
					if(count!=null&&!"".equals(count)){
						total=Integer.parseInt(count.toString());
					}
					//未激活人数
					sql="select count(*) from tb_organ_member a where a.community_id="+commOrgan.getCommunityId()+" and a.un_alumni_id in (select un_alumni_id  from tb_un_alumni b where b.state_cid is null or b.state_cid=0) and (a.is_repeat is null or a.is_repeat=0)";
					count=this.getCommonBo().getSQLUnique(sql);
					if(count!=null&&!"".equals(count)){
						jihuo=Integer.parseInt(count.toString());
					}
					text+="【"+jihuo+"|"+total+"】";
				
					
					
//					//得到所有成员
//					List memSet=this.getCommonBiz().findHQL("from TbOrganMember a where a.tbCommunity.communityId="+commOrgan.getCommunityId());
//					if(memSet!=null&&memSet.size()!=0){
//						int jihuo=0;
//						int total=memSet.size();
//						for (Iterator iterator = memSet.iterator(); iterator.hasNext();) {
//							TbOrganMember member=(TbOrganMember) iterator.next();
//							if(member!=null){
//								TbUnAlumni unAlumni=member.getTbUnAlumni();
//								if(unAlumni!=null){
//									if(unAlumni.getIsJihuo()!=null&&unAlumni.getIsJihuo()==1){
//										jihuo++;
//									}
//								}
//							}
//						}
//						text=jihuo+"|"+total;
//					}else {
//						text="0|0";
//					}
				}
			}
			if("".equals(text)){
				text="0|0";
			}
		}
		return text;
	}
}
