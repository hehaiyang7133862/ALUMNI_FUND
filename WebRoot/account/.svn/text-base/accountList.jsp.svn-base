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

</script>
</head>
  
 <body>
    <%@ include file="../common/navigation.jsp"%>
    <div class="page">
		<form id="formId" action="<%=basePath%>account!list.action" method="post" onSubmit="checkForm(this)">
  			<table class="lyt_search" width="100%">
  				<tr>  					
  					<td align="right">
				    	<div class="lyt_button" >							
							<input type="button" value="新增"  onclick='MyFormWin.showMyWin("新增","account!add.action",630,450);return false;'/>
						</div>
				    </td>
  				</tr>
  			</table>
		</form>
		<table class="lyt_result">
			<tr>
				<th width="70">序号</th>
				<th width="200">账号名称</th>
				<th>开户行</th>
				<th>账号</th>
				<th width="160">币种</th>
				<th width="100">备注</th>
				<th width="100">操作</th>
			</tr>
			<c:if test="${not empty accountlist}">
				<c:forEach items="${accountlist}" var="temp" varStatus="status">
					<tr>
						<td><c:out value="${(p.curr-1)*p.size+status.count}" /></td>
						<td><c:out value="${temp.accountName}" /></td>
						<td><c:out value="${temp.accountBank}" /></td>
						<td><c:out value="${temp.accountNum}" /></td>
						<td><c:out value="${temp.currency}" /></td>
						<td><c:out value="${temp.memo}" /></td>
						<td>
							<a href="javascript:;" onClick='MyFormWin.showMyWin("编辑","account!edit.action?id=${temp.accountId}",630,450);return false;'>编辑</a>
							<a href="javascript:;" onClick='MyMsg.confirm("确定要删除此项？","account!del.action?id=${temp.accountId}","false");return false;'>删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<my:page action="account!list.action"></my:page>
		<%@ include file="../common/bottom.jsp"%>
	</div>  	
</body>
</html>
