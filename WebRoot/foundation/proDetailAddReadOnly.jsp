<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<div class="page">
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note" style="width: 80px;">承诺日期</td>
				<td style="width: 160px;">
					<c:out value="${bean.dealDate}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">承诺金额</td>
				<td style="width: 160px;">
					<fmt:formatNumber value='${bean.dealAmount}' pattern='0.##' type='number'/>&nbsp;元
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">到账日期</td>
				<td style="width: 160px;">
					<c:out value="${bean.toDate}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">到账金额</td>
				<td style="width: 160px;">
					<fmt:formatNumber value='${bean.toAmount}' pattern='0.##' type='number'/>&nbsp;元
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">到账账户</td>
				<td colspan="3">
					<c:forEach items="${accList}" var="temp" varStatus="status">
						<c:if test="${temp.accountId==bean.tbDonationAccout.accountId}">
							<c:out value="${temp.accountName}" />:<c:out value="${temp.accountNum}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">指定用途</td>
				<td colspan="3">
					<my:fundField type="VIEW" lead="TYPE_FUND" value="${bean.tbFields.parentId}" />-&gt;<my:fundField type="VIEW" parentId="${bean.tbFields.parentId}" value="${bean.proUse}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">是否配比</td>
				<td style="width: 160px;" colspan="3">
					<c:choose>
						<c:when test="${bean.sfpb=='1'}">是（<c:choose>
							<c:when test="${bean.status=='1'}">未申报&nbsp;</c:when>
							<c:when test="${bean.status=='2'}">申报中&nbsp;</c:when>
							<c:when test="${bean.status=='3'}">申报成功&nbsp;</c:when>
							<c:when test="${bean.status=='4'}">申报失败&nbsp;</c:when>
						</c:choose>申报金额：<c:out value="${bean.amount}" />元&nbsp;实际金额：<c:out value="${bean.realAmount}" />元）</c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
			<tr>
				<td class="lyt_view_note" style="width: 80px; ">备注</td>
				<td colspan="3">
					<span style="width:414px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="detailId" table="TbProDetail" value="${bean.detailId}"></my:timeView>
	</div>
</body>
</html>