<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head> 
<body>
<%@ include file="roleInfo.jsp"%>
<form id="formId" action="<%=basePath%>userUnAlumni.action" method="post">
<input name="grandId" type="hidden" value="<c:out value='${grandId}' />" />
<input name="roleId" type="hidden" value="<c:out value='${bean_role.roleId}' />" />
<table border="0" cellpadding="0" cellspacing="0"  class="lyt_search">
  <tr>
    <td width="60" align="right" class="lyt_search_note">帐号</td>
    <td width="240">
      <input type="text" name="userCard" value="<c:out value='${userCard}' />" />
    </td>
    <td width="60" align="right" class="lyt_search_note">姓名</td>
    <td>
      <input type="text" name="userName" value="<c:out value='${userName}' />" />
      </td>
  </tr>
</table>

<div class="lyt_button">
<input type="submit" value="查询" />
<input type="button" value="重置" onClick='formReset("formId")' />
<input type="button" value="返回" 
	onClick="location.href='<%=basePath%>userInList.action?roleId=${bean_role.roleId}&grandId=${grandId}'" />
</div>
</form>
<table width="100%" border="0" cellspacing="0" class="lyt_result">
  <tr>
    <th width="50">序号</th>
    <th>帐号</th>
    <th>姓名</th>
    <th>手机</th>
    <th>邮箱</th>
    <th width="80">操作</th>
  </tr>
  <c:forEach items="${list_user}" var="tmp" varStatus="status">
  <tr>
    <td><b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>&nbsp;</td>
    <td><c:out value="${tmp.userLogin}" />&nbsp;</td>
    <td>
    	<c:out value="${tmp.nameCn}" />&nbsp;
 	</td>
    <td><c:out value="${tmp.handsetFirst}" />&nbsp;</td>
    <td>
    	<c:out value="${tmp.mailFirst}" />&nbsp;
    </td>
    <td>
    	<a href="<%=basePath%>userUnAlumni!del.action?delsId=${tmp.unAlumniId}&${p.pars}">加入</a>
    </td>
  </tr>
  </c:forEach>
</table>
<my:page action="userUnAlumni.action"></my:page>
</body>
</html>