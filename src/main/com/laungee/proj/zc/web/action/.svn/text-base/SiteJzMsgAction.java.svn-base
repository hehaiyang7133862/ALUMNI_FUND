package com.laungee.proj.zc.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteJzMsg;
import com.laungee.proj.common.util.DateUtil;

public class SiteJzMsgAction extends BaseAction {
	// 众筹网捐赠寄语管理
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbSiteJzMsg a where 1=1";
		List pars = this.getList();
		// 捐赠寄语
		String msg = request.getParameter("msg");
		if(msg!=null && !"".equals(msg)){
			hql += " and a.msgCnt like ?";
			pars.add("%"+msg+"%");
			request.setAttribute("msg", msg);
		}
		// 捐赠人
		String msgFrom = request.getParameter("msgFrom");
		if(msgFrom!=null && "1".equals(msgFrom)){
			hql += " and a.msgFrom like ?";
			pars.add("%"+msgFrom+"%");
			request.setAttribute("msgFrom", msgFrom);
		}
		// 对外发布
		String publish = request.getParameter("publish");
		if(publish!=null && "1".equals(publish)){
			hql += " and a.publishFlag='1'";
		}else if(publish!=null && "0".equals(publish)){
			hql += " and (a.publishFlag is null or a.publishFlag<>'1')";
		}
		request.setAttribute("publish", publish);
		// 默认排序
		hql += " order by a.msgTime desc,a.msgId desc";
		// 执行查询
		List beanList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("beanList", beanList);
		// 返回
		return "list";
	}
	
	// 众筹网捐赠寄语新建、编辑
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 对象
		TbSiteJzMsg bean = null;
		// msgId
		String msgId = request.getParameter("id");
		if(msgId!=null && !"".equals(msgId)){
			try{
				bean = (TbSiteJzMsg)this.getCommonBo().get(TbSiteJzMsg.class, new Long(msgId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return INPUT;
	}
	
	// 众筹网捐赠寄语新建、编辑保存
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 对象
		TbSiteJzMsg bean = null;
		// msgId
		String msgId = request.getParameter("msgId");
		if(msgId!=null && !"".equals(msgId)){
			try{
				bean = (TbSiteJzMsg)this.getCommonBo().get(TbSiteJzMsg.class, new Long(msgId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteJzMsg();
		}
		// 捐赠寄语
		String msgCnt = request.getParameter("msgCnt");
		bean.setMsgCnt(msgCnt);
		// 捐赠人
		String msgFrom = request.getParameter("msgFrom");
		bean.setMsgFrom(msgFrom);
		// 捐赠时间
		String msgTime = request.getParameter("msgTime");
		Date msgTimeDate = null;
		if(msgTime!=null && !"".equals(msgTime)){
			try{
				msgTimeDate = DateUtil.toDate(msgTime,"yyyy-MM-dd");
			}catch(Exception e){}
		}
		bean.setMsgTime(msgTimeDate);
		// 对外发布
		String publishFlag = request.getParameter("publishFlag");
		if(publishFlag!=null && "1".equals(publishFlag)){
			bean.setPublishFlag("1");
		}else{
			bean.setPublishFlag("0");
		}
		// 备注
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// 保存
		try{
			bean = (TbSiteJzMsg)this.getCommonBo().saveOrUpdate(bean);
			// 返回
			return SUCCESS;
		}catch(Exception e){
			request.setAttribute("bean", bean);
			// 返回
			return INPUT;
		}
	}
	
	// 众筹网捐赠寄语删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 对象
		TbSiteJzMsg bean = null;
		// msgId
		String msgId = request.getParameter("id");
		if(msgId!=null && !"".equals(msgId)){
			try{
				bean = (TbSiteJzMsg)this.getCommonBo().get(TbSiteJzMsg.class, new Long(msgId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		// 返回
		return execute();
	}
	
}