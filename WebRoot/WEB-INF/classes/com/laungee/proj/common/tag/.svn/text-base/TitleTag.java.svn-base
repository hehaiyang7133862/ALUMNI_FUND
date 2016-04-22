package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class TitleTag  extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//得到当前系统名称--例如华东理工大学
	@Override
	public int doStartTag() throws JspException {
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		String sql="select a.field_value from tb_fields a where a.code=upper('copyrighttag')";
		String obj="";
		try {
			obj = (biz.getSQLUnique(sql)).toString();
		} catch (Exception e) {
			//默认为空
			obj="";
		}
		if(obj==null||"".equals(obj)){
			//默认为空
			obj="";
		}
		TagUtils.getInstance().write(pageContext,obj.toString());
		return super.doStartTag();
	}
}
