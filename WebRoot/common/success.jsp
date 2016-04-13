
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script language="javascript" type="text/javascript">
Temp={
	refresh:function(){
		if(typeof(window.parent.myRefresh) == "undefined"){
			window.parent.MyFormWin.close();
		}
		else{
			window.parent.myRefresh();
		}
	},
	close:function(){
		window.parent.MyFormWin.close();
	}
}
$(document).ready(function(){
	if(typeof(window.parent.myRefresh) != "undefined"){
		window.parent.myRefresh();
		return;
	}
	else if(typeof(window.parent.refresh) != "undefined"){
		window.parent.refresh();
		return;
	}
	else{
		window.parent.MyFormWin.close();
	}
});
</script>
<style type="text/css">
body{
	text-align:center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
  
<body>
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<div class="ico_wait" style="margin:0 auto;">
</div>
<br />
<br />
<br />
<br />
<span style="font-size:14px;">请稍候...</span>
</body>
</html>
