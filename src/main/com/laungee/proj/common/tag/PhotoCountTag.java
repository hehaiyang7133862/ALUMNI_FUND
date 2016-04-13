package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.PersonPhotoManager;
import com.laungee.proj.common.web.PhotoCountManager;

public class PhotoCountTag extends TagSupport {
	// ID
	private String value;
	// �����ǩ
	public int doStartTag() throws JspException {
		try {
			int count=PhotoCountManager.getInstance().photoCount(value);
			TagUtils.getInstance().write(pageContext,count+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ����
		return SKIP_BODY;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
