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
		<form id="formId" action="<%=basePath%>dxgl!list.action" method="post" onSubmit="checkForm(this)">
  			<table class="lyt_search" width="100%">
  				<tr>
  					<td class="lyt_search_note">类型编号</td>
  					<td>
						<input name="code" type="text" id="code" value='<c:out value="${code}" />' maxlength="50">
					</td>
					<td class="lyt_search_note">类型名称</td>
  					<td>
						<input name="name" type="text" id="name" value='<c:out value="${name}" />' maxlength="50">
					</td>  				
  					<td align="right">
				    	<div class="lyt_button" >
							<input type="submit" value="查询"  />
							<input type="button" value="新增"  onclick='MyFormWin.showMyWin("新增","dxgl!add.action",630,450);return false;'/>
						</div>
				    </td>
  				</tr>
  			</table>
		</form>
		<table class="lyt_result">
			<tr>
				<th width="70">序号</th>
				<th width="200">类型编号</th>
				<th>类型名称</th>
				<th>类型摘要</th>
				<th width="100">操作</th>
			</tr>
			<c:if test="${not empty dxlist}">
				<c:forEach items="${dxlist}" var="temp" varStatus="status">
					<tr>
						<td><c:out value="${(p.curr-1)*p.size+status.count}" /></td>
						<td><c:out value="${temp.code}" /></td>
						<td><c:out value="${temp.name}" /></td>
						<td><c:out value="${temp.remark}" /></td>
						<td>
							<a href="javascript:;" onClick='MyFormWin.showMyWin("编辑","dxgl!edit.action?id=${temp.dxId}",630,450);return false;'>编辑</a>
							<a href="javascript:;" onClick='MyMsg.confirm("确定要删除此项？","dxgl!del.action?id=${temp.dxId}","false");return false;'>删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<my:page action="dxgl!list.action"></my:page>
		<%@ include file="../common/bottom.jsp"%>
	</div>  	
</body>
</html>
