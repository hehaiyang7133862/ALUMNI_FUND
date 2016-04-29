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
<script type="text/javascript">
function doSubmit(){
	formFormat("formId");
	return true;
}
function myRefresh(){
	location.reload();
}
</script>
<body>
	<%@ include file="../common/navigation.jsp"%>
	<c:if test="${not empty bean.commId}">
		<%@ include file="commodityHeadReadOnly.jsp"%>
	</c:if>
	<form id="formId" action="<%=basePath%>commodityReadOnly!info.action" method="get" onsubmit="return doSubmit()">
		<input name="commId" value="${bean.commId}" type="hidden" /> <input
			name="num" value="${num}" type="hidden" />
		<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0"
			class="lyt_search">
			<tr>
				<td width="110"><select name="publish" style="width:100px;"
					onchange="doQuery();">
						<option value="">全部</option>
						<option value="1" <c:if test="${publish=='1'}">selected</c:if>>对外显示</option>
						<option value="0" <c:if test="${publish=='0'}">selected</c:if>>不对外显示</option>
				</select>
				</td>
				<td width="400">提示：商品图片不少于1个！</td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input value="刷新" type="submit" style="margin-right:20px"/>
					</div></td>
			</tr>
		</table>
	</form>
	<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
		<tr>
			<th width="40">序号</th>
			<th width="200">图片</th>
			<th width="100">是否对外显示</th>
			<th>描述信息</th>
			<th>备注信息</th>
			<th width="60">操作</th>
		</tr>
		<c:forEach items="${beanList}" var="pic" varStatus="status">
			<tr>
				<td><b><c:out value="${status.count}" /> </b></td>
				<td style="height:100px;">
					<div style="width:200px;overflow:hidden;">
						<img alt="" src="<%=basePath%>${pic.picPath}" height="100" />
					</div></td>
				<td><c:choose>
						<c:when test="${pic.picPublish=='1'}">对外显示</c:when>
						<c:otherwise>不对外显示</c:otherwise>
					</c:choose>
				</td>
				<td>${pic.picAlt}</td>
				<td>${pic.memo}</td>
				<td><a
					onclick="MyFormWin.showMyWin('图片详情','<%=basePath%>commodityReadOnly!operationPic.action?picId=${pic.picId}&commId=${bean.commId}',580,300);return false;"
					href="javascript:;">详情</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>