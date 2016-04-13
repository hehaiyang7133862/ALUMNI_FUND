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
		location='<%=basePath%>donate!dorlist.action';
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
			<form id="adviseForm" action="<%=basePath%>donate!dorlist.action" method="post" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" width="80px">
							姓名
						</td>
						<td width="211">
							<input style="width: 204px;" type="text" name="nameCn" value="<c:out value='${nameCn}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							办公电话
						</td>
						<td width="250">
							<input style="width: 204px;" type="text" name="telephone" value="<c:out value='${telephone}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							邮箱
						</td>
						<td colspan="3">
							<input style="width: 278px;" type="text" name="mail" value="<c:out value='${mail}' />" maxlength="100" />
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							手机
						</td>
						<td>
							<input style="width: 204px;" type="text" name="handset" value="<c:out value='${handset}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							工作单位
						</td>
						<td>
							<input style="width: 204px;" type="text" name="company" value="<c:out value='${company}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							行业
						</td>
						<td style="width: 84px;">
							<select name="industryFid" style="width: 84px;">
								<option value="">全部</option>
								<my:field type="OPTION" lead="INDUSTRY" value="${industryFid}"></my:field>
							</select>
						</td>
						<td class="lyt_search_note">
							是否校友
						</td>
						<td>
							<select style="width: 84px;" name="isStu" id="isStu">
								<option value="">全部</option>
								<option value="0" <c:if test="${isStu=='0'}">selected</c:if>>否</option>
								<my:field type="OPTION" lead="ALUMNITYPE" value="${isStu}" />
							</select>
						</td>
					</tr>
					<tr>
						<td align="right" class="lyt_search_opt" colspan="8">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input name="" value="新增" type="button" onClick="MyFormWin.showMyWin('捐赠人详细','<%=basePath%>donate!toDorAdd.action',580,380);return false;" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th>姓名</th>
					<th>工作单位</th>
					<th width="60">职务</th>
					<th>办公电话</th>
					<th>手机</th>
					<th>邮箱</th>
					<th width="60">是否校友</th>
					<th width="70">行业</th>
					<th width="70">人物属性</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${dorList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<a href="javascript:;" onClick="MyFormWin.showMyWin('捐赠人详细','<%=basePath%>donate!toDorAdd.action?memId=${temp.memId}',590,390);return false;">
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.nameCn}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.nameCn}" /></c:otherwise>
							</c:choose>
							</a>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.companyName}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.company}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.dutyName}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.dutyName}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.telephoneFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.telephone}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.handsetFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.handset}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.mailFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.mail}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${temp.isStu=='0'}">
								否
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${not empty temp.tbAlumni}">
									<c:set var="typeFid" value="${temp.tbAlumni.typeFid}" />
									<c:set var="typeFidIndex" value="${myFn:lastIndexOf(typeFid,',')+1}" />
									<my:fieldView value="${fn:substring(typeFid,typeFidIndex,fn:length(typeFid))}" />
								</c:when>
								<c:otherwise>
									<my:fieldView value="${temp.isStu}" />
								</c:otherwise>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}">
								<my:fieldView value="${temp.tbAlumni.industryNowFid}"></my:fieldView>
							</c:when>
							<c:otherwise>
								<my:fieldView value="${temp.industryFid}"></my:fieldView>
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${not empty temp.tbAlumni}">
									<c:set var="gradeFid" value="${temp.tbAlumni.gradeFid}" />
									<c:set var="gradeFidIndex" value="${myFn:lastIndexOf(gradeFid,',')+1}" />
									<my:fieldView value="${fn:substring(gradeFid,gradeFidIndex,fn:length(gradeFid))}" />
								</c:when>
								<c:otherwise>
									<my:fieldView value="${temp.gradeFid}"></my:fieldView>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="javascript:;" onClick="MyFormWin.showMyWin('捐赠人详细','<%=basePath%>donate!toDorAdd.action?memId=${temp.memId}',590,390);return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除')){location='<%=basePath%>donate!delDonationor.action?memId=${temp.memId}'}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty p}">
			<my:page action="donate!dorlist.action"></my:page>
			</c:if>
		</div>
	</body>
</html>