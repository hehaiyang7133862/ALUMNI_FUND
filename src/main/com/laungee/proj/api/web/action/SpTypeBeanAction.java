package com.laungee.proj.api.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodityType;

public class SpTypeBeanAction extends BaseAction {
	// ��Ʒ��������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityType bean = null;
		// ��Ʒ����ID
		String typeId = request.getParameter("id");
		if(typeId!=null && !"".equals(typeId)){
			try{
				bean = (TbCommodityType)this.getCommonBo().get(TbCommodityType.class, new Long(typeId));
			}catch(Exception e){}
		}
		if(bean==null){
			// ��������
			request.setAttribute("result", "error");
			// ����
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// ���ر�ʶ��all������ȫ�����νṹ��
		String flag = request.getParameter("flag");
		if(flag==null || !"all".equals(flag)){
			flag = "single";
		}
		request.setAttribute("flag", flag);
		// ����
		return SUCCESS;
	}
}