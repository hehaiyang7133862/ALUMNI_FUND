// 全局路径
var basePath = "http://localhost:8080/langsinext";
if(typeof(glbRootPath) != "undefined"){
	basePath = glbRootPath;
}
// 编号
var sid = "0";
// 名称
var sname = "属性大类";
// 扩展窗体
FormEditWin = function(){
	var curFormWin;
	return {
		width : 650,
		height : 500,
		// 查看
		showViewWin: function(node){
			if(sid!=node.id){
				window.document.getElementById("iFrame2Id").src="attrForward.action?id="+node.id+"&glbChildWidth="+glbChildWidth;
			}
		},
		// 添加
		showAddWin : function(parentNode) {
			// 显示添加子叶子节点窗口
			var number = parentNode.indexOf(parentNode.lastChild) + 1;
			var editpage = basePath
					+ "attrParentPreAdd.action?parentId="+ parentNode.id;
			var window = this.createWin("winNodeAdd", "添加属性大类", editpage, function() {
				parentNode.reload();
			});
			window.show();
		},
		// 编辑
		showEditWin : function(node) {
			// 显示目录编辑窗口
			var editpage = basePath
					+ "attrParentPreEdit.action?id=" + node.id;
			var window = this.createWin("winNodeEdit" + node.id, node.text, editpage, function() {
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
					title : "菜单编辑窗口-" + winTitle,
					width : this.width,
					height : this.height,
					maximizable : true,
					modal : true,
					html : "<iframe width='100%' height='100%' frameborder='0' src='"
							+ iframePage + "'></iframe>"
				});
				this.reloadNavNode = closeFun;
			}
			curFormWin = win;
			return win;
		},
		reloadNavNode : function() {
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
	var navEditor;
	var leafMenu;
	var dirMenu;
	var dirRoot;
	var loader;
	var root;
	var removeFlag = false;
	var titleChangeFlag = false;
	var nodeSelected;
	var mgr;
	return {
		init : function(){
			if(!mgr){
				Ext.Msg.alert("警告提示","请先通过NavTree.setMgr()设置mgr");
				return;
			}
			if(!loader){
				loader = new Ext.tree.TreeLoader({
					url : basePath + '/attrParent.action'
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
					id : sid,
					text : sname
				});
			}
			if(!nav){
				nav = new Ext.tree.TreePanel({
					el : 'tree-panel1',
					width : glbWidth,
					autoScroll : true,
					animate : true,
					loader : loader,
					root : root,
					enableDD : true,
					listeners : {
						'click' : function(node, event) {
							if(sid!=node.id){
								window.document.getElementById("iFrame2Id").src="attrForward.action?id="+node.id+"&glbChildWidth="+glbChildWidth;
							}
							if (node.isLeaf()) {
								// 为叶子节点时，点击不进入链接
								event.stopEvent();
							}
						}
					}
				});
				// 添加右键菜单
				nav.on("contextmenu", this.showTreeMenu);
				// 当节点移动时触发事件
				nav.on("movenode", function(tree, node, oldParent, newParent, index) {
					mgr.ajaxMoveNode(node.id, oldParent.id, newParent.id, index,function(success){
						if(!success){
							Ext.Msg.confirm("移动失败", "节点移动不符合规定", function(btn) {
								location.reload();
							})
						}
					});
				});
				// 当节点删除时触发事件
				nav.on("remove", function(tree, parentNode, node) {
					if (removeFlag) {
						mgr.ajaxRemoveNode(node.id);
					}
				});
			}
			this.setDirRoot();
			this.setDirMenu();
			this.setLeafMenu();
		},
		setMgr : function(manager){
			mgr = manager;
		},
		getMgr : function(){
		},
		// 设置根级菜单
		setDirRoot: function(){
			if(!dirRoot){
				dirRoot = new Ext.menu.Menu({
					items : [{
						text : "添加子级",
						handler : function() {
							FormEditWin.showAddWin(nodeSelected);
						}
					}]
				});
			}
		},
		// 设置目录菜单
		setDirMenu: function(){
			if(!dirMenu){
			}
		},
		// 设置叶子菜单
		setLeafMenu: function(){
			if(!leafMenu){
				leafMenu = new Ext.menu.Menu({
					items : [{
						text : "查看",
						handler : function() {
							FormEditWin.showViewWin(nodeSelected);
						}
					}, "-", {
						text : "编辑",
						handler : function() {
							FormEditWin.showEditWin(nodeSelected);
						}
					}, "-", {
						text : "删除",
						handler : this.delTreeItemComfirm
					}]
				});
			}
		},
		// 菜单触发器
		showTreeMenu : function(node, e){
			nodeSelected = node;
			nodeSelected.select();
			if(node.id==0){
				// 显示根节点菜单
				dirRoot.showAt(e.getPoint());
			}else if (node.isLeaf()) {
				// 显示叶子节点菜单
				leafMenu.showAt(e.getPoint());
			} else {
				// 显示目录节点菜单
				dirMenu.showAt(e.getPoint());
			}
		},
		// 删除确认框
		delTreeItemComfirm : function(){
			mgr.ajaxFindNode(nodeSelected.id, function(success) {
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
			if (nodeSelected != nav.getRootNode()) {
				removeFlag = true;
				nodeSelected.remove();
				removeFlag = false;
			} else {
				Ext.Msg.alert("警告", "不能删除树的根节点！");
			}
		},
		show : function(){
			nav.render(Ext.getBody());
			nav.getRootNode().toggle();
		}
	}
}();


// 文档加载完毕执行
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = "../UI/ext/resources/images/default/s.gif";
	if(typeof(AttrManager)=="undefined"){
		Ext.Msg.alert("警告提示","请先设置DWR，并实例化AttrManager");
	}else{
		NavTree.setMgr(AttrManager);
		NavTree.init();
		NavTree.show();
	}
});