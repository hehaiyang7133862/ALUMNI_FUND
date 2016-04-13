package com.laungee.proj.manage.web.action;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrForwardAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		String id=this.getRequest().getParameter("id");
		if(null==id||"".equals(id))
			return null;
		TbField tbAttribute=(TbField)getCommonBo().get(TbField.class, new Long(id));
		getRequest().setAttribute("bean_attr", tbAttribute);
		// 返回
		return SUCCESS;
	}

}
