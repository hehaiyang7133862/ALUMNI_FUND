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
	text:'${ele.typeName} [${list_count[i.index]}]',
	name:'${ele.typeName}',
	<c:if test="${empty ele.children}" >
	expanded:true,
	</c:if>
	singleClickExpand:true
}
	<c:if test="${i.index<len}">,</c:if>
</c:forEach>
]