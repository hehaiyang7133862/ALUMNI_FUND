package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrParentAddAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 属性名称
		String attrName=request.getParameter("attrName");
		// 属性编号
		String code=request.getParameter("code");
		// 属性备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbField tbAttribute=new TbField();
		tbAttribute.setFieldName(attrName);
		tbAttribute.setCode(code);
		tbAttribute.setMemo(memo);
		if(null!=parentId&&!"".equals(parentId)){
			tbAttribute.setParentId(new Long(parentId));
		}
		// 保存属性
		getCommonBo().save(tbAttribute);
		// 返回
		return SUCCESS;
	}

}
