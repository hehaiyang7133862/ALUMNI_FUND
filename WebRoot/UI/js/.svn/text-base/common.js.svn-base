//获取根目录
function getBasePath() {
	var els = document.getElementsByTagName('script'), src;
	for (var i = 0, len = els.length; i < len; i++) {
		src = els[i].src || '';
		if (/UI\/js\/common.js/.test(src)) {
			return src.replace(/UI\/js\/common.js/,"");
		}
	}
	return '';
}
function addResizeEvent(func) {
	func();
	var oldResize = window.onresize;
	if(typeof(oldResize)!='function'){
		window.onresize = func;
	}else{
		window.onresize = function(){
			oldResize();
			func();
		};
	}
}
function getX(e) {
	e = e || window.event;
	return e.pageX || e.clientX + document.body.scroolLeft;
}
function getY(e) {
	e = e|| window.event;
	return e.pageY || e.clientY + document.body.scrollTop;
}
var error="#FF99FF";
function formReset(sid){
	//input
	var eles1=document.getElementById(sid).getElementsByTagName("input");
	for(var i=0;i<eles1.length;i++){
		//text
		if(eles1[i].type=="text"){
			eles1[i].value="";
		}
		//radio
		if(eles1[i].type=="radio"){
			if(eles1[i].value==""){
				eles1[i].checked=true;
			}
		}
	}
	//textarea
	var eles2=document.getElementById(sid).getElementsByTagName("textarea");
	for(var i=0;i<eles2.length;i++){
		eles2[i].value="";
	}
	//select
	var eles3=document.getElementById(sid).getElementsByTagName("select");
	for(var i=0;i<eles3.length;i++){
		eles3[i].selectedIndex=0;
	}
}
function formFormat(sid){
	//input
	var eles1=document.getElementById(sid).getElementsByTagName("input");
	for(var i=0;i<eles1.length;i++){
		if(eles1[i].type=="text"){
			eles1[i].value=eles1[i].value.replace(/(^\s*)|(\s*$)/g, "");
			eles1[i].style.backgroundColor="";
		}
	}
	//textarea
	var eles2=document.getElementById(sid).getElementsByTagName("textarea");
	for(var i=0;i<eles2.length;i++){
		eles2[i].value=eles2[i].value.replace(/(^\s*)|(\s*$)/g, "");
		eles2[i].style.backgroundColor="";
	}
}
function checkAll(sid){
}
function checkNone(sid){
}
function getLength(val){   
	return val.replace(/[^\x00-\xff]/g,"**").length;
}
function myLoad(){
	if($(".lyt_search").size()>=1){
		$(".lyt_search").ready(function(){
			$('tr:even').addClass('lyt_result_even');
		})
	}
}
function tabStyle(){
	if($(".lyt_search").size()>=1){
		$(".lyt_search").ready(function(){
			$('tr:even').addClass('lyt_result_even');
		})
	}
}
function flxNext(obj){
	if(typeof(obj)=="object"){
		$(obj).nextAll("div:first").toggleClass("flx_hidden");
	}
}
function flxToggle(){
	var len=arguments.length;
	for(var i=0;i<len;i++){
		$("#"+arguments[i]).toggleClass("flx_hidden");
	}
}
function flxHidden(){
	var len=arguments.length;
	for(var i=0;i<len;i++){
		$("#"+arguments[i]).toggleClass("flx_hidden");
	}
}
function flxShow(){
	var len=arguments.length;
	for(var i=0;i<len;i++){
		$("#"+arguments[i]).toggleClass("flx_hidden");
	}
}
function nextDisable(obj){
	if($(obj).val()!=""){
		$(obj).next().removeAttr("disabled");
	}
	else{
		$(obj).next().attr("disabled","disabled");
	}
}
function nextAble(obj){
	if($(obj).val()!=""){
		$(obj).next().removeAttr("disabled");
		$(obj).next().nextAll().attr("disabled","disabled");
	}
	else{
		$(obj).next().val("");
		$(obj).next().nextAll().attr("disabled","disabled");
	}
}
function nextAllAble(obj){
	if($(obj).val()!=""){
		$(obj).nextAll().removeAttr("disabled");
	}
	else{
		$(obj).val();
		$(obj).nextAll().attr("disabled","disabled");
	}
}
function ajaxDDL(url,id1,id2){
	val=$("#"+id1).val();
	if("0"==val || ""==val){
		return;
	}
	var pars ={id:val};
	jQuery.get(
		url,
		pars,
		function(xml){
			var obj=$("#"+id2).get()[0];
			obj.length=0;
			obj.options.add((new Option("--请选择--","",true,true)));
			$(xml).find("fields").each(function(i){
				var opt=new Option($(this).children("name").text(),$(this).children("id").text());
				obj.options.add(opt);
			});
		},
		"xml");
}
function ajaxDDLF(url,id1,id2){
	val=$("#"+id1).val();
	if("0"==val || ""==val){
		var obj=$("#"+id2).get()[0];
		obj.length=0;
		obj.options.add((new Option("全部","",true,true)));
		return;
	}
	var pars ={id:val};
	jQuery.get(
		url,
		pars,
		function(xml){
			var obj=$("#"+id2).get()[0];
			obj.length=0;
			obj.options.add((new Option("全部","",true,true)));
			$(xml).find("fields").each(function(i){
				var opt=new Option($(this).children("name").text(),$(this).children("id").text());
				obj.options.add(opt);
			});
		},"xml");
}
function ajaxDDLAjax(url,id1,id2){
	val=$("#"+id1).val();
	if("0"==val || ""==val){
		return;
	}
	var pars ={id:val};
	jQuery.ajax({
		type:"POST",
		url:url,
		data:pars,
		async:false,
		dataType:"xml",
		success:function(xml){
			var obj=$("#"+id2).get()[0];
			obj.length=0;
			obj.options.add((new Option("--请选择--","",true,true)));
			$(xml).find("fields").each(function(i){
				var opt=new Option($(this).children("name").text(),$(this).children("id").text());
				obj.options.add(opt);
			});
		}});
}
function ajaxDDLText(url,id1,id2,str){
	val=$("#"+id1).val();
	if("0"==val || ""==val){
		var obj=$("#"+id2);
		obj.empty();
		$("<option value=''>"+str+"</option>").appendTo($("#"+id2));
		obj[0].selectedIndex=0;
		return;
	}
	var pars ={id:val};
	jQuery.ajax({
		type:"POST",
		url:url,
		data:pars,
		async:false,
		dataType:"xml",
		success:function(xml){
			var obj=$("#"+id2).get()[0];
			obj.length=0;
			if(str==""){
				str="--请选择--";
			}
			obj.options.add((new Option(str,"",true,true)));
			$(xml).find("fields").each(function(i){
				var opt=new Option($(this).children("name").text(),$(this).children("id").text());
				obj.options.add(opt);
			});
		}});
}
function myArea(){
	var args=arguments;
	var argId="";
	var argCount=1000;
	var argNotice="";
	if(args.length>=1){
		argId=args[0];
		argNotice=argId+"_notice";
	}
	if(args.length>=2){
		argCount=parseInt(args[1]);
	}
	if(args.length>=3){
		argNotice=args[2];
	}
	var numId=argNotice+"_num";
	// 设提示文
	if($("#"+argNotice).size()==0){
		var html="<div style='color:#666666;' id='"+argNotice+"'></div>";
		$("#"+argId).after(html);
	}
	if($("#"+numId).size()==0){
		var html="您还可以输入<b style='font-size:14px;font-weight:400;' id='"+numId+"'>0</b>字";
		$("#"+argNotice).html(html);
	}
	// 设置字数
	var val=argCount-$("#"+argId).val().replace(/[^\x00-\xff]/g,"**").length;
	if(val>0){
		$("#"+numId).html(val);
	}
	else{
		$("#"+argNotice).html("输入内容已经达到长度限制");
	}
}

