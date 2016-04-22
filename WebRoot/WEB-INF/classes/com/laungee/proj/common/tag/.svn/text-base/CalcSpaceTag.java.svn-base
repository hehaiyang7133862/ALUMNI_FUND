package com.laungee.proj.common.tag;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts2.ServletActionContext;

public class CalcSpaceTag extends TagSupport {
	// ID
	private String value;
	private int w;
	private int h;
	public void setW(int w) {
		this.w = w;
	}
	public void setH(int h) {
		this.h = h;
	}
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			int width=0;
			int height=0;
			if(null==value||"".equals(value)){
				width=28;
				height=30;
			}else {
				
				String realPath=ServletActionContext.getServletContext().getRealPath(value);
				File file=new File(realPath);
				
				if(file.exists()){
					BufferedImage bi=ImageIO.read(file);
					width=bi.getWidth();
					height=bi.getHeight();
					
				}else {
					width=28;
					height=30;
				}
			}
			String margin="";
			String top="";
			if((h-height)>0){
				top=((h-height)/2)+"px ";
			}else {
				top="-2px ";
			}
			if(top.equals("-2px ")){
				margin+=top+"0px -4px ";
			}else {
				margin+=top+"0px -2px ";
			}
			String left="";
			if((w-width)>0){
				left=((w-width)/2)+"px;";
			}else {
				left="0px;";
			}
			margin+=left;
			TagUtils.getInstance().write(pageContext,margin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return SKIP_BODY;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
