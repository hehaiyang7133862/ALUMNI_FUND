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
	formFormat("form1Id");
	var msg="";
	// 统计项编号
	if(form.menuName.value==""){
		msg+="类型名称不能为空\n";
		form.menuName.style.backgroundColor=error;
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
<div class="page" style="width:600px;">
<br />
<form id="form1Id" action="<%=basePath%>menuAdd.action" method="post" onSubmit="return checkForm(this)">
<input name="parentId" type="hidden" value="<c:out value='${parentId}' />" />
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td width="100" align="right">菜单名称</td>
    <td>
      <input type="text" name="menuName" value=""  maxlength="100" /><span class="ico_warn">*</span></td>
  </tr>
  <tr>
    <td align="right">菜单链接</td>
    <td><input type="text" name="urlLink" value=""  maxlength="100" /></td>
  </tr>
  <tr>
    <td align="right">显示图标</td>
    <td><input type="text" name="urlIcon" value=""  maxlength="100" /></td>
  </tr>
  <tr>
    <td align="right">备注</td>
    <td><textarea name="memo"></textarea></td>
  </tr>
</table>
<div class="opt_view">
<input type="submit" value="· 保存 ·" class="btn_m" />
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" value="· 重置 ·" class="btn_m" />
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="· 取消 ·" class="btn_m"
	onClick="if(confirm('您确定要放弃此操作吗？'))window.parent.FormEditWin.close();" />
</form>
</div>
</body>
</html>
