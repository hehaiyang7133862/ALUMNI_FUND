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
			top.uploadTab('众筹【'+id+'】详情','众筹【'+id+'】详情','<%=basePath%>zcProjEditReadOnly.action?projId='+id);
		}
		$(document).ready(function(){
			windowResizeFn.add(function(){
				$('#resultDiv').width(document.body.clientWidth);
				$('#resultDiv').height(document.body.clientHeight-120-$('.lyt_nav').height());
				if($('.lyt_result').width()>$('#resultDiv').width()){
					$('.lyt_result').css('width','1800px');
				}else{
					$('.lyt_result').css('width','100%');
				}
			});
		});
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<form id="formId" action="<%=basePath%>zcProjReadOnly!list.action" method="get" onsubmit="return doSubmit()">
				<input name="typeId" value="${typeId}" type="hidden" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:60px;">
							项目名称 
						</td>
						<td width="80">
							<input type="text" name="projName" style="width:80px;" value="<c:out value='${projName}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							项目编码
						</td>
						<td width="80">
							<input type="text" name="projCode" style="width:80px;" value="<c:out value='${projCode}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							项目标签
						</td>
						<td width="80">
							<input type="text" name="searchKey" style="width:80px;" value="<c:out value='${searchKey}' />" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							上架
						</td>
						<td width="80">
							<select id="shelvesFlag" name="shelvesFlag" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${shelvesFlag=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${shelvesFlag=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:60px;">
							热门
						</td>
						<td width="80">
							<select name="hotFlag" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${hotFlag=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${hotFlag=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							目标完成后继续捐赠
						</td>
						<td>
							<select name="completedJz" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${completedJz=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${completedJz=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note">
							项目结束后归为经典
						</td>
						<td>
							<select name="classicStatus" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${classicStatus=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${classicStatus=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note">
							众筹状态
						</td>
						<td>
							<select name="zcStatus" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${zcStatus=='1'}">selected</c:if>>即将开始</option>
								<option value="2" <c:if test="${zcStatus=='2'}">selected</c:if>>众筹中</option>
								<option value="3" <c:if test="${zcStatus=='3'}">selected</c:if>>已结束</option>
							</select>
						</td>
						<td class="lyt_search_note">
							目标完成
						</td>
						<td>
							<select name="completed" style="width:80px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${completed=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${completed=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note">
							热门排序
						</td>
						<td>
							<input type="text" name="hotOrder" style="width:80px;" value="<c:out value='${hotOrder}' />" />
						</td>
						<td colspan="3">
						</td>
					</tr>
				</table>
			</form>
			<div id="resultDiv" style="height:60px;overflow:auto;">
			<table border="0" cellspacing="0" class="lyt_result" style="width:1800px;">
				<tr>
					<th width="40">序号</th>
					<th>
						<my:order action="zcProjReadOnly!list.action" title="项目名称" name="a.projName" />
					</th>
					<th width="60">操作</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="显示发布时间" name="a.shelvesTime" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="众筹目标" name="a.targetAmout" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="众筹开始时间" name="a.begTime" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="众筹截止时间" name="a.endTime" />
					</th>
					<th width="30">上架</th>
					<th width="30">热门</th>
					<th width="65">
						<my:order action="zcProjReadOnly!list.action" title="热门排序" name="a.hotOrder" />
					</th>
					<th width="65">目标完成后继续捐赠</th>
					<th width="65">项目结束后归为经典</th>
					<th width="60">众筹状态</th>
					<th width="60">
						<my:order action="zcProjReadOnly!list.action" title="结束剩余天数" name="a.endLastDay" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="筹得" name="a.zcedAmout" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="支持笔数" name="a.zcedCount" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="支持人数" name="a.zcedPersonCount" />
					</th>
					<th width="100">
						<my:order action="zcProjReadOnly!list.action" title="目标完成度" name="a.zcedPercent" />
					</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${beanList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td title="<c:out value="${temp.projName}"/>&#10;编码：<c:out value="${temp.projCode}"/>&#10;标签：<c:out value="${temp.searchKey}"/>">
						<p style="line-height:20px;"><a href="javascript:;" onclick="goEdit('${temp.projId}');"><c:out value="${temp.projName}"></c:out></a></p>
						<c:if test="${not empty temp.projCode}">
						<p style="line-height:20px;">编码：<c:out value="${temp.projCode}"/></p>
						</c:if>
						<c:if test="${not empty temp.searchKey}">
						<p style="line-height:20px;">标签：<c:forEach items="${fn:split(temp.searchKey,',')}" var="searchKeyTemp" varStatus="searchKeyStatus"><a href="<%=basePath%>zcProjReadOnly!list.action?searchKey=<c:out value="${searchKeyTemp}"/>&<c:out value="${fn:replace(p.pars,'searchKey','radom')}"/>"><c:out value="${searchKeyTemp}"/></a></c:forEach></p>
						</c:if>
					</td>
					<td title="操作">
						<a href="javascript:;" onclick="goEdit('${temp.projId}');">详情</a>
					</td>
					<td style="text-align:center;">
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.shelvesTime}'/>
					</td>
					<td>
						<fmt:formatNumber value='${temp.targetAmout}' pattern='#,##0.##' type='number'/> 元
					</td>
					<td style="text-align:center;">
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.begTime}'/>
					</td>
					<td style="text-align:center;">
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.endTime}'/>
					</td>
					<td style="text-align:center;">
						<c:choose>
						<c:when test="${temp.shelvesFlag=='1'}">是</c:when>
						<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align:center;">
						<c:choose>
						<c:when test="${temp.hotFlag=='1'}">是</c:when>
						<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${temp.hotFlag=='1'}"><c:out value="${temp.hotOrder}"></c:out></c:if>
					</td>
					<td style="text-align:center;">
						<c:choose>
						<c:when test="${temp.completedJz=='1'}">是</c:when>
						<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align:center;">
						<c:choose>
						<c:when test="${temp.classicStatus=='1'}">是</c:when>
						<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align:center;">
						<c:choose>
						<c:when test="${temp.projStatus=='1'}">即将开始</c:when>
						<c:when test="${temp.projStatus=='2'}">众筹中</c:when>
						<c:when test="${temp.projStatus=='3'}">已结束</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${temp.projStatus=='2' && not empty temp.endLastDay}"><fmt:formatNumber value='${temp.endLastDay}' pattern='#,##0.##' type='number'/> 天</c:if>
					</td>
					<td>
						<fmt:formatNumber value='${temp.zcedAmout}' pattern='#,##0.##' type='number'/> 元
					</td>
					<td>
						<fmt:formatNumber value='${temp.zcedCount}' pattern='#,##0' type='number'/>
					</td>
					<td>
						<fmt:formatNumber value='${temp.zcedPersonCount}' pattern='#,##0' type='number'/>
					</td>
					<td>
						<fmt:formatNumber value='${temp.zcedPercent}' pattern='#,##0.00' type='number'/> %
					</td>
					<td title="操作">
						<a href="javascript:;" onclick="goEdit('${temp.projId}');">详情</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			<my:page action="zcProjReadOnly!list.action"></my:page>
		</div>
	</body>
</html>