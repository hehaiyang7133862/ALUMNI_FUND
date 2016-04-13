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
	function sub(){
		MyMask.show("正在计算中,请稍后....");
		document.forms[0].submit();
	}
	function doExport(){
		if(check()==false){
			return;
		}
		MyMask.show("正在导出数据,请稍后....");
		$.ajax({ 
		    type:"post",
		    <c:choose>
				<c:when test="${type==1 }">url:"<%=basePath%>found!listDoExport.action",</c:when>
				<c:when test="${type==2 }">url:"<%=basePath%>found!payDoExport.action",</c:when>
				<c:when test="${type==3 }">url:"<%=basePath%>found!proclDoExport.action?proType=2",</c:when>
				<c:when test="${type==4 }">url:"<%=basePath%>found!proclDoExport.action?proType=3",</c:when>
				<c:when test="${type==5 }">url:"<%=basePath%>found!proclDoExport.action?proType=1",</c:when>
			</c:choose>
			data:$("#adviseForm").serialize(), 
			dataType:"json",
		    success:function(data){
				//var json=JSON.parse(data);
				var json=data;
				$.each(json,function(index,item){
					if(item.result=="success"){
						localGo("fileDown!downImp.action",{name:item.name,path:item.path});
					}else if(item.result=="warning"){
						MyMsg.alert(item.value);
					}else if(item.result=="error"){
						MyMsg.alert("实体资源导出失败！");
					}
				});
				setTimeout(function(){
					MyMask.hide();
				},0);
			}
		 });
	}
	function localGo(){
		var arr=arguments;
		var arrLen = arr.length;
		if(arrLen<1){
			return;
		}
		var url = arr[0];
		if(arrLen>1){
			if(url.indexOf("?")==-1){
				url += "?";
			}else{
				url += "&";
			}
			url += $.param(arr[1]);
		}
		MyMask.show("正在处理中，请稍候..");
		setTimeout(function(){
			location.href = getBasePath() + url;
		},0);
	}
	function check(){
		if($("#year").val()==""&&$("#startDate").val()==""&&$("#endDate").val()==""){
			MyMsg.alert("请选择时间！");
			return false;
		}
		return true;
	}
	function showPro(name,id){
		parent.addTab(name,"项目管理【"+id+"】","<%=basePath%>found!toPage.action?foundId="+id);
	}
	</script>
