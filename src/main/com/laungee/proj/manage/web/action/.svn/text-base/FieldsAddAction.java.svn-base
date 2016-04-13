package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.web.FieldManagers;


public class FieldsAddAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 编号
		String code=request.getParameter("code");
		// 名称
		String fieldName=request.getParameter("fieldName");
		// 赋值
		String fieldValue=request.getParameter("fieldValue");
		// 图标
		String fieldIcon=request.getParameter("fieldIcon");
		// 叶子
		String isLeaf=request.getParameter("isLeaf");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbFields tb=new TbFields();
		tb.setCode(code);
		tb.setFieldName(fieldName);
		tb.setFieldValue(fieldValue);
		tb.setFieldIcon(fieldIcon);
		tb.setIsLeaf(isLeaf);
		tb.setMemo(memo);
		tb.setParentId(new Long(parentId));
		// 排序号
		String numHql="select max(a.numOrder) from TbFields a where a.parentId="+parentId;
		int num=getCommonBo().findHQLCount(numHql);
		tb.setNumOrder(new Long(num+1));
		// 保存
		TbFields obj=(TbFields) getCommonBo().save(tb);
		String hql="select a.tbField.code from TbFields a where a.fieldId="+obj.getFieldId();
		Object obj2=getCommonBo().getHQLUnique(hql);
//		FieldManagers.getInstance().setList(obj2.toString());
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String pre() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 设置父类编号
		this.getRequest().setAttribute("parentId", parentId);
		// 返回
		return INPUT;
	}
}
