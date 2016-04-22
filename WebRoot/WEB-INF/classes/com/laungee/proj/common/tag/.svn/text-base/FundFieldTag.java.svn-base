package com.laungee.proj.common.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;


import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.web.FieldManagers;

public class FundFieldTag extends TagSupport {
	// 父类
	private String lead;
	private Long leadId;
	private Long parentId;
	// 赋值
	private Long value;
	private String valueCode;
	private String valueDefault;
	private Long showId;
	private Long fid;
	// 类型：SELECT,OPTION,VIEW,CHECKBOX,RADIO
	private String type;
	// 格式：ICON-NAME-VALUE
	private String pattern;
	// 元素编号
	private String id;
	// 元素名称
	private String name;
	private String eleName;
	// 类选择器
	private String clazz;
	// 处理标签
	public int doStartTag() throws JspException {
		try {
			FieldManagers manager=FieldManagers.getInstance();
			// 父类
			if(null!=leadId){
				TbFields tbField=manager.getById(leadId);
				if(null!=tbField){
					lead=tbField.getCode();
				}
			}
			// 赋值
			if(null!=fid){
				value=fid;
			}
			if(null!=showId){
				value=showId;
			}
			if(null!=valueCode){
				TbFields tbField=manager.getByCode(valueCode);
				if(null!=tbField){
					value=tbField.getFieldId();
				}
			}
			// 默认值
			if(null==value && null!=valueDefault){
				TbFields tbField=manager.getByCode(valueDefault);
				if(null!=tbField){
					value=tbField.getFieldId();
				}
			}
			// 名称
			if(null!=eleName){
				name=eleName;
			}
			// 初始化
			if(null==pattern){
				pattern="NAME";
			}
			if(null==type){
				type="SELECT";
			}
			// 查询集合
			List list=null;
			if(parentId!=null&&!"".equals(parentId)){
				list=manager.getByParentId(parentId);
			}else{
				list=manager.findList(lead);
			}
			
			String tag=null;
			if(null!=list){
				// SELECT
				if("SELECT".equalsIgnoreCase(type) || null==type){
					tag=select(list);
				}
				// OPTION
				if("OPTION".equalsIgnoreCase(type)){
					tag=option(list);
				}
				// RADIO
				if("RADIO".equalsIgnoreCase(type)){
					tag=radio(list);
				}
				// VIEW
				if("VIEW".equalsIgnoreCase(type)){
					tag=view(list);
				}
			}
			if(null==tag){
				tag="";
			}
			TagUtils.getInstance().write(pageContext,tag);
			// 父类
			lead =null;
			leadId=null;
			// 赋值
			value =null;
			valueCode=null;
			valueDefault=null;
			showId =null;
			fid =null;
			// 类型：SELECT,OPTION,VIEW,CHECKBOX,RADIO
			type =null;
			// 格式：ICON,NAME,VALUE
			pattern =null;
			// 元素编号
			id =null;
			// 元素名称
			name =null;
			eleName =null;
			// 类选择器
			clazz =null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// SELECT
	private String select(List list){
		StringBuffer tag=new StringBuffer();
		tag.append("<select");
		if(null!=id){
			tag.append(" id=\""+id+"\"");
		}
		if(null!=name){
			tag.append(" name=\""+name+"\"");
		}
		if(null!=clazz){
			tag.append(" class=\""+clazz+"\"");
		}
		tag.append(">");
		tag.append("<option class=\"fnt_gray\" value=\"\">--请选择--</option>");
		// 选择项
		try {
			TbFields tb=null;
			for (int i = 0; i < list.size(); i++) {
				tb=(TbFields)list.get(i);
				tag.append("<option value=\""+tb.getFieldId()+"\"");
				// 值
				if(null==value){
					if("DEFAULT".equalsIgnoreCase(tb.getCode())){
						tag.append(" selected");
					}
				}
				else{ 
					if(tb.getFieldId().equals(value)){
						tag.append(" selected");
					}
				}
				tag.append(">");
				// 标签
				if(pattern.toUpperCase().indexOf("NAME")!=-1){
					tag.append(tb.getFieldName());
				}
				if(pattern.toUpperCase().indexOf("VALUE")!=-1){
					tag.append(tb.getFieldValue());
				}
				tag.append("</option>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tag.append("</select>");
		return tag.toString();
	}
	// OPTION
	private String option(List list){
		StringBuffer tag=new StringBuffer();
		// 选择项
		try {
			TbFields tb=null;
			for (int i = 0; i < list.size(); i++) {
				tb=(TbFields)list.get(i);
				tag.append("<option value=\""+tb.getFieldId()+"\"");
				// 值
				if(null==value){
					if("DEFAULT".equalsIgnoreCase(tb.getCode())){
						tag.append(" selected");
					}
				}
				else{ 
					if(tb.getFieldId().equals(value)){
						tag.append(" selected");
					}
				}
				tag.append(">");
				// 标签
				if(pattern.toUpperCase().indexOf("NAME")!=-1){
					tag.append(tb.getFieldName());
				}
				if(pattern.toUpperCase().indexOf("VALUE")!=-1){
					tag.append(tb.getFieldValue());
				}
				tag.append("</option>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag.toString();
	}
	// RADIO
	private String radio(List list){
		StringBuffer tag=new StringBuffer();
		// 请求对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
		try {
			TbFields tb=null;
			for (int i = 0; i < list.size(); i++) {
				tb=(TbFields)list.get(i);
				tag.append("<label for="+id+tb.getFieldId()+"><input type=\"radio\" style=\"width:20px;height:20px;\" ");
				if(null!=id){
					tag.append(" id=\""+id+tb.getFieldId()+"\"");
				}
				if(null!=name){
					tag.append(" name=\""+name+"\"");   
				}
				if(null!=clazz){
					tag.append(" class=\""+clazz+"\"");
				}
				tag.append(" value=\""+tb.getFieldId()+"\"");
				// 值
				if(null==value){
					if("DEFAULT".equalsIgnoreCase(tb.getCode())){
						tag.append(" checked");
					}
				}
				else{ 
					if(tb.getFieldId().equals(value)){
						tag.append(" checked");
					}
				}
				
				tag.append("/>");
				// 标签
				if(pattern.toUpperCase().indexOf("ICON")!=-1){
					tag.append("<image align=\"top\" src=\""+basePath+tb.getFieldIcon()+"\" />");
				}
				if(pattern.toUpperCase().indexOf("NAME")!=-1){
					tag.append(tb.getFieldName());
				}
				if(pattern.toUpperCase().indexOf("VALUE")!=-1){
					tag.append(tb.getFieldValue());
				}
				tag.append("</label>&nbsp;&nbsp;&nbsp;");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag.toString();
	}
	// VIEW
	private String view(List list){
		// 请求对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
		StringBuffer tag=new StringBuffer();
		try {
			TbFields tbField=null;
			for (int i = 0; i < list.size(); i++) {
				tbField=(TbFields)list.get(i);
				if(tbField.getFieldId().equals(value)){
					// 显示
					if(pattern.toUpperCase().indexOf("ICON")!=-1){
						tag.append("<image align=\"top\" src=\""+basePath+tbField.getFieldIcon()+"\" />");
					}
					if(pattern.toUpperCase().indexOf("NAME")!=-1){
						tag.append(tbField.getFieldName());
					}
					if(pattern.toUpperCase().indexOf("VALUE")!=-1){
						tag.append(tbField.getFieldValue());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag.toString();
	}
	// VIEWS
	private String views(List list){
		// 请求对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
		StringBuffer tag=new StringBuffer();
		try {
			TbFields tbField=null;
			for (int i = 0; i < list.size(); i++) {
				tbField=(TbFields)list.get(i);
				// 显示
				if(pattern.toUpperCase().indexOf("ICON")!=-1){
					tag.append("<image align=\"top\" src=\""+basePath+tbField.getFieldIcon()+"\" />");
				}
				if(pattern.toUpperCase().indexOf("NAME")!=-1){
					tag.append(tbField.getFieldName());
				}
				if(pattern.toUpperCase().indexOf("VALUE")!=-1){
					tag.append(tbField.getFieldValue());
				}
				tag.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag.toString();
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	public void setValueDefault(String valueDefault) {
		this.valueDefault = valueDefault;
	}
}

//	public int doStartTag() throws JspException {
//		if(null!=fid){
//			showId=fid;
//		}
//		if(null!=name){
//			eleName=name;
//		}
//		try {
//			// 请求对象
//			if(null==glbLead || glbLead!=lead){
//				glbList=FieldManager.getInstance().findList(lead);
//				glbLead=lead;
//			}
//			// SELECT格式
//			if("SELECT".equalsIgnoreCase(type)){
//				TagUtils.getInstance().write(pageContext,select());
//			}
//			// OPTION格式
//			else if("OPTION".equalsIgnoreCase(type)){
//				TagUtils.getInstance().write(pageContext,option());
//			}
//			// VIEW格式
//			else if("VIEW".equalsIgnoreCase(type)){
//				TagUtils.getInstance().write(pageContext,view());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		type=null;
//		lead=null;
//		eleId=null;
//		eleName=null;
//		showId=null;
//		fid=null;
//		showName=null;
//		name=null;
//		showValue=null;
//		className=null;
//		// 返回
//		return 0;
//	}
//	// SELECT
//	private String select(){
//		StringBuffer tag=new StringBuffer();
//		tag.append("<select");
//		if(null!=eleId){
//			tag.append(" id=\""+eleId+"\"");
//		}
//		if(null!=eleName){
//			tag.append(" name=\""+eleName+"\"");
//		}
//		if(null!=className){
//			tag.append(" class=\""+className+"\"");
//		}
//		tag.append(">");
//		tag.append("<option class=\"fnt_gray\" value=\"0\">-- 请选择 --</option>");
//		boolean flag=true;
//		TbField tbField=null;
//		// 传入值
//		if(null!=showId && !new Long(0).equals(showId)  && null!=glbList){
//			for (int i = 0; i < glbList.size(); i++) {
//				tbField=(TbField)glbList.get(i);
//				if("ORDER".equalsIgnoreCase(showValue)){
//					tag.append("<option value=\""+tbField.getNumOrder()+"\"");
//					if(null!=tbField.getNumOrder() && showId.equals(tbField.getNumOrder().toString())){
//						// 显示名称
//						tag.append(" selected");
//					}
//				}
//				else{
//					tag.append("<option value=\""+tbField.getFieldId()+"\"");
//					// 符合条件
//					if(showId.equals(tbField.getFieldId())){
//						// 显示名称
//						tag.append(" selected");
//					}
//				}
//				tag.append(">"+tbField.getFieldName()+"</option>");
//				if(flag){
//					flag=false;
//				}
//			}
//		}
//		// 默认值
//		if(flag && null!=glbList){
//			for (int i = 0; i < glbList.size(); i++) {
//				tbField=(TbField)glbList.get(i);
//				if("ORDER".equalsIgnoreCase(showValue)){
//					tag.append("<option value=\""+tbField.getNumOrder()+"\"");
//				}
//				else{
//					tag.append("<option value=\""+tbField.getFieldId()+"\"");
//				}
////				// 符合条件
////				if("default".equals(tbField.getCode())){
////					// 显示名称
////					tag.append(" selected");
////				}
//				tag.append(">"+tbField.getFieldName()+"</option>");
//			}
//		}
//		tag.append("</select>");
//		return tag.toString();
//	}
//	// OPTION
//	private String option(){
//		StringBuffer tag=new StringBuffer();
//		boolean flag=true;
//		TbField tbField=null;
//		// 传入值
//		if(null!=showId && !new Long(0).equals(showId)  && null!=glbList){
//			for (int i = 0; i < glbList.size(); i++) {
//				tbField=(TbField)glbList.get(i);
//				if("ORDER".equalsIgnoreCase(showValue)){
//					tag.append("<option value=\""+tbField.getNumOrder()+"\"");
//					if(null!=tbField.getNumOrder() && showId.equals(tbField.getNumOrder().toString())){
//						// 显示名称
//						tag.append(" selected");
//					}
//				}
//				else{
//					tag.append("<option value=\""+tbField.getFieldId()+"\"");
//					// 符合条件
//					if(showId.equals(tbField.getFieldId())){
//						// 显示名称
//						tag.append(" selected");
//					}
//				}
//				tag.append(">"+tbField.getFieldName()+"</option>");
//				if(flag){
//					flag=false;
//				}
//			}
//		}
//		// 默认值
//		if(flag && null!=glbList){
//			for (int i = 0; i < glbList.size(); i++) {
//				tbField=(TbField)glbList.get(i);
//				if("ORDER".equalsIgnoreCase(showValue)){
//					tag.append("<option value=\""+tbField.getNumOrder()+"\"");
//				}
//				else{
//					tag.append("<option value=\""+tbField.getFieldId()+"\"");
//				}
////				// 符合条件
////				if("default".equals(tbField.getCode())){
////					// 显示名称
////					tag.append(" selected");
////				}
//				tag.append(">"+tbField.getFieldName()+"</option>");
//			}
//		}
//		return tag.toString();
//	}
//	// VIEW
//	private String view(){
//		StringBuffer tag=new StringBuffer();
//		boolean flag=true;
//		TbField tbField=null;
//		// 传入值
//		if(null!=showId && !new Long(0).equals(showId) && null!=glbList){
//			for (int i = 0; i < glbList.size(); i++) {
//				tbField=(TbField)glbList.get(i);
//				// 符合条件
//				if(showId.equals(tbField.getFieldId())){
//					if("MEMO".equalsIgnoreCase(showName)){
//						// 显示备注
//						tag.append(tbField.getMemo());
//					}
//					else{
//						// 显示名称
//						tag.append(tbField.getFieldName());
//					}
//					flag=false;
//				}
//			}
//		}
//		// 默认值
////		if(flag && null!=glbList){
////			for (int i = 0; i < glbList.size(); i++) {
////				tbField=(TbField)glbList.get(i);
////				// 符合条件
////				if("DEFAULT".equalsIgnoreCase(tbField.getCode())){
////					// 显示备注
////					if("MEMO".equalsIgnoreCase(showName)){
////						tag.append(tbField.getMemo());
////					}
////					else{
////						// 显示名称
////						tag.append(tbField.getFieldName());
////					}
////				}
////			}
////		}
//		return tag.toString();
//	}
//	
//	// RADIO
//	private String radio(){
//		StringBuffer tag=new StringBuffer();
//		return tag.toString();
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//
//	public void setLead(String lead) {
//		this.lead = lead;
//	}
//
//
//	public void setEleId(String eleId) {
//		this.eleId = eleId;
//	}
//
//
//	public void setEleName(String eleName) {
//		this.eleName = eleName;
//	}
//
//
//	public void setShowId(Long showId) {
//		this.showId = showId;
//	}
//
//
//	public void setShowName(String showName) {
//		this.showName = showName;
//	}
//
//	public void setShowValue(String showValue) {
//		this.showValue = showValue;
//	}
//	public void setFid(Long fid) {
//		this.fid = fid;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public void setClassName(String className) {
//		this.className = className;
//	}
//}