</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<form id="adviseForm" action="<%=basePath%>found!proListSearch.action" method="post" onsubmit="return check()">
				<input type="hidden" value="${type }" name="type" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note">选择时间</td>
						<td width="130px">
							<select id="year" name="year" style="width:130px;">
								<option value="">全部年份</option>
								<c:forEach begin="1990" end="${nowYear}" var="temp" varStatus="status">
			 					<c:set var="iyear" value="${nowYear+1990-temp}"/>
								<option value="${iyear}" <c:if test="${year==iyear}">selected</c:if>>${iyear}年</option>
								</c:forEach>
					    	</select>
				    	</td>
						<td class="lyt_search_note" style="color:red;">自定义时间</td>
						<td width="230px">
							<input style="width: 80px;" type="text" name="startDate" id="startDate" readonly="true" class="lyt_search_date" value='${startDateObj}' />
							<img onClick="WdatePicker({el:'startDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
								到
							<input style="width: 80px;" type="text" name="endDate" id="endDate" readonly="true" class="lyt_search_date" value='${endDateObj}' />
							<img onClick="WdatePicker({el:'endDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					    </td>
						<td class="lyt_search_note" width="80px">
							项目名称
						</td>
						<td width="137">
							<input style="width: 130px;" type="text" name="foundName" value="<c:out value='${foundName}' />" maxlength="100" />
						</td>
						<c:if test="${type==3 || type==4 || type==5}">
						<td class="lyt_view_note" style="width: 80px;">
							协议状态
						</td>
						<td style="width: 105px;">
							<select style="width: 100px;" name="proStatus">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="PROTOCOL_STATUS" value="${proStatus}" />
							</select>
						</td>
						</c:if>
						<c:if test="${type==5 }">
						<td class="lyt_search_note">配比状态</td>
						<td width="80px">
							<select style="width:80px" name="status">
								<option value="">全部</option>
								<option <c:if test="${status==0 }">selected</c:if> value="0">否</option>
								<option <c:if test="${status==1 }">selected</c:if> value="1">未申报</option>
								<option <c:if test="${status==2 }">selected</c:if> value="2">申报中</option>
								<option <c:if test="${status==3 }">selected</c:if> value="3">申报成功</option>
								<option <c:if test="${status==4 }">selected</c:if> value="4">申报失败</option>
							</select>
						</td>
						</c:if>
					</tr>
				    <c:if test="${type==1 }">
					<tr>
						<td class="lyt_search_note">项目状态</td>
						<td width="130px">
							<select style="width:130px" name="status">
								<option value="">全部</option>
								<option <c:if test="${status==1 }">selected</c:if> value="1">执行中</option>
								<option <c:if test="${status==2 }">selected</c:if> value="2">休眠</option>
								<option <c:if test="${status==3 }">selected</c:if> value="3">结束</option>
							</select>
						</td>
						<td class="lyt_search_note">项目分类</td>
						<td width="100px">
							<select style="width:100px" name="foundtypeFid">
								<option value="">全部</option>
								<option <c:if test="${foundtypeFid==1 }">selected</c:if> value="1">单项</option>
								<option <c:if test="${foundtypeFid==2 }">selected</c:if> value="2">专项</option>
								<option <c:if test="${foundtypeFid==3 }">selected</c:if> value="3">年度</option>
							</select>
						</td>
						<td class="lyt_search_note">是否留本</td>
						<td width="130px">
							<select style="width:130px" name="isKeep">
								<option value="">全部</option>
								<option <c:if test="${isKeep==1 }">selected</c:if> value="1">是</option>
								<option <c:if test="${isKeep==2 }">selected</c:if> value="2">否</option>
							</select>
						</td>
						
					</tr>
					</c:if>
					<tr>
						<td align="right" class="lyt_search_opt" colspan="12">
							<div class="lyt_button">
								<input value="查询" type="button" onclick="sub()" />
								<input value="导出" type="button" onclick="doExport()"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table style="width: 100%;" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<c:if test="${type==1 }">
					<th>项目名称</th>
					<th width="80">项目创建时间</th>
					<th width="60">项目分类</th>
					<th width="60">是否留本</th>
					<th width="80">协议总金额</th>
					<th width="80">到账总额</th>
					<th width="80">支出总额</th>
					<th width="80">剩余金额</th>
						<c:if test="${not empty startDate && not empty endDate}">
							<c:choose>
								<c:when test="${not empty startDateObj && not empty endDateObj}">
									<th width="220">${startDate}~${endDate}到账金额</th>
									<th width="220">${startDate}~${endDate}支出金额</th>
								</c:when>
								<c:when test="${not empty year}">
									<th width="120">${year}到账金额</th>
									<th width="120">${year}支出金额</th>
								</c:when>
								<c:otherwise>
									<th width="120">${startDate}~${endDate}到账金额</th>
									<th width="120">${startDate}~${endDate}支出金额</th>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:if>
					<c:if test="${type==2 }">
					<th>项目名称</th>
					<th>指定用途</th>
					<th>支出金额</th>
					<th>支出账户</th>
					<th>支出日期</th>
					<th>接受单位</th>
					<th>联系人</th>
					</c:if>
					<c:if test="${type==3 || type==4 || type==5}">
					<th>项目名称</th>
					<th>协议编号</th>
					<th>协议名称</th>
					<th>协议金额</th>
					<th>捐赠方</th>
					<th>使用方</th>
					<th>签订日期</th>
					<th>协议状态</th>
					<th>到账账户</th>
					<c:choose>
						<c:when test="${type==4}">
						<th>承诺日期</th>
						<th>承诺金额</th>
						</c:when>
						<c:otherwise>
						<th>到账日期</th>
						<th>到账金额</th>
						</c:otherwise>
					</c:choose>
					<th width="80">指定用途</th>
						<c:if test="${type==5}">
						<th>申报金额</th>
						<th>实际金额</th>
						</c:if>
					</c:if>
				</tr>
				<c:if test="${not empty proList}">
				<c:choose>
					<c:when test="${type==1}">
						<tr>
							<td style="color:red;">总计</td><td></td><td></td><td></td>
							<td style="color:red;">
								<fmt:parseNumber var='xyAmount' value='${amountXy}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${xyAmount}' pattern='#,##0.##' type='number'/>
							</td>
							<td style="color:red;">
								<fmt:parseNumber var='dzAmount' value='${amountDz}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${dzAmount}' pattern='#,##0.##' type='number'/>
							</td>
							<td style="color:red;">
								<fmt:parseNumber var='zcAmount' value='${amountZc}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${zcAmount}' pattern='#,##0.##' type='number'/>
							</td>
							<td style="color:red;">
								<fmt:parseNumber var='surAmount' value='${amountSur}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${surAmount}' pattern='#,##0.##' type='number'/>
							</td>
							<c:if test="${not empty startDate && not empty endDate}">
								<td style="color:red;">
									<fmt:parseNumber var='timeDzAmount' value='${amountTimeDz}' pattern='0.##' type='number'/>
									<fmt:formatNumber value='${timeDzAmount}' pattern='#,##0.##' type='number'/>
								</td>
								<td style="color:red;">
									<fmt:parseNumber var='timeZcAmount' value='${amountTimeZc}' pattern='0.##' type='number'/>
									<fmt:formatNumber value='${timeZcAmount}' pattern='#,##0.##' type='number'/>
								</td>
							</c:if>
						</tr>
					</c:when>
					<c:when test="${type==2}">
						<tr>
							<td style="color:red;">总计</td><td></td>
							<td style="color:red;">
								<fmt:parseNumber var='paySum' value='${sumPay}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${paySum}' pattern='#,##0.##' type='number'/>
							</td>
							<td></td><td></td><td></td><td></td>
						</tr>
					</c:when>
					<c:when test="${type==3 || type==4}">
						<tr>
							<td style="color:red;">总计</td><td></td><td></td>
							<td style="color:red;">
								<fmt:parseNumber var='proAmountSum' value='${sumProAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${proAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
							<td></td><td></td><td></td><td></td><td></td><td></td>

							<td style="color:red;">
								<fmt:parseNumber var='toAmountSum' value='${sumToAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${toAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
							<td></td>
						</tr>
					</c:when>
					<c:when test="${type==5}">
						<tr>
							<td style="color:red;">总计</td>
							<td></td>
							<td></td>
							<td style="color:red;">
								<fmt:parseNumber var='proAmountSum' value='${sumProAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${proAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
							<td></td><td></td><td></td><td></td><td></td><td></td>
							<td style="color:red;">
								<fmt:parseNumber var='toAmountSum' value='${sumToAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${toAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
							<td></td>
							<td style="color:red;">
								<fmt:parseNumber var='sbAmountSum' value='${sumSbAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${sbAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
							<td style="color:red;">
								<fmt:parseNumber var='toAmountSum' value='${sumSjAmount}' pattern='0.##' type='number'/>
								<fmt:formatNumber value='${toAmountSum}' pattern='#,##0.##' type='number'/>
							</td>
						</tr>
					</c:when>
				</c:choose>
				</c:if>
				<c:forEach items="${proList}" var="temp" varStatus="status">
		    		<c:set var="proTemp" value="${proList[status.index]}"/>
					<tr>
					<c:forEach items="${proTemp}" var="bean" varStatus="beanStatus">
						<c:choose>
							<c:when test="${beanStatus.index==0 }">
							<td><a onclick="showPro('${proTemp[0]}',${idList[status.index]})" href="javascript:;"><c:out value="${proTemp[0]}"></c:out></a></td>
							</c:when>
							<c:otherwise>
							<td><c:out value="${proTemp[beanStatus.index]}"></c:out></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>