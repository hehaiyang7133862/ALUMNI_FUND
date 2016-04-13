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
		function doSubmit(){
			formFormat("formId");
			return true;
		}
		function goNew(){
			top.uploadTab('协议管理 > 新建协议','新建协议','<%=basePath%>found!toProPage.action?foundId=${foundId}');
		}
		function goEdit(id){
			top.uploadTab('协议【'+id+'】管理','协议【'+id+'】管理','<%=basePath%>found!toProPage.action?proId='+id+'&foundId=${foundId}');
		}
		function goMingxiMgt(id){
			top.uploadTab('协议【'+id+'】管理','协议【'+id+'】管理','<%=basePath%>found!proDetailList.action?point=2&proId='+id+'&foundId=${foundId}&num=1');
		}
		function goDelete(id){
			MyMsg.confirmFn('确定要删除该项目协议？',function(){
				location='<%=basePath%>found!delPro.action?proId='+id+'&foundId=${foundId}&${fn:replace(p.pars,'foundId','radom')}';
			});
		}
	</script>
</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<c:if test="${empty sub}">
			<%@ include file="fundMenu.jsp"%>
			</c:if>
			<form id="formId" action="<%=basePath%>found!procl.action" method="get" onsubmit="return doSubmit()">
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="foundId" value="${foundId}">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:70px">
							协议编号
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="proCode" value="<c:out value='${proCode}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:70px">
							协议名称
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="proName" value="<c:out value='${proName}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:70px">
							捐赠来源
						</td>
						<td width="85">
							<select style="width:80px;" name="jzSource">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="DONATION_SOURCE" value="${jzSource}"></my:fundField>
							</select>
						</td>
						<td class="lyt_search_note" style="width:70px">
							捐赠类型
						</td>
						<td width="85">
							<select style="width:80px;" name="jzType">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="TYPE_DOCATION" value="${jzType}" />
							</select>
						</td>
						<td class="lyt_search_note" style="width:70px">
							协议金额
						</td>
						<td width="210">
							<select name="proSign" style="width: 80px;">
								<option value=">=" <c:if test="${proSign=='>='}">selected</c:if>>大于等于</option>
								<option value=">" <c:if test="${proSign=='>'}">selected</c:if>>大于</option>
								<option value="=" <c:if test="${proSign=='='}">selected</c:if>>等于</option>
								<option value="&lt;" <c:if test="${proSign=='<'}">selected</c:if>>小于</option>
								<option value="&lt;=" <c:if test="${proSign=='<='}">selected</c:if>>小于等于</option>
							</select>
							<input style="width:106px;" type="text" name="proAmount" onkeyup="limit(this)" value="<c:out value='${proAmount}' />" maxlength="100" />
							元
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input value="新增" type="button" onClick='goNew();' />
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note" style="width:70px">
							捐赠方
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="jzfObject" value="<c:out value='${jzfObject}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:70px">
							使用方
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="syfObject" value="<c:out value='${syfObject}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:70px">
							捐赠方性质
						</td>
						<td width="85">
							<select style="width: 80px;" name="jzfxz">
								<option value="">全部</option>
								<option value="1" <c:if test="${jzfxz=='1'}">selected</c:if>>单位</option>
								<option value="2" <c:if test="${jzfxz=='2'}">selected</c:if>>个人</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:70px">
							协议状态
						</td>
						<td width="85">
							<select style="width:80px;" name="proStatus">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="PROTOCOL_STATUS" value="${proStatus}" />
							</select>
						</td>
						<td class="lyt_search_note" style="width:70px">
							签订日期
						</td>
						<td colspan="2">
							<input style="width: 75px;" type="text" name="startDate" id="startDate" readonly="true" class="lyt_search_date" value='${startDate}' />
							<img onClick="WdatePicker({el:'startDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
								到
							<input style="width: 75px;" type="text" name="endDate" id="endDate" readonly="true" class="lyt_search_date" value='${endDate}' />
							<img onClick="WdatePicker({el:'endDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						</td>
					</tr>
				</table>
			</form>
			<table style="width: 100%;" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th width="70">
						<my:order action="found!procl.action" title="协议编号" name="a.proCode" />
					</th>
					<th>
						<my:order action="found!procl.action" title="协议名称" name="a.proName" />
					</th>
					<th width="105">
						<my:order action="found!procl.action" title="协议金额（元）" name="a.proAmount" />
					</th>
					<!-- <th>实际到账（元）</th> -->
					<th width="100">捐赠方</th>
					<th width="100">受赠方</th>
					<th width="100">使用方</th>
					<th width="55">捐赠来源</th>
					<th width="70">捐赠方性质</th>
					<th width="70">
						<my:order action="found!procl.action" title="签订日期" name="a.dealDate" />
					</th>
					<th width="70">
						<my:order action="found!procl.action" title="执行日期" name="a.startTime" />
					</th>
					<th width="60">协议状态</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${proList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<c:out value="${temp.proCode}"></c:out>
						</td>
						<td>
							<a href="javascript:;" onclick="goEdit('${temp.proId}');"><c:out value="${temp.proName}"></c:out></a>
						</td>
						<td>
							<fmt:formatNumber value="${temp.proAmount}" />
						</td>
						<!--<td>
							<fmt:formatNumber value="${temp.toAmount}" />
						</td> -->
						<td>
							<c:out value="${temp.strJzfObject}"></c:out>
						</td>
						<td>
							<c:out value="${temp.szfObject}"></c:out>
						</td>
						<td>
							<c:out value="${temp.strSyfObject}"></c:out>
						</td>
						<td>
							<my:fundField type="VIEW" lead="DONATION_SOURCE" value="${temp.jzSource}" />
						</td>
						<td>
							<c:if test="${temp.jzfxz=='1'}">单位</c:if>
							<c:if test="${temp.jzfxz=='2'}">个人</c:if>
						</td>
						<td>
							<c:out value="${temp.dealDate}"></c:out>
						</td>
						<td>
							<c:out value="${temp.startTime}"></c:out>——<c:out value="${temp.endTime}"></c:out>
						</td>
						<td>
							<my:fundField type="VIEW" lead="PROTOCOL_STATUS" value="${temp.proStatus}" />
						</td>
						<td>
							<a href="javascript:;" onclick="goEdit('${temp.proId}');">编辑</a>
							<a href="javascript:;" onclick="goMingxiMgt('${temp.proId}');">明细</a>
							<a href="javascript:;" onclick="goDelete('${temp.proId}');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<my:page action="found!procl.action"></my:page>
		</div>
	</body>
</html>