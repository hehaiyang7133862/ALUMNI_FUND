<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/calendar.jsp"%>
<%@ include file="../common/kindeditor.jsp"%>
<script type="text/javascript">
	function detailOn(obj){
		$('#detailTitleUl').children().css({'color':'','background':'url(<%=basePath%>UI/images/nav_point.png) no-repeat top left'});
		$('#detailContentDiv').children().hide();
		
		var titleObj = $(obj);
		var contentObj = $('#detailContentDiv table:eq('+titleObj.prevAll().length+')');
		
		titleObj.css({'color':'#fff','background':'url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left'});
		contentObj.show();
		contentObj.find('input[name="detailTitle"]').select();
	}
</script>
</head>
<body>
	<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:if test="${not empty bean.projId}">
			<%@ include file="projMenuReadOnly.jsp"%>
		</c:if>
		<input type="hidden" name="projId" value="${bean.projId}" />
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note">项目名称</td>
				<td colspan="3">
					<c:out value="${bean.projName}" />
				</td>
				<td class="lyt_view_note">项目编码</td>
				<td><c:out value="${bean.projCode}" /></td>
			</tr>
			<tr>
				<td class="lyt_view_note">所属分类</td>
				<td colspan="3">
					<c:forEach items="${typeList}" var="temp" varStatus="status">
						<c:if test="${temp.typeId==bean.projType}">
							<c:out value="${temp.typeName}" />
						</c:if>
					</c:forEach>
				</td>
				<td class="lyt_view_note">显示发布时间</td>
				<td>
					<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.shelvesTime}' />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width:100px;">目标金额</td>
				<td style="width:170px;">
					<fmt:formatNumber value='${bean.targetAmout}' pattern='0.##' type='number' />&nbsp;元
				</td>
				<td class="lyt_view_note" style="width:100px;">是否上架</td>
				<td style="width:170px;"><c:choose>
						<c:when test="${bean.shelvesFlag=='1'}">&nbsp;是</c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_note" style="width:120px;">是否热门</td>
				<td>
					<c:choose>
						<c:when test="${bean.hotFlag=='1'}">&nbsp;是（排序:<c:out value="${bean.hotOrder}" />） </c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">众筹开始时间</td>
				<td>
					<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.begTime}' />
				</td>
				<td class="lyt_view_note">众筹截止时间</td>
				<td>
					<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.endTime}' />
				</td>
				<td class="lyt_view_note">目标完成后继续众筹</td>
				<td>
					<c:choose>
						<c:when test="${bean.completedJz=='1'}">&nbsp;是</c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">项目关键字标签</td>
				<td colspan="3">
					<c:out value="${bean.searchKey}" />
				</td>
				<td class="lyt_view_area">结束后归入经典回顾</td>
				<td>
					<c:choose>
						<c:when test="${bean.classicStatus=='1'}">&nbsp;是</c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">项目简介</td>
				<td colspan="5">
					<span style="width:760px; display:block"><c:out value='${bean.projIntro}' /></span>
				</td>
			</tr>
		</table>
		<c:if test="${bean.optionOther=='1'}">
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">支持任意捐选项</div>
			</div>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:100px;">任意捐选项名称</td>
					<td style="width:170px;">
						<c:out value="${bean.optionOtherName}" /></td>
					<td class="lyt_view_note" style="width:62px;">起捐金额</td>
					<td style="width:110px;"><fmt:formatNumber value='${bean.minAmount}' pattern='0.##' type='number' />&nbsp;元
					</td>
					<td class="lyt_view_note" style="width:62px;">选项描述</td>
					<td style="width:270px;">
						<c:out value="${bean.optionOtherMemo}" />
					</td>
				</tr>
			</table>
		</c:if>
		<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
			<div style="float:left;padding-left:8px;overflow:hidden;">项目内容</div>
		</div>
		<table width="875" style="margin-left:5px;margin-top:5px;" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="nav_GA_left" width="2" height="28"></td>
				<td class="nav_GA_center">
					<div class="nav_GA">
						<ul id="detailTitleUl">
							<li onclick="detailOn(this);"
								style="line-height:22px;text-align:center;cursor:pointer;color:#fff;background:url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left;"><c:out
									value="${bean.detail1Title}" /> <c:if
									test="${empty bean.detail1Title}">&nbsp;</c:if></li>
							<c:if test="${not empty bean.detail2Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail2Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail3Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail3Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail4Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail4Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail5Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail5Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail6Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail6Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail7Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail7Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail8Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail8Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail9Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail9Title}" /></li>
							</c:if>
							<c:if test="${not empty bean.detail10Title}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detail10Title}" /></li>
							</c:if>
						</ul>
					</div>
				</td>
				<td class="nav_GA_right" width="2"></td>
			</tr>
		</table>
		<div id="detailContentDiv" style="overflow:hidden;">
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail1Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=1"></iframe>
					</td>
				</tr>
			</table>
			<c:if test="${not empty bean.detail2Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail2Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=2"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail3Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail3Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=3"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail4Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail4Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=4"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail5Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail5Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=5"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail6Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail6Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=6"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail7Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail7Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=7"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail8Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail8Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=8"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail9Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail9Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=9"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detail10Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detail10Title}" />
						<c:choose>
							<c:when test="${bean.detail1Publish=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getZcProjDetail.action?ProjId=${bean.projId}&index=10"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
			<div style="float:left;padding-left:8px;overflow:hidden;">其他信息</div>
		</div>
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_area" style="width:100px;">备注说明</td>
				<td colspan="5" style="width:760px">
					<c:out value='${bean.memo}' />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">附件</td>
				<td>
					<iframe class="frm_file" marginheight="0" frameborder="0"
						src="<%=basePath %>fileListReadOnly.action?name=TbZcproj&ele=relIds&id=${bean.projId}">
					</iframe>
				</td>
			</tr>
		</table>
		<my:timeView idField="projId" table="TbZcproj" value="${bean.projId}"></my:timeView>
	</div>
</body>
</html>