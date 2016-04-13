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
	// 格化表单
	formFormat("formId");
  return true;
}
</script>
</head> 
<body>
<div class="page">
<form id="formId" action="<%=basePath%>userList.action" method="post" onSubmit="return checkForm(this)">
<table border="0" cellpadding="0" cellspacing="0"  class="lyt_search" width="100%">
  <tr>
    <td  align="right" class="lyt_search_note">账号</td>
    <td >
      <input type="text" name="userCard" value="<c:out value='${userCard}' />" maxlength="30"/>
    </td>
    <td align="right" class="lyt_search_note">姓名</td>
    <td >
      <input type="text" name="userName" value="<c:out value='${userName}' />" maxlength="50"/>
      </td>
      <td >
      <div class="lyt_button" align="right">
      <input type="submit" value="查询"/>
      <input type="button" value="重置" onClick='formReset("formId")' />
       <input type="button" value="添加" onClick="location.href='<%=basePath%>userEdit!pre.action?flag=3'"/>
       </div>
      </td>
  </tr>
</table>
</form>
<table width="100%" border="0" cellspacing="0" class="lyt_result">
  <tr>
    <th width="60">序号</th>
     <th><my:order action="userList.action" title="姓名" name="a.userName" /></th>
    <th><my:order action="userList.action" title="账号" name="a.userCard" /></th>
    <th><my:order action="userList.action" title="账户状态" name="a.userOpen" /></th>
    <th width="80">操作</th>
  </tr>
  <c:forEach items="${list_user}" var="tmp" varStatus="status">
  <tr>
    <td><b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>&nbsp;</td>
    <td>
    	<c:out value="${tmp.userName}" />
 	</td>
     <td><c:out value="${tmp.userCard}" />&nbsp;</td>
    <td>
    <c:if test="${tmp.userOpen==1}">
    启用
    </c:if>
    <c:if test="${tmp.userOpen==2}">
    停用
    </c:if>
    </td>
    <td>
    <a  href="<%=basePath%>userEdit!pre.action?id=${tmp.userId}">编辑</a>
    <a onClick="return confirm('确定要删除吗？')" href="<%=basePath%>userList!del.action?delsId=${tmp.userId}&${p.pars}">删除</a>
    </td>
  </tr>
  </c:forEach>
</table>
<my:page action="userList.action"></my:page>
</div>
</body>
</html>
