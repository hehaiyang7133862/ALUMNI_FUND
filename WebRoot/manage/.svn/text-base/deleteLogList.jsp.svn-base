<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="<%=basePathHead%>UI/js/calendar.js" type="text/javascript"></script>
<script language="javascript">
window.onload=function(){
	myLoad();
}
function checkForm(form){
	// 格化表单
	formFormat("recordid");
	var msg="";
	
}
function refresh(){ 
     document.getElementById("recordid").submit();
}
function checkAll(parent){
	//var boxes=document.getElementsByTagName("input");
	var boxes=$("input");
	for(var i=0;i<boxes.length;i++){
		var box=boxes[i];
		if(box.type=="checkbox"&&box.id!=parent.id){
			box.checked=parent.checked;
		}
	}
}
$(document).ready(function(){
	$("#deleteLog").change(function(){
		$("#recordid").submit();
	});
});
function dochecked(tempBox){
	var boxes=$("input");
	var tempCount=0;
	for(var i=0;i<boxes.length;i++){
		var box=boxes[i];
		if(box.type=="checkbox"&&box.id!="parentBox"){
			if(box.checked)
				tempCount++;
		}
	}
	if(tempCount==10){
		$("#parentBox").attr("checked","checked");
	}
	if(tempCount==0){
		$("#parentBox").removeAttr("checked");
	}
}
function doSubmit(){
	var boxes=$("input");
	var tempCount=0;
	for(var i=0;i<boxes.length;i++){
		var box=boxes[i];
		if(box.type=="checkbox"&&box.id!="parentBox"){
			if(box.checked)
				tempCount++;
		}
	}
	if(tempCount==0){
		Ext.Msg.alert("错误信息","请先选择删除对象！");
		return false;
	}else{
		Ext.Msg.confirm('系统提示','确定要删除'+tempCount+'条日志吗？',
      	function(btn){
	        if(btn=='yes'){
				$("#recordid").attr("action","<%=basePath%>deleteLog!deleteLog.action?tag=del");
				$("#recordid").submit();
	        }
      	},this);
	}
}
</script>
</head> 
<body>
<form id="recordid" action="<%=basePath%>dolistlog!dolistlog.action?tag=del" method="post" onSubmit="return checkForm(this)">
<table class="lyt_search" width="100%">
 <tr>
    <td align="right" class="lyt_search_note" style="width:200px;">
    	删除
    	<select name="deleteLog" id="deleteLog" style="width:50px;">
    		<option value="" <c:if test="${delTime=='' }">selected</c:if>>全部</option>
    		<option value="3" <c:if test="${delTime=='3' }">selected</c:if>>3</option>
    		<option value="5" <c:if test="${delTime=='5' }">selected</c:if>>5</option>
    		<option value="15" <c:if test="${delTime=='15' }">selected</c:if>>15</option>
    		<option value="30" <c:if test="${delTime=='30' }">selected</c:if>>30</option>
    		<option value="60" <c:if test="${delTime=='60' }">selected</c:if>>60</option>
    		<option value="90" <c:if test="${delTime=='90' }">selected</c:if>>90</option>
    		<option value="180" <c:if test="${delTime=='180' }">selected</c:if>>180</option>
    	</select>
    	天前的日志
    </td>
    <td>
    	<div class="lyt_button" style="float:right">
		    <input type="button" value="删除" onclick="doSubmit()"/>
		</div>
    </td>
  </table>

<table width="100%" border="0" cellspacing="0" class="lyt_result">
  <tr>
    <th width="80">全选/反选<input type="checkbox" id="parentBox" value="0" onclick="checkAll(this)"></th>
    <th>姓名</th>
    <th>登录IP</th>
    <th>操作时间</th>
    <th>操作类型</th>
    <th>操作标识</th>
  </tr>
  <c:forEach items="${list_log}" var="tmp" varStatus="status">
  <tr>
    <td><input type="checkbox" name="loginId" id="loginId" value="${tmp.loginId }" onclick="dochecked()" /></td>
    <td><c:out value="${tmp.loginName}" /></td>
    <td>
    	<c:out value="${tmp.loginIp}" />
    </td>
    <td>
    	<fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${tmp.updateTime}"/>
 	</td>
    <td>
    	<c:out value="${tmp.handType}"></c:out>
    </td>
    <td>
    	<c:out value="${tmp.handSign}"></c:out>
    </td>
  </tr>
  </c:forEach>
</table>

</form>
<my:page action="dolistlog!dolistlog.action"></my:page>
</body>
</html>
