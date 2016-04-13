package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.FoundManager;

public class TimeTag extends TagSupport {
	// 对象编号
	private Long value;
	// 类型
	private String table;
	//id字段
	private String idField;
	
	// 标签处理
	public int doStartTag() throws JspException {
		try {
			if(null!=value&&table!=null&&idField!=null){
				String name=FoundManager.getInstance().findName(table,idField,value);
				TagUtils.getInstance().write(pageContext,name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 初始化
		value=null;
		// 返回
		return 0;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
}