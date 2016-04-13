package com.laungee.proj.manage.web.action;

import java.util.List;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrChildPreEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 编号
		String id=getRequest().getParameter("id");
		// 属性信息
		if(null==id||"".equals(id))
			return null;
		TbField tbAttribute=(TbField)getCommonBo().get(TbField.class, new Long(id));
		getRequest().setAttribute("bean_attr", tbAttribute);
		// 返回
		return SUCCESS;
	}

}
