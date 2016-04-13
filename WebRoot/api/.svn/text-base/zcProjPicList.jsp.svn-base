<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	count:'<c:out value="${fn:length(beanList)}"/>',
	proj:'<c:out value="${proj}"/>',
	code:'<c:out value="${code}"/>',
	publish:'<c:out value="${publish}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.picId}"/>',
		code:'<c:out value="${temp.numOrder}"/>',
		alt:'<my:escapeHtml value="${temp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty temp.picPath}"><%=basePath%><c:out value="${temp.picPath}"/></c:if>',
		publish:'<c:out value="${temp.picPublish}"/>',
		memo:'<my:escapeHtml value="${temp.memo}" removeTarget="false" changePath="false"/>'
	}
	</c:forEach>
	]
}