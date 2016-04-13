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
	// 角色名称
	if(form.roleName.value==""){
		msg+="角色名称不能为空\n";
		form.roleName.style.backgroundColor=error;
	}
	if(form.code.value==""){
		msg+="编号不能为空\n";
		form.code.style.backgroundColor=error;
	}
	// 备注
	if(getLeng(form.memo.value)>1000){
		msg+="备注超过最大字符(1000字节)\n";
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
<br />
<form id="formId" action="<%=basePath%>roleAdd.action" method="post" onSubmit="return checkForm(this)">
<input name="parentId" type="hidden" value="<c:out value='${parentId}' />" />
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td align="right" class="lyt_view_note">角色名称</td>
    <td>
    	<input type="text" name="roleName" value=""  maxlength="100" /><span class="fnt_warn">*</span></td>
  </tr>
  <tr>
    <td align="right" class="lyt_view_note">编号</td>
    <td>
    	<input type="text" name="code" value=""  maxlength="50" /><span class="fnt_warn">*</span>
    </td>
  </tr>
  <tr>
  	<td align="right" class="lyt_view_note">添加子级</td>
    <td>
      <select name="isLeaf">
      	<option value="1">否</option>
      	<option value="0">能</option>
      </select>
    </td>
  </tr>
  <tr>
    <td align="right" class="lyt_view_area">备注</td>
    <td>
      <textarea name="memo"></textarea></td>
  </tr>
</table>
<div class="lyt_submit">
<input type="submit" value="保存" />
<input type="reset" value="重置" />
<input type="button" value="取消"
	onClick="if(confirm('您确定要放弃此操作吗？'))window.parent.FormEditWin.close();" />
</div>
</form>
</div>
</body>
</html>
