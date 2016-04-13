package com.laungee.proj.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.web.DefaultImageManager;

public class DefaultImageTag extends TagSupport {
	//社区名称
	private String value;
	//图片名称
	private String imgName;
	//标签处理
	@Override
	public int doStartTag() throws JspException {
		if(null!=value&&null!=imgName){
			String fileName=DefaultImageManager.getInstance().doDefaultImage(imgName,value);
			TagUtils.getInstance().write(pageContext, fileName);
		}
		//初始化
		imgName="";
		//初始化
		value="";
		//返回
		return 0;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
