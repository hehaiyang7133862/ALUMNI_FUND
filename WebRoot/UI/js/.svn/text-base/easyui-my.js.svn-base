$(function(){	
	$.extend($.fn.validatebox.defaults.rules, {  
        minLength: {  
            validator: function(value, param){  
                return value.length >= param[0];  
            },  
            message: '不能少于{0}个字'  
        },
        maxLength: {  
            validator: function(value, param){  
                return value.length <= param[0];  
            },  
            message: '不能超过{0}个字'  
        },
        proofCode: {  
            validator: function(value, param){  
                return value == param[0];  
            },  
            message: '请准确填写验证码'  
        },
        rePwd: {  
            validator: function(value, param){  
                return value == param[0];  
            },  
            message: '请准确填写确认新密码'  
        },
        checkPwd: {  
            validator: function(value, param){
                return "true" == param[0].trim();  
            },  
            message: '请准确填写密码'  
        },
        qq: {  
            validator: function(value, param){
                return value.match(/^[1-9][0-9]{4,}$/);  
            },  
            message: '请准确填写QQ'  
        },
        msn: {  
            validator: function(value, param){
                return value.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);  
            },  
            message: '请准确填写MSN'  
        },
        num: {  
            validator: function(value, param){
                return value.match(/^[1-9]\d*$/);  
            },  
            message: '必须为整数'  
        }
    });
    
    //超链接title效果
    var x = 10;  
	var y = 20;
	$("a.tooltip").mouseover(function(e){
       	this.myTitle = this.title;
		this.title = "";	
	    var tooltip = "<div id='tooltip' style='z-index:9999;'>"+ this.myTitle +"<\/div>"; //创建 div 元素
		$("body").append(tooltip);	//把它追加到文档中
		$("#tooltip")
			.css({
				"top": (e.pageY+y) + "px",
				"left": (e.pageX+x)  + "px"
			}).show("fast");	  //设置x坐标和y坐标，并且显示
    }).mouseout(function(){		
		this.title = this.myTitle;
		$("#tooltip").remove();   //移除 
    }).mousemove(function(e){
		$("#tooltip")
			.css({
				"top": (e.pageY+y) + "px",
				"left": (e.pageX+x)  + "px"
			});
	});
});

var MyEasyWin = function(){
	var curWin;
	return {
		width : 600,
		height : 500,
		// 调用弹出窗口
		showWin : function(title,url,width,height) {
			if ($('#winDiv').length == 0){
				$("<div>",{
					id:'winDiv',
					html:'<iframe width="100%" height="100%" frameborder="0" src="' + url + '"></iframe>'
				}).appendTo("body");
			}
			$('#winDiv').addClass('easyui-window');
			curWin=$('#winDiv').window({  
				title:title,
			    width:width,  
			    height:height,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    modal:true
			});
			
			return curWin;
		},
		close : function() {
			if(curWin){
				curWin.window('close');
			}
		}
	}
}();

function MyEasyConfirm(content,url){
	$.messager.confirm('提示', content, function(r){
		if(r){
			location.href = url;
		}
	});
}
function MyEasyAlert(content){
	$.messager.alert('提示',content);
}
function MyEasyErrorAlert(content){
	$.messager.alert('提示',content,'error');
}
function MyEasyInfoAlert(content){
	$.messager.alert('提示',content,'info');
}
function MyEasyQuestionAlert(content){
	$.messager.alert('提示',content,'question');
}
function MyEasyWarningAlert(content){
	$.messager.alert('提示',content,'warning');
}