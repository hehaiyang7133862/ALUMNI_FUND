package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.PersonPhotoManager;
import com.laungee.proj.common.web.PhotoCountManager;

public class PhotoCountTag extends TagSupport {
	// ID
	private String value;
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			int count=PhotoCountManager.getInstance().photoCount(value);
			TagUtils.getInstance().write(pageContext,count+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return SKIP_BODY;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
