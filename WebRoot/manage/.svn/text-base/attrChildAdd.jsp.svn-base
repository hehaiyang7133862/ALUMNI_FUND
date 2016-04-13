<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.laungee.proj.common.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
<title></title>
<link href="<%=basePath%>UI/css/common.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>UI/js/common.js" type="text/javascript"></script>
<style type="text/css">
body{
	padding-bottom:0px;
}
</style>
<script language="javascript">
function checkForm(form){
	formFormat("form1Id");
	var msg="";
	// 属性名称
	if(form.attrName.value==""){
		msg+="属性名称不能为空\n";
		form.attrName.style.backgroundColor=error;
	}
	// 属性编号
	if(form.code.value==""){
		msg+="属性编号不能为空\n";
		form.code.style.backgroundColor=error;
	}
	// 属性备注
	if(!(getLength(form.memo.value)<=800)){
		msg+="属性备注不能超过800字节\n";
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
<div class="page" style="width:600px;">
<br />
<form id="form1Id" action="<%=basePath%>attrChildAdd.action" method="post" onSubmit="return checkForm(this)">
<input name="parentId" type="hidden" value="<c:out value='${parentId}' />" />
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td width="70" align="right">名称</td>
    <td>
    	<input type="text" name="attrName" value=""  maxlength="100" /><span class="ico_warn">*</span></td>
    </tr>
  <tr>
    <td align="right">编号</td>
    <td><input type="text" name="code" value=""  maxlength="100" /><span class="ico_warn">*</span></td>
  </tr>
  <tr>
    <td align="right">是否为叶子</td>
    <td>
    	<select name="isLeaf">
        <option value="1">是</option>
        <option value="0">否</option>
        </select>
    </td>
  </tr>
  <tr>
    <td align="right">备注</td>
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
