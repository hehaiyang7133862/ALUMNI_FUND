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
		function goView(id){
			MyFormWin.close();
			MyFormWin.showMyWin('捐赠详情','<%=basePath%>zcOtherOrderReadOnly!view.action?orderId='+id,580,310);
		}
		$(document).ready(function(){
			windowResizeFn.add(function(){
				$('#resultDiv').width(document.body.clientWidth);
				$('#resultDiv').height(document.body.clientHeight-175-$('.lyt_nav').height());
				if($('.lyt_result').width()>$('#resultDiv').width()){
					$('.lyt_result').css('width','2140px');
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
			<form id="formId" action="<%=basePath%>zcOtherOrderReadOnly.action" method="get" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" style="width:60px;">
							捐赠项目
						</td>
						<td width="70">
							<input type="text" name="projName" style="width:70px;" value="<c:out value='${projName}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							捐赠单号
						</td>
						<td width="70">
							<input type="text" name="orderNum" style="width:70px;" value="<c:out value='${orderNum}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							捐赠方式
						</td>
						<td width="70">
							<input type="text" name="orderType" style="width:70px;" value="<c:out value='${orderType}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note" style="width:60px;">
							捐赠状态
						</td>
						<td width="70">
							<select name="orderStatus" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${orderStatus=='1'}">selected</c:if>>已付款</option>
								<option value="0" <c:if test="${orderStatus=='0'}">selected</c:if>>待付款</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:40px;">
							证书
						</td>
						<td width="70">
							<select name="needZhengshu" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${needZhengshu=='1'}">selected</c:if>>需要</option>
								<option value="0" <c:if test="${needZhengshu=='0'}">selected</c:if>>不需要</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:40px;">
							发票
						</td>
						<td width="70">
							<select name="needFapiao" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${needFapiao=='1'}">selected</c:if>>需要</option>
								<option value="0" <c:if test="${needFapiao=='0'}">selected</c:if>>不需要</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:60px;">
							匿名捐赠
						</td>
						<td>
							<select name="niMing" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${niMing=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${niMing=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input value="查询" type="submit" style="margin-right:20px"/>
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							捐赠币种
						</td>
						<td>
							<input type="text" name="amountType" style="width:70px;" value="<c:out value='${amountType}' />" />
						</td>
						<td class="lyt_search_note">
							捐赠人
						</td>
						<td>
							<input type="text" name="personName" style="width:70px;" value="<c:out value='${personName}' />" />
						</td>
						<td class="lyt_search_note">
							捐赠人信息
						</td>
						<td>
							<input type="text" name="personInfo" style="width:70px;" value="<c:out value='${personInfo}' />" />
						</td>
						<td class="lyt_search_note">
							校友
						</td>
						<td>
							<select name="alumni" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${alumni=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${alumni=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note">
							校友信息
						</td>
						<td>
							<input type="text" name="alumniInfo" style="width:70px;" value="<c:out value='${alumniInfo}' />" />
						</td>
						<td class="lyt_search_note" style="width:40px;">
							标记
						</td>
						<td>
							<select name="markFlag" style="width:70px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${markFlag=='1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${markFlag=='0'}">selected</c:if>>否</option>
							</select>
						</td>
						<td class="lyt_search_note" style="width:60px;">
							标记包含
						</td>
						<td colspan="2">
							<input type="text" name="mark" style="width:70px;" value="<c:out value='${mark}' />" maxlength="100" />
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							捐赠人类型
						</td>
						<td>
							<select name="personType" style="width:70px;margin-top:4px;">
								<option value="">全部</option>
								<option value="1" <c:if test="${personType=='1'}">selected</c:if>>个人</option>
								<option value="2" <c:if test="${personType=='2'}">selected</c:if>>集体</option>
							</select>
						</td>
						<td class="lyt_search_note">
							提交时间
						</td>
						<td colspan="5">
							<div style="margin-top:4px;overflow:hidden;">
							<input type="text" id="orderTimeBeg" name="orderTimeBeg" class="txt" style="width:110px;" value="<c:out value='${orderTimeBeg}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTimeBeg'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
							至
							<input type="text" id="orderTimeEnd" name="orderTimeEnd" class="txt" style="width:110px;" value="<c:out value='${orderTimeEnd}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTimeEnd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
							</div>
						</td>
						<td class="lyt_search_note">
							付款时间
						</td>
						<td colspan="8">
							<div style="margin-top:4px;overflow:hidden;">
							<input type="text" id="orderOkTimeBeg" name="orderOkTimeBeg" class="txt" style="width:110px;" value="<c:out value='${orderOkTimeBeg}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTimeBeg'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
							至
							<input type="text" id="orderOkTimeEnd" name="orderOkTimeEnd" class="txt" style="width:110px;" value="<c:out value='${orderOkTimeEnd}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTimeEnd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
			<div id="resultDiv" style="height:60px;overflow:auto;">
			<table border="1" cellspacing="0" cellpadding="0" class="lyt_result" style="width:2140px;">
				<tr>
					<th width="40">序号</th>
					<th width="80"><my:order action="zcOtherOrderReadOnly.action" title="捐赠单号" name="a.orderNum" /></th>
					<th width="120"><my:order action="zcOtherOrderReadOnly.action" title="项目名称" name="a.projName" /></th>
					<th width="60"><my:order action="zcOtherOrderReadOnly.action" title="捐赠币种" name="a.orderAmountType" /></th>
					<th width="60"><my:order action="zcOtherOrderReadOnly.action" title="捐赠币种总金额" name="a.orderAmountView" /></th>
					<th width="60"><my:order action="zcOtherOrderReadOnly.action" title="实算总金额（元）" name="a.orderAmount" /></th>
					<th width="100"><my:order action="zcOtherOrderReadOnly.action" title="捐赠方式" name="a.orderType" /></th>
					<th width="45">捐赠状态</th>
					<th width="85"><my:order action="zcOtherOrderReadOnly.action" title="提交时间" name="a.orderTime" /></th>
					<th width="85"><my:order action="zcOtherOrderReadOnly.action" title="付款时间" name="a.orderOkTime" /></th>
					<th width="30"><my:order action="zcOtherOrderReadOnly.action" title="捐赠人类型" name="a.personType" /></th>
					<th width="30"><my:order action="zcOtherOrderReadOnly.action" title="捐赠人数" name="a.personCount" /></th>
					<th width="30">操作</th>
					<th width="30">需要证书</th>
					<th width="30">需要发票</th>
					<th width="30">匿名捐赠</th>
					<th width="60">捐赠人</th>
					<th width="140">捐赠人信息</th>
					<th width="30">校友</th>
					<th width="140">校友资料</th>
					<th width="140">标记</th>
				</tr>
				<c:forEach items="${orderList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td style="white-space:normal;">
						<c:out value="${temp.orderNum}" />
					</td>
					<td>
						<c:choose>
						<c:when test="${not empty temp.projUrl}">
							<a href="<c:out value="${temp.projUrl}"/>" target="_blank"><c:out value="${temp.projName}" /></a>
						</c:when>
						<c:otherwise>
							<c:out value="${temp.projName}" />
						</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:out value="${temp.orderAmountType}"/>
					</td>
					<td>
						<fmt:formatNumber value='${temp.orderAmountView}' pattern='#,##0.##' type='number'/> <c:out value="${temp.orderAmountUnit}"/>
					</td>
					<td>
						<fmt:formatNumber value='${temp.orderAmount}' pattern='#,##0.##' type='number'/>
					</td>
					<td>
						<c:out value="${temp.orderType}" />
					</td>
					<td>
						<c:choose><c:when test="${temp.orderStatus=='1'}">已付款</c:when><c:otherwise>待付款</c:otherwise></c:choose>
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.orderTime}'/>
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.orderOkTime}'/>
					</td>
					<td>
						<c:choose><c:when test="${temp.personType=='2'}">集体</c:when><c:otherwise>个人</c:otherwise></c:choose>
					</td>
					<td>
						<fmt:formatNumber value='${temp.personCount}' pattern='#,##0' type='number'/>
					</td>
					<td title="操作">
						<a href="javascript:;" onclick="goView('${temp.orderId}');">详情</a>
					</td>
					<td style="text-align:center;">
						<c:choose><c:when test="${temp.needZhengshu=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
					</td>
					<td style="text-align:center;">
						<c:choose><c:when test="${temp.needFapiao=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
					</td>
					<td style="text-align:center;">
						<c:choose><c:when test="${temp.niMing=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
					</td>
					<td>
						<c:choose>
						<c:when test="${not empty temp.unAlumniId}">
							<a href="javascript:;"><c:out value="${temp.personName}" /></a>
						</c:when>
						<c:otherwise>
							<c:out value="${temp.personName}" />
						</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose><c:when test="${temp.personSex=='1'}">性别：男；</c:when><c:when test="${temp.personSex=='2'}">性别：女；</c:when></c:choose><c:if test="${not empty temp.personPhone}">手机：<c:out value="${temp.personPhone}" />；</c:if><c:if test="${not empty temp.personPhone}">电话：<c:out value="${temp.personTel}" />；</c:if><c:if test="${not empty temp.personEmail}">邮箱：<c:out value="${temp.personEmail}" />；</c:if><c:if test="${not empty temp.addressSheng || not empty temp.addressShi || not empty temp.addressQu || not empty temp.addressDetail}">地址：<c:if test="${not empty temp.addressSheng}"><c:out value="${temp.addressSheng}" />（省） </c:if><c:if test="${not empty temp.addressShi}"><c:out value="${temp.addressShi}" />（市） </c:if><c:if test="${not empty temp.addressQu}"><c:out value="${temp.addressQu}" />（区） </c:if><c:if test="${not empty temp.addressDetail}"><c:out value="${temp.addressDetail}" /></c:if>；</c:if><c:if test="${not empty temp.addressZip}">邮编：<c:out value="${temp.addressZip}" />；</c:if>
					</td>
					<td style="text-align:center;">
						<c:choose><c:when test="${temp.alumniFlag=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
					</td>
					<td>
						<c:if test="${temp.alumniFlag=='1'}">
						<c:if test="${not empty temp.studyYearin}">入学年：<c:out value="${temp.studyYearin}" />；</c:if><c:if test="${not empty temp.studyYearover}">离校年：<c:out value="${temp.studyYearover}" />；</c:if><c:if test="${not empty temp.studyAcademy}">院系：<c:out value="${temp.studyAcademy}" />；</c:if><c:if test="${not empty temp.studyMajor}">专业：<c:out value="${temp.studyMajor}" />；</c:if><c:if test="${not empty temp.studyClass}">班级：<c:out value="${temp.studyClass}" />；</c:if><c:if test="${not empty temp.studyNum}">学号：<c:out value="${temp.studyNum}" />；</c:if><c:if test="${not empty temp.studyDegree}">学历：<c:out value="${temp.studyDegree}" />；</c:if>
						</c:if>
					</td>
					<td>
						<c:out value="${temp.mark}" />
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			<my:page action="zcOtherOrderReadOnly.action"></my:page>
			<div class="lyt_submit" style="margin-top:15px;padding:10px 10px 0px 10px;">
				<div style="float:left;color:blue;font-weight:bold;overflow:hidden;">
					合计：<fmt:formatNumber value='${orderCount}' pattern='#,##0' type='number'/>条，其中已付款：<fmt:formatNumber value='${orderOkCount}' pattern='#,##0' type='number'/>条（个人：<fmt:formatNumber value='${orderOkType1Count}' pattern='#,##0' type='number'/>条 | 集体：<fmt:formatNumber value='${orderOkType2Count}' pattern='#,##0' type='number'/>条 | 在线：<fmt:formatNumber value='${orderOkType0Count}' pattern='#,##0' type='number'/>条）合计金额：<fmt:formatNumber value='${orderOkAmount}' pattern='#,##0.##' type='number'/>元
				</div>
	  		</div>
		</div>
	</body>
</html>