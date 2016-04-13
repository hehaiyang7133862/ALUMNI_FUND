<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	count:'<c:out value="${fn:length(beanList)}"/>',
	comm:'<c:out value="${comm}"/>',
	name:'<c:out value="${name}"/>',
	shelves:'<c:out value="${shelves}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.detailId}"/>',
		name:'<c:out value="${temp.detailName}"/>',
		pic:'<c:if test="${not empty temp.detailPic}"><%=basePath%><c:out value="${temp.detailPic}"/></c:if>',
		intro:'<my:escapeHtml value="${temp.detailIntro}" removeTarget="false" changePath="false"/>',
		stock:'<fmt:formatNumber value="${temp.stockNum}" pattern="0" type="number"/>',
		costFee:'<fmt:formatNumber value="${temp.costFee}" pattern="0.##" type="number"/>',
		saleFee:'<fmt:formatNumber value="${temp.saleFee}" pattern="0.##" type="number"/>',
		shelves:'<c:out value="${temp.isShelves}"/>',
		order:'<c:out value="${temp.numOrder}"/>',
		memo:'<my:escapeHtml value="${temp.memo}" removeTarget="false" changePath="false"/>'
	}
	</c:forEach>
	]
}