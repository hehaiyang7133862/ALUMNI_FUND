// 路径
var spath = "";
if(typeof(glbPath) != "undefined"){
	spath = glbPath;
}
// 编号
var sid = "0";
if(typeof(glbId) != "undefined"){
	sid = glbId;
}
// 名称
var sname = "";
if(typeof(glbName) != "undefined"){
	sname = glbName;
}
// 数量
var scount = "";
if(typeof(glbCount) != "undefined"){
	scount = glbCount;
}
// 提示
var stitle = "";
if(typeof(glbTitle) != "undefined"){
	stitle = glbTitle;
}
// 导航树
NavTree = function(){
	var nav;
	var dirMenu;
	var loader;
	var root;
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
					url : spath + 'zcProj!typeJson.action'
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
					name : sname,
					text : sname + ' ['+scount+']',
					title : stitle,
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
					enableDD : false,
					listeners : {
						'click' : function(node, event) {
							NavTree.showList(node);
						}
					}
				});
				// 添加右键菜单
				nav.on("contextmenu", this.showTreeMenu);
			}
			this.setDirMenu();
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
			dirMenu.showAt(e.getPoint());
		},// 设置目录菜单
		setDirMenu: function(){
			if(!dirMenu){
				dirMenu = new Ext.menu.Menu({
					items : [{
						text : "查看",
						handler : function() {
							NavTree.showList(nodeSelected);
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
		showList: function(node){
			if(node==root){
				$('#myMap').html(root.attributes.name);
			}else{
				var map = node.attributes.name;
				var pnode = node.parentNode;
				while(pnode!=root){
					map = pnode.attributes.name + ' > ' + map;
					pnode = pnode.parentNode;
				}
				$('#myMap').html(map);
			}
			$('#myFrame').attr('src',spath+'zcProj!list.action?typeId='+node.id);
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
	if(typeof(ZcProjTypeManager)=="undefined"){
		Ext.Msg.alert("警告提示","请先设置DWR");
	}else{
		NavTree.setMgr(ZcProjTypeManager);
		NavTree.init();
	}
});