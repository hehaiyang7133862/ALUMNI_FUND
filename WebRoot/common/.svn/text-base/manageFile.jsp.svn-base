<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="include.jsp"%>
<c:if test="${fn:length(fileList)==0}">无</c:if>
<c:forEach items="${fileList}" var="tmp" varStatus="status">
	<div><c:out value="${status.count}" />.
		<my:field lead="ICON_FILE" type="VIEW" pattern="ICON" valueCode="${tmp.tbResource.resType}" valueDefault="default"></my:field>
		<a href="<%=basePath%>fileDown.action?delsId=${tmp.tbResource.resId}">
			<c:out value="${tmp.tbResource.resName}" />
		</a>
		(
	    <c:choose>
	    <c:when test="${tmp.tbResource.resSize<1024}" >
	        <fmt:formatNumber value="${tmp.tbResource.resSize}" pattern="#.##" type="number"/>B
	    </c:when>
	    <c:when test="${tmp.tbResource.resSize<(1024*1024)}" >
	        <fmt:formatNumber value="${tmp.tbResource.resSize/1024}" pattern="#.##" type="number"/>KB
	    </c:when>
	    <c:otherwise>
	        <fmt:formatNumber value="${tmp.tbResource.resSize/(1024*1024)}" pattern="#.##" type="number"/>MB
	    </c:otherwise>
	    </c:choose>          
	    )
	</div>
</c:forEach>