<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.laungee.proj.common.util.*,com.laungee.proj.common.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	return true;
}

$(document).ready(function(){
    // 后台信息
	var msg="<c:out value='${msg}' />";
	if(msg!=""){
		MyMsg.alert("<c:out value='${msg}' />");
	}
});
</script>
</head>
  
<body>
<div class="float">
<form action="dxgl!doEdit.action" method="post" onSubmit="return checkForm(this)" id="formId">
<input type="hidden" name="id" value="${obj.dxId}" />
<table class="lyt_view">
  <tr>
    <td class="lyt_search_note" width="100">类型编号</td>
    <td>
    	<input type="text" name="code" value='<c:out value="${obj.code}" />' maxlength="50"/><span style="color: red;">*</span>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">类型名称</td>
    <td>
    	<input type="text" name="name" value='<c:out value="${obj.name}" />' maxlength="50"/><span style="color: red;">*</span>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">类型摘要</td>
    <td>
    	<textarea name="remark"><c:out value="${obj.remark}" /></textarea>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">类型说明</td>
    <td>
    	<textarea name="memo"><c:out value="${obj.memo}" /></textarea>
    </td>
  </tr>
</table>
<my:timeView idField="dxId" table="TbDonationDxgl" value="${obj.dxId}"></my:timeView>
<div class="lyt_submit">
	<input type="submit" value="保存" />
	<input type="reset" value="重置" />
	<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
</div>
</form>
</div>
</body>
</html>
