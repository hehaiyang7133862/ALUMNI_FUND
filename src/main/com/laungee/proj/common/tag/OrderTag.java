package com.laungee.proj.common.tag;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.laungee.proj.common.util.EncryptUtil;
import com.laungee.proj.common.util.IEncryptUtil;
import com.laungee.proj.common.util.PaginationDto;
import com.laungee.proj.common.util.StringUtils;


public class OrderTag extends BodyTagSupport implements IEncryptUtil {
	// 请求地址
	private String action;
	// 排序字段
	private String sort;
	private String name;
	// 排序名称
	private Object title;
	// 参数列表_S
	private PaginationDto dto;
	
	public int doStartTag() throws JspException {
		if(null==title){
			return super.doStartTag();
		}
		try {
			// 请求对象
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			boolean nopage=true;
			// 分页对象
			if(null==dto){
				dto=(PaginationDto)request.getAttribute("p");
			}
			if(null==dto){
				dto=new PaginationDto();
				//排序
				String order=request.getParameter("order");
				if(order==null||"".equals(order)){
					order="none";
				}
				dto.setOr(order);
				//参数
				String st=request.getParameter("st");
				if(st==null||"".equals(st)){
					st=EncryptUtil.encrypt(SORT_KEY+name);
				}
				dto.setSt(st);
				
				/* 请求参数 */
				StringBuffer parts = new StringBuffer();
				String parName;
				String[] parValues;
				// 循环获取参数
				for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
					// 参数名称
					parName = e.nextElement().toString();
					if (!dto.getRemove().contains(parName) && !parName.startsWith("dels")){
						try {
							parValues = request.getParameterValues(parName);
							if(null!=parValues && parValues.length == 1){
								request.setAttribute(parName, parValues[0]);
							}
							if(null!=parValues){
								request.setAttribute(parName+"s", parValues);
							}
							for (int i = 0; i < parValues.length; i++) {
								parts.append(parName + "=" + URLEncoder.encode(parValues[i], "UTF-8")+ "&");
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				// 部分参数
				dto.setPart(new StringUtils().subpagepar("&"+parts.toString()));
			}else{
				nopage=false;
			}
			// 请求地址
			String url="";
			if(null!=action){
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
			    url=basePath+action+"?"+dto.getPart();
			}
			// 排序类型
			String className="";
			String sort=EncryptUtil.encrypt(SORT_KEY+name);
			url+=dto.getSst()+"="+sort+"&"+dto.getSor()+"=";
			// 处理排序
			if(sort.equals(dto.getSt())){
				if("none".equalsIgnoreCase(dto.getOr())){
					className="sort_none";
					url+="desc";
				}
				else if("desc".equalsIgnoreCase(dto.getOr())){
					className="sort_desc";
					url+="asc";
				}
				else if("asc".equalsIgnoreCase(dto.getOr())){
					className="sort_asc";
					url+="none";
				}
			}
			else{
				className="sort_none";
				url+="desc";
			}
			// 设置标题
			TagUtils.getInstance().write(pageContext,title.toString());
			String a="";
			if(nopage){
				// URL链接
				a="<a href=\""+url+"\" class=\""+className+"\" onclick=\"MyMask.show();\"></a>";
			}else{
				// URL链接
				a="<a href=\""+url+"&size="+dto.getSize()+"&curr="+dto.getCurr()+"\" class=\""+className+"\" onclick=\"MyMask.show();\"></a>";
			}
			TagUtils.getInstance().write(pageContext,a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		dto=null;
		sort=null;
		name=null;
		title=null;
		return SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			// 分页对象
			if(null==dto){
				dto=(PaginationDto)request.getAttribute("p");
			}
			// 请求地址
			String url="";
			if(null!=action){
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
			    url=basePath+action+"?"+dto.getPart();
			}
			// 排序类型
			String className="";
			String sortName=EncryptUtil.encrypt(SORT_KEY+this.sort);
			url+=dto.getSst()+"="+sortName+"&"+dto.getSor()+"=";
			// 处理排序
			if(sortName.equals(dto.getSt())){
				if("none".equalsIgnoreCase(dto.getOr())){
					className="sort_none";
					url+="desc";
				}
				else if("desc".equalsIgnoreCase(dto.getOr())){
					className="sort_desc";
					url+="asc";
				}
				else if("asc".equalsIgnoreCase(dto.getOr())){
					className="sort_asc";
					url+="none";
				}
			}
			else{
				className="sort_none";
				url+="desc";
			}
			// 输出对象
			BodyContent bodyContent = super.getBodyContent();
			String text=this.bodyContent.getString();
			JspWriter out = bodyContent.getEnclosingWriter();
			// 设置标题
			out.print(text);
			// URL链接
			out.print("<a href=\""+url+"&size="+dto.getSize()+"&curr="+dto.getCurr()+"\" class=\""+className+"\" onclick=\"MyMask.show();\"></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		dto=null;
		sort=null;
		name=null;
		title=null;
		return super.doEndTag();
	}

	public void setAction(String action) {
		this.action = action;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setDto(PaginationDto dto) {
		this.dto = dto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(Object title) {
		try {
			this.title = ExpressionEvaluatorManager.evaluate("title", title.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
			
		} 

	}
}