package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.web.FieldManagers;

public class FieldsEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String id=request.getParameter("id");
		// 编号
		String code=request.getParameter("code");
		// 名称
		String fieldName=request.getParameter("fieldName");
		// 赋值
		String fieldValue=request.getParameter("fieldValue");
		// 图标
		String fieldIcon=request.getParameter("fieldIcon");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbFields tb=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
		
		tb.setFieldName(fieldName);
		tb.setFieldValue(fieldValue);
		tb.setFieldIcon(fieldIcon);
		tb.setMemo(memo);
		// 保存
		TbFields obj=(TbFields)getCommonBo().update(tb);
//		FieldManagers.getInstance().setList(obj.getTbField().getCode());
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String pre() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 编号
		String id=request.getParameter("id");
		// 属性信息
		TbFields tbAttribute=(TbFields)getCommonBo().get(TbFields.class, new Long(id));
		getRequest().setAttribute("bean", tbAttribute);
		// 返回
		return INPUT;
	}
}
