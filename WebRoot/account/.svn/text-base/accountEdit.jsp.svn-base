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
<form action="account!doEdit.action" method="post" onSubmit="return checkForm(this)" id="formId">
<input type="hidden" name="id" value="${obj.accountId}" />
<table class="lyt_view">
  <tr>
    <td class="lyt_search_note" width="100">账户名称</td>
    <td>
    	<input type="text" name="accountName" value='<c:out value="${obj.accountName}" />' maxlength="50"/><span style="color: red;">*</span>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">开户行</td>
    <td>
    	<input type="text" name="accountBank" value='<c:out value="${obj.accountBank}" />' maxlength="50"/>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">账号</td>
    <td>
    	<input type="text" name="accountNum" value='<c:out value="${obj.accountNum}" />' maxlength="50"/><span style="color: red;">*</span>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">币种</td>
    <td>
    	<input type="text" name="currency" value='<c:out value="${obj.currency}" />' maxlength="50"/>
    </td>
  </tr>
  <tr>
    <td class="lyt_search_note">备注</td>
    <td>
    	<textarea name="memo"><c:out value="${obj.memo}" /></textarea>
    </td>
  </tr>
</table>
<my:timeView idField="accountId" table="TbDonationAccout" value="${obj.accountId}"></my:timeView>
<div class="lyt_submit">
	<input type="submit" value="保存" />
	<input type="reset" value="重置" />
	<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
</div>
</form>
</div>
</body>
</html>
