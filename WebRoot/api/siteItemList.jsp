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
	group:'<c:out value="${group}"/>',
	publish:'<c:out value="${publish}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.itemId}"/>',
		mgtRange:'<c:out value="${temp.mgtRange}"/>',
		groupCode:'<c:out value="${temp.groupCode}"/>',
		groupItemOrder:'<fmt:formatNumber value="${temp.groupItemOrder}" pattern="0" type="number"/>',
		name:'<c:out value="${temp.itemName}"/>',
		type:'<c:out value="${temp.itemType}"/>',
		linkUrl:'<c:out value="${temp.linkUrl}"/>',
		publish:'<c:out value="${temp.publishFlag}"/>',
		memo:'<my:escapeHtml value="${temp.memo}" removeTarget="false" changePath="false"/>'
	}
	</c:forEach>
	]
}