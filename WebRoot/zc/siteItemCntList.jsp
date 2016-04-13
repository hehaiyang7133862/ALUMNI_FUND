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
			MyFormWin.showMyWin("新建", '<%=basePath%>siteItemCnt!edit.action?item=${beanItem.itemId}',850,450);
		}
		function goEdit(id){
			MyFormWin.showMyWin('编辑','<%=basePath%>siteItemCnt!edit.action?item=${beanItem.itemId}&id='+id,850,450);
		}
		function goDelete(id){
			MyMsg.confirmFn('确定要删除该内容？',function(){
				location.href='<%=basePath%>siteItemCnt!del.action?id='+id+'&${fn:replace(p.pars,'id','radom')}';
			});
		}
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<c:choose>
			<c:when test="${beanItem.itemType=='9'}">
				<div style="padding:20px 20px;font-weight:bold;overflow:hidden;">
				链接模式，指向 <a href="<c:out value="${beanItem.linkUrl}"/>" target="_blank"><c:out value="${beanItem.linkUrl}"/></a>
				</div>
			</c:when>
			<c:otherwise>
			<c:if test="${beanItem.itemType=='1'}">
			<div style="padding:6px 6px;font-weight:bold;background-color:#f8f176;overflow:hidden;">！正文模式，默认显示第一个</div>
			</c:if>
			<form id="formId" action="<%=basePath%>siteItemCnt.action" method="get" onsubmit="return doSubmit()">
				<input name="item" value="${beanItem.itemId}" type="hidden" />
				<table style="width:100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:60px;">
							标题
						</td>
						<td width="80">
							<input type="text" name="title" style="width:80px;" value="<c:out value='${title}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							图标
						</td>
						<td width="80">
							<select name="logo" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${logo=='1'}">selected</c:if>>有</option>
								<option value="0" <c:if test="${logo=='0'}">selected</c:if>>无</option>
							</select>
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
					<th width="100">图标</th>
					<th>标题</th>
					<th width="150">文档类型</th>
					<th width="100">对外发布</th>
					<th width="100">发布时间</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${beanList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td title="图标">
						<c:if test="${not empty temp.contentLogo}">
						<img alt="" src="<%=basePath%><c:out value="${temp.contentLogo}"/>" height="60"/>
						</c:if>
					</td>
					<td>
						<a href="javascript:;" onclick="goEdit('${temp.contentId}');"><c:out value="${temp.contentTitle}"/></a>
					</td>
					<td>
						<c:choose>
						<c:when test="${temp.contentType=='9'}">链接，<a href="<c:out value="${temp.linkUrl}"/>" target="_blank"><c:out value="${temp.linkUrl}"/></a></c:when>
						<c:otherwise>正文</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
						<c:when test="${temp.publishFlag=='1'}">已发布</c:when>
						<c:otherwise>未发布</c:otherwise>
						</c:choose>
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.publishTime}'/>
					</td>
					<td title="操作">
						<a href="javascript:;" onclick="goEdit('${temp.contentId}');">编辑</a>
						<a href="javascript:;" onclick="goDelete('${temp.contentId}');">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<my:page action="siteItemCnt.action"></my:page>
			</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>