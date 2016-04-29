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
	<div class="float">
		<table class="lyt_view">
			<c:if test="${not empty bean.picId}">
				<tr>
					<td class="lyt_view_area">当前图片</td>
					<td>
						<div style="width:400px;overflow:hidden;">
							<img alt="" src="<%=basePath%>${bean.picPath}" height="100" />
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="lyt_view_note" style="width:54px;">图片参数</td>
				<td width="80">
					<c:out value="${bean.numOrder}" />
					<c:choose>
						<c:when test="${bean.picPublish=='1'}">&nbsp;对外显示 </c:when>
						<c:otherwise>&nbsp;不对外显示</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">图片说明</td>
				<td>
					<span style=" width:400px; display:block"><c:out value='${bean.picAlt}' /></span>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注说明</td>
				<td>
					<span style=" width:400px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="picId" table="TbZcprojPic" value="${bean.picId}"></my:timeView>
	</div>
</body>
</html>