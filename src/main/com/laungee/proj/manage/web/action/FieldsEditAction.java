package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.web.FieldManagers;

public class FieldsEditAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// ������
		String id=request.getParameter("id");
		// ���
		String code=request.getParameter("code");
		// ����
		String fieldName=request.getParameter("fieldName");
		// ��ֵ
		String fieldValue=request.getParameter("fieldValue");
		// ͼ��
		String fieldIcon=request.getParameter("fieldIcon");
		// ��ע
		String memo=request.getParameter("memo");
		// ������Ϣ
		TbFields tb=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
		
		tb.setFieldName(fieldName);
		tb.setFieldValue(fieldValue);
		tb.setFieldIcon(fieldIcon);
		tb.setMemo(memo);
		// ����
		TbFields obj=(TbFields)getCommonBo().update(tb);
//		FieldManagers.getInstance().setList(obj.getTbField().getCode());
		// ����
		return SUCCESS;
	}
	// Ԥ����
	public String pre() throws Exception{
		HttpServletRequest request=this.getRequest();
		// ���
		String id=request.getParameter("id");
		// ������Ϣ
		TbFields tbAttribute=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
		getRequest().setAttribute("bean", tbAttribute);
		// ����
		return INPUT;
	}
}
