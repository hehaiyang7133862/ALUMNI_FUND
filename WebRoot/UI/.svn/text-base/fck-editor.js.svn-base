WE.util.HTMLEditor = function(instanceName, params) {
    this.InstanceName	= instanceName ;
    if(!params)
    {
        params = {};
    }
    this.Width			= params.width			|| '100%' ;
    this.Height			= params.height		|| '200' ;
    this.ToolbarSet		= params.toolbarSet	|| 'Default' ;
    this.Value			= params.Value			|| '' ;
    this.contextPath = params.contextPath || "";

    this.oFCKeditor = new FCKeditor(this.InstanceName);
    this.oFCKeditor.ToolbarSet = this.ToolbarSet;
    this.oFCKeditor.Width =  this.Width;
    this.oFCKeditor.Height = this.Height;
    this.oFCKeditor.BasePath = this.contextPath+this.oFCKeditor.BasePath;


};

WE.util.HTMLEditor.prototype.ReplaceTextarea = function()
{
    this.oFCKeditor.ReplaceTextarea();
}

WE.util.addMiniEditor = function(id, params)
{
    if(!params)
        params = {};

    params['toolbarSet'] = "Mini";

    WE.util.addEditor(id, params);
}
WE.util.addEditor = function(id, params)
{
    if(YAHOO.util.Dom.get(id))
    {
        var weEditor = new WE.util.HTMLEditor(id, params);
        weEditor.ReplaceTextarea();
    }
}
