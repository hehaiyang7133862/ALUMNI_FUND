package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.web.FieldManager;

public class FieldAddAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 编号
		String code=request.getParameter("code");
		// 名称
		String fieldName=request.getParameter("fieldName");
		// 赋值
		String fieldValue=request.getParameter("fieldValue");
		// 图标
		String fieldIcon=request.getParameter("fieldIcon");
		// 叶子
		String isLeaf=request.getParameter("isLeaf");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		TbField tb=new TbField();
		tb.setCode(code);
		tb.setFieldName(fieldName);
		tb.setFieldValue(fieldValue);
		tb.setFieldIcon(fieldIcon);
		tb.setIsLeaf(isLeaf);
		tb.setMemo(memo);
		if(null!=parentId&&!"".equals(parentId)){
			tb.setParentId(new Long(parentId));
		}
		// 排序号
		String numHql="select max(a.numOrder) from TbField a where a.parentId="+parentId;
		int num=getCommonBo().findHQLCount(numHql);
		tb.setNumOrder(new Long(num+1));
		// 保存
		TbField entity=(TbField)getCommonBo().save(tb);
		if(code!=null&&!"".equals(code)){
			//更新学校信息
			if(code.equalsIgnoreCase("SCHOOLCODE")){
				String obj=fieldValue;
				if(obj!=null&&!"".equals(obj)){
					//更新邮件模版
					//得到模版标题
					String sql="select a.subject,a.content from tb_email_fix a where a.code='SENDJIHUOEMAIL'";
					try {
						Object[] o=(Object[])this.getCommonBo().getSQLUnique(sql);
						if(o!=null){
							String title=(o[0]).toString();
							String subTitle="";
							if(title!=null&&!"".equals(title)){
								subTitle=title.substring(title.indexOf('[')+1,title.indexOf(']'));	
							}
							String content=(o[1]).toString();
							String newTitle=obj+"校友总会";
							if(content.indexOf(newTitle)==-1){
								content=content.replaceAll(subTitle,newTitle);
								title=title.replaceAll(subTitle,newTitle);
								sql="update tb_email_fix a set a.subject='"+title+"',a.content='"+content+"' where a.code='SENDJIHUOEMAIL'";
								this.getCommonBo().execute(sql);
							}
						}
						//更新发件人
						sql="update tb_field a set a.field_value='"+obj+"校友总会' where a.code='userAlias'";
						this.getCommonBo().execute(sql);
					} catch (Exception e) {
					}
				}
			}
		}
		// 返回
		return SUCCESS;
	}
	// 预处理
	public String pre() throws Exception{
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 设置父类编号
		this.getRequest().setAttribute("parentId", parentId);
		// 返回
		return INPUT;
	}
}
