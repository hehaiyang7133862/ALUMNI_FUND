function getBasePath(){
	var els = document.getElementsByTagName('script'), src;
	for (var i = 0, len = els.length; i < len; i++) {
		src = els[i].src || '';
		if (/UI\/js\/login.js/.test(src)) {
			return src.replace(/UI\/js\/login.js/,"");
		}
	}
	return '';
}
function positionMiddle(){
	var top = (document.documentElement.clientHeight-$(".alumniLogin").height())/2;
	if(top < 5){
		top = 5;
	}
	$(".alumniLogin").css("margin-top",top);
}
function showNotice(msg){
	$(".loginNotice").html(msg);
}
$(document).ready(function(){
						    // 后台信息
	if(top.location.href != location.href){
		top.location.href = location.href;
	}
	
	var glbBasePath = getBasePath();
	var mainBg = glbBasePath+"UI/images/loginMainbg3.jpg";
	var mainCntBg = glbBasePath+"UI/images/loginMaincnt3.jpg";
	$(".mainBg").css("background","url("+mainBg+") repeat-x left top");
	$(".mainBg .mainCnt").css("background","url("+mainCntBg+") repeat-x left top");

	$(".loginTxt,.loginPwd").click(function(){
		$(this).find(".loginInput").focus();
	});
	$(".loginInput").focus(function(){
		var parent = $(this).parent();
		if(parent.hasClass("loginTxt")){
			parent.addClass("loginTxtFocus").removeClass("loginTxt");
		}
		if(parent.hasClass("loginPwd")){
			parent.addClass("loginPwdFocus").removeClass("loginPwd");
		}
	}).blur(function(){
		if($(this).val()==""){
			var parent = $(this).parent();
			if(parent.hasClass("loginTxtFocus")){
				parent.addClass("loginTxt").removeClass("loginTxtFocus");
			}
			if(parent.hasClass("loginPwdFocus")){
				parent.addClass("loginPwd").removeClass("loginPwdFocus");
			}
		}else{
		
		}
	});
	
//身份认证切换
$(".logintype1").click(function(){ 
		if($(this).hasClass("logintype2")){
			$(this).addClass("logintype1").removeClass("logintype2");
			$("#typeid").val("0");
		}else{
		 $(this).addClass("logintype2").removeClass("logintype1");
		  $("#typeid").val("1");
		
		}
});
$(".logintype2").click(function(){ 
		if($(this).hasClass("logintype1")){
			$(this).addClass("logintype2").removeClass("logintype1");
			$("#typeid").val("1");
		}else{
		 $(this).addClass("logintype1").removeClass("logintype2");
		  $("#typeid").val("0");
		
		}
});
	$(".loginReg").click(function(){
		top.location.href = glbBasePath+"alumniReg";
	});
	
	
	positionMiddle();
	window.onresize = positionMiddle;
});