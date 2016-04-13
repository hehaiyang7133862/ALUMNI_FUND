<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	count:'<c:out value="${count}"/>',
	page:'<c:out value="${page}"/>',
	<c:choose>
	<c:when test="${page=='1'}">
	pageSize:'<c:out value="${pageSize}"/>',
	pageCount:'<c:out value="${pageCount}"/>',
	pageNum:'<c:out value="${pageNum}"/>',
	</c:when>
	<c:otherwise>
	num:'<c:out value="${num}"/>',
	size:'<c:out value="${size}"/>',
	</c:otherwise>
	</c:choose>
	proj:'<c:out value="${proj}"/>',
	begTime:'<fmt:formatDate value="${begTime}" pattern="yyyy-MM-dd"/>',
	endTime:'<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>',
	orderType:'<c:out value="${orderType}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.gressId}"/>',
		proj:'<c:out value="${temp.projId}"/>',
		time:'<fmt:formatDate value="${temp.gressTime}" pattern="yyyy-MM-dd"/>',
		intro:'<my:escapeHtml value="${temp.gressIntro}" removeTarget="false" changePath="false"/>',
		content:'<my:escapeHtml value="${temp.gressContent}" removeTarget="false" changePath="true"/>'
	}
	</c:forEach>
	]
}