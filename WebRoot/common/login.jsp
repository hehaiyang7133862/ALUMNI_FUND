<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.wiscom.is.*, java.net.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title><my:titleTag/>综合业务管理系统</title>
<script src="<%=basePath%>UI/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<link href="<%=basePath%>UI/css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>UI/js/login.js" type="text/javascript"></script>
<link href="<%=basePath%>UI/ext/resources/css/ext-all.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>UI/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/ext/ext-all.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/ext/adapter/treenodecheckui/TreeCheckNodeUI.js" type="text/javascript"></script>
<script src="<%=basePath%>UI/js/ext-my.js" type="text/javascript"></script>
<style type="text/css">
html{
  background-color: #206494;
}
.newalumniLogin {
  background-color: #206494;
  height: 100%;
  width: 100%;
  margin: 0 auto;
}
.newalumniLogin .newmainBg {
  background-image: url("/UI/images/loginBg.jpg");
  background-repeat:no-repeat;
  height: 580px;
  width: 1200px;
  margin: 0 auto;
  position: relative;
}
.newalumniLogin .newfooter {
  background-color: #206494;
  color: #ffffff;
  text-align: center;
}
.newalumniLogin .filterIm{
    filter:alpha(opacity=0);
	-moz-opacity:0;
	-khtml-opacity: 0;
	opacity: 0;
}
.newloginInput {
  	background: none repeat scroll 0 0 #f0eef1;
    border: 0 none;
    font-size: 14px;
    height: 36px;
    left: 195px;
    line-height: 36px;
    position: absolute;
    top: 217px;
    width: 224px;
}
.newloginForm .loginPwd {
  top: 273px;
}
.newloginForm .loginCode {
    top: 329px;
    width: 210px;
}
.newloginForm .newloginIn {
	height: 27px;
	left: 190px;
	position: absolute;
	top: 423px;
	width: 168px;
	filter:alpha(opacity=0);
	-moz-opacity:0;
	-khtml-opacity: 0;
	opacity: 0;
}
.newloginForm .reset {
  left: 388px;
  width: 80px;
}
.newloginForm .proofcode {
	background: none repeat scroll 0 0 #f0eef1;
	color: orange;
	cursor: pointer;
	font-size: 16px;
	font-weight: bold;
	height: 34px;
	left: 408px;
	letter-spacing: 2px;
	line-height: 34px;
	position: absolute;
	text-align: center;
	top: 330px;
	width: 55px;
	z-index: 2;
}
.newalumniLogin .notice {
  bottom: 60px;
  color: #000000;
  font-weight: bold;
  height: 32px;
  left: 189px;
  overflow: hidden;
  position: absolute;
}
.newalumniLogin .notice span {
  color: red;
}
</style>
</head>
<script language="javascript">
$(document).ready(function(){
	$("#divLeft").click(function(){
		$("#valiDiv").css("background-position","left 0");
		$(".loginLine").css("display","");
		$("#loginForm").attr("action","<%=basePath%>login.action");
	});
	$("#divRight").click(function(){
		$("#valiDiv").css("background-position","left 50px");
		$(".loginLine").css("display","none");
		$("#loginForm").attr("action","<%=basePath%>sso/login");
	});
	
	resetCode();
	$("#userCheckCode").click(resetCode);
	
	$("input[class*='newloginInput']").focus(function(){
		$(this).removeClass("filterIm");
	});
	$("input[class*='newloginInput']").blur(function(){
		if($(this).val()==""){
			$(this).addClass("filterIm");
		}
	});
});
function resetCode(){
	var seed = '0123456789';
    var code = '';
    for(var i=0; i<4; i++){
        code += seed.substr(Math.floor(Math.random()*(10)), 1);
    }
    $("#userCheckCode").html(code);
}
function checkForm(){
	if($('#Token1').val()==''){
		$('#noticeMsg').html('请输入账号！');
		$('#userCheckCode').click();
		$('#Token1').focus();
		return false;
	}
	if($('#Token2').val()==''){
		$('#noticeMsg').html('请输入密码！');
		$('#userCheckCode').click();
		$('#Token2').focus();
		return false;
	}
	if($('#Token3').val()!=$('#userCheckCode').html()){
		$('#noticeMsg').html('验证码错误，请输入验证码！');
		$('#userCheckCode').click();
		$('#Token3').val('').focus();
		return false;
	}
	return true;
}
</script>
<body>
<div class="newalumniLogin">
  		<div class="header">
        </div>
        <div class="newmainBg">
	  		<div class="newmainCnt">
  				<form id="loginForm" action="<%=basePath%>login.action" method="post" onsubmit="return checkForm()">
				<div class="newloginForm">
					<input type="text" id="Token1" name="Token1" class="newloginInput filterIm" value="" autocomplete="off"/>
					<input type="password" id="Token2" name="Token2" class="newloginInput loginPwd filterIm" value=""/>
					<input type="text" id="Token3" name="Token3" class="newloginInput loginCode filterIm" value=""/>
					<div id="userCheckCode" class="proofcode" title="看不清楚？可以点击此处换一张"></div>
					<input type="submit" class="newloginIn" value=""/>
					<input type="reset" class="newloginIn reset" value=""/>
				</div>
				</form>
	        </div>
	        <div class="notice"><span id="noticeMsg"><c:out value="${alert}" default=""/></span></div>
        </div>
        <div class="newfooter">
        	版权所有 © <my:titleTag/>
        	&nbsp;&nbsp;&nbsp;&nbsp;
        	建议使用Internet Explorer 6.0及以上版本浏览系统
        </div>
    </div>
</body>
</html>
