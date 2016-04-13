<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.typeId}"/>',
	name:'<c:out value="${bean.typeName}"/>',
	code:'<c:out value="${bean.typeCode}"/>'
	<c:if test="${flag=='all'}">
		<c:set var="beanList" value="${bean.children}" scope="request"/>
		<c:if test="${not empty beanList}">
		,set:<jsp:include page="zcProjTypeListDetail.jsp" flush="true"/>
		</c:if>
	</c:if>
}