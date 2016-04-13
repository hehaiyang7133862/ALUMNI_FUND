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
<form action="xxgl!doEdit.action" method="post" onSubmit="return checkForm(this)" id="formId">
<input type="hidden" name="id" value="${obj.xxId}" />
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
  	<td class="lyt_search_note">受惠对象</td>
	<td>
		<select name="foundObject">
			<option value="">--请选择--</option>
			<my:fundField type="OPTION" lead="ACCEPT_OBJECT" value="${obj.foundObject}"></my:fundField>
		</select>
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
  <tr>
  	<td class="lyt_search_note">附件</td>
    <td>
		<input id="relIds" name="relIds" type="hidden" value="" />
		<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbDonationXxgl&ele=relIds&id=${obj.xxId}">
		</iframe>
    </td>
  </tr>
</table>
<my:timeView idField="xxId" table="TbDonationXxgl" value="${obj.xxId}"></my:timeView>
<div class="lyt_submit">
	<input type="submit" value="保存" />
	<input type="reset" value="重置" />
	<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
</div>
</form>
</div>
</body>
</html>
