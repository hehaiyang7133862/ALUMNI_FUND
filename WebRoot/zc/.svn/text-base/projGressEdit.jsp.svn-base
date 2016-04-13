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
		var gressContentEditor;
		$(document).ready(function(){
		  	KindEditor.ready(function(K) {
				gressContentEditor = K.create("textarea[name='gressContent']", {
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
					imageTabIndex : 1
				});
			});
		});
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			if($("#gressTime").val() == ""){
				MyMsg.alertFn('请选择进度时间！',function(){
					$("#gressTime").focus();
				});
				return false;
			}
			if($("#memo").val().length > 1000){
				MyMsg.alertFn('备注说明必须在1000字以内！',function(){
					$("#memo").focus();
				});
				return false;
			}
			gressContentEditor.sync();
			return true;
		}
		</script>
	</head>
	<body>
		<div class="float">
		<form id="formId" name="formId" action="<%=basePath%>zcProjEdit!gressUpdate.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="projId" value="${bean.projId}" />
			<input type="hidden" name="gressId" value="${bean.gressId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						进度时间
						<span>*</span>
					</td>
					<td width="660">
						<input style="width: 132px;" type="text" id="gressTime" name="gressTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd' value='${bean.gressTime}'/>' />
						<img onClick="WdatePicker({el:'gressTime',dateFmt:'yyyy-MM-dd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						进度详情
					</td>
					<td>
						<textarea id="gressContent" name="gressContent" style="width:650px;height:300px;visibility:hidden;"><c:out value='${bean.gressContent}'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						备注说明
					</td>
					<td>
						<textarea id="memo" name="memo" style="width:652px;height:80px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="gressId" table="TbZcprojProgress" value="${bean.gressId}"></my:timeView>
			<div class="lyt_submit" style="width: 724px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>