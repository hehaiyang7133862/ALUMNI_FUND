<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:set var="len" value="${fn:length(list_role)-1}" ></c:set>
[
<c:forEach items="${list_role}" var="ele" varStatus="i">
{
	id:'${ele.roleId}',
	text:'${ele.roleName} [${ele.code}][${ele.usercount}]',
	<c:if test="${ele.isLeaf == 1}">
		leaf:true,
	</c:if>
	singleClickExpand:true
}
	<c:if test="${i.index<len}">,</c:if>
</c:forEach>
]