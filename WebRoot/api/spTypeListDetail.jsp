<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
[
<c:forEach items="${beanList}" var="temp" varStatus="status">
<c:if test="${status.index>0}">,</c:if>
{
	id:'<c:out value="${temp.typeId}"/>',
	name:'<c:out value="${temp.typeName}"/>',
	code:'<c:out value="${temp.typeCode}"/>'
	<c:if test="${flag=='all'}">
		<c:set var="beanList" value="${temp.children}" scope="request"/>
		<c:if test="${not empty beanList}">
		,set:<jsp:include page="spTypeListDetail.jsp" flush="true"/>
		</c:if>
	</c:if>
}
</c:forEach>
]