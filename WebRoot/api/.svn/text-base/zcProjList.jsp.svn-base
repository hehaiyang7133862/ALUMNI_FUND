<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	endLastViewDay:'<c:out value="${endLastViewDay}"/>',
	endLastViewHour:'<c:out value="${endLastViewHour}"/>',
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
	type:'<c:out value="${type}"/>',
	shelves:'<c:out value="${shelves}"/>',
	hot:'<c:out value="${hot}"/>',
	hotOrder:'<c:out value="${hotOrder}"/>',
	status:'<c:out value="${status}"/>',
	complete:'<c:out value="${complete}"/>',
	completedJz:'<c:out value="${completedJz}"/>',
	classicStatus:'<c:out value="${classicStatus}"/>',
	minAmount:'<c:out value="${minAmount}"/>',
	maxAmount:'<c:out value="${maxAmount}"/>',
	key:'<c:out value="${key}"/>',
	name:'<c:out value="${name}"/>',
	code:'<c:out value="${code}"/>',
	target:'<c:out value="${target}"/>',
	order:'<c:out value="${order}"/>',
	orderType:'<c:out value="${orderType}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.projId}"/>',
		name:'<c:out value="${temp.projName}"/>',
		code:'<c:out value="${temp.projCode}"/>',
		type:'<c:out value="${temp.projType}"/>',
		shelvesTime:'<fmt:formatDate value="${temp.shelvesTime}" pattern="yyyy-MM-dd HH:mm"/>',
		begTime:'<fmt:formatDate value="${temp.begTime}" pattern="yyyy-MM-dd HH:mm"/>',
		endTime:'<fmt:formatDate value="${temp.endTime}" pattern="yyyy-MM-dd HH:mm"/>',
		targetAmout:'<fmt:formatNumber value="${temp.targetAmout}" pattern="0.##" type="number"/>',
		shelves:'<c:out value="${temp.shelvesFlag}"/>',
		hot:'<c:out value="${temp.hotFlag}"/>',
		hotOrder:'<c:out value="${temp.hotOrder}"/>',
		completedJz:'<c:out value="${temp.completedJz}"/>',
		classicStatus:'<c:out value="${temp.classicStatus}"/>',
		optionOther:'<c:out value="${temp.optionOther}"/>',
		optionOtherName:'<c:out value="${temp.optionOtherName}"/>',
		optionOtherMemo:'<my:escapeHtml value="${temp.optionOtherMemo}" removeTarget="false" changePath="false"/>',
		minAmount:'<fmt:formatNumber value="${temp.minAmount}" pattern="0.##" type="number"/>',
		searchKey:'<my:escapeHtml value="${temp.searchKey}" removeTarget="false" changePath="false"/>',
		pic:[
		<c:forEach items="${temp.tbZcprojPubPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picLarge:[
		<c:forEach items="${temp.tbZcprojPubLargePics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picNormal:[
		<c:forEach items="${temp.tbZcprojPubNormalPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picSmall:[
		<c:forEach items="${temp.tbZcprojPubSmallPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		option:[
		<c:forEach items="${temp.tbZcprojPubOptions}" var="optionTemp" varStatus="optionStatus">
		<c:if test="${optionStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${optionTemp.optionId}"/>',
			name:'<c:out value="${optionTemp.optionName}"/>',
			code:'<c:out value="${optionTemp.optionCode}"/>',
			intro:'<my:escapeHtml value="${optionTemp.optionMemo}" removeTarget="false" changePath="false"/>',
			amount:'<fmt:formatNumber value="${optionTemp.optionAmount}" pattern="0.##" type="number"/>',
			limitCount:'<c:out value="${optionTemp.limitCount}"/>',
			lastCount:'<fmt:formatNumber value="${optionTemp.optionCount}" pattern="0" type="number"/>',
			unitName:'<c:out value="${optionTemp.unitName}"/>'
		}
		</c:forEach>
		],
		intro:'<my:escapeHtml value="${temp.projIntro}" removeTarget="false" changePath="false"/>',
		status:'<c:out value="${temp.projStatus}"/>',
		endLastDay:'<c:out value="${temp.endLastDay}"/>',
		zcedCount:'<fmt:formatNumber value="${temp.zcedCount}" pattern="0" type="number"/>',
		zcedPersonCount:'<fmt:formatNumber value="${temp.zcedPersonCount}" pattern="0" type="number"/>',
		zcedAmout:'<fmt:formatNumber value="${temp.zcedAmout}" pattern="0.##" type="number"/>',
		zcedPercent:'<fmt:formatNumber value="${temp.zcedPercent}" pattern="0.##" type="number"/>'
	}
	</c:forEach>
	]
}