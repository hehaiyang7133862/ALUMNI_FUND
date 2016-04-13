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
		function goNew(){
			MyFormWin.showMyWin("新建", '<%=basePath%>siteJzMsg!edit.action',850,450);
		}
		function goEdit(id){
			MyFormWin.showMyWin('编辑','<%=basePath%>siteJzMsg!edit.action?id='+id,850,450);
		}
		function goDelete(id){
			MyMsg.confirmFn('确定要删除该内容？',function(){
				location.href='<%=basePath%>siteJzMsg!del.action?id='+id+'&${fn:replace(p.pars,'id','radom')}';
			});
		}
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<form id="formId" action="<%=basePath%>siteJzMsg.action" method="get" onsubmit="return doSubmit()">
				<table style="width:100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:60px;">
							捐赠人
						</td>
						<td width="80">
							<input type="text" name="msgFrom" style="width:80px;" value="<c:out value='${msgFrom}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							捐赠寄语
						</td>
						<td width="80">
							<input type="text" name="msg" style="width:80px;" value="<c:out value='${msg}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							发布
						</td>
						<td width="80">
							<select name="publish" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${publish=='1'}">selected</c:if>>已发布</option>
								<option value="0" <c:if test="${publish=='0'}">selected</c:if>>未发布</option>
							</select>
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input value="新增" type="button" onclick="goNew();" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th width="150">捐赠人</th>
					<th>捐赠寄语</th>
					<th width="80">捐赠时间</th>
					<th width="100">对外发布</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${beanList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td>
						<c:out value="${temp.msgFrom}"/>
					</td>
					<td>
						<c:out value="${temp.msgCnt}"/>
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd' value='${temp.msgTime}'/>
					</td>
					<td>
						<c:choose>
						<c:when test="${temp.publishFlag=='1'}">已发布</c:when>
						<c:otherwise>未发布</c:otherwise>
						</c:choose>
					</td>
					<td title="操作">
						<a href="javascript:;" onclick="goEdit('${temp.msgId}');">编辑</a>
						<a href="javascript:;" onclick="goDelete('${temp.msgId}');">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<my:page action="siteJzMsg.action"></my:page>
		</div>
	</body>
</html>