package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;

public class AttrChildAddAction extends BaseAction {
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
		// 是否为叶子
		String isLeaf=request.getParameter("isLeaf");
		// 属性备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbField tbAttribute=new TbField();
		tbAttribute.setFieldName(attrName);
		tbAttribute.setCode(code);
		tbAttribute.setMemo(memo);
		tbAttribute.setIsLeaf(isLeaf);
		if(null!=parentId&&!"".equals(parentId)){
			tbAttribute.setParentId(new Long(parentId));
		}
		// 保存属性
		getCommonBo().save(tbAttribute);
		// 返回
		return SUCCESS;
	}

}
