package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrChildEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		// 属性编号
		String attrId=request.getParameter("attrId");
		// 属性名称
		String attrName=request.getParameter("attrName");
		// 属性编号
		String code=request.getParameter("code");
		// 是否为叶子
		String isLeaf=request.getParameter("isLeaf");
		// 属性备注
		String memo=request.getParameter("memo");
		// 属性信息
		if(null==attrId||"".equals(attrId))
			return null;
		TbField tbAttribute=(TbField)getCommonBo().get(TbField.class, new Long(attrId));
		tbAttribute.setFieldName(attrName);
		tbAttribute.setCode(code);
		tbAttribute.setIsLeaf(isLeaf);
		tbAttribute.setMemo(memo);
		// 保存属性
		getCommonBo().update(tbAttribute);
		// 返回
		return SUCCESS;
	}

}
