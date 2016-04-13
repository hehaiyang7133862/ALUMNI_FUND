<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.msgId}"/>',
	msgCnt:'<my:escapeHtml value="${bean.msgCnt}" removeTarget="false" changePath="false"/>',
	msgFrom:'<c:out value="${bean.msgFrom}"/>',
	msgTime:'<fmt:formatDate value="${bean.msgTime}" pattern="yyyy-MM-dd"/>',
	publish:'<c:out value="${bean.publishFlag}"/>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}