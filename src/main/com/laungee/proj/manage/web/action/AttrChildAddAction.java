package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrChildAddAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// �������
		HttpServletRequest request=ServletActionContext.getRequest();
		// ������
		String parentId=request.getParameter("parentId");
		// ��������
		String attrName=request.getParameter("attrName");
		// ���Ա��
		String code=request.getParameter("code");
		// �Ƿ�ΪҶ��
		String isLeaf=request.getParameter("isLeaf");
		// ���Ա�ע
		String memo=request.getParameter("memo");
		// ������Ϣ
		TbField tbAttribute=new TbField();
		tbAttribute.setFieldName(attrName);
		tbAttribute.setCode(code);
		tbAttribute.setMemo(memo);
		tbAttribute.setIsLeaf(isLeaf);
		if(null!=parentId&&!"".equals(parentId)){
			tbAttribute.setParentId(new Long(parentId));
		}
		// ��������
		getCommonBo().save(tbAttribute);
		// ����
		return SUCCESS;
	}

}
