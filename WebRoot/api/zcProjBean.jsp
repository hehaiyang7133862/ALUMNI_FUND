<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	endLastViewDay:'<c:out value="${endLastViewDay}"/>',
	endLastViewHour:'<c:out value="${endLastViewHour}"/>',
	id:'<c:out value="${bean.projId}"/>',
	name:'<c:out value="${bean.projName}"/>',
	code:'<c:out value="${bean.projCode}"/>',
	type:'<c:out value="${bean.projType}"/>',
	shelvesTime:'<fmt:formatDate value="${bean.shelvesTime}" pattern="yyyy-MM-dd HH:mm"/>',
	begTime:'<fmt:formatDate value="${bean.begTime}" pattern="yyyy-MM-dd HH:mm"/>',
	endTime:'<fmt:formatDate value="${bean.endTime}" pattern="yyyy-MM-dd HH:mm"/>',
	targetAmout:'<fmt:formatNumber value="${bean.targetAmout}" pattern="0.##" type="number"/>',
	shelves:'<c:out value="${bean.shelvesFlag}"/>',
	hot:'<c:out value="${bean.hotFlag}"/>',
	hotOrder:'<c:out value="${bean.hotOrder}"/>',
	completedJz:'<c:out value="${bean.completedJz}"/>',
	classicStatus:'<c:out value="${bean.classicStatus}"/>',
	optionOther:'<c:out value="${bean.optionOther}"/>',
	optionOtherName:'<c:out value="${bean.optionOtherName}"/>',
	optionOtherMemo:'<my:escapeHtml value="${bean.optionOtherMemo}" removeTarget="false" changePath="false"/>',
	minAmount:'<fmt:formatNumber value="${bean.minAmount}" pattern="0.##" type="number"/>',
	searchKey:'<my:escapeHtml value="${bean.searchKey}" removeTarget="false" changePath="false"/>',
	pic:[
	<c:forEach items="${bean.tbZcprojPubPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picLarge:[
	<c:forEach items="${bean.tbZcprojPubLargePics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picNormal:[
	<c:forEach items="${bean.tbZcprojPubNormalPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picSmall:[
	<c:forEach items="${bean.tbZcprojPubSmallPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	option:[
	<c:forEach items="${bean.tbZcprojPubOptions}" var="optionTemp" varStatus="optionStatus">
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
	intro:'<my:escapeHtml value="${bean.projIntro}" removeTarget="false" changePath="false"/>',
	<c:if test="${exceptDetail!='1' && exceptDetail!='true'}">
	detail:[
		<c:set var="detailIndex" value="0"/>
		<c:if test="${not empty bean.detail1Title && bean.detail1Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail1Title}"/>',
				content:'<my:escapeHtml value="${bean.detail1Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail2Title && bean.detail2Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail2Title}"/>',
				content:'<my:escapeHtml value="${bean.detail2Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail3Title && bean.detail3Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail3Title}"/>',
				content:'<my:escapeHtml value="${bean.detail3Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail4Title && bean.detail4Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail4Title}"/>',
				content:'<my:escapeHtml value="${bean.detail4Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail5Title && bean.detail5Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail5Title}"/>',
				content:'<my:escapeHtml value="${bean.detail5Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail6Title && bean.detail6Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail6Title}"/>',
				content:'<my:escapeHtml value="${bean.detail6Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail7Title && bean.detail7Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail7Title}"/>',
				content:'<my:escapeHtml value="${bean.detail7Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail8Title && bean.detail8Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail8Title}"/>',
				content:'<my:escapeHtml value="${bean.detail8Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail9Title && bean.detail9Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail9Title}"/>',
				content:'<my:escapeHtml value="${bean.detail9Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
		<c:if test="${not empty bean.detail10Title && bean.detail10Publish=='1'}">
			<c:if test="${detailIndex>0}">,</c:if>
			<c:set var="detailIndex" value="${detailIndex+1}"/>
			{
				title:'<c:out value="${bean.detail10Title}"/>',
				content:'<my:escapeHtml value="${bean.detail10Content}" removeTarget="false" changePath="true"/>'
			}
		</c:if>
	],
	</c:if>
	status:'<c:out value="${bean.projStatus}"/>',
	endLastDay:'<c:out value="${bean.endLastDay}"/>',
	zcedCount:'<fmt:formatNumber value="${bean.zcedCount}" pattern="0" type="number"/>',
	zcedPersonCount:'<fmt:formatNumber value="${bean.zcedPersonCount}" pattern="0" type="number"/>',
	zcedAmout:'<fmt:formatNumber value="${bean.zcedAmout}" pattern="0.##" type="number"/>',
	zcedPercent:'<fmt:formatNumber value="${bean.zcedPercent}" pattern="0.##" type="number"/>'
}