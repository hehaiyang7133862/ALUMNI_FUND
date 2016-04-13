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
		<style>
</style>
		<%@ include file="../common/head.jsp"%>
		<%@ include file="../common/calendar.jsp"%>
		<%@ include file="../common/kindeditor.jsp"%>
		<script type="text/javascript">
		<c:if test="${not empty bean.projId && opt=='insert'}">
			parent.updateTabTitle('众筹【${bean.projId}】管理', '众筹【${bean.projId}】管理');
		</c:if>
		function changeHot(){
			if($('#hotFlag').val()=='1'){
				$('#hotOrder').attr('readonly',false).css('background-color','transparent').focus();
			}else{
				$('#hotOrder').attr('readonly',true).css('background-color','#E5E5E5');
			}
		}
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			if($("#projName").val() == ""){
				MyMsg.alertFn('请输入项目名称！',function(){
					$("#projName").focus();
				});
				return false;
			}
			if($("#projCode").val() == ""){
				MyMsg.alertFn('请输入项目编码！',function(){
					$("#projCode").focus();
				});
				return false;
			}
			if($("#typeId").val() == ""){
				MyMsg.alertFn('请选择项目分类！',function(){
					$("#typeId").focus();
				});
				return false;
			}
			if($("#shelvesTime").val() == ""){
				MyMsg.alertFn('请选择项目众筹发布时间！',function(){
					$("#shelvesTime").focus();
				});
				return false;
			}
			if($("#targetAmout").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#targetAmout").val())){
				MyMsg.alertFn('请输入众筹目标金额（最多两位小数的正整数）！',function(){
					$("#targetAmout").select();
				});
				return false;
			}
			if($("#shelvesFlag").val() == ""){
				MyMsg.alertFn('请选择项目是否上架！',function(){
					$("#shelvesFlag").focus();
				});
				return false;
			}
			if($("#hotFlag").val() == ""){
				MyMsg.alertFn('请选择项目是否热门！',function(){
					$("#hotFlag").focus();
				});
				return false;
			}
			if($("#begTime").val() == ""){
				MyMsg.alertFn('请选择项目众筹开始时间！',function(){
					$("#begTime").focus();
				});
				return false;
			}
			if($("#endTime").val() == ""){
				MyMsg.alertFn('请选择项目众筹截止时间！',function(){
					$("#endTime").focus();
				});
				return false;
			}
			if($("#completedJz").val() == ""){
				MyMsg.alertFn('请选择目标完成后是否继续众筹！',function(){
					$("#completedJz").focus();
				});
				return false;
			}
			if($("#classicStatus").val() == ""){
				MyMsg.alertFn('请选择项目结束后是否归入经典回顾！',function(){
					$("#classicStatus").focus();
				});
				return false;
			}
			if($("#projIntro").val().length > 1000){
				MyMsg.alertFn('项目简介必须在1000字以内！',function(){
					$("#projIntro").focus();
				});
				return false;
			}
			<c:if test="${empty bean.projId}">
			var obj,msg = '';
			$('input[name="pic"]').each(function(){
				obj = $(this);
				var val = obj.val();
				if(val == ""){
					msg = "请选择需要上传的项目图片，或者移除不需要的项目图片编辑框！";
					return false;
				}else{
	   				var valType = val.split(".")[val.split(".").length-1].toLowerCase();
					if(valType!="gif" && valType!="jpg" && valType!="jpeg" && valType!="png" && valType!="bmp"){
						msg = "项目图片只允许jpg,png,bmp,jpeg,gif格式!";
						return false;
					}
				}
			});
			if(msg!=''){
				MyMsg.alertFn(msg,function(){
					obj.focus();
				});
				return false;
			}
			$('input[name="picOrder"]').each(function(){
				obj = $(this);
				if(obj.val() == ""){
					msg = '请输入项目图片排序值，或者移除不需要的项目图片编辑框！';
					return false;
				}
			});
			if(msg!=''){
				MyMsg.alertFn(msg,function(){
					obj.focus();
				});
				return false;
			}
			$('input[name="optionName"]').each(function(){
				obj = $(this);
				if(obj.val() == ""){
					msg = '请输入项目选项名称，或者移除不需要的项目选项编辑框！';
					return false;
				}
			});
			if(msg!=''){
				MyMsg.alertFn(msg,function(){
					obj.focus();
				});
				return false;
			}
			$('input[name="optionAmount"]').each(function(){
				obj = $(this);
				if(obj.val() == "" || !/^\d+(.\d{1,2})?$/.test(obj.val())){
					msg = '请输入项目选项金额（最多两位小数的正整数），或者移除不需要的项目选项编辑框！';
					return false;
				}
			});
			if(msg!=''){
				MyMsg.alertFn(msg,function(){
					obj.select();
				});
				return false;
			}
			</c:if>
			if($('#optionOther').attr('checked')){
				if($('#optionOtherName').val() == ""){
					MyMsg.alertFn('请输入任意捐选项名称，或者取消支持任意捐！',function(){
						$("#optionOtherName").focus();
					});
					return false;
				}
				if($("#minAmount").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#minAmount").val())){
					MyMsg.alertFn('请输入任意捐起捐金额（最多两位小数的正整数）！',function(){
						$("#minAmount").select();
					});
					return false;
				}
			}
			if($("#memo").val().length > 1000){
				MyMsg.alertFn('备注说明必须在1000字以内！',function(){
					$("#memo").focus();
				});
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:if test="${not empty bean.projId}">
		<%@ include file="projMenu.jsp"%>
		</c:if>
		<form id="formId" name="formId" action="<%=basePath%>zcProjEdit!update.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
			<input type="hidden" name="projId" value="${bean.projId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						项目名称
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:445px" id="projName" name="projName" type="text" value='<c:out value="${bean.projName}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note">
						项目编码
						<span>*</span>
					</td>
					<td>
						<input style="width: 150px" id="projCode" name="projCode" type="text" value='<c:out value="${bean.projCode}" />' maxlength="100" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						所属分类
						<span>*</span>
					</td>
					<td colspan="3">
						<select id="typeId" name="typeId" style="width:445px">
							<option value="">--请选择--</option>
							<c:forEach items="${typeList}" var="temp" varStatus="status">
								<option value="${temp.typeId}" <c:if test="${temp.typeId==bean.projType}">selected</c:if>>${temp.typeName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="lyt_view_note">
						显示发布时间
						<span>*</span>
					</td>
					<td>
						<input style="width: 132px;" type="text" id="shelvesTime" name="shelvesTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.shelvesTime}'/>' />
						<img onClick="WdatePicker({el:'shelvesTime',dateFmt:'yyyy-MM-dd HH:mm'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:100px;">
						目标金额
						<span>*</span>
					</td>
					<td style="width:170px;">
						<input style="width: 132px;" id="targetAmout" name="targetAmout" type="text" value='<fmt:formatNumber value='${bean.targetAmout}' pattern='0.##' type='number'/>' maxlength="50" />
						元
					</td>
					<td class="lyt_view_note" style="width:100px;">
						是否上架
						<span>*</span>
					</td>
					<td style="width:170px;">
						<select id="shelvesFlag" name="shelvesFlag" style="width:150px;">
							<option value="1" <c:if test="${bean.shelvesFlag=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.shelvesFlag=='0'}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_note" style="width:120px;">
						是否热门
						<span>*</span>
					</td>
					<td style="width:170px;">
						<select id="hotFlag" name="hotFlag" style="width:54px;" onchange="changeHot();">
							<option value="1" <c:if test="${bean.hotFlag=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.hotFlag=='0'}">selected</c:if>>否</option>
						</select>
						&nbsp;排序
						<input style="width:60px" id="hotOrder" name="hotOrder" type="text" value='<c:out value="${bean.hotOrder}" />' <c:if test="${bean.hotFlag=='0'}">readonly="readonly"</c:if> maxlength="50" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						众筹开始时间
						<span>*</span>
					</td>
					<td>
						<input style="width: 132px;" type="text" id="begTime" name="begTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.begTime}'/>' />
						<img onClick="WdatePicker({el:'begTime',dateFmt:'yyyy-MM-dd HH:mm'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note">
						众筹截止时间
						<span>*</span>
					</td>
					<td>
						<input style="width: 132px;" type="text" id="endTime" name="endTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.endTime}'/>' />
						<img onClick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd HH:mm'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note">
						目标完成后继续众筹
						<span>*</span>
					</td>
					<td>
						<select id="completedJz" name="completedJz" style="width:150px;">
							<option value="1" <c:if test="${bean.completedJz=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.completedJz=='0'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						项目关键字标签
					</td>
					<td colspan="3">
						<input style="width:365px" id="searchKey" name="searchKey" type="text" value='<c:out value="${bean.searchKey}" />' maxlength="1000" />
						<span style="color:#999;">（以逗号间隔）</span>
					</td>
					<td class="lyt_view_area">
						结束后归入经典回顾
						<span>*</span>
					</td>
					<td>
						<select id="classicStatus" name="classicStatus" style="width:150px;">
							<option value="1" <c:if test="${bean.classicStatus=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.classicStatus=='0'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						项目简介
					</td>
					<td colspan="5">
						<textarea id="projIntro" name="projIntro" style="width:760px;height:60px;" onclick="myArea('projIntro',1000);" onkeyup="myArea('projIntro',1000);" onkeydown="myArea('projIntro',1000);" onchange="myArea('projIntro',1000);"><c:out value='${bean.projIntro}' /></textarea>
					</td>
				</tr>
			</table>
			<c:if test="${empty bean.projId}">
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">项目图片<span style="font-weight:normal;">（注意：项目图片不少于1个）</span></div>
				<div style="float:right;padding-right:12px;overflow:hidden;"><a href="javascript:;" onclick="picAdd();">添加图片</a></div>
			</div>
			<table id="picTable" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:100px;">
						<span name="picIndex">1</span>）选择图片
						<span>*</span>
					</td>
					<td style="width:210px;">
						<input style="width:200px;" name="pic" type="file" />
					</td>
					<td class="lyt_view_note" style="width:62px;">
						图片排序
						<span>*</span>
					</td>
					<td style="width:110px;">
						<input style="width:100px" name="picOrder" type="text" maxlength="50" />
					</td>
					<td class="lyt_view_note" style="width:62px;">
						图片描述
					</td>
					<td style="width:220px;">
						<input style="width:210px" name="picMemo" type="text" maxlength="100" />
					</td>
					<td></td>
				</tr>
			</table>
			<script type="text/javascript">
				function picAdd(){
					$('#picTable tbody').append('<tr>' + 
						'<td class="lyt_view_note" style="width:100px;"><span name="picIndex">' + ($('#picTable tr').length+1) + '</span>）选择图片\n<span>*</span></td>' + 
						'<td style="width:210px;"><input style="width:200px;" name="pic" type="file" /></td>' + 
						'<td class="lyt_view_note" style="width:62px;">图片排序\n<span>*</span></td>' + 
						'<td style="width:110px;"><input style="width:100px" name="picOrder" type="text" maxlength="50" /></td>' + 
						'<td class="lyt_view_note" style="width:62px;">图片描述</td>' + 
						'<td style="width:220px;"><input style="width:210px" name="picMemo" type="text" maxlength="100" /></td>' + 
						'<td><a href="javascript:;" onclick="picRemove(this);">移除</a></td>' + 
					'</tr>');
				}
				function picRemove(obj){
					$(obj).parent().parent().remove();
					$('span[name="picIndex"]').each(function(i){
						$(this).text(i+1);
					});
				}
			</script>
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">项目选项</div>
				<div style="float:right;padding-right:12px;overflow:hidden;"><a href="javascript:;" onclick="optionAdd();">添加选项</a></div>
			</div>
			<table id="optionTable" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:100px;">
						<span name="optionIndex">1</span>）选项名称
						<span>*</span>
					</td>
					<td style="width:210px;">
						<input style="width:200px" name="optionName" type="text" maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width:62px;">
						选项金额
						<span>*</span>
					</td>
					<td style="width:110px;">
						<input style="width:82px" name="optionAmount" type="text" maxlength="50" />
						元
					</td>
					<td class="lyt_view_note" style="width:62px;">
						选项描述
					</td>
					<td style="width:220px;">
						<input style="width:210px" name="optionMemo" type="text" maxlength="1000" />
					</td>
					<td>
						<a href="javascript:;" onclick="optionRemove(this);">移除</a>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				function optionAdd(){
					$('#optionTable tbody').append('<tr>' + 
						'<td class="lyt_view_note" style="width:100px;"><span name="optionIndex">' + ($('#optionTable tr').length+1) + '</span>）选项名称\n<span>*</span></td>' + 
						'<td style="width:210px;"><input style="width:200px" name="optionName" type="text" maxlength="100" /></td>' + 
						'<td class="lyt_view_note" style="width:62px;">选项金额\n<span>*</span></td>' + 
						'<td style="width:110px;"><input style="width:82px" name="optionAmount" type="text" maxlength="50" />\n元</td>' + 
						'<td class="lyt_view_note" style="width:62px;">选项描述</td>' + 
						'<td style="width:220px;"><input style="width:210px" name="optionMemo" type="text" maxlength="1000" /></td>' + 
						'<td><a href="javascript:;" onclick="optionRemove(this);">移除</a></td>' + 
					'</tr>');
				}
				function optionRemove(obj){
					$(obj).parent().parent().remove();
					$('span[name="optionIndex"]').each(function(i){
						$(this).text(i+1);
					});
				}
			</script>
			</c:if>
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">
					支持任意捐选项
					&nbsp;
					<label for="optionOther" style="font-weight:normal;" onclick="optionOtherCheck();">
						<input type="checkbox" id="optionOther" name="optionOther" class="checkbox" value="1" <c:if test="${bean.optionOther=='1'}">checked</c:if>/>&nbsp;支持
					</label>
				</div>
			</div>
			<table id="optionOtherTable" class="lyt_view" <c:if test="${bean.optionOther!='1'}">style="display:none;"</c:if>>
				<tr>
					<td class="lyt_view_note" style="width:100px;">
						任意捐选项名称
						<span>*</span>
					</td>
					<td style="width:210px;">
						<input style="width:200px" id="optionOtherName" name="optionOtherName" type="text" value="<c:out value="${bean.optionOtherName}"/>" maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width:62px;">
						起捐金额
						<span>*</span>
					</td>
					<td style="width:110px;">
						<input style="width:82px" id="minAmount" name="minAmount" type="text" value="<fmt:formatNumber value='${bean.minAmount}' pattern='0.##' type='number'/>" maxlength="50" />
						元
					</td>
					<td class="lyt_view_note" style="width:62px;">
						选项描述
					</td>
					<td style="width:270px;">
						<input style="width:266px" id="optionOtherMemo" name="optionOtherMemo" type="text" value="<c:out value="${bean.optionOtherMemo}"/>" maxlength="1000" />
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				function optionOtherCheck(){
					if($('#optionOther').attr('checked')){
						$('#optionOtherTable').show();
					}else{
						$('#optionOtherTable').hide();
					}
				}
			</script>
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">项目内容</div>
				<div style="float:right;padding-right:12px;overflow:hidden;"><a href="javascript:;" onclick="detailAdd();">添加标签</a></div>
			</div>
			<table width="875" style="margin-left:5px;margin-top:5px;" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="nav_GA_left" width="2" height="28"></td>
					<td class="nav_GA_center">
						<div class="nav_GA">
			    		<ul id="detailTitleUl">
			    			<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;color:#fff;background:url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left;"><c:out value="${bean.detail1Title}" /><c:if test="${empty bean.detail1Title}">&nbsp;</c:if></li>
			    			<c:if test="${not empty bean.detail2Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail2Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail3Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail3Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail4Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail4Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail5Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail5Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail6Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail6Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail7Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail7Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail8Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail8Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail9Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail9Title}"/></li>
			    			</c:if>
			    			<c:if test="${not empty bean.detail10Title}">
			    				<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;"><c:out value="${bean.detail10Title}"/></li>
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
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail1Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail1Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail1Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent1" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail1Content}"/></textarea>
						</td>
					</tr>
				</table>
    			<c:if test="${not empty bean.detail2Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail2Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail2Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail2Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent2" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail2Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail3Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail3Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail3Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail3Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent3" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail3Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail4Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail4Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail4Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail4Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent4" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail4Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail5Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail5Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail5Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail5Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent5" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail5Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail6Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail6Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail6Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail6Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent6" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail6Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail7Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail7Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail7Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail7Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent7" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail7Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail8Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail8Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail8Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail8Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent8" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail8Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail9Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail9Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail9Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail9Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent9" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail9Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
    			<c:if test="${not empty bean.detail10Title}">
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							标签名称
							<span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px" name="detailTitle" type="text" value="<c:out value="${bean.detail10Title}"/>" maxlength="100" onchange="detailTitleChange(this);" />
						</td>
						<td>
							<select name="detailPublish" style="width:90px;">
								<option value="1" <c:if test="${bean.detail10Publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${bean.detail10Publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
							&nbsp;
							<a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a>
							<a href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea id="detailContent10" name="detailContent" style="width:875px;height:300px;visibility:hidden;"><c:out value="${bean.detail10Content}"/></textarea>
						</td>
					</tr>
				</table>
    			</c:if>
   			</div>
			<script type="text/javascript">
			  	KindEditor.ready(function(K) {
			  		$('textarea[name="detailContent"]').each(function(){
						K.create('#'+$(this).attr('id'), {
							items : [
								'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
								'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
								'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
								'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
								'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
								'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
								'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'map', 'link', 'unlink'
							],
							langType : 'zh_CN',
							resizeType : 1,
							themeType : 'simple',
							allowFileManager : false,
							imageTabIndex : 1,
							afterBlur: function(){ this.sync(); }
						});
			  		});
				});
				function detailOn(obj){
					$('#detailTitleUl').children().css({
						'color':'',
						'background':'url(<%=basePath%>UI/images/nav_point.png) no-repeat top left'
					});
					$('#detailContentDiv').children().hide();
					var titleObj = $(obj);
					var contentObj = $('#detailContentDiv table:eq('+titleObj.prevAll().length+')');
					titleObj.css({
						'color':'#fff',
						'background':'url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left'
					});
					contentObj.show();
					contentObj.find('input[name="detailTitle"]').select();
				}
				var detailIndex = 10;
				function detailAdd(){
					detailIndex += 1;
					$('#detailTitleUl').append('<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;">&nbsp;</li>');
					$('#detailContentDiv').append('<table class="lyt_view" style="display:none;">' + 
						'<tr>' + 
							'<td class="lyt_view_note" style="width:100px;">标签名称\n<span>*</span></td>' + 
							'<td style="width:210px;"><input style="width:200px" name="detailTitle" type="text" maxlength="100" onchange="detailTitleChange(this);" /></td>' + 
							'<td>' + 
								'<select name="detailPublish" style="width:90px;"><option value="1">对外显示</option><option value="0">不对外显示</option></select>\n&nbsp;\n' + 
								'<a href="javascript:;" onclick="detailLeft(this);">左移</a>\n' + 
								'<a href="javascript:;" onclick="detailRight(this);">右移</a>\n' + 
								'<a href="javascript:;" onclick="detailRemove(this);">移除</a>\n' + 
							'</td>' + 
						'</tr>' + 
						'<tr>' + 
							'<td colspan="3"><textarea id="detailContent'+detailIndex+'" name="detailContent" style="width:875px;height:300px;visibility:hidden;"></textarea></td>' + 
						'</tr>' + 
					'</table>');
					$('#detailTitleUl li:last').click();
					KindEditor.create('#detailContent'+detailIndex, {
						items : [
							'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
							'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
							'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
							'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
							'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
							'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
							'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'map', 'link', 'unlink'
						],
						langType : 'zh_CN',
						resizeType : 1,
						themeType : 'simple',
						allowFileManager : false,
						imageTabIndex : 1,
						afterBlur: function(){ this.sync(); }
					});
				}
				function detailTitleChange(obj){
					var thisObj = $(obj);
					if(thisObj.val()!=''){
						$('#detailTitleUl li:eq('+thisObj.parents('table').prevAll().length+')').text(thisObj.val());
					}else{
						$('#detailTitleUl li:eq('+thisObj.parents('table').prevAll().length+')').html('&nbsp;');
					}
				}
				function detailLeft(obj){
					var contentObj = $(obj).parents('table');
					var index = contentObj.prevAll().length;
					if(index>0){
						var titleObj = $('#detailTitleUl li:eq('+index+')');
						titleObj.after(titleObj.prev());
						contentObj.after(contentObj.prev());
					}
				}
				function detailRight(obj){
					var contentObj = $(obj).parents('table');
					var index = contentObj.prevAll().length;
					if(contentObj.nextAll().length>0){
						var titleObj = $('#detailTitleUl li:eq('+index+')');
						titleObj.before(titleObj.next());
						contentObj.before(contentObj.next());
					}
				}
				function detailRemove(obj){
					var contentObj = $(obj).parents('table');
					var titleObj = $('#detailTitleUl li:eq('+contentObj.prevAll().length+')');
					titleObj.remove();
					contentObj.remove();
				}
			</script>
			<div style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">其他信息</div>
			</div>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_area" style="width:100px;">
						备注说明
					</td>
					<td>
						<textarea id="memo" name="memo" style="width:760px;height:60px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						附件
					</td>
					<td>
						<input id="relIds" name="relIds" type="hidden" value="" />
						<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbZcproj&ele=relIds&id=${bean.projId}">
						</iframe>
					</td>
				</tr>
			</table>
			<my:timeView idField="projId" table="TbZcproj" value="${bean.projId}"></my:timeView>
			<div class="lyt_submit" style="width:872px;text-align:right;">
				<input type="submit" value="提交" />
				<input type="reset" value="重置" />
			</div>
		</form>
		</div>
	</body>
</html>