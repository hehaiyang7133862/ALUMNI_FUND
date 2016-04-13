// 路径
var spath = "";
if(typeof(glbPath) != "undefined"){
	spath = glbPath;
}
// 扩展窗体
FormEditWin = function(){
	var curFormWin;
	return {
		width : 600,
		height : 500,
		// 添加
		showAddWin : function(parentNode) {
			var window = this.createWin("winNodeAdd", "新建栏目", spath + "siteItem!edit.action?group="+parentNode.id.substring(5), function() {
				parentNode.reload();
			});
			window.show();
		},
		// 编辑
		showEditWin : function(node) {
			var window = this.createWin("winNodeEdit", "编辑栏目-" + node.text, spath + "siteItem!edit.action?group="+node.parentNode.id.substring(5)+"&id="+node.id.substring(4), function() {
				var nodeparent = node.parentNode;
				var tree = node.getOwnerTree();
				nodeparent.on("expand", function(pnode) {
					tree.getNodeById(node.id).select();
				}, this, {
					single : true
				});
				node.parentNode.reload();
			});
			window.show();
		},
		// 调用弹出窗口
		createWin : function(winId, winTitle, iframePage, closeFun) {
			// 供各类型窗口创建时调用
			var win = Ext.getCmp(winId);
			if (!win) {
				win = new Ext.Window({
					id : winId,
					title : winTitle,
					width : this.width,
					height : this.height,
					maximizable : true,
					modal : true,
					html : "<iframe width='100%' height='100%' frameborder='0' src='" + iframePage + "'></iframe>"
				});
				this.reloadNavNode = closeFun;
			}
			curFormWin = win;
			return win;
		},
		close : function() {
			if(curFormWin){
				curFormWin.close();
			}
		}
	}
}();
// 导航树
NavTree = function(){
	var nav;
	var dirMenu;
	var itemMenu;
	var loader;
	var root;
	var nodeSelected;
	var removeFlag;
	var mgr;
	return {
		init : function(){
			if(!mgr){
				Ext.Msg.alert("警告提示","请先通过NavTree.setMgr()设置mgr");
				return;
			}
			if(!loader){
				loader = new Ext.tree.TreeLoader({
					url : spath + 'siteItem!json.action'
				});
				loader.on('beforeload', function(treeloader, node) {
					treeloader.baseParams = {
						id : node.id,
						method : 'tree'
					};
				}, this);
			}
			if(!root){
				root = new Ext.tree.AsyncTreeNode({
					id : '0',
					name : '栏目管理',
					text : '栏目管理',
					title : '栏目管理',
					expanded : true
				});
			}
			if(!nav){
				nav = new Ext.tree.TreePanel({
					renderTo:'tree-panel',
					autoScroll : true,
					autoHeight:true,
					animate : false,
					loader : loader,
					border:false,
					root : root,
					rootVisible : false, 
					enableDD : true,
					listeners : {
						'click' : function(node, event) {
							if(node.id.indexOf('item')!=-1){
								NavTree.showList(node);
							}
						},
						'dblclick' : function(node, event) {
							if(node.id.indexOf('item')!=-1){
								FormEditWin.showEditWin(node);
							}
						}
					}
				});
				// 添加右键菜单
				nav.on("contextmenu", this.showTreeMenu);
				// 当节点移动时触发事件
				nav.on("beforemovenode", function(tree, node, oldParent, newParent, index) {
					if(oldParent.id!=newParent.id){
						return false;
					}
					if(newParent.id.indexOf('group')==-1){
						return false;
					}
					mgr.moveNode(node.id.substring(4), index,function(success){
						if(!success){
							MyMsg.alertFn("节点移动不符合规定", function(btn) {
								NavTree.reloadTree();
							});
						}
					});
					return true;
				});
				// 当节点删除时触发事件
				nav.on("remove", function(tree, parentNode, node) {
					if (removeFlag) {
						mgr.removeNode(node.id.substring(4));
					}
				});
			}
			this.setDirMenu();
			this.setItemMenu();
		},
		setMgr : function(manager){
			mgr = manager;
		},
		getMgr : function(){
		},
		// 菜单触发器
		showTreeMenu : function(node, e){
			nodeSelected = node;
			nodeSelected.select();
			if(nodeSelected.id.indexOf('item')!=-1){
				itemMenu.showAt(e.getPoint());
			}else{
				dirMenu.showAt(e.getPoint());
			}
		},
		// 设置目录菜单
		setDirMenu: function(){
			if(!dirMenu){
				dirMenu = new Ext.menu.Menu({
					items : [{
						text : "新建栏目",
						handler : function() {
							FormEditWin.showAddWin(nodeSelected);
						}
					},'-',{
						text : "刷新",
						handler : function() {
							NavTree.reloadNodeById(nodeSelected.id);
						}
					}]
				});
			}
		},
		// 设置栏目菜单
		setItemMenu: function(){
			if(!itemMenu){
				itemMenu = new Ext.menu.Menu({
					items : [{
						text : "编辑栏目",
						handler : function() {
							FormEditWin.showEditWin(nodeSelected);
						}
					},'-',{
						text : "内容维护",
						handler : function() {
							NavTree.showList(nodeSelected);
						}
					},'-',{
						text : "删除",
						handler : this.delTreeItemComfirm
					}]
				});
			}
		},
		showList: function(node){
			var map = node.attributes.name;
			var pnode = node.parentNode;
			while(pnode!=root){
				map = pnode.attributes.name + ' > ' + map;
				pnode = pnode.parentNode;
			}
			$('#myMap').html('<div class="lyt_nav">'+map+'</div>');
			$('#myFrame').attr('src',spath+'siteItemCnt.action?item='+node.id.substring(4));
		},
		// 删除确认
		delTreeItemComfirm : function(){
			mgr.findNode(nodeSelected.id.substring(4), function(success) {
				if(success){
					Ext.Msg.confirm("确认删除", "确定要删除所选节点吗？", function(btn) {
						if (btn == "yes") {
							NavTree.delTreeItem();
						}
					});
				}
				else{
					Ext.Msg.alert('删除失败', '请先删除被选节点的所有子项！');
				}
			});
		},
		// 删除操作
		delTreeItem : function(){
			removeFlag = true;
			nodeSelected.remove();
			removeFlag = false;
		},
		reloadTree : function(){
			root.attributes.children = false; 
			root.reload();
		},
		reloadNodeById : function(nodeId){
			var node = nav.getNodeById(nodeId);
			if(node){
				node.attributes.children = false; 
				node.reload();
			}
		}
	}
}();
// 文档加载完毕执行
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = spath+"UI/ext/resources/images/default/s.gif";
	if(typeof(SiteItemManager)=="undefined"){
		Ext.Msg.alert("警告提示","请先设置DWR");
	}else{
		NavTree.setMgr(SiteItemManager);
		NavTree.init();
	}
});