package com.laungee.proj.zc.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteJzMsg;
import com.laungee.proj.common.util.DateUtil;

public class SiteJzMsgAction extends BaseAction {
	// �ڳ��������������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ѯ
		String hql = "from TbSiteJzMsg a where 1=1";
		List pars = this.getList();
		// ��������
		String msg = request.getParameter("msg");
		if(msg!=null && !"".equals(msg)){
			hql += " and a.msgCnt like ?";
			pars.add("%"+msg+"%");
			request.setAttribute("msg", msg);
		}
		// ������
		String msgFrom = request.getParameter("msgFrom");
		if(msgFrom!=null && "1".equals(msgFrom)){
			hql += " and a.msgFrom like ?";
			pars.add("%"+msgFrom+"%");
			request.setAttribute("msgFrom", msgFrom);
		}
		// ���ⷢ��
		String publish = request.getParameter("publish");
		if(publish!=null && "1".equals(publish)){
			hql += " and a.publishFlag='1'";
		}else if(publish!=null && "0".equals(publish)){
			hql += " and (a.publishFlag is null or a.publishFlag<>'1')";
		}
		request.setAttribute("publish", publish);
		// Ĭ������
		hql += " order by a.msgTime desc,a.msgId desc";
		// ִ�в�ѯ
		List beanList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("beanList", beanList);
		// ����
		return "list";
	}
	
	// �ڳ������������½����༭
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ����
		TbSiteJzMsg bean = null;
		// msgId
		String msgId = request.getParameter("id");
		if(msgId!=null && !"".equals(msgId)){
			try{
				bean = (TbSiteJzMsg)this.getCommonBo().get(TbSiteJzMsg.class, new Long(msgId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ����
		return INPUT;
	}
	
	// �ڳ������������½����༭����
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ����
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
		// ��������
		String msgCnt = request.getParameter("msgCnt");
		bean.setMsgCnt(msgCnt);
		// ������
		String msgFrom = request.getParameter("msgFrom");
		bean.setMsgFrom(msgFrom);
		// ����ʱ��
		String msgTime = request.getParameter("msgTime");
		Date msgTimeDate = null;
		if(msgTime!=null && !"".equals(msgTime)){
			try{
				msgTimeDate = DateUtil.toDate(msgTime,"yyyy-MM-dd");
			}catch(Exception e){}
		}
		bean.setMsgTime(msgTimeDate);
		// ���ⷢ��
		String publishFlag = request.getParameter("publishFlag");
		if(publishFlag!=null && "1".equals(publishFlag)){
			bean.setPublishFlag("1");
		}else{
			bean.setPublishFlag("0");
		}
		// ��ע
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// ����
		try{
			bean = (TbSiteJzMsg)this.getCommonBo().saveOrUpdate(bean);
			// ����
			return SUCCESS;
		}catch(Exception e){
			request.setAttribute("bean", bean);
			// ����
			return INPUT;
		}
	}
	
	// �ڳ�����������ɾ��
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ����
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
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		// ����
		return execute();
	}
	
}