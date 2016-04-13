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
	<%@ include file="../common/calendar.jsp"%>
	<script type="text/javascript">
	function goNew(){
		top.uploadTab('众筹管理 > 新建众筹','新建众筹','<%=basePath%>found!toProZcPage.action?num=${num}&proType=2&foundId=${foundId}');
	}
	function goEdit(id){
		top.uploadTab('众筹【'+id+'】管理','众筹【'+id+'】管理','<%=basePath%>found!toProZcPage.action?point=2&num=${num}&proType=2&sub=${sub}&proId='+id+'&foundId=${foundId}');
	}
	function goDelete(id){
		MyMsg.confirmFn('确定要删除该项目众筹？',function(){
			location='<%=basePath%>found!delPro.action?point=2&proType=2&proId='+id+'&foundId=${foundId}&sub=${sub}&num=${num}&${fn:replace(p.pars,'foundId','radom')}';
		});
	}
	function myRefresh(){
		location='<%=basePath%>found!proclZc.action?foundId=${foundId}&num=${num}&sub=${sub}';
	}
	</script>
</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:if test="${empty sub}">
		<%@ include file="fundMenu.jsp"%>
		</c:if>
		<c:choose>
		<c:when test="${not empty foundId or sub=='all'}">
			<form id="adviseForm" action="<%=basePath%>found!proclZc.action?sub=${sub}" method="post" onsubmit="">
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="foundId" value="${foundId}">
				<input type="hidden" name="proType" value="${proType}">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" width="80px">
							协议编号
						</td>
						<td width="181">
							<input style="width: 174px;" type="text" name="proCode" value="<c:out value='${proCode}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							协议名称
						</td>
						<td width="181">
							<input style="width: 174px;" type="text" name="proName" value="<c:out value='${proName}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							目标金额
						</td>
						<td>
							<select name="proSign" style="width: 80px;">
								<option value=">=" <c:if test="${proSign=='>='}">selected</c:if>>大于等于</option>
								<option value=">" <c:if test="${proSign=='>'}">selected</c:if>>大于</option>
								<option value="=" <c:if test="${proSign=='='}">selected</c:if>>等于</option>
								<option value="&lt;" <c:if test="${proSign=='<'}">selected</c:if>>小于</option>
								<option value="&lt;=" <c:if test="${proSign=='<='}">selected</c:if>>小于等于</option>
							</select>
							<input style="width: 120px;" type="text" name="proAmount" onkeyup="limit(this)" value="<c:out value='${proAmount}' />" maxlength="100" />
						</td>
						<td align="right" class="lyt_search_opt" colspan="8">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input name="" value="新增" type="button" onClick='goNew()' />
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							签订日期
						</td>
						<td width="211">
							<input style="width: 70px;" type="text" name="startDate" id="startDate" readonly="true" class="lyt_search_date" value='${startDate}' />
							<img onClick="WdatePicker({el:'startDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
								到
							<input style="width: 70px;" type="text" name="endDate" id="endDate" readonly="true" class="lyt_search_date" value='${endDate}' />
							<img onClick="WdatePicker({el:'endDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						</td>
						<td class="lyt_search_note">
							是否热门
						</td>
						<td>
							<select name="isHOt" style="width: 174px;">
								<option value="1" <c:if test="${isHOt=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${isHOt=='0'}">selected</c:if>>否</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<table style="width: 100%;" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th width="80">操作</th>
					<th width="100">
						<my:order action="found!proclZc.action" title="协议编号" name="a.proCode" />
					</th>
					<th>
						<my:order action="found!proclZc.action" title="协议名称" name="a.proName" />
					</th>
					<th width="120">
						<my:order action="found!proclZc.action" title="协议金额（元）" name="a.proAmount" />
					</th>
					<th width="100">
						<my:order action="found!proclZc.action" title="开始日期" name="a.startDate" />
					</th>
					<th width="100">
						<my:order action="found!proclZc.action" title="结束日期" name="a.endDate" />
					</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${proList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<a href="javascript:;" onclick="location='<%=basePath%>found!toProZcPage.action?proId=${temp.proId}&foundId=${temp.foundId}&sub=${sub}';return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除？')){location='<%=basePath%>found!delPro.action?proId=${temp.proId}&foundId=${temp.foundId}&sub=${sub}&num=${num}'}">删除</a>
						</td>
						<td>
							<c:out value="${temp.proCode}"></c:out>
						</td>
						<td>
							<a href="javascript:;" onclick="location='<%=basePath%>found!toProPage.action?proType=${proType }&proId=${temp.proId}&foundId=${temp.foundId}&sub=${sub}';return false;"><c:out value="${temp.proName}"></c:out></a>
						</td>
						<td>
							<fmt:formatNumber value="${temp.proAmount}" />
						</td>
						<td>
							<c:out value="${temp.startTime}"></c:out>
						</td>
						<td>
							<c:out value="${temp.endTime}"></c:out>
						</td>
						<td>
							<a href="javascript:;" onclick="goEdit('${temp.proId}');">编辑</a>
							<a href="javascript:;" onclick="goDelete('${temp.proId}');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty p}">
			<my:page action="found!proclZc.action"></my:page>
			</c:if>
		</c:when>
		<c:otherwise>
			<div style="font-size: 16px;padding: 10px;">请先创建项目</div>
		</c:otherwise>
		</c:choose>
		</div>
	</body>
</html>