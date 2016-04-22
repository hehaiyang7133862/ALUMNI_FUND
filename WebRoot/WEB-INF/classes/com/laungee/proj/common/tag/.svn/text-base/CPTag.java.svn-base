package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class CPTag  extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//得到当前系统名称--例如华东理工大学
	@Override
	public int doStartTag() throws JspException {
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		//版权标签
		String sql="select a.field_value from tb_field a where a.code=upper('copyrighttag')";
		String tag="";
		try {
			tag = (biz.getSQLUnique(sql)).toString();
		} catch (Exception e) {
			//默认为校友会
			tag="校友会";
		}
		if(tag==null||"".equals(tag)){
			//默认为校友会
			tag="校友会";
		}
		TagUtils.getInstance().write(pageContext,tag.toString());
		return super.doStartTag();
	}
}
