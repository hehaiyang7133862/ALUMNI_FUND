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
<title>捐赠方列表</title>
<%@ include file="../common/head.jsp"%>
<script language="javascript">
function updateObject(val,flag,type){
	var rs="";
	// 添加
	if(flag==1){
		rs=val;
		$("#oldType").val(type);
	}
	// 移除
	$("#comIds").val(rs);
	document.getElementById("form1Id").submit();
}
function funback(){
	var comId=$("#comIds").val();
    var comNames=document.getElementById("comNames").value;
    var type=$("#oldType").val();
    window.parent.setJzfNames(comId,comNames,type);
	window.parent.parent.MyFormWin.restore();
	window.parent.MyFormWin.close();
}
// 查询验证
function validFind(form){
	formFormat("frmfindId");
	return true;
}
$(document).ready(function(){
	$("input[type='radio']").change(function(){
		$("#form1Id").submit();
	});
});
</script>
	</head>
	<body>
		<div class="float">
			<c:if test="${not empty comIds&&comIds!=''}">
				<div class="bar_cap">
					已添加对象
				</div>
				<table border="0" cellspacing="0" cellpadding="0" class="lyt_result">
					<tr>
						<c:choose>
						<c:when test="${oldType=='1'}">
							<c:forEach var="temp" items="${inComList}" varStatus="state">
								<td style="width: 20%;">
									<c:out value="${temp.comName}" />(单位)
									&nbsp;&nbsp;
									<span style="color: #F0C; cursor: pointer; font-style: italic" onClick="updateObject('<c:out value='${temp.comId}' />',2,0)">移除</span>
								</td>
								<c:if test="${0==state.count%5}">
							</tr>
							<tr>
							</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="temp" items="${inDorList}" varStatus="state">
								<td style="width: 20%;">
									<c:choose>
									<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.nameCn}"></c:out></c:when>
									<c:otherwise><c:out value="${temp.nameCn}" /></c:otherwise>
									</c:choose>(个人)
									&nbsp;&nbsp;
									<span style="color: #F0C; cursor: pointer; font-style: italic" onClick="updateObject('<c:out value='${temp.memId}' />',2,0)">移除</span>
								</td>
								<c:if test="${0==state.count%5}">
							</tr>
							<tr>
							</c:if>
							</c:forEach>
						</c:otherwise>
						</c:choose>
						
					</tr>
				</table>

			</c:if>
			<!-- ### -->
			<c:choose>
			<c:when test="${oldType=='1'}">
			<input id="comNames" name="comNames" type="hidden" value='<c:forEach  var="temp" items="${inComList}" ><c:out value="${temp.comName}" /></c:forEach>'>
			</c:when>
			<c:otherwise>
			<input id="comNames" name="comNames" type="hidden" value='<c:forEach  var="temp" items="${inDorList}" ><c:choose><c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.nameCn}"></c:out></c:when><c:otherwise><c:out value="${temp.nameCn}" /></c:otherwise></c:choose></c:forEach>'>
			</c:otherwise>
			</c:choose>
			
			<form name="form1" id="form1Id" method="post" action="<%=basePath%>found!findObject.action" onSubmit="return validFind(this)">
				<input id="comIds" name="comIds" type="hidden" value="<c:out value='${comIds}' />">
				<input id="oldType" name="oldType" type="hidden" value="<c:out value='${oldType}' />">
				<table width="100%" class="lyt_search">
					<tr>
						<td align="right" class="lyt_search_note">
							捐赠方名称
						</td>
						<td>
							<input style="width: 100px;" name="comName" type="text" id="textfield" value='<c:out value="${comName}" />' maxlength="20">
						</td>
						<td align="right" class="lyt_search_note">
							捐赠方性质
						</td>
						<td>
							<input style="width: 15px;height:15px;" id="company" type="radio" name="type" value="1" <c:if test="${type=='1'}">checked</c:if> onchange="$('#form1Id').submi();return false;"><label for="company">单位</label>
							<input style="width: 15px;height:15px;" id="person" type="radio" name="type" value="2" <c:if test="${type=='2'}">checked</c:if> onchange="$('#form1Id').submi();return false;"><label for="person">个人</label>
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
				<c:choose>
				<c:when test="${type=='1'}">
					<tr>
						<th>单位名称</th>
						<th>主要业务活动</th>
						<th>所属行业</th>
						<th>详细地址</th>
						<th>单位负责人</th>
						<th width="60">操作</th>
					</tr>
					<c:forEach var="temp" items="${companyList}"
						varStatus="state">
						<tr>
							<td>
								<span style="color: #06F; cursor: pointer" onClick="updateObject('<c:out value='${temp.comId}' />',1,1)"><c:out value="${temp.comName}" /></span>
							</td>
							<td>
								<c:out value="${temp.comBusi}" />
							</td>
							<td>
								<my:fieldView value="${temp.industryFid}"></my:fieldView>
							</td>
							<td>
								<c:out value="${temp.comAddress}"></c:out>
							</td>
							<td>
								<c:out value="${temp.responseName}"></c:out>
							</td>
							<td>
								<span style="color: #06F; cursor: pointer" onClick="updateObject('<c:out value='${temp.comId}' />',1,1)">添加</span>
							</td>
						</tr>
					</c:forEach>
					<input id="comNames" name="comNames" type="hidden" value='<c:forEach  var="temp" items="${inComList}" ><c:out value="${temp.comName}" /></c:forEach>'>
				</c:when>
				<c:otherwise>
					<tr>
						<th>姓名</th>
						<th>工作单位</th>
						<th>职务</th>
						<th width="60">操作</th>
					</tr>
					<c:forEach var="temp" items="${dorList}"
						varStatus="state">
						<tr>
							<td>
								<span style="color: #06F; cursor: pointer" onClick="updateObject('<c:out value='${temp.memId}' />',1,2)">
								<c:choose>
								<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.nameCn}"></c:out></c:when>
								<c:otherwise><c:out value="${temp.nameCn}" /></c:otherwise>
								</c:choose>
								</span>
							</td>
							<td>
								<c:if test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.companyName}" /></c:if>
							</td>
							<td>
								<c:choose>
								<c:when test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.dutyName}"></c:out></c:when>
								<c:otherwise><c:out value="${temp.dutyName}" /></c:otherwise>
								</c:choose>
							</td>
							<td>
								<span style="color: #06F; cursor: pointer" onClick="updateObject('<c:out value='${temp.memId}' />',1,2)">添加</span>
							</td>
						</tr>
					</c:forEach>
					<input id="comNames" name="comNames" type="hidden" value='<c:forEach  var="temp" items="${inDorList}" ><c:out value="${temp.tbAlumni.nameCn}" /></c:forEach>'>
				</c:otherwise>
				</c:choose>
				
			</table>
			<my:page action="found!findObject.action"></my:page>
			<!-- ### -->
		</div>
	</body>
</html>
