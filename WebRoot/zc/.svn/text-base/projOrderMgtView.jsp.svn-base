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
		<div class="lyt_submit" style="position:fixed;right:0px;bottom:0px;width:75px;height:25px;background:none;border:0px;overflow:hidden;">
			<input type="button" value="标记" style="letter-spacing:0px;font-weight:bold;" onclick="parent.goMark('${bean.orderId}');" />
		</div>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						捐赠单号
					</td>
					<td>
						<c:out value="${bean.orderNum}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠项目
					</td>
					<td>
						<c:out value="${bean.projName}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠选项
					</td>
					<td>
						<c:out value="${bean.optionName}" default="任意捐"/><c:if test="${not empty bean.optionCount}"> <b>×</b> <c:out value="${bean.optionCount}" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠币种
					</td>
					<td>
						<c:out value="${bean.orderAmountType}"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠币种总金额
					</td>
					<td>
						<fmt:formatNumber value='${bean.orderAmountView}' pattern='0.##' type='number'/> <c:out value="${bean.orderAmountUnit}"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						实算总金额
					</td>
					<td>
						<fmt:formatNumber value='${bean.orderAmount}' pattern='0.##' type='number'/> 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠方式
					</td>
					<td>
						<c:out value="${bean.orderType}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠状态
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
					<td class="lyt_view_note">
						证书
					</td>
					<td>
						<c:choose>
						<c:when test="${bean.needZhengshu=='1'}">需要</c:when>
						<c:otherwise>不需要</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						发票
					</td>
					<td>
						<c:choose>
						<c:when test="${bean.needFapiao=='1'}">需要</c:when>
						<c:otherwise>不需要</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠人类型
					</td>
					<td>
						<c:choose>
						<c:when test="${bean.personType=='2'}">集体（实捐<fmt:formatNumber value='${bean.personCount}' pattern='#,##0' type='number'/>人）</c:when>
						<c:otherwise>个人</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						捐赠人
					</td>
					<td style="line-height:24px;">
						<p><c:out value="${bean.personName}" /><c:if test="${bean.niMing=='1'}"><b>（匿名捐赠）</b></c:if></p>
						<p>性别：<c:choose><c:when test="${bean.personSex=='1'}">男</c:when><c:when test="${bean.personSex=='2'}">女</c:when><c:otherwise>不详</c:otherwise></c:choose></p>
						<p>手机：<c:out value="${bean.personPhone}" /></p>
						<p>电话：<c:out value="${bean.personTel}" /></p>
						<p>邮箱：<c:out value="${bean.personEmail}" /></p>
						<p>邮编：<c:out value="${bean.addressZip}" /></p>
						<p>地址：<c:if test="${not empty bean.addressSheng}"><c:out value="${bean.addressSheng}" />（省） </c:if><c:if test="${not empty bean.addressShi}"><c:out value="${bean.addressShi}" />（市） </c:if><c:if test="${not empty bean.addressQu}"><c:out value="${bean.addressQu}" />（区） </c:if><c:if test="${not empty bean.addressDetail}"><c:out value="${bean.addressDetail}" /></c:if></p>
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