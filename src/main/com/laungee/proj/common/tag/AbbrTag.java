package com.laungee.proj.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.taglib.TagUtils;

/**
 * 
 * @author Administrator
 * 
 */
public class AbbrTag extends TagSupport {

	// 原字符串
	private String value;
	// 截断长度
	private int length;
	// 替代字符
	private String last;

	public int doStartTag() throws JspException {
		// 请求对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		// 分页对象
		if(null==last){
			last="..";
		}
		try {
			TagUtils.getInstance().write(pageContext,abbr(value,length,last));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 初始化
		value=null;
		// 返回
		return 0;
	}
	
	private String abbr(String str, int width, String ellipsis) {
		if (str == null || "".equals(str)) {
			return "";
		}
		if(null==ellipsis||"null".equals(ellipsis)){
			ellipsis="";
		}
		int d = 0;
		int n = 0;
		for (; n < str.length(); n++) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width) {
				break;
			}
		}
		if (d <= width) {
			return str;
		}
		return str = str.substring(0, n) + ellipsis;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setLast(String last) {
		this.last = last;
	}
}
