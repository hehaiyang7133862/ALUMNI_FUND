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
	<form id="formId" action="<%=basePath%>commodityReadOnly!info.action"
		method="get" onsubmit="return doSubmit()">
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
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input value="刷新" type="submit" style="margin-right:20px"/> 
					</div>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
		<tr>
			<th width="40">序号</th>
			<th width="100">是否对外显示</th>
			<th>参数名称</th>
			<th>参数值</th>
			<th>备注信息</th>
			<th width="60">操作</th>
		</tr>
		<c:forEach items="${beanList}" var="temp" varStatus="status">
			<tr>
				<td><b><c:out value="${status.count}" /> </b>
				</td>
				<td><c:choose>
						<c:when test="${temp.paramPublish=='1'}">对外显示</c:when>
						<c:otherwise>不对外显示</c:otherwise>
					</c:choose></td>
				<td>${temp.paramName }</td>
				<td>${temp.paramValue}</td>
				<td>${temp.memo}</td>
				<td>
					<a onclick="MyFormWin.showMyWin('参数详情','<%=basePath%>commodityReadOnly!operationParam.action?paramId=${temp.paramId}&commId=${bean.commId}',580,200);return false;" href="javascript:;">详情</a> 
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>