function myAreaLen() {
	var args = arguments;
	var argId = "";
	var argCount = 1000;
	var argNotice = "";
	if (args.length >= 1) {
		argId = args[0];
		argNotice = argId + "_notice";
	}
	if (args.length >= 2) {
		argCount = parseInt(args[1]);
	}
	if (args.length >= 3) {
		argNotice = args[2];
	}
	var numId = argNotice + "_num";
	// 设提示文
	if ($("#" + argNotice).size() == 0) {
		var html = "<div style='color:#666666;' id='" + argNotice + "'></div>";
		$("#" + argId).after(html);
	}
	if ($("#" + numId).size() == 0) {
		var html = "您还可以输入<b style='font-size:14px;font-weight:400;' id='"
				+ numId + "'>0</b>字";
		$("#" + argNotice).html(html);
	}
	// 设置字数
	var val = argCount
			- $("#" + argId).val().length;
	if (val > 0) {
		$("#" + numId).html(val);
	} else {
		$("#" + argNotice).html("输入内容已经达到长度限制");
	}
}
function setFrmHeight(obj) {
	var win = obj;
	if (document.all) {
		if (document.getElementById) {
			if (win && !window.opera) {
				if (win.contentDocument
						&& win.contentDocument.body.offsetHeight)
					win.height = win.contentDocument.body.offsetHeight;
				else if (win.Document && win.Document.body.scrollHeight)
					win.height = win.Document.body.scrollHeight;
			}
		}
	} else {
		var frm = obj;
		var subWeb = document.frames? document.frames[obj.id].document: frm.contentDocument;
		if (frm != null && subWeb != null) {
			frm.style.height = "0px";// 初始化一下,否则会保留大页面高度
			if(subWeb.documentElement.scrollHeight<=500){
				frm.style.height="500px";
			}else{
				frm.style.height = subWeb.documentElement.scrollHeight + "px";
			}
			frm.style.width = subWeb.documentElement.scrollWidth + "px";
			subWeb.body.style.overflowX = "auto";
			subWeb.body.style.overflowY = "auto";
		}
	}
}

