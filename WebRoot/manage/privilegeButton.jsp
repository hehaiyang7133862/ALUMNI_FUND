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
<form action="<%=basePath%>prebutton!buttonEdit.action" method="get">
<input type="hidden" name="roleId" value="<c:out value='${bean_role.roleId}' />" />
<input type="hidden" name="grandId" value="<c:out value='${grandId}' />" />
<input type="hidden" name="code" value='<%=request.getParameter("code")%>' />
<c:forEach items="${list_main}" var="mainTemp" varStatus="i">
    <!-- 按钮主项 -->
    <c:if test="${mainTemp.isLeaf==0}">
    <div style="font-weight:bold;margin-top:10px;margin-left:5px;">
    <c:out value="${mainTemp.menuName}" />
    </div>
    </c:if>
    <!-- 按钮子项 -->
	<c:forEach items="${list_sub}" var="subTemp">
		<c:if test="${subTemp.parentId==mainTemp.menuId}">
		<c:choose>
		<c:when test='${fn:contains(list_self,subTemp.menuId)}'>
			<span style=" width:150px;">
        	<input id="menuId<c:out value='${subTemp.menuId}' />" name="menuId" type="checkbox" value="<c:out value='${subTemp.menuId}' />" checked />
        	<LABEL for="menuId<c:out value='${subTemp.menuId}' />"><c:out value="${subTemp.menuName}" /></LABEL>
        	</span>
		</c:when>
		<c:otherwise>
			<span style=" width:150px;">
        	<input id="menuId<c:out value='${subTemp.menuId}' />" name="menuId" type="checkbox" value="<c:out value='${subTemp.menuId}' />" />
        	<LABEL for="menuId<c:out value='${subTemp.menuId}' />"><c:out value="${subTemp.menuName}" /></LABEL>
        	</span>
		</c:otherwise>
		</c:choose>
        </c:if>
	</c:forEach>
</c:forEach>
<div class="lyt_submit">
	<input type="submit" value="确定" />
    <input type="reset" value="重置" />
</div>

</form>
</body>
</html>