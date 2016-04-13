package com.laungee.proj.common.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import sun.security.jca.GetInstance;

import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.web.FieldManager;
import com.sun.org.apache.bcel.internal.generic.Select;

public class FieldViewTag extends TagSupport {
	// 父类
	private String value;
	// 格式：ICON-NAME-VALUE
	private String pattern;
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			if(pattern==null||"".equals(pattern)){
				pattern="NAME";
			}
			if(value==null){
				value="";
			}
			String tag=view();
			if(null==tag){
				tag="";
			}
			TagUtils.getInstance().write(pageContext,tag);
			// 赋值
			value =null;
			// 格式：ICON,NAME,VALUE
			pattern =null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// VIEW
	private String view(){
		String str = "";
		if("".equals(value)||"".equals(value)){
			str=value;
			return str;
		}
		if (value != null && !"".equals(value)) {
			String[] ss = value.split("[\\pP‘’“”]");
			if (ss != null && ss.length != 0) {
				for (int i = 0; i < ss.length; i++) {
					String temp = ss[i];
					Object tempObj = null;
					TbField bean=null;
					try {
						if(temp!=null&&!"".equals(temp)){
							bean = FieldManager.getInstance().getById(new Long(temp));
							if(pattern.toUpperCase().indexOf("NAME")!=-1){
								tempObj=bean.getFieldName();
							}
							if(pattern.toUpperCase().indexOf("VALUE")!=-1){
								tempObj=(bean.getFieldValue());
							}
							if(pattern.toUpperCase().indexOf("CODE")!=-1){
								tempObj=(bean.getCode());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (tempObj != null) {
						if (i + 1 == ss.length) {
							str += tempObj;
						} else {
							str += tempObj + ",";
						}
					}
				}
			}
		}
		return str;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}