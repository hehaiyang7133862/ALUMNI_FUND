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
function myRefresh(){
	location.reload();
}
</script>
<body>
<%@ include file="fundMenu.jsp"%>
<%@ include file="protocolMenu.jsp"%>
	<div class="lyt_button">
	<input type="button" value="刷新" onclick="location.reload();" />&nbsp;
	<input type="button" value="新增" onclick="MyFormWin.showMyWin('新增参数','<%=basePath%>found!operationParam.action?proId=${proId}&num=${num}',580,280);return false;" />
			<input type="hidden" name="foundId" value="${foundId }" />
			<input type="hidden" name="proId" value="${proId }" />
			<input name="proType" type="hidden" value="<c:out value='${proType}' />" />
	</div>
		<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
			<tr>
				<th>参数名称</th>
				<th>参数值</th>
				<th>备注信息</th>
				<th width="130">操作</th>
			</tr>
			<c:forEach items="${beanList}" var="temp" varStatus="status">
				<tr>
					<td>${temp.paramName }</td>
					<td>${temp.paramValue}</td>
					<td>${temp.memo}</td>
					<td>
						<c:if test="${status.first==false}"><a onclick="if(confirm('您确定要上移吗？'))location='<%=basePath%>found!moveParam.action?foundId=${foundId}&proType=${proType }&type=1&paramId=${temp.paramId}&proId=${proId}&num=${num}'" href="javascript:;">上移</a></c:if>
						<c:if test="${status.last==false}"><a onclick="if(confirm('您确定要下移吗？'))location='<%=basePath%>found!moveParam.action?foundId=${foundId}&proType=${proType }&type=2&paramId=${temp.paramId}&proId=${proId}&num=${num}'" href="javascript:;">下移</a></c:if>
						<a onclick="MyFormWin.showMyWin('修改参数','<%=basePath%>found!operationParam.action?foundId=${foundId}&proType=${proType }&paramId=${temp.paramId}&proId=${proId}&num=${num}',580,280);return false;" href="javascript:;">编辑</a>
						<a onclick="if(confirm('您确定要删除吗？'))location='<%=basePath%>found!deleteParam.action?foundId=${foundId}&proType=${proType }&paramId=${temp.paramId}&proId=${proId}&num=${num}'" href="javascript:;">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>