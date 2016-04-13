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
	item:'<c:out value="${item}"/>',
	publish:'<c:out value="${publish}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.contentId}"/>',
		item:'<c:out value="${temp.itemId}"/>',
		logo:'<c:if test="${not empty temp.contentLogo}"><%=basePath%><c:out value="${temp.contentLogo}"/></c:if>',
		title:'<c:out value="${temp.contentTitle}"/>',
		intro:'<my:escapeHtml value="${temp.contentIntro}" removeTarget="false" changePath="false"/>',
		type:'<c:out value="${temp.contentType}"/>',
		linkUrl:'<c:out value="${temp.linkUrl}"/>',
		publish:'<c:out value="${temp.publishFlag}"/>',
		publishTime:'<fmt:formatDate value="${temp.publishTime}" pattern="yyyy-MM-dd HH:mm"/>',
		memo:'<my:escapeHtml value="${temp.memo}" removeTarget="false" changePath="false"/>'
	}
	</c:forEach>
	]
}