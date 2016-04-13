package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.OrganMemberManager;

public class OrganMemberTag extends TagSupport{
	
	private String organId;
	
	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public int doStartTag() throws JspException {
		try {
			String count=OrganMemberManager.getInstance().memberCount(organId);
			TagUtils.getInstance().write(pageContext,count);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.doStartTag();
	}
}
