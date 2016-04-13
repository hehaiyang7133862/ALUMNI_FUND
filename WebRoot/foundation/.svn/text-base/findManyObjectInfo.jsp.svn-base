<%@ include file="../common/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>接受单位列表</title>
		<%@ include file="../common/head.jsp"%>
		<script language="javascript">
function updateObject(val,flag){
	var str=$("#comIds").val();
	var rs="";
	// 添加
	if(flag==1){
		if(str==""||str.length==0){
			rs=str+val;
		}else{
			rs=str+","+val;
		}
	}
	// 移除
	if(flag==2){
		var arr=str.split(",");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!=val&&arr[i]!=""){
				if(rs==""||rs.length==0){
					rs+=arr[i];
				}else{
					rs+=','+arr[i];
				}
			}
		}
	}
	$("#comIds").val(rs);
	document.getElementById("form1Id").submit();
}
function funback(){
	var comId=$("#comIds").val();
    var comNames=document.getElementById("comNames").value;
    window.parent.setNames(comId,comNames);
	window.parent.parent.MyFormWin.restore();
	window.parent.MyFormWin.close();
}
// 查询验证
function validFind(form){
	formFormat("frmfindId");
	return true;
}
</script>
	</head>
	<body>
		<div class="float">
			<c:if test="${not empty comIds&&comIds!=''}">
				<div class="bar_cap">
					已添加友对象
				</div>
				<table border="0" cellspacing="0" cellpadding="0" class="lyt_result">
					<tr>
						<c:forEach var="temp" items="${inComList}" varStatus="state">
							<td style="width: 20%;">
								<c:out value="${temp.comName}" />-<c:out value="${temp.relationName}" />
								&nbsp;&nbsp;
								<span style="color: #F0C; cursor: pointer; font-style: italic" onClick="updateObject('<c:out value='${temp.comId}' />',2)">移除</span>
							</td>
							<c:if test="${0==state.count%5}">
					</tr>
					<tr>
						</c:if>
						</c:forEach>
					</tr>
				</table>

			</c:if>
			<!-- ### -->
			<input id="comNames" name="comNames" type="hidden" value='<c:forEach  var="temp" items="${inComList}" ><c:out value="${temp.comName}" />-<c:out value="${temp.relationName}" />,</c:forEach>'>
			<form name="form1" id="form1Id" method="post" action="<%=basePath%>found!findManyObject.action" onSubmit="return validFind(this)">
				<input id="comIds" name="comIds" type="hidden" value="<c:out value='${comIds}' />">
				<table width="100%" class="lyt_search">
					<tr>
						<td align="right" class="lyt_search_note">
							单位名称
						</td>
						<td>
							<input style="width: 100px;" name="comName" type="text" id="textfield" value='<c:out value="${comName}" />' maxlength="20">
						</td>
						<td style="width: 250px;">
							<div class="lyt_button">
								<input type="button" value="确定" onClick="funback()" />
								<input type="submit" value="查询" />
							</div>
						</td>
					</tr>
				</table>

			</form>
			<!--###-->
			<!--###-->
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_result">
				<tr>
					<th>单位名称</th>
					<th>账户</th>
					<th>负责人</th>
					<th>手机</th>
					<th>联系人</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach var="temp" items="${companyList}"
					varStatus="state">
					<tr>
						<td>
							<c:out value="${temp.comName}" />
						</td>
						<td>
							<c:out value="${temp.accountName}" />&nbsp;<c:out value="${temp.accountNum}" />
						</td>
						<td>
							<c:out value="${temp.responseName}" />
						</td>
						<td>
							<c:out value="${temp.responseHandset}" />
						</td>
						<td>
							<c:out value="${temp.relationName}" />
						</td>
						<td>
							<c:out value="${temp.relationHandset}" />
						</td>
						<td>
							<span style="color: #06F; cursor: pointer" onClick="updateObject('<c:out value='${temp.comId}' />',1)">添加</span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<my:page action="found!findManyObject.action"></my:page>
			<!-- ### -->
		</div>
	</body>
</html>
