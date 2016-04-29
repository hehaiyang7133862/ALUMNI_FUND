<%@ include file="include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="head.jsp"%>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<c:forEach items="${list_file}" var="tmp" varStatus="status">
			<tr>
				<td><c:out value="${status.count}" />. <a
					href="<%=basePath%>fileDown.action?delsId=<c:out value='${tmp.tbFile.fileId}'></c:out>&tab=<c:out value='${tab}'></c:out>&id=<c:out value='${id}'></c:out>&fd=<c:out value='${fd}'></c:out>">
						<c:out value="${tmp.tbFile.fileName}" /> </a> ( <c:choose>
						<c:when test="${tmp.tbFile.fileSize<1024}">
							<fmt:formatNumber value="${tmp.tbFile.fileSize}" pattern="#.##"
								type="number" />B
    </c:when>
						<c:when test="${tmp.tbFile.fileSize<(1024*1024)}">
							<fmt:formatNumber value="${tmp.tbFile.fileSize/1024}"
								pattern="#.##" type="number" />KB
    </c:when>
						<c:otherwise>
							<fmt:formatNumber value="${tmp.tbFile.fileSize/(1024*1024)}"
								pattern="#.##" type="number" />MB
    </c:otherwise>
					</c:choose> ) </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>