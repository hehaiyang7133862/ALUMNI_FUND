package com.laungee.proj.manage.web.action;

import java.util.List;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrChildPreEditAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		// ���
		String id=getRequest().getParameter("id");
		// ������Ϣ
		if(null==id||"".equals(id))
			return null;
		TbField tbAttribute=(TbField)getCommonBo().get(TbField.class, new Long(id));
		getRequest().setAttribute("bean_attr", tbAttribute);
		// ����
		return SUCCESS;
	}

}
