// 扩展窗体
var MyFormWin = function(){
	var curWin;
	var winHideFn;
	return {
		width : 600,
		height : 450,
		title : "",
		// 显示窗口
		showWin : function(title,url) {
			var len=arguments.length;
			var window = this.createWin(url, title,this.width,this.height,null);
			window.show();
		},
		// 显示窗口
		showMyWin : function(title,url,myWidth,myHeight) {
			var len=arguments.length;
			var window = this.createWin(url, title,myWidth,myHeight,null);
			window.show();
		},
		showMyWinFn : function(title,url,myWidth,myHeight,functions) {
			var len=arguments.length;
			var window = this.createWin(url, title,myWidth,myHeight,functions);
			window.show();
		},
		// 显示窗口
		showWinMax : function(title,url) {
			var len=arguments.length;
			var window = this.createWin(url,title,this.width,this.height,null);
			window.show();
			this.maximize();
		},
		// 窗口
		show:function(){
			var len=arguments.length;
			var window;
			if(len==4){
				window = this.createWin(arguments[0],arguments[1],arguments[2],arguments[3],null);
			}
			else if(len=2){
				window = this.createWin(arguments[0],arguments[1],this.width,this.height,null);
			}
			else{
				window = this.createWin(arguments[0],this.title,this.width,this.height,null);
			}
			window.show();
			return false;
		},
		showMax:function(){
			var len=arguments.length;
			var window;
			if(len==4){
				window = this.createWin(arguments[0],arguments[1],arguments[2],arguments[3],null);
			}
			else if(len=2){
				window = this.createWin(arguments[0],arguments[1],this.width,this.height,null);
			}
			else{
				window = this.createWin(arguments[0],this.title,this.width,this.height,null);
			}
			window.show();
			this.maximize();
			return false;
		},
		// 调用弹出窗口
		createWin : function(url,title,width,height,closeFn) {
			var win = Ext.getCmp("win");
			if(closeFn!=null){
				//重置窗体隐藏时触发方法
				winHideFn = closeFn;
			}
			if (!win) {
				win = new Ext.Window({
					id : "win",
					title : this.title+title,
					width : width,
					height : height,
					maximizable : true,
					modal : true,
					resizable:false,
					listeners: {
						hide:function(w){
							//隐藏窗口前先还原,滚动条才不会消失
							w.restore();
							//隐藏窗口前先还原位置居中
							w.center();
							//隐藏窗体触发方法
							if(typeof(winHideFn)=='function'){
								winHideFn();
							}
							winHideFn = null;
						},
						close:function(w){
							//关键部分：关闭窗口前先还原,滚动条才不会消失
							w.restore();
						},
						maximize:function(w){   
							//关键部分：最大化后需要将窗口重新定位，否则窗口会从最顶端开始最大化                       
							w.setPosition(document.body.scrollLeft,document.body.scrollTop);
						}
					},
					html : "<iframe width='100%' height='100%' frameborder='0' src='"
							+ url + "'></iframe>"
				});
			}
			curWin = win;
			return win;
		},
		close : function() {
			if(curWin){
				curWin.close();
			}
		},
	 	maximize:function(){
	 		if(curWin){
	 			curWin.maximize();
	 		}
		},
		restore:function(w){
			if(curWin){
	 			curWin.restore();
	 		}
		}
	}
}();
// 掩饰层
var MyMask = function(){
	var mask;
	return {
		show : function(){
		    if(typeof(mask) == "undefined"){
				mask = new Ext.LoadMask(Ext.getBody(), {msg:"正在处理中,请稍候.." });
			}
			mask.show();
		},
		showHtml : function(html){
			mask = new Ext.LoadMask(Ext.getBody(), {msg:html });
			mask.show();
		},
		hide : function(){
			if(typeof(mask) == "undefined"){
				mask = new Ext.LoadMask(Ext.getBody(), {msg:"正在处理中,请稍候.." });
			}
			mask.hide();
		}
	}
}();
// 提示对话框
var MyMsg = function(){
	return {
		alert : function(msg){
			Ext.Msg.buttonText.ok='确定';
		    Ext.Msg.alert('提示', msg);
		},
		alertFn : function(msg,fn){
			Ext.Msg.buttonText.ok='确定';
		    Ext.Msg.alert('提示', msg);
		    Ext.Msg.show({
				title:'提示',
				msg:msg,
				buttons:Ext.Msg.OK,
				fn:fn
			});
		},
		confirm : function(msg,url){
			Ext.Msg.buttonText.yes='确定';
		    Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', msg, function(btn, text){
				if(btn=="yes"){
					MyMask.show("正在处理中，请稍候..");
					setTimeout(function(){
						location.href = url;
					},0);
				}
			});
		},
		confirmClose : function(msg){
			Ext.Msg.buttonText.yes='确定';
		    Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', msg, function(btn, text){
				if(btn=="yes"){
					parent.MyWin.close();
				}
			});
		},
		confirmFn : function(){
			var arr = arguments;
			var arrLen = arr.length;
			if(arrLen>1){
				Ext.Msg.buttonText.yes='确定';
			    Ext.Msg.buttonText.no="取消";
				Ext.Msg.confirm('提示', arr[0], function(btn, text){
					if(btn=="yes"){
						var fn = arr[1];
						if(typeof(fn)=='function'){
							fn();
						}
					}else if(arrLen>2){
						var fn = arr[2];
						if(typeof(fn)=='function'){
							fn();
						}
					}
				});
			}
		}
	}
}();