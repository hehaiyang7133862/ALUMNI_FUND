package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.PersonPhotoManager;

public class PersonPhotoTag extends TagSupport {
	// 现在时间
	private String nowDate;
	//创建时间
	private String createDate;
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			int count=PersonPhotoManager.getInstance().calcDate(nowDate,createDate);
			StringBuffer text=new StringBuffer();
			if(count<=3){
				//新相册
				text.append("<div class=\"fr\"><img class=\"new\" src=\"UI/images/space.gif\"></div>");
			}
			TagUtils.getInstance().write(pageContext,text.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return SKIP_BODY;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
