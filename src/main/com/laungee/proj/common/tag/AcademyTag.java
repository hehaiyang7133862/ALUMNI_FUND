package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.AcademyManager;

public class AcademyTag extends TagSupport {
	// 校友编号
	private Long value;
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			boolean flag=AcademyManager.getInstance().check(value);
			String text="";
			if(flag){
				text="是";
			}
			else{
				text="否";
			}
			TagUtils.getInstance().write(pageContext,text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return SKIP_BODY;
	}
	public void setValue(Long value) {
		this.value = value;
	}
}
