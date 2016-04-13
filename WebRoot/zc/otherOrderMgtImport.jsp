<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<%@ include file="../common/head.jsp"%>
	<script language="javascript">
		// 查询验证
		function validFind(form){
			var fileName = $("#file").val();
		  	if(fileName.length>0){
	   			var fileType = fileName.split(".")[fileName.split(".").length-1].toLowerCase();
				if(fileType!="xls"){
					MyMsg.alertFn("导入文件只允许xls格式!",function(){$("#file").focus();});
					return false;
				}
			}else{
				MyMsg.alertFn("请选择需要导入的文件(只允许xls格式)!",function(){$("#file").focus();});
				return false;
			}
			// 导入进度时间戳
			$("#random").val(new Date().getTime());
			// 导入进度
			setInterval("importProcess()",1000);
			return true;
		}
		// 导入进度
		function importProcess(){
			$.ajax({
				type:'post',
		   		url:"<%=basePath%>zcOtherOrder!process.action",
		   		data:{random:$("#random").val()},
				success:function(data){
					var json=JSON.parse(data);
					if(json.count!=''){
						MyMask.showHtml("正在导入中<b>（"+json.index+"/"+json.count+"）</b>,请稍候..");
					}
				}        
			});
		}
		// 下载模板
		function downModel(){
			MyMask.showHtml("正在下载导入模板,请稍后....");
			$.ajax({ 
			    url:"<%=basePath%>zcOtherOrder!doExport.action?type=1", 
			    type:"post",
			    dataType:'text',
			    cache:false,
			    data:$('#formId').serialize(),
			    success:function(data){
					var json = JSON.parse(data);
					if(json.result=="success"){
						setTimeout(function(){
							location.href = "fileDown!downImp.action?name=其他捐赠信息导入模板.xls&path=" + json.path;
						},0);
					}else{
						MyMsg.alert("模板下载失败！");
					}
					setTimeout(function(){
						MyMask.hide();
					},0);
				}
			 });
		}
	</script>
</head> 
<body>
	<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<div class="body">
		<form id="formId" action="<%=basePath%>zcOtherOrder!doImport.action" method="post" enctype="multipart/form-data" onSubmit="return validFind(this)" >
			<input type="hidden" id="random" name="random" value="" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_search_note">导入文件</td>
					<td class="field">
						<input type="file" id="file" name="file" class="file" style="width:280px;" />
					</td>
					<td class="field">
						<div class="lyt_button">
    						<input type="submit" value="导入"/>
    					</div>
					</td>
				</tr>
				<tr>
					<td class="lyt_search_note">模板下载</td>
					<td class="field">
						<a href="javascript:;" onclick="downModel();">下载导入模板</a>
					</td>
					<td class="field">
					</td>
				</tr>
			</table>
		</form>
		<div class="lyt_opt" style="padding-left:5px;color:#0099FF;">
			<!-- <p>注：系统依据带（<span style="color: red;">*</span>）必填字段等关键信息定位导入数据，请严格按照导入模板的格式组织导入的数据</p> -->
			<p style="padding-top:5px;font-weight:bold;">说明：1、项目名称+捐赠金额+捐赠人三个字段在导入模板中必须存在，否则无法导入；</p>
			<p style="text-indent:3.2em;padding-top:5px;font-weight:bold;">2、捐赠金额必须为大于0（最多保持两位小数）；</p>
			<p style="text-indent:3.2em;padding-top:5px;font-weight:bold;">3、通过捐赠单号，导入更新捐赠信息时，系统作全字段内容覆盖，若不想覆盖指定字段，可移除模板中指定字段列；或者，导出捐赠信息，修改后，再行导入；</p>
		</div>
		<c:if test="${not empty successcount}">
			<p style="padding-left:5px;padding-top:5px;font-size:12px;line-height:20px;">成功导入<span style="color:#F00;font-weight:bold;"><c:out value="${successcount}" /></span>条记录；未成功导入<span style="color:#F00;font-weight:bold;"><c:out value="${errorcount}" /></span>条记录。</p>
		</c:if>
		<c:if test="${not empty errorList}">
			<p style="padding-left:5px;padding-top:5px;font-size:12px;line-height:20px;color:#F00;font-weight:bold;">导入结果反馈</p>
			<table class="lyt_result">
				<tr>
					<th width="50">
						序号
					</th>
					<th width="100">Excel行号</th>
					<th>反馈信息</th>
				</tr>
				<c:forEach items="${errorList}" var="natemp" varStatus="status2">
					<tr>
						<td>
							<b><c:out value="${status2.count}" /></b>
						</td>
						<td>
							<c:out value="${natemp[0]}" />
						</td>
						<td>
							<c:out value="${natemp[1]}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		</div>
	</div>
</body>
</html>