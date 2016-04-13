<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.wiscom.is.*, java.net.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title><my:titleTag/>校友综合服务管理平台</title>
<script src="<%=basePath%>UI/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<link href="<%=basePath%>UI/css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>UI/js/login.js" type="text/javascript"></script>
<link href="<%=basePath%>UI/ext/resources/css/ext-all.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>UI/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/ext/ext-all.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/ext/adapter/treenodecheckui/TreeCheckNodeUI.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/js/ext-my.js" type="text/javascript"></script>
</head>
<script language="javascript">
$(document).ready(function(){
   
	var tmpAlert="<c:out value='${alert}' />";
	if(tmpAlert!=""){
		MyMsg.alert("<c:out value='${alert}' />");
	}
});
</script>
<body>
<div class="alumniLogin">
  		<div class="header">
        </div>
        <div class="mainBg">
	  		<div class="mainCnt">
		  		<div class="loginForm">
					<div class="loginOpt">
						<input type="button" class="loginIn" value="" onclick="location.href='http://cer.nju.edu.cn/amserver/UI/Login?goto=http://localhost:8115/login.action';return false;"/>
					</div>
				</div>
	        </div>
        </div>
        <div class="footer">
        	版权所有 © <my:titleTag/>
        	&nbsp;&nbsp;&nbsp;&nbsp;
        	建议使用Internet Explorer 6.0及以上版本浏览系统
        </div>
    </div>
</body>
</html>
