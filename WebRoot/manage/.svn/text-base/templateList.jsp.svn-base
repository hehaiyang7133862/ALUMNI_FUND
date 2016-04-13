<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/calendar.jsp"%>
<script type="text/javascript">
function doSubmit(){
	var tempI=0;
	var flag=true;
	return flag;
}
</script>
</head> 
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<form id="adviseForm" action="<%=basePath%>tpList!templateList.action" method="post" onsubmit="return doSubmit()">
<table style="width:100%;" border="0" cellpadding="0" cellspacing="0"  class="lyt_search">
<tr>
<td class="lyt_search_note">模版名称</td>
<td width="185">
	<input type="text" name="tempName" value="<c:out value='${tempName}' />" maxlength="100"/>
</td>
<td align="right" class="lyt_search_opt">
	<div class="lyt_button">
	      <input id="saveForm" name="submit" value="查询" type="submit" />
	      <input name="" value="新增" type="button" onClick='MyFormWin.showMyWin("新增","<%=basePath %>/tpToAdd.action",800,480);return false;' />
	</div>
</td>
</tr>
</table>
</form>
<table width="100%" border="0" cellspacing="0" class="lyt_result">
  <tr>
    <th width="60">序号</th>
    <th>模版名称</th>
    <th>生日祝福主题</th>
    <th>是否为发送模版</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${list}" var="temp" varStatus="status">
  	<tr>
  		<td><b><c:out value="${(p.curr-1)*p.size+status.count}" /></b></td>
  		<td>${temp.memo }</td>
  		<td>
  			<a href="javascript:void(0)" onclick='MyFormWin.showMyWin("编辑","<%=basePath %>/tpToEdit.action?id=${temp.tempId }",800,480);return false;'>
  				<c:out value="${temp.tempTitle }"></c:out>
  			</a>
  		</td>
  		<td>
  			<c:choose>
  				<c:when test="${temp.flag==1}">
  					是
  				</c:when>
  				<c:otherwise>
  					否
  				</c:otherwise>
  			</c:choose>
  		</td>
  		<td>
  			<a href="javascript:void(0);" <c:if test="${temp.flag==1 }">style="color:gray"</c:if> <c:if test="${temp.flag!=1 }">onclick="location='<%=basePath %>/tpModify.action?id=${temp.tempId }';return false;"</c:if> title="应用为发送模版">应用</a>
  			<a href="javascript:void(0)" onclick='MyFormWin.showMyWin("编辑","<%=basePath %>/tpToEdit.action?id=${temp.tempId }",800,480);return false;'>编辑</a>
  			<a href="javascript:void(0);" onclick="if(confirm('是否确定删除？')){location='<%=basePath %>/tpDel.action?id=${temp.tempId }'}">删除</a>
  		</td>
  	</tr>
  </c:forEach>
</table>
<my:page action="tpList!templateList.action"></my:page>
</div>
</body>
</html>