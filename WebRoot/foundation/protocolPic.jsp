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
	提示：最多可上传十张照片，第一个照片默认为首图。
	<div class="lyt_button">
		<input type="button" value="刷新" onclick="location.reload();" />&nbsp;
		<input type="button" value="新增" onclick="MyFormWin.showMyWin('新增图片','<%=basePath%>found!operationPic.action?proId=${proId}&num=${num}',580,280);return false;" />
			<input type="hidden" name="foundId" value="${foundId }" />
			<input type="hidden" name="proId" value="${proId }" />
			<input name="proType" type="hidden" value="<c:out value='${proType}' />" />
	</div>
		<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
			<tr>
				<th width="180">图片</th>
				<th>描述信息</th>
				<th>备注信息</th>
				<th width="140">操作</th>
			</tr>
			<c:forEach items="${beanList}" var="pic" varStatus="status">
				<c:if test="${status.index<10 }">
					<tr>
						<td style="height:120px;">
							<div style="width:100px;">
								<img width="100%" height="100px" src="<%=basePath%>upload/image/${pic.picPath}" alt="${pic.picAlt}" />
							</div>
						</td>
						<td>${pic.picAlt}</td>
						<td>${pic.memo}</td>
					<td>
						<c:if test="${status.first==false}">
							<a onclick="if(confirm('您确定要上移吗？'))location='<%=basePath%>found!movePic.action?foundId=${foundId}&proType=${proType }&type=1&picId=${pic.picId}&proId=${proId}&num=${num}'" href="javascript:;">上移</a>
						</c:if>
						<c:if test="${status.last==false}">
							<a onclick="if(confirm('您确定要下移吗？'))location='<%=basePath%>found!movePic.action?foundId=${foundId}&proType=${proType }&type=2&picId=${pic.picId}&proId=${proId}&num=${num}'" href="javascript:;">下移</a>
						</c:if>
						<a onclick="MyFormWin.showMyWin('修改图片','<%=basePath%>found!operationPic.action?foundId=${foundId}&proType=${proType }&picId=${pic.picId}&proId=${proId}&num=${num}',580,380);return false;" href="javascript:;">编辑</a>
						<a onclick="if(confirm('您确定要删除吗？'))location='<%=basePath%>found!deletePic.action?foundId=${foundId}&proType=${proType }&picId=${pic.picId}&proId=${proId}&num=${num}'" href="javascript:;">删除</a>
					</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
</body>
</html>