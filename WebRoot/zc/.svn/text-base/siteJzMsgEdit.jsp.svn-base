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
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			var logoVal = $('#contentLogo').val();
			if(logoVal != ""){
	   			var logoValype = logoVal.split(".")[logoVal.split(".").length-1].toLowerCase();
				if(logoValype!="gif" && logoValype!="jpg" && logoValype!="jpeg" && logoValype!="png" && logoValype!="bmp"){
					MyMsg.alert("图标只允许jpg,png,bmp,jpeg,gif格式!");
					return false;
				}
			}
			if($("#contentIntro").val().length > 1000){
				MyMsg.alertFn('简介必须在1000字以内！',function(){
					$("#contentIntro").focus();
				});
				return false;
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
		<div class="float">
		<form id="formId" name="formId" action="<%=basePath%>siteJzMsg!doEdit.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="msgId" value="${bean.msgId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						捐赠寄语
					</td>
					<td width="710">
						<textarea id="msgCnt" name="msgCnt" style="width:700px;height:60px;" onclick="myArea('msgCnt',2000);" onkeyup="myArea('msgCnt',2000);" onkeydown="myArea('msgCnt',2000);" onchange="myArea('msgCnt',2000);"><c:out value='${bean.msgCnt}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						捐赠人
					</td>
					<td>
						<input type="text" id="msgFrom" name="msgFrom" style="width:700px;" value="<c:out value="${bean.msgFrom}"/>" maxLength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						捐赠时间
					</td>
					<td>
			    		<input style="width:132px;" type="text" id="msgTime" name="msgTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd' value='${bean.msgTime}'/>' />
						<img onClick="WdatePicker({el:'msgTime',dateFmt:'yyyy-MM-dd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
				</tr>
			  	<tr>
			    	<td class="lyt_view_note">对外发布</td>
			    	<td>
			    		<label for="publishFlag">
			    			<input type="checkbox" id="publishFlag" name="publishFlag" value="1" <c:if test="${bean.publishFlag=='1'}">checked</c:if> class="checkbox" style="width:auto;height:auto;" />
			    			对外显示发布
			    		</label>
			    	</td>
			  	</tr>
				<tr>
					<td class="lyt_view_area">
						备注
					</td>
					<td>
						<textarea id="memo" name="memo" style="width:700px;height:50px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="msgId" table="TbSiteJzMsg" value="${bean.msgId}"></my:timeView>
			<div class="lyt_submit" style="width: 780px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>