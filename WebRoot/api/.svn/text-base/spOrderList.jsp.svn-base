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
	comm:'<c:out value="${comm}"/>',
	donation:'<c:out value="${donation}"/>',
	alumniUn:'<c:out value="${alumniUn}"/>',
	orderStatus:'<c:out value="${orderStatus}"/>',
	order:'<c:out value="${order}"/>',
	orderType:'<c:out value="${orderType}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.orderId}"/>',
		comm:'<c:out value="${temp.commId}"/>',
		commName:'<c:out value="${temp.commName}"/>',
		commPic:'<c:if test="${not empty temp.commPic}"><%=basePath%><c:out value="${temp.commPic}"/></c:if>',
		option:'<c:out value="${temp.commDetailId}"/>',
		optionName:'<c:out value="${temp.commDetailName}"/>',
		optionCostfee:'<fmt:formatNumber value="${temp.commCostfee}" pattern="0.##" type="number"/>',
		optionSalefee:'<fmt:formatNumber value="${temp.commSalefee}" pattern="0.##" type="number"/>',
		optionCount:'<fmt:formatNumber value="${temp.commNum}" pattern="0" type="number"/>',
		shipType:'<c:out value="${temp.shippingType}"/>',
		shipFee:'<fmt:formatNumber value="${temp.shippingFee}" pattern="0.##" type="number"/>',
		shipCurFee:'<fmt:formatNumber value="${temp.shippingCurfee}" pattern="0.##" type="number"/>',
		donationFee:'<fmt:formatNumber value="${temp.donationFee}" pattern="0.##" type="number"/>',
		orderAmount:'<fmt:formatNumber value="${temp.orderFee}" pattern="0.##" type="number"/>',
		orderNo:'<c:out value="${temp.orderNo}"/>',
		orderSource:'<c:out value="${temp.orderSource}"/>',
		orderType:'<c:out value="${temp.orderType}"/>',
		orderStatus:'<c:out value="${temp.orderStatus}"/>',
		orderTime:'<fmt:formatDate value="${temp.orderTime}" pattern="yyyy-MM-dd HH:mm"/>',
		orderOkTime:'<fmt:formatDate value="${temp.orderOkTime}" pattern="yyyy-MM-dd HH:mm"/>',
		suportIndex:'<c:out value="${temp.suportIndex}"/>',
		personName:'<c:out value="${temp.buyerName}"/>',
		niMing:'<c:out value="${temp.niMing}" default="0"/>',
		personIp:'<c:out value="${temp.buyerIp}"/>',
		personSex:'<c:out value="${temp.buyerSex}"/>',
		personEmail:'<c:out value="${temp.buyerEmail}"/>',
		personPhone:'<c:out value="${temp.buyerMobile}"/>',
		personTel:'<c:out value="${temp.buyerPhone}"/>',
		addressZip:'<c:out value="${temp.buyerZipcode}"/>',
		addressDetail:'<c:if test="${not empty temp.buyerSheng}"><c:out value="${temp.buyerSheng}"/>（省）</c:if><c:if test="${not empty temp.buyerShi}"><c:out value="${temp.buyerShi}"/>（市）</c:if><c:if test="${not empty temp.buyerQu}"><c:out value="${temp.buyerQu}"/>（区）</c:if><c:if test="${not empty temp.buyerAddress}"><c:out value="${temp.buyerAddress}"/></c:if>',
		alumniFlag:'<c:out value="${temp.alumniFlag}"/>',
		alumniUn:'<c:out value="${temp.unAlumniId}"/>',
		studyYearin:'<c:out value="${temp.studyYearin}"/>',
		studyYearover:'<c:out value="${temp.studyYearover}"/>',
		studyAcademy:'<c:out value="${temp.studyAcademy}"/>',
		studyMajor:'<c:out value="${temp.studyMajor}"/>',
		studyClass:'<c:out value="${temp.studyClass}"/>',
		studyNum:'<c:out value="${temp.studyNum}"/>',
		studyDegree:'<c:out value="${temp.studyDegree}"/>',
		workCompany:'<c:out value="${temp.workCompany}"/>',
		workDepart:'<c:out value="${temp.workDepart}"/>',
		workDuty:'<c:out value="${temp.workDuty}"/>',
		orderMemo:'<my:escapeHtml value="${temp.orderMemo}" removeTarget="false" changePath="false"/>',
		receipt:'<my:escapeHtml value="${temp.receipt}" removeTarget="false" changePath="true"/>',
		starNum:'<fmt:formatNumber value="${temp.starNum}" pattern="0" type="number"/>',
		starMemo:'<my:escapeHtml value="${temp.starMemo}" removeTarget="false" changePath="false"/>',
		starTime:'<fmt:formatDate value="${temp.starTime}" pattern="yyyy-MM-dd HH:mm"/>'
	}
	</c:forEach>
	]
}