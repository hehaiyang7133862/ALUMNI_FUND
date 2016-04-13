<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	count:'<c:out value="${fn:length(beanList)}"/>',
	proj:'<c:out value="${proj}"/>',
	name:'<c:out value="${name}"/>',
	code:'<c:out value="${code}"/>',
	publish:'<c:out value="${publish}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.optionId}"/>',
		name:'<c:out value="${temp.optionName}"/>',
		code:'<c:out value="${temp.optionCode}"/>',
		intro:'<my:escapeHtml value="${temp.optionMemo}" removeTarget="false" changePath="false"/>',
		amount:'<fmt:formatNumber value="${temp.optionAmount}" pattern="0.##" type="number"/>',
		limitCount:'<c:out value="${temp.limitCount}"/>',
		lastCount:'<fmt:formatNumber value="${temp.optionCount}" pattern="0" type="number"/>',
		unitName:'<c:out value="${temp.unitName}"/>',
		order:'<c:out value="${temp.optionOrder}"/>',
		publish:'<c:out value="${temp.optionPublish}"/>',
		memo:'<my:escapeHtml value="${temp.memo}" removeTarget="false" changePath="false"/>'
	}
	</c:forEach>
	]
}