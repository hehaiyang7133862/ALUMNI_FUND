package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.web.FieldManager;

public class FieldEditAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 父类编号
		String id=request.getParameter("id");
		// 编号
		String code=request.getParameter("hidCode");
		// 名称
		String fieldName=request.getParameter("fieldName");
		// 赋值
		String fieldValue=request.getParameter("fieldValue");
		// 图标
		String fieldIcon=request.getParameter("fieldIcon");
		// 备注
		String memo=request.getParameter("memo");
		// 属性信息
		if(null==id||"".equals(id))
			return null;
		//原来的学校名称
		String oldName="";
		TbField tb=(TbField)getCommonBo().get(TbField.class, new Long(id));
		oldName=tb.getFieldValue();
		tb.setFieldName(fieldName);
		tb.setFieldValue(fieldValue);
		tb.setFieldIcon(fieldIcon);
		tb.setMemo(memo);
		// 保存
		TbField entity=(TbField)getCommonBo().update(tb);
		//FieldManager.getInstance().setList(obj.getTbField().getCode());
		if(code!=null&&!"".equals(code)){
			if(code.equalsIgnoreCase("SCHOOLCODE")){
				String obj=fieldValue;
				if(obj!=null&&!"".equals(obj)){
					try {
						//更新邮件模版
						//得到模版标题
						String sql="select a.subject,a.content from tb_email_fix a where a.code='SENDJIHUOEMAIL'";
						Object[] o=(Object[])this.getCommonBo().getSQLUnique(sql);
						if(o!=null&&o.length!=0){
							String title=(o[0]).toString();
							String content=(o[1]).toString();
							//标题
							if(title!=null&&!"".equals(title)){
								title=title.replaceAll(oldName, obj);
							}
							//内容
							if(content!=null&&!"".equals(content)){
								content=content.replaceAll(oldName, obj);
							}
							sql="update tb_email_fix a set a.subject='"+title+"',a.content='"+content+"' where a.code='SENDJIHUOEMAIL'";
							this.getCommonBo().execute(sql);
						}
						//更新找回密码
						sql="select a.subject,a.content from tb_email_fix a where a.code='GETPWDEMAIL'";
						o=(Object[])this.getCommonBo().getSQLUnique(sql);
						if(o!=null&&o.length!=0){
							String title=(o[0]).toString();
							String content=(o[1]).toString();
							//标题
							if(title!=null&&!"".equals(title)){
								title=title.replaceAll(oldName, obj);
							}
							//内容
							if(content!=null&&!"".equals(content)){
								content=content.replaceAll(oldName, obj);
							}
							sql="update tb_email_fix a set a.subject='"+title+"',a.content='"+content+"' where a.code='GETPWDEMAIL'";
							this.getCommonBo().execute(sql);
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
		// 编号
		String id=request.getParameter("id");
		// 属性信息
		if(null==id||"".equals(id))
			return null;
		TbField tbAttribute=(TbField)getCommonBo().get(TbField.class, new Long(id));
		getRequest().setAttribute("bean", tbAttribute);
		// 返回
		return INPUT;
	}
}
