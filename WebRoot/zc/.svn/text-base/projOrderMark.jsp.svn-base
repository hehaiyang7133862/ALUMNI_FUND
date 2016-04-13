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
		var receiptEditor;
		$(document).ready(function(){
		  	KindEditor.ready(function(K) {
				receiptEditor = K.create("textarea[name='receipt']", {
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
			if($("#mark").val().length > 1000){
				MyMsg.alertFn('标记信息必须在1000字以内！',function(){
					$("#mark").focus();
				});
				return false;
			}
			if($("#memo").val().length > 1000){
				MyMsg.alertFn('备注说明必须在1000字以内！',function(){
					$("#memo").focus();
				});
				return false;
			}
			receiptEditor.sync();
			return true;
		}
		</script>
	</head>
	<body>
		<div class="float">
		<form id="formId" name="formId" action="<%=basePath%>zcProjEdit!orderMarkUpdate.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="projId" value="${bean.projId}" />
			<input type="hidden" name="orderId" value="${bean.orderId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						标记信息
					</td>
					<td width="660">
						<textarea id="mark" name="mark" style="width:652px;height:80px;" onclick="myArea('mark',1000);" onkeyup="myArea('mark',1000);" onkeydown="myArea('mark',1000);" onchange="myArea('mark',1000);"><c:out value='${bean.mark}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						回执信息
					</td>
					<td>
						<textarea id="receipt" name="receipt" style="width:650px;height:300px;visibility:hidden;"><c:out value='${bean.receipt}'/></textarea>
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
			<my:timeView idField="orderId" table="TbZcprojOrder" value="${bean.orderId}"></my:timeView>
			<div class="lyt_submit" style="width: 724px; text-align: right;">
				<input type="button" value="捐赠详情" style="letter-spacing:0px;" onclick="parent.goView('${bean.orderId}');" />
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>