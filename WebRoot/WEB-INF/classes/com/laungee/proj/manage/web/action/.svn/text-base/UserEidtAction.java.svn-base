package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommunity;
import com.laungee.proj.common.model.TbOrgan;
import com.laungee.proj.common.model.TbUser;
import com.laungee.proj.common.util.EncryptUtil;

public class UserEidtAction extends BaseAction {
	public String execute() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ID
		String id=request.getParameter("id");
		// 用户信息
		TbUser bean=null;
		if(null!=id && !"".equals(id)){
			bean=(TbUser)getCommonBo().get(TbUser.class, new Long(id));
		}
		else{
			bean=new TbUser();
			String userCard=request.getParameter("userCard");
			String isHql="select a from TbUser a where rownum=1 and a.userCard='"+userCard+"'";
			TbUser isTbUser=(TbUser)getCommonBo().getHQLUnique(isHql);
			if(null!=isTbUser){
				request.setAttribute("alert", "帐号已存在！");
				return SUCCESS;
			}
			// 帐号
			bean.setUserCard(userCard);
		}
		// 密码
		String userPwd=request.getParameter("userPwd");
		if(null!=userPwd && !"".equals(userPwd)&&!"******".equals(userPwd)){
			bean.setUserPwd(EncryptUtil.encrypt(userPwd));
		}
		// 是否为统一身份证
		String starCid=request.getParameter("starCid");
		bean.setStarCid(starCid);
		
		// 姓名
		String userName=request.getParameter("userName");
		bean.setUserName(userName);
		
		// 手机
		String relHandset=request.getParameter("relHandset");
		bean.setRelHandset(relHandset);
		// 电话
		String relTelephone=request.getParameter("relTelephone");
		bean.setRelTelephone(relTelephone);
		// 邮箱
		String relEmail=request.getParameter("relEmail");
		bean.setRelEmail(relEmail);
		// 备注
		String memo=request.getParameter("memo");
		bean.setMemo(memo);
		
		// 密级
		String alumnilevel=request.getParameter("alumnilevel");
		if(alumnilevel!=null&&!alumnilevel.equals("")){
			bean.setAlumniLevel(new Long(alumnilevel));
		}
		// 有效性
		String isopen=request.getParameter("isopen");
		if(isopen!=null&&!isopen.equals("")){
			bean.setUserOpen(new Long(isopen));
		}
		//校友会机构
		String strid="";
		String strname="";
		String organId[]=request.getParameterValues("organId");
		if(organId!=null&&organId.length>0){
			 if(organId.length==1){
				 TbCommunity tbOrgan=(TbCommunity) this.getCommonBo().get(TbCommunity.class, new Long(organId[0].toString()));
			     strid+=organId[0].toString();
				 strname+=tbOrgan.getName();
			 }else{
				 for(int i=0;i<organId.length;i++){
					 TbCommunity tbOrgan=(TbCommunity) this.getCommonBo().get(TbCommunity.class, new Long(organId[i].toString()));
					 if(i==0){
						 if(organId[i]!=null){
							 strid+=organId[i].toString()+",";
						     strname+=tbOrgan.getName()+",";
						 }
					 }else if(i==organId.length-1){
						 if(organId[i]!=null){
						    strid+=organId[i].toString();
					        strname+=tbOrgan.getName();
						 }
					 }else{
						 if(organId[i]!=null){
						   strid+=organId[i].toString()+",";
					        strname+=tbOrgan.getName()+",";
						 }
					 }
				 }
			 }
			 bean.setAlumniOrgan(strid);
			 bean.setAlumniOrganname(strname);
		}
		// 执行
		getCommonBo().saveOrUpdate(bean);
		// 返回
		return SUCCESS;
	}
	public String pre() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ID
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		// 用户信息
		if(null!=id && !"".equals(id)){
			TbUser bean=(TbUser)getCommonBo().get(TbUser.class, new Long(id));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("alumniLevel",flag);
		//查询校友机构管理信息
		List organList1=this.getCommonBo().findHQL("from TbCommunity a where a.organType=1"
         +" order by a.communityId");
		request.setAttribute("organList1",organList1);
		List organList2=this.getCommonBo().findHQL("from TbCommunity a where a.organType=2 and organLevel=3"
         +" order by a.communityId");
		request.setAttribute("organList2",organList2);
		List organList3=this.getCommonBo().findHQL("from TbCommunity a where a.organType=3 and organLevel=3"
        +" order by a.communityId");
		request.setAttribute("organList3",organList3);
		List organList4=this.getCommonBo().findHQL("from TbCommunity a where a.organType=4 and organLevel=3"
         +" order by a.communityId");
		request.setAttribute("organList4",organList4);
		List organList7=this.getCommonBo().findHQL("from TbCommunity a where a.organType=7 and organLevel=3"
		         +" order by a.communityId");
				request.setAttribute("organList7",organList7);
		// 执行
		return INPUT;
	}
	public String prepasswords() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ID
		String id=request.getParameter("id");
		// 用户信息
		if(null!=id && !"".equals(id)){
			TbUser bean=(TbUser)getCommonBo().get(TbUser.class, new Long(id));
			request.setAttribute("bean", bean);
		}
		// 执行
		return SUCCESS;
	}
	public String updatepassword() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ID
		String id=request.getParameter("id");
		// 用户信息
		if(null!=id && !"".equals(id)){
			TbUser bean=(TbUser)getCommonBo().get(TbUser.class, new Long(id));
			// 密码
			String userPwd=request.getParameter("userPwd");
			if(null!=userPwd && !"".equals(userPwd)&&!"******".equals(userPwd)){
				bean.setUserPwd(EncryptUtil.encrypt(userPwd));
			}
			// 手机
			String relHandset=request.getParameter("relHandset");
			bean.setRelHandset(relHandset);
			// 电话
			String relTelephone=request.getParameter("relTelephone");
			bean.setRelTelephone(relTelephone);
			// 邮箱
			String relEmail=request.getParameter("relEmail");
			bean.setRelEmail(relEmail);
			this.getCommonBo().update(bean);
			request.setAttribute("bean", bean);
		}
		
		request.setAttribute("alert", "修改成功！");
		// 执行
		return SUCCESS;
	}
}
