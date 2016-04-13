function tips_pop() {
	var MsgPop = document.getElementById("winpop");// 获取窗口这个对象,即ID为winpop的对象
	var popH = parseInt(MsgPop.style.height);// 用parseInt将对象的高度转化为数字,以方便下面比较
	if (popH == 24||popH==0) { // 如果窗口的高度是0
		MsgPop.style.display = "block";// 那么将隐藏的窗口显示出来
		show = setInterval("changeH('up')", 2);// 开始以每0.002秒调用函数changeH("up"),即每0.002秒向上移动一次
		//$("#closeMsg").css("background","url('../UI/images/panel.jpg') no-repeat scroll center top 1px rgba(0, 0, 0, 0)");
		$("#closeMsg").attr("class","close");
	} else { // 否则
		hide = setInterval("changeH('down')", 2);// 开始以每0.002秒调用函数changeH("down"),即每0.002秒向下移动一次
		//$("#closeMsg").css("background","url('../UI/images/panel.jpg') no-repeat scroll center top -24px rgba(0, 0, 0, 0)");
		$("#closeMsg").attr("class","up");
	}
}
function changeH(str) {
	var MsgPop = document.getElementById("winpop");
	var popH = parseInt(MsgPop.style.height);
	if (str == "up") { // 如果这个参数是UP
		if (popH <= 100) { // 如果转化为数值的高度小于等于100
			MsgPop.style.height = (popH + 4).toString() + "px";// 高度增加4个象素
		} else {
			clearInterval(show);// 否则就取消这个函数调用,意思就是如果高度超过100象度了,就不再增长了
		}
	}
	if (str == "down") {
		if (popH >= 28) { // 如果这个参数是down
			MsgPop.style.height = (popH - 4).toString() + "px";// 那么窗口的高度减少4个象素
		} else { // 否则
			clearInterval(hide); // 否则就取消这个函数调用,意思就是如果高度小于4个象度的时候,就不再减了
			//MsgPop.style.display = "none"; // 因为窗口有边框,所以还是可以看见1~2象素没缩进去,这时候就把DIV隐藏掉
		}
	}
}
window.onload = function() { // 加载
	var basePath=getBasePath();
	//document.getElementById('winpop').style.height = '0px';// 
	//setTimeout("tips_pop()", 10); // 3秒后调用tips_pop()这个函数
	//默认关闭
	var MsgPop = document.getElementById("winpop");
	MsgPop.style.display = "none";
	MsgPop.style.height = "24px";
	$("#closeMsg").attr("class","up");
	//加载文件
	$("#conMsg").load(basePath+"login!ldManageFile.action?num="+Math.random());
	$("#showEdit").click(function(event){
		event.stopPropagation();
		//打开文件上传方法
		MyFormWin.showMyWinFn("文件上传",basePath+"common/manageFileEdit.jsp",400,200,function(){
			//加载文件
			$("#conMsg").load(basePath+"login!ldManageFile.action?num="+Math.random());
		});
	});
	$("#msgTit").css("cursor","pointer");
	$("#msgTit").click(function(){
		tips_pop();
	});
	var ie = !-[1,];
//	if(ie){
//		var scrollHeight=document.body.scrollHeight;
//		alert("scrollHeight---"+scrollHeight);
//		var windowHeight=$(window).height();
//		alert("windowHeight---"+windowHeight);
//		if(scrollHeight>windowHeight){
//			var top=scrollHeight-(windowHeight+76);
//			if(top<0){
//				return false;
//			}
//			$("#winpop").css("bottom",top+"px");
//			$(this).scroll(function(){
//				var tp=$(this).scrollTop();
//				$("#winpop").css("bottom",(top-tp)+"px");
//			});
//		}
	//}else{
		//firefox滚动条事件
		var top=$(this).scrollTop();
		$("#winpop").css("bottom",(-top+5)+"px");
		$(this).scroll(function(){
			top=$(this).scrollTop();
			$("#winpop").css("bottom",(-top+5)+"px");
		});
	//}
	//关闭
	$("#winpop").hover(function(){
		$("#closeSpan").css("display","block");
		//$("#closeSpan").next("span").css("margin-left","5px");
	},function(){
		$("#closeSpan").css("display","none");
		//$("#closeSpan").next("span").css("margin-left","207px");
	});
	$("#closeSpan").click(function(event){
		event.stopPropagation();
		var MsgPop = document.getElementById("winpop");
		MsgPop.style.display = "none";// 隐藏窗口
	});
}