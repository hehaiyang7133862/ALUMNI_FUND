package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.UserManager;

public class UserTag extends TagSupport {
	// ��Ա���
	private Long value;
	// ����
	private String type;
	
	// ��ǩ����
	public int doStartTag() throws JspException {
		try {
			if(null!=value){
				String name=UserManager.getInstance().findName(value);
				TagUtils.getInstance().write(pageContext,name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ʼ��
		value=null;
		// ����
		return 0;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
}