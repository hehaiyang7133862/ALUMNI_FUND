<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.optionId}"/>',
	proj:'<c:out value="${bean.projId}"/>',
	name:'<c:out value="${bean.optionName}"/>',
	code:'<c:out value="${bean.optionCode}"/>',
	intro:'<my:escapeHtml value="${bean.optionMemo}" removeTarget="false" changePath="false"/>',
	amount:'<fmt:formatNumber value="${bean.optionAmount}" pattern="0.##" type="number"/>',
	limitCount:'<c:out value="${bean.limitCount}"/>',
	lastCount:'<fmt:formatNumber value="${bean.optionCount}" pattern="0" type="number"/>',
	unitName:'<c:out value="${bean.unitName}"/>',
	order:'<c:out value="${bean.optionOrder}"/>',
	publish:'<c:out value="${bean.optionPublish}"/>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}