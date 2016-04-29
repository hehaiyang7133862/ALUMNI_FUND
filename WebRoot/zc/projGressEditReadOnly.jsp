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
<%@ include file="../common/calendar.jsp"%>
<%@ include file="../common/kindeditor.jsp"%>
</head>
<body>
	<div class="float">
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note" style="width:80px;">进度时间 </td>
				<td width="660">
					<fmt:formatDate pattern='yyyy-MM-dd' value='${bean.gressTime}'/>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">进度详情</td>
				<td>
					<iframe style="width:645px;height:300px" src="getGressContent.action?gressId=${bean.gressId}"></iframe>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注说明</td>
				<td>
					<span style="width:652px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="gressId" table="TbZcprojProgress" value="${bean.gressId}"></my:timeView>
	</div>
</body>
</html>