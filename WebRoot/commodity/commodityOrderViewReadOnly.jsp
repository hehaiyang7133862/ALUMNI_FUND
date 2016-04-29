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
			<%@ include file="../common/navigation.jsp"%>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						订单号
					</td>
					<td>
						<c:out value="${bean.orderNo}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						商品名称
					</td>
					<td>
						<c:out value="${bean.commName}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						商品型号
					</td>
					<td>
						<c:out value="${bean.commDetailName}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						成本价
					</td>
					<td>
						<fmt:formatNumber value='${bean.commCostfee}' pattern='0.##' type='number'/>&nbsp;&nbsp; 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						售价
					</td>
					<td>
						<fmt:formatNumber value='${bean.commSalefee}' pattern='0.##' type='number'/>&nbsp;&nbsp; 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						商品数量
					</td>
					<td>
						<c:out value="${bean.commNum}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						取货方式
					</td>
					<td>
						<c:choose>
						<c:when test="${bean.shippingType=='1'}">买家自提</c:when>
						<c:otherwise>物流运输</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						运费
					</td>
					<td>
						<fmt:formatNumber value='${bean.shippingFee}' pattern='0.##' type='number'/>&nbsp;&nbsp; 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠
					</td>
					<td>
						<fmt:formatNumber value='${bean.donationFee}' pattern='0.##' type='number'/>&nbsp;&nbsp; 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						总金额
					</td>
					<td>
						<fmt:formatNumber value='${bean.orderFee}' pattern='0.##' type='number'/>&nbsp;&nbsp; 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						支付方式
					</td>
					<td>
						<c:out value="${bean.orderType}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						支付状态
					</td>
					<td>
						<c:choose>
						<c:when test="${bean.orderStatus=='1'}">已付款</c:when>
						<c:otherwise>待付款</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						提交时间
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderTime}'/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						付款时间
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderOkTime}'/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						买家
					</td>
					<td style="line-height:24px;">
						<p><c:out value="${bean.buyerName}" /></p>
						<p>性别：<c:choose><c:when test="${bean.buyerSex=='1'}">男</c:when><c:when test="${bean.buyerSex=='2'}">女</c:when><c:otherwise>不详</c:otherwise></c:choose></p>
						<p>手机：<c:out value="${bean.buyerMobile}" /></p>
						<p>电话：<c:out value="${bean.buyerPhone}" /></p>
						<p>邮箱：<c:out value="${bean.buyerEmail}" /></p>
						<p>邮编：<c:out value="${bean.buyerZipcode}" /></p>
						<p>地址：<c:if test="${not empty bean.buyerSheng}"><c:out value="${bean.buyerSheng}" />（省） </c:if><c:if test="${not empty bean.buyerShi}"><c:out value="${bean.buyerShi}" />（市） </c:if><c:if test="${not empty bean.buyerQu}"><c:out value="${bean.buyerQu}" />（区） </c:if><c:if test="${not empty bean.buyerAddress}"><c:out value="${bean.buyerAddress}" /></c:if></p>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						校友资料
					</td>
					<td style="line-height:24px;">
						<c:choose>
						<c:when test="${bean.alumniFlag=='1'}">
							<p>校友</p>
							<p>入学年：<c:out value="${bean.studyYearin}" /></p>
							<p>离校年：<c:out value="${bean.studyYearover}" /></p>
							<p>院系：<c:out value="${bean.studyAcademy}" /></p>
							<p>专业：<c:out value="${bean.studyMajor}" /></p>
							<p>班级：<c:out value="${bean.studyClass}" /></p>
							<p>学号：<c:out value="${bean.studyNum}" /></p>
							<p>学历：<c:out value="${bean.studyDegree}" /></p>
						</c:when>
						<c:otherwise>非校友</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						工作资料
					</td>
					<td style="line-height:24px;">
							<p>公司：<c:out value="${bean.workCompany}" /></p>
							<p>部门：<c:out value="${bean.workDepart}" /></p>
							<p>职位：<c:out value="${bean.workDuty}" /></p>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						其他信息
					</td>
					<td style="line-height:24px;">
						<c:out value="${bean.orderMemo}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						<b style="color:#ff0000;">标记信息</b>
					</td>
					<td>
						<c:out value="${bean.mark}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						<b style="color:#ff0000;">回执信息</b>
					</td>
					<td>
						${bean.receipt}
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						<b style="color:#ff0000;">备注说明</b>
					</td>
					<td>
						<c:out value="${bean.memo}" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>