$(document).ready(function(){
	
//	$("div[class^=lyt_]").children("input[type!='hidden']").each(function(i){
//		var temp=$(this);
//		if(typeof(temp.attr("nostyle"))=="undefined"){
//			temp.css("margin","0");
//			temp.css({"background":"url('"+getBasePath()+"UI/images/btnbg_center.jpg') repeat scroll 0 0 transparent","border":"medium none","height":"22px","width":temp.val().length*20});
//			temp.before("<input type='button' name='beforeInput' />");
//			temp.after("<input type='button' name='afterInput' />");
//			$("input[name='beforeInput']").css({"background":"url('"+getBasePath()+"UI/images/btnbg_left.jpg') no-repeat scroll 0 0 transparent","border":"medium none","height":"22px","width":"4px","margin":"0"});
//			$("input[name='afterInput']").css({"background":"url('"+getBasePath()+"UI/images/btnbg_right.jpg') no-repeat scroll 0 0 transparent","border":"medium none","height":"22px","width":"4px","margin":"0","margin-right":"15"});
//		}
//	});
	$("div[class^=lyt_]").children("input[type!='hidden']").each(function(i){
		var temp=$(this);
		if(temp.attr("tagName")=="INPUT"){
//			$(temp).addClass("btn_com");
//			$(temp).wrap("<span class='spanButton'></span>");
			if(temp.attr("title")==""){
				temp.attr("title",$.trim(temp.val()));
			}
		}
	});
		
	if(parent.MyMask){
		parent.MyMask.hide();
	}
	if(parent.parent.MyMask){
		parent.parent.MyMask.hide();
	}
	
	//文本框获取焦点时边框高亮
	var textInputs = document.getElementsByTagName("input");
	var len = textInputs.length;
	var index = 0;
	var textInput;
	
	for( index = 0; index < len; index++ ) {
		textInput = textInputs[index];
		if( textInput.getAttribute("type") == "text" || textInput.getAttribute("type") == "password" ){
			textInput.onfocus = function(){
				$(this).addClass("txtMouseFocus");
			};
			
			textInput.onblur = function(){
				$(this).removeClass("txtMouseFocus");
			};
		}
	}
	
	//PANEL TITLE 点击展开或收缩
	var panelTitleObjA = $(".panel .title a");
	var notClk=true;
	panelTitleObjA.click(function(){
		notClk=false;
	});
	var panelTitleObj = $(".panel .title");
	if(panelTitleObj.length>0){
		panelTitleObj.click(function(){
			var titleObj = $(this);
			var contentObj = titleObj.next();
			if(notClk){//如果点击的是PANEL TITLE上的操作按钮，则不触发此事件
				if(titleObj.hasClass("titleExpan")){
					titleObj.removeClass("titleExpan");
					contentObj.removeClass("contentExpan");
				}else{
					titleObj.addClass("titleExpan");
					contentObj.addClass("contentExpan");
				}
			}
			notClk=true;
		});
	}
	
	//数据列表，选中一个或多个单元格，背景变色
	$('.lyt_result tr:even').addClass('lyt_result_even');
	$(".lyt_result tr").click(function(){
		var obj = $(this);
     	if(obj.hasClass("trClick")){
      		obj.removeClass("trClick");
      	}else{
    		obj.addClass("trClick");
 		}
	}).mouseover(function(){
		var obj = $(this);
     	if(!obj.hasClass("trClick")){
      		obj.addClass("trHighlight");
 		}
	}).mouseout(function(){
		var obj = $(this);
     	if(!obj.hasClass("trClick")){
      		obj.removeClass("trHighlight");
 		}
	});
	//表格鼠标移动事件
	$(".lyt_result td").hover(function(){
		if(!$(this).attr("title")){
			$(this).attr("title",$.trim($(this).text()));
		}
	});
	
	/*
	//数据列表，选中一个或多个单元格，背景变色
	$(".lyt_result td").attr("selected","0");
	var mouseState=0;
	var hangBeg;var hangEnd;
	$(".lyt_result td").click(function(){
							var selected = $(this).parent("tr").attr("selected");
					     	if(selected == 1){
					      		$(this).parent("tr").attr("selected","0");
					        	$(this).parent("tr").removeClass("trClick");
					      	}else{
					    		$(this).parent("tr").attr("selected","1");
					      		$(this).parent("tr").addClass("trClick");
					 		}
						}).mousedown(function(){
										hangBeg = $(this).parent("tr").prevAll().length; 
										hangEnd = $(this).parent("tr").prevAll().length; 
										mouseState = 1;
						}).mouseup(function(){
										mouseState = 0;
						}).mouseover(function(){
										if(mouseState == 1){
											var xBeg;var xEnd;
											hangEnd = $(this).parent("tr").prevAll().length;
											if(hangBeg <= hangEnd){
												xBeg = hangBeg;
												xEnd = hangEnd;
											}else{
												xBeg = hangEnd;
												xEnd = hangBeg;
											}
											
											$(this).parent().parent().find("tr").each(function(){
												$(this).attr("selected","0");
										      	$(this).removeClass("trClick");
									      		var hang = $(this).prevAll().length;
									      			
									      		for(i=xBeg;i<=xEnd;i++){
													if(i == hang){
														$(this).attr("selected","1");
											      		$(this).addClass("trClick");
													}
												}
											});
										}else{
											$(this).parent("tr").find("td").each(function(){
													$(this).addClass('trHighlight');
											});
										}
						}).mouseout(function(){
										if(mouseState != 1){
											$(this).parent("tr").find("td").each(function(){
													$(this).removeClass('trHighlight');
											});
										}
						});
	//表格鼠标移动事件
	$(".lyt_result td").hover(function(){
		if($(this).attr("title")==""){
			$(this).attr("title",$.trim($(this).text()));
		}
	});*/
	
	//将readonly的输入框变为灰色
	$("*[readonly]").css("background-color","#E5E5E5");
	
	//将radio的边框去掉
	$("input[type='radio']").css("border","0");
	//将checkbox的边框去掉
	$("input[type='checkbox']").css("border","0");
});

