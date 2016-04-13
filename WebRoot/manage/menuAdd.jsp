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
	// 名称
	if(form.menuName.value==""){
		msg+="名称不能为空\n";
		form.menuName.style.backgroundColor=error;
	}
	// 编号
	if(form.code.value==""){
		msg+="编号不能为空\n";
		form.code.style.backgroundColor=error;
	}
	// 备注
	if(!(getLength(form.memo.value)<=800)){
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
<c:set var="scode" value='<%=request.getParameter("scode") %>' />
<div class="float">
<form id="form1Id" action="<%=basePath%>menuAdd.action" method="post" onSubmit="return checkForm(this)">
<input name="parentId" type="hidden" value="<c:out value='${parentId}' />" />
<input name="scode" type="hidden" value="<c:out value='${scode}' />" />
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td width="70" align="right">名称</td>
    <td>
      <input type="text" name="menuName" value=""  maxlength="100" /><span class="ico_warn">*</span></td>
  </tr>
  <tr>
    <td align="right">代码</td>
    <td>
      <input type="text" name="code" value=""  maxlength="50" /><span class="ico_warn">*</span></td>
  </tr>
  <c:if test='${scode=="button"}'>
  <tr>
    <td align="right">赋值</td>
    <td><input type="text" name="urlLink" value=""  maxlength="100" /></td>
  </tr>
  </c:if>
  <c:if test='${scode=="menu"}'>
  <tr>
    <td align="right">链接</td>
    <td><input type="text" name="urlLink" value=""  maxlength="100" /></td>
  </tr>
  <tr>
    <td align="right">图标</td>
    <td><input type="text" name="urlIcon" value=""  maxlength="100" /></td>
  </tr>
  </c:if>
  <tr>
    <td align="right">备注</td>
    <td><textarea name="memo"></textarea></td>
  </tr>
</table>

<div class="lyt_submit">
<input type="submit" value="保存" class="btn_m" />
<input type="reset" value="重置" class="btn_m" />
<input type="button" value="取消" class="btn_m"
	onClick="if(confirm('您确定要放弃此操作吗？'))window.parent.FormEditWin.close();" />
</form>
</div>
</body>
</html>
