package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.UserManager;

public class UserTag extends TagSupport {
	// 人员编号
	private Long value;
	// 类型
	private String type;
	
	// 标签处理
	public int doStartTag() throws JspException {
		try {
			if(null!=value){
				String name=UserManager.getInstance().findName(value);
				TagUtils.getInstance().write(pageContext,name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 初始化
		value=null;
		// 返回
		return 0;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
}