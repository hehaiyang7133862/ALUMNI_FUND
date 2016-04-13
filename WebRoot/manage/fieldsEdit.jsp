<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
<%@ include file="../common/head.jsp"%>
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	
	var msg="";
	// 编号
	if(form.code.value==""){
		msg+="请输入编号\n";
		form.code.style.backgroundColor=error;
	}
	// 名称
	if(form.fieldName.value==""){
		msg+="请输入名称\n";
		form.fieldName.style.backgroundColor=error;
	}
	// 备注
	if(!(getLength(form.memo.value)<=1000)){
		msg+="备注不能超过800字节\n";
		form.memo.style.backgroundColor=error;
	}
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}
	else{
		return true;
	}
	
}
</script>

</head>
<body>
<div class="float">
<form id="formId" action="<%=basePath%>fieldsEdit.action" method="post" onSubmit="return checkForm(this)">
<input name="id" type="hidden" value="<c:out value='${bean.fieldId}' />" />
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td class="lyt_view_note">代码</td>
    <td><input type="text" name="code" id="code" value="${bean.code}" "  maxlength="50" disabled/><span class="fnt_warn">*</span></td>
  </tr>
  <tr>
    <td class="lyt_view_note">名称</td>
    <td>
    	<input type="text" name="fieldName" id="fieldName" value="${bean.fieldName}"  maxlength="100" /><span class="fnt_warn">*</span></td>
  </tr>
  <tr>
    <td class="lyt_view_note">赋值</td>
    <td>
    	<input type="text" name="fieldValue" value="${bean.fieldValue}"  maxlength="100" /></td>
  </tr>
  <tr>
    <td class="lyt_view_note">图标</td>
    <td>
    	<input type="text" name="fieldIcon" value="${bean.fieldIcon}"  maxlength="100" />
        </td>
  </tr>
  <tr>
    <td class="lyt_view_note">备注</td>
    <td>
      <textarea name="memo" id="memoId" ><c:out value="${bean.memo}" /></textarea></td>
  </tr>
</table>
<div class="lyt_submit">
<input type="submit"  value="保存" />
<input type="button" value="取消"
	onClick="if(confirm('您确定要放弃此操作吗？'))window.parent.FormEditWin.close();" />
</div>
</form>
</div>
</body>
</html>