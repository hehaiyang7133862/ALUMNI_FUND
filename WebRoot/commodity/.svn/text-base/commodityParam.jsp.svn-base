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
	<%@ include file="commodityHead.jsp"%>
	</c:if>
	<form id="formId" action="<%=basePath%>commodity!info.action" method="get" onsubmit="return doSubmit()">
		<input name="commId" value="${bean.commId}" type="hidden" />
		<input name="num" value="${num}" type="hidden" />
		<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
			<tr>
				<td width="400"></td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input value="刷新" type="submit" />
						<input value="新增" type="button" onclick="MyFormWin.showMyWin('新增参数','<%=basePath%>commodity!operationParam.action?commId=${bean.commId}',580,310);return false;" />
					</div>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
		<tr>
			<th width="40">序号</th>
			<th>参数名称</th>
			<th>参数值</th>
			<th>备注信息</th>
			<th width="60">操作</th>
		</tr>
		<c:forEach items="${beanList}" var="temp" varStatus="status">
		<tr>
			<td>
				<b><c:out value="${status.count}" /></b>
			</td>
			<td>${temp.paramName }</td>
			<td>${temp.paramValue}</td>
			<td>${temp.memo}</td>
			<td>
				<a onclick="MyFormWin.showMyWin('修改参数','<%=basePath%>commodity!operationParam.action?paramId=${temp.paramId}&commId=${bean.commId}',580,310);return false;" href="javascript:;">编辑</a>
				<a onclick="MyMsg.confirm('您确定要删除吗？','<%=basePath%>commodity!deleteParam.action?paramId=${temp.paramId}&commId=${bean.commId}&num=${num}');" href="javascript:;">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>