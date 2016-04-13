
$(document).ready(function(){
	$('.lytHeader .menuInfo .memu .cnt li:first').attr('clicked','1').addClass('sel');
	$('.lytHeader .menuInfo .memuDetail ul:first').show();
	$('.lytHeader .menuInfo .memu .cnt li').mouseenter(function(){
		var liObj = $(this);
		if(liObj.attr('clicked')!='1'){
			$(this).addClass('sel');
		}
	}).mouseleave(function(){
		var liObj = $(this);
		if(liObj.attr('clicked')!='1'){
			$(this).removeClass('sel');
		}
	}).click(function(){
		var liObj = $(this);
		liObj.attr('clicked','1');
		liObj.prevAll().attr('clicked','0').removeClass('sel');
		liObj.nextAll().attr('clicked','0').removeClass('sel');
		var index = liObj.prevAll().length;
		$('.lytHeader .menuInfo .memuDetail ul').each(function(i){
			if(index==i){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	});
	$('.lytHeader .menuInfo .memuDetail li').mouseenter(function(){
		$(this).addClass('sel');
	}).mouseleave(function(){
		$(this).removeClass('sel');
	}).click(function(){
		var liObj = $(this);
		addTab(liObj.attr('map'),liObj.attr('title'),getBasePath()+'nav.action?url='+encodeURIComponent(liObj.attr('url'))+'&code='+liObj.attr('code'));
	});

    var bodyTabsObj = new Ext.TabPanel({
    	id:'mainTabs',
        renderTo:'tabsContainer',
        activeTab:0,
        tabIndex:1,
        enableTabScroll:true,
        items:[
            {contentEl:'homePage', title: '首页'}
        ],
        listeners:{
        	contextmenu:function(panel,tab,e){
				var tabMenu = new Ext.menu.Menu({
					defaultAlign:'left',
					items:[
						{
							text:'关闭其他标签',
							handler:function(){
								panel.items.each(function(){
						            if(this.closable){
						                disableAll = false;
						                if(this != tab){
						                    panel.remove(this);
						                }
						            }
						        });
							}
						}
					]
				});
				tabMenu.showAt(e.getPoint());
			}
        }
    });
	fillIn();
	window.onresize = fillIn;
});
function fillIn(){
	setTimeout(function(){
		var lytContainerObj = $('.lytContainer');
		var lytHeaderObj = $('.lytHeader');
		var lytBodyObj = $('.lytBody');
		var bodyTabsObj = Ext.getCmp('mainTabs');
		var lytFooterObj = $('.lytFooter');
		var winWidth = document.body.clientWidth;
		var winHeight = document.body.clientHeight;
		
		if(winWidth<1060){
			winWidth=1060;
		}
		lytContainerObj.width(winWidth);
		bodyTabsObj.setWidth(winWidth-17);
		
		var bodyHeight = winHeight-lytHeaderObj.height()-lytFooterObj.height();
		if(bodyHeight<600){
			bodyHeight=600;
		}
		lytBodyObj.height(bodyHeight);
		bodyTabsObj.setHeight(bodyHeight);
		//lytContainerObj.height(winHeight-16);
		
		$('.lytHeader .menuInfo .memu .cnt li').each(function(i){
			var menuObj = $(this);
			var childMenuObj = $('.lytHeader .menuInfo .memuDetail ul:eq('+i+')');
			var marginLeft = menuObj.offset().left+menuObj.width()/2-childMenuObj.width()/2;
			if(marginLeft<0){
				marginLeft=0;
			}
			if((marginLeft+childMenuObj.width())>(winWidth-17)){
				marginLeft=winWidth-17-childMenuObj.width();
			}
			childMenuObj.css('margin-left',marginLeft+'px');
		});
		setTimeout(function(){$('.glbLyt').css('overflow','auto');},0);
	},0);
}
function addTab(nav, tit, link){
	var bodyTabsObj = Ext.getCmp('mainTabs');
	var bodyTabItemsObj = bodyTabsObj.items;
	for(i=0;i<bodyTabItemsObj.length;i++){
		var item = bodyTabItemsObj.get(i);
		if(item.title==tit){
			bodyTabsObj.activate(item);
			return;
		}
	}
	var tabIndex = bodyTabsObj.tabIndex;
	var tabId = 'mainTab'+tabIndex;
	var tabLodingId = 'mainTabLoading'+tabIndex;
	var tabFrameId = 'mainTabFrame'+tabIndex;
	var tabIsClose = tabIndex>0 ? true:false;
    bodyTabsObj.add({
    	id: tabId,
        title: tit,
        nav: nav,
        html: '<div id=\''+tabLodingId+'\' class=\'lytFrameLoading\'>正在加载,请稍候..</div>' + 
        	  '<iframe id=\''+tabFrameId+'\' src=\''+link+'\' scrolling="auto" frameborder="0" ' + 
        	  'width="100%" height="100%" style=\'display:none;\' ' + 
        	  'onload=\'tabFrameLoad(this);\'></iframe>',
        closable:tabIsClose
    }).show();
    bodyTabsObj.tabIndex = tabIndex+1;
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
        html: '<div id="'+tabLodingId+'" class="lytFrameLoading">正在加载,请稍候..</div>' + 
        	  '<iframe id="'+tabFrameId+'" name="'+tabFrameId+'" src="'+link+'" scrolling="auto" frameborder="0" ' + 
        	  'width="100%" height="100%" style="display:none;" ' + 
        	  'onload="tabFrameLoad(this);"></iframe>',
        closable:tabIsClose
    }).show();
    mainTabsObj.tabIndex = tabIndex+1;	
}
function updateTabTitle(nav,tit){
	var tabObj = Ext.getCmp('mainTabs').getActiveTab();
	tabObj.setTitle(tit);
	tabObj.nav = nav;
}
function closeTab(){
	var tabObj = Ext.getCmp('mainTabs').getActiveTab();
	tabObj.destroy();
}
function tabFrameLoad(obj){
	var tabFrameId = obj.id;
	var tabLodingId = 'mainTabLoading'+tabFrameId.replace('mainTabFrame','');
	$('#'+tabLodingId).hide();
	$('#'+tabLodingId).html('正在加载,请稍候..');
	$('#'+tabFrameId).show();
}
function getTabNav(){
	var tabObj = Ext.getCmp('mainTabs').getActiveTab();
	return tabObj.nav;
}