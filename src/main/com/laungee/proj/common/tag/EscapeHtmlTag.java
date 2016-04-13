package com.laungee.proj.common.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

public class EscapeHtmlTag extends TagSupport {

	// 需要转义的内容Attribute保存名称
	private String var;
	// 需要转义的内容
	private String value;
	// 需要转义的内容 移除标签
	private Boolean removeTarget;
	// 需要转义的内容 修改上传文件相对路径为绝对路径
	private Boolean changePath;

	public int doStartTag() throws JspException {
		// 返回值
		String result = "";
		// 执行转义
		if(value!=null && !"".equals(value)){
			result = value;
			// 移除标签
			if(removeTarget){
				Pattern pattern = Pattern.compile("<[^>]+>",Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(result);
				result = matcher.replaceAll("").replace("&nbsp;", "");
			}
			// 修改相对路径为绝对路径
			if(changePath){
				// 获取请求对象
				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				// 获取请求对象BASE PATH
				String basePath = request.getScheme()+"://"+request.getServerName();
				if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80)
						|| ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){
					basePath += request.getContextPath()+"/";
				}else{
					basePath += ":"+request.getServerPort()+request.getContextPath()+"/";
				}
				result = result.replace("\"/upload", "\""+basePath+"upload").replace("'/upload", "'"+basePath+"upload");
			}
			// 移除换行符
			result = result.replaceAll("(\r\n|\r|\n|\n\r)", "");
			// &
			result = result.replace("&", "&#38;");
			// "
			result = result.replace("\"", "&#34;");
			// '
			result = result.replace("'", "&#39;");
			// \
			result = result.replace("\\", "&#92;");
			// /
			result = result.replace("/", "&#47;");
			// 空格
			result = result.replace(" ", "&#32;");
			// <
			result = result.replace("<", "&#60;");
			// >
			result = result.replace(">", "&#62;");
		}
		if(var!=null && !"".equals(var)){
			pageContext.setAttribute(var, result);
		}
		// 输出返回值
		try {
			TagUtils.getInstance().write(pageContext,result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 初始化
		var = null;
		value = null;
		removeTarget = false;
		changePath = false;
		// 返回
		return 0;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setRemoveTarget(Boolean removeTarget) {
		this.removeTarget = removeTarget;
	}
	
	public void setChangePath(Boolean changePath) {
		this.changePath = changePath;
	}
	
}