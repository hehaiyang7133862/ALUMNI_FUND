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
	<script type="text/javascript">
	function doSubmit(){
	}
	function myRefresh(){
		location='<%=basePath%>donate!acceptList.action';
	}
	
	$(document).ready(function(){
    	<c:if test="${not empty alert}">
    		//刷新
    		setTimeout(myRefresh(),200);
    	</c:if>
    });
	</script>
</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
			<form id="adviseForm" action="<%=basePath%>donate!acceptList.action" method="post" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" width="80px">
							接受单位
						</td>
						<td width="211">
							<input style="width: 204px;" type="text" name="comName" value="<c:out value='${comName}' />" maxlength="100" />
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input name="" value="新增" type="button" onClick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action',580,380);return false;" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th>单位名称</th>
					<th>账户名称</th>
					<th>负责人</th>
					<th>联系电话</th>
					<th>联系手机</th>
					<th>联系邮箱</th>
					<th>联系人</th>
					<th>联系电话</th>
					<th>联系手机</th>
					<th>联系邮箱</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${acceptList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<a href="javascript:;" onClick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action?comId=${temp.comId}',590,390);return false;">
							<c:out value="${temp.comName}"></c:out>
							</a>
						</td>
						<td>
							<c:out value="${temp.accountName}"></c:out><c:if test="${not empty temp.accountNum}">****<c:out value="${fn:substring(temp.accountNum,fn:length(temp.accountNum)-4,fn:length(temp.accountNum))}"></c:out></c:if>
						</td>
						<td>
							<c:out value="${temp.responseName}"></c:out>
						</td>
						<td>
							<c:out value="${temp.responseTel}"></c:out>
						</td>
						<td>
							<c:out value="${temp.responseHandset}"></c:out>
						</td>
						<td>
							<c:out value="${temp.responseMail}"></c:out>
						</td>
						<td>
							<c:out value="${temp.relationName}"></c:out>
						</td>
						<td>
							<c:out value="${temp.relationTel}"></c:out>
						</td>
						<td>
							<c:out value="${temp.relationHandset}"></c:out>
						</td>
						<td>
							<c:out value="${temp.relationMail}"></c:out>
						</td>
						<td>
							<a href="javascript:;" onClick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action?comId=${temp.comId}',590,390);return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除')){location='<%=basePath%>donate!delAccept.action?comId=${temp.comId}'}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty p}">
			<my:page action="donate!acceptList.action"></my:page>
			</c:if>
		</div>
	</body>
</html>