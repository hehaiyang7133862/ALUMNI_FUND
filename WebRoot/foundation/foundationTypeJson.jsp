<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="len" value="${fn:length(list_ele)-1}" ></c:set>
[
<c:forEach items="${list_ele}" var="ele" varStatus="i">
{
	id:'${ele.typeId}',
	text:'<c:out value="${ele.typeName}"/> [${list_count[i.index]}]',
	name:'${ele.typeName}',
	title:'<c:out value="${ele.typeName}"/>&#10;&#10;总数：<c:out value="${list_count[i.index]}"/><c:forEach items="${beanTypeList}" var="temp" varStatus="status">； <c:out value="${temp.fieldName}"/>：<c:out value="${list_counts[i.index][status.index]}"/></c:forEach>',
	expanded:<c:choose><c:when test="${empty ele.children}" >true</c:when><c:otherwise>false</c:otherwise></c:choose>
}
	<c:if test="${i.index<len}">,</c:if>
</c:forEach>
]