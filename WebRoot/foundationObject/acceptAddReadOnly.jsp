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
				<td class="lyt_view_note" style="width: 80px;">单位名称</td>
				<td style="width: 160px;" colspan="3">
					<c:out value="${bean.comName}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">账户名称</td>
				<td style="width: 160px;">
					<c:out value="${bean.accountName}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">账号</td>
				<td>
					<c:out value="${bean.accountNum}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">单位负责人</td>
				<td style="width: 160px;">
					<c:out value="${bean.responseName}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">联系电话</td>
				<td style="width: 160px;">
					<c:out value="${bean.responseTel}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">联系手机</td>
				<td style="width: 160px;">
					<c:out value="${bean.responseHandset}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">联系邮箱</td>
				<td style="width: 160px;">
					<c:out value="${bean.responseMail}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">单位联系人</td>
				<td style="width: 160px;">
					<c:out value="${bean.relationName}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">联系电话</td>
				<td style="width: 160px;">
					<c:out value="${bean.relationTel}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">联系手机</td>
				<td style="width: 160px;">
					<c:out value="${bean.relationHandset}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">联系邮箱</td>
				<td style="width: 160px;">
					<c:out value="${bean.relationMail}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
					备注</td>
				<td colspan="3">
					<span style="display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="comId" table="TbAcceptCompany"
			value="${bean.comId}"></my:timeView>
	</div>
</body>
</html>