//控制只能输入数字
function limit(obj){
	var val=obj.value;
	if(!(/^[1-9]\d*$/.test(val))){
	     obj.value="";
	     return false;
	}
}
//if(!(self.frameElement && self.frameElement.tagName.toUpperCase()=="IFRAME")){
//	var localPage = location.href;
//	var loginPage = glbPath+"login.action";
	//var exitPage = glbPath+"login!exit.action";
	//var loginJspPage = glbPath+"common/login.jsp";
	//if(localPage!=loginPage && localPage!=exitPage && localPage!=loginJspPage){
	//	location.href=glbPath+"common/openError.jsp";
	//}
//}

function addTab(nav, tit, link){
	var mainTabsObj = Ext.getCmp('mainTabs');
	var mainTabItemsObj = mainTabsObj.items;
	for(i=0;i<mainTabItemsObj.length;i++){
		var item = mainTabItemsObj.get(i);
		if(item.title==tit){
			mainTabsObj.activate(item);
			return;
		}
	}
	var tabIndex = mainTabsObj.tabIndex;
	var tabId = 'mainTab'+tabIndex;
	var tabLodingId = 'mainTabLoading'+tabIndex;
	var tabFrameId = 'mainTabFrame'+tabIndex;
	var tabIsClose = tabIndex>0 ? true:false;
    mainTabsObj.add({
    	id: tabId,
        title: tit,
        nav: nav,
        html: '<div id=\''+tabLodingId+'\' class=\'lytFrameLoading\'>正在加载,请稍候..</div>' + 
        	  '<iframe id=\''+tabFrameId+'\' src=\''+link+'\' scrolling="auto" frameborder="0" ' + 
        	  'width="100%" height="100%" style=\'display:none;\' ' + 
        	  'onload=\'tabFrameLoad(this);\'></iframe>',
        closable:tabIsClose
    }).show();
    mainTabsObj.tabIndex = tabIndex+1;
}
function tabFrameLoad(obj){
	var tabFrameId = obj.id;
	var tabLodingId = 'mainTabLoading'+tabFrameId.replace('mainTabFrame','');
	$('#'+tabLodingId).hide();
	$('#'+tabLodingId).html('正在加载,请稍候..');
	$('#'+tabFrameId).show();
}
function uploadTab(nav, tit, link){
	var mainTabsObj = Ext.getCmp('mainTabs');
	var mainTabItemsObj = mainTabsObj.items;
	for(i=0;i<mainTabItemsObj.length;i++){
		var item = mainTabItemsObj.get(i);
		if(item.title==tit){
			var tabId = item.id;
			var tabIndex = tabId.replace('mainTab','');
			var tabLodingId = 'mainTabLoading'+tabIndex;
			var tabFrameId = 'mainTabFrame'+tabIndex;
			$('#'+tabFrameId).attr('src','');
			setTimeout(function(){
				$('#'+tabFrameId).hide();
				$('#'+tabLodingId).show();
				mainTabsObj.activate(item);
				$('#'+tabFrameId).attr('src',link);
			},0);
			return;
		}
	}
	var tabIndex = mainTabsObj.tabIndex;
	var tabId = 'mainTab'+tabIndex;
	var tabLodingId = 'mainTabLoading'+tabIndex;
	var tabFrameId = 'mainTabFrame'+tabIndex;
	var tabIsClose = tabIndex>0 ? true:false;
    mainTabsObj.add({
    	id: tabId,
        title: tit,
        nav: nav,
        html: '<div id="'+tabLodingId+'" class="tabsLoading">正在加载,请稍候..</div>' + 
        	  '<iframe id="'+tabFrameId+'" name="'+tabFrameId+'" src="'+link+'" scrolling="auto" frameborder="0" ' + 
        	  'width="100%" height="100%" style="display:none;" ' + 
        	  'onload="tabFrameLoad(this);"></iframe>',
        closable:tabIsClose
    }).show();
    mainTabsObj.tabIndex = tabIndex+1;	
}

function updateTabTitle(tit){
	var tabObj = Ext.getCmp('mainTabs').getActiveTab();
	tabObj.setTitle(tit);
}
