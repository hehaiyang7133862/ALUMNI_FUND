// 菜单伸缩
function switchLeft(t){
	var fs=parent.document.getElementById("manageSetId");
	var obj=document.getElementById("closeId");
	if(fs.cols=="3,10,*"){
		if(t==1){
			obj.alt="隐藏左栏";
			fs.cols="230,10,*";
			obj.src=glbRootPath+"UI/images/flex_open.gif";
		}
		else if(t==2){
			obj.src=glbRootPath+"UI/images/flex_open_on.png";
		}
		else if(t==3){
			obj.src=glbRootPath+"UI/images/flex_open.png";
		}
	}
	else{
		if(t==1){
			obj.alt="展开左栏";
			fs.cols="3,10,*";
			obj.src=glbRootPath+"UI/images/flex_close.png";
		}
		else if(t==2){
			obj.src=glbRootPath+"UI/images/flex_close_on.png";
		}
		else if(t==3){
			obj.src=glbRootPath+"UI/images/flex_close.png";
		}
	}
}
