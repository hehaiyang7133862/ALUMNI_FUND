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
			formFormat("formId");
			return true;
		}
		function goEdit(id){
			top.uploadTab('项目【'+id+'】详情','项目【'+id+'】详情','<%=basePath%>foundReadOnly!toPage.action?foundId='+id);
		}
		function goXieyiMgt(id){
			top.uploadTab('项目【'+id+'】详情','项目【'+id+'】详情','foundReadOnly!procl.action?foundId='+id+'&num=1');
		}
		function goZhichuMgt(id){
			top.uploadTab('项目【'+id+'】详情','项目【'+id+'】详情','foundReadOnly!pay.action?foundId='+id+'&num=2');
		}
		function goZhixingMgt(id){
			top.uploadTab('项目【'+id+'】详情','项目【'+id+'】详情','foundReadOnly!doc.action?foundId='+id+'&num=3');
		}
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<form id="formId" action="<%=basePath%>foundReadOnly!list.action" method="get" onsubmit="return doSubmit()">
				<input name="typeId" value="${typeId}" type="hidden" />
				<input name="type" value="${type}" type="hidden" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:55px;">
							项目名称
						</td>
						<td width="70">
							<input type="text" name="foundName" style="width:70px;" value="<c:out value='${foundName}' />" maxlength="100" />
						</td>
						<c:if test="${empty type}">
						<td class="lyt_search_note" style="width:30px;">
							类别
						</td>
						<td width="60">
							<select name="foundtypeFid" style="width:60px;">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="FOUNDATION_TYPE" value="${foundtypeFid}" />
							</select>
						</td>
						</c:if>
						<td class="lyt_search_note" style="width:30px;">
							级别
						</td>
						<td width="60">
							<select name="foundLevel" style="width:60px;">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="FOUNDATION_LEVEL" value="${foundLevel}" />
							</select>
						</td>
						<td class="lyt_search_note" style="width:30px;">
							状态
						</td>
						<td width="65">
							<select name="foundStatus" style="width:65px;">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="FOUNDATION_STATUS" value="${foundStatus}" />
							</select>
						</td>
						<td class="lyt_search_note" style="width:30px;">
							留本
						</td>
						<td width="60">
							<select name="isKeep" style="width:60px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${isKeep=='1'}">selected</c:if>>是</option>
								<option value="2" <c:if test="${isKeep=='2'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:55px;">
							创建时间
						</td>
						<td width="205">
							<input style="width:72px;" type="text" name="startDate" id="startDate" readonly="true" class="lyt_search_date" value='${startDate}' />
							<img onClick="WdatePicker({el:'startDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
								到
							<input style="width:72px;" type="text" name="endDate" id="endDate" readonly="true" class="lyt_search_date" value='${endDate}' />
							<img onClick="WdatePicker({el:'endDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						</td>
						
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" style="margin-right:20px"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th>
						<my:order action="foundReadOnly!list.action" title="项目名称" name="a.foundName" />
					</th>
					<th width="70">
						<my:order action="foundReadOnly!list.action" title="创建时间" name="a.foundCreateDate" />
					</th>
					<c:if test="${empty type}">
					<th width="70">项目类别</th>
					</c:if>
					<th width="70">项目级别</th>
					<th width="70">受惠对象</th>
					<th width="70">是否留本</th>
					<th width="70">项目状态</th>
					<th width="140">操作</th>
				</tr>
				<c:forEach items="${foundList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td>
						<a href="javascript:;" onclick="goEdit('${temp.foundId}');"><c:out value="${temp.foundName}"></c:out></a>
					</td>
					<td>
						<c:out value="${temp.foundCreateDate}"></c:out>
					</td>
					<c:if test="${empty type}">
					<td>
						<my:fundField type="VIEW" lead="FOUNDATION_TYPE" value="${temp.foundtypeFid}" />
					</td>
					</c:if>
					<td>
						<my:fundField type="view" lead="FOUNDATION_LEVEL" value="${temp.foundLevel}" />
					</td>
					<td>
						<my:fundField type="view" lead="ACCEPT_OBJECT" value="${temp.foundObject}" />
					</td>
					<td>
						<c:choose>
						<c:when test="${temp.isKeep=='1'}">是</c:when>
						<c:when test="${temp.isKeep=='2'}">否</c:when>
						</c:choose>
					</td>
					<td>
						<my:fundField type="view" lead="FOUNDATION_STATUS" value="${temp.foundStatus}" />
					</td>
					<td>
						<a href="javascript:;" onclick="goEdit('${temp.foundId}');">详情</a>
						<a href="javascript:;" onclick="goXieyiMgt('${temp.foundId}');">协议</a>
						<a href="javascript:;" onclick="goZhichuMgt('${temp.foundId}');">支出</a>
						<a href="javascript:;" onclick="goZhixingMgt('${temp.foundId}');">文档</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<my:page action="foundReadOnly!list.action"></my:page>
		</div>
	</body>
</html>