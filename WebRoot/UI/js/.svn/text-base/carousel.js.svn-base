Core.widget.Carousel = (function() {
	var Dom = Core.Dom;
	var Event = Core.Event;
	var Builder = Core.Builder;
	// Download by http://www.codefans.net
	var samplesContainer = Dom.get('carousel_container');
	var samplesList = Dom.get('samples_list');
	var photoContainer = Dom.get('carousel_photo_container');
	var photo = Dom.get('carousel_photo');
	var photoIntro = Dom.get('carousel_photo_intro');
	var BtnLastPhoto = Dom.get('carousel_btn_lastpic');
	var BtnNextPhoto = Dom.get('carousel_btn_nextpic');
	var lastDiv=Dom.get("left");
	var nextDiv=Dom.get("right");

	var samplesItems = samplesList.getElementsByTagName('a');
	var lisItems = samplesList.getElementsByTagName('li');
	var i, len = samplesItems.length;
	var samplesItemWidth = 112;
	var oneScreenItemsNum = parseInt(samplesContainer.offsetWidth, 10)
			/ samplesItemWidth;
	var lastIndex = 0;
	var curIndex = 0;
	var movedGroups = 0;
	var groups = len < oneScreenItemsNum ? 0 : Math.ceil(len
			/ oneScreenItemsNum)
			- 1;
	var samplesLeft = 0;
	var isVisited = [];
	var samplePhotos = [];
	var imgPath = [];
	var imgAlt = [];
	var hrefRef = [];
	var valIndex=Dom.get('valIndex');
	var imgIndex=0;
	if(valIndex){
		imgIndex=valIndex.value;
	}
	var imgCount=0;
	var moveIndex=0;
	return {
		init : function() {
			var that = this, defaultPhoto = new Image();
			Dom.setStyle(samplesContainer, 'overflow', 'hidden');
			Dom.setStyle(samplesList, 'width', ((len * 112) + 'px'));
			for (i = 0; i < len; i += 1) {
				isVisited[i] = false;
				samplePhotos[i] = samplesItems[i].getElementsByTagName('img')[0];
				imgPath[i] = samplesItems[i].getAttribute('href');
				imgAlt[i] = samplesItems[i].getElementsByTagName('img')[0]
						.getAttribute('alt');
				hrefRef[i]=samplesItems[i].getAttribute('ref');
				Event.addListener(samplesItems[i], 'click', function(index) {
							return function(event) {
								var evt = event || window.event;
								curIndex = index;
								that.focusSample();
								that.chgPhoto();
								Event.stopEvent(evt);
							}
						}(i));
				Event.addListener(lisItems[i], 'click', function(index) {
							return function(event) {
								var evt = event || window.event;
								curIndex = index;
								that.focusSample();
								that.chgPhoto();
								Event.stopEvent(evt);
							}
						}(i));
			}
			imgCount=samplePhotos.length;
			defaultPhoto.src = photo.src;
			this.autoSize.call(defaultPhoto);
			var width = defaultPhoto.width;
			var height = defaultPhoto.height;
			var imgPercent = width / height;
			if (width > 800) {
				width = 800;
				height = (width / imgPercent);
			}
			if(parseInt(height)>500){
				photoContainer.style.height = height + 'px';
				lastDiv.style.height=height+'px';
				nextDiv.style.height=height+'px';
			}else{
				photoContainer.style.height = '500px';
				lastDiv.style.height='500px';
				nextDiv.style.height='500px';
			}
			Event.addListener(BtnLastPhoto, 'click', this.lastPhotos);
			Event.addListener(BtnNextPhoto, 'click', this.nextPhotos);
			Event.addListener(lastDiv, 'click', this.lastDivs);
			Event.addListener(nextDiv, 'click', this.nextDivs);
			moveIndex=parseInt(imgIndex/Math.ceil(oneScreenItemsNum));
			if(moveIndex!=0){
				movedGroups = moveIndex;
				Core.widget.Carousel.move(oneScreenItemsNum*moveIndex);
			}
		},
		lastPhotos : function(e) {
			if (groups) {
				var evt = e || window.event;
				movedGroups -= 1;
				if (movedGroups < 0) {
					movedGroups = groups;
					Core.widget.Carousel.move(oneScreenItemsNum * groups);
				} else {
					Core.widget.Carousel.move(-oneScreenItemsNum);
				}
				moveIndex=movedGroups;
				Event.stopEvent(evt);
			}
		},
		nextPhotos : function(e) {
			if (groups) {
				var evt = e || window.event;
				movedGroups += 1;
				if (movedGroups > groups) {
					movedGroups = 0;
					Core.widget.Carousel.move(-oneScreenItemsNum * groups);
				} else {
					Core.widget.Carousel.move(oneScreenItemsNum);
				}
				moveIndex=movedGroups;
				Event.stopEvent(evt);
			}
		},
		lastDivs:function(){
			imgIndex=parseInt(imgIndex);
			imgIndex=imgIndex-1;
			curIndex=imgIndex;
			
			var temp=parseInt(imgIndex/Math.ceil(oneScreenItemsNum));
			if(moveIndex!=temp){
				var tempIndex=moveIndex-temp;
				moveIndex=temp;
				movedGroups = temp;
				if (movedGroups <=0) {
					movedGroups = 0;
				}
				Core.widget.Carousel.move(-oneScreenItemsNum * tempIndex);
			}
			if(curIndex<0){
				alert("已经是第一张了");
				imgIndex=0;
				return;
			}
			Dom.setStyle(samplePhotos[lastIndex], 'opacity', 1);
			Dom.setStyle(samplePhotos[curIndex], 'opacity', .4);
			lastIndex = curIndex;
			var tempImage = new Image();
			var shardow = null;
			tempImage.src = imgPath[curIndex];
			jsonEntity=$.ajax({
				type:'POST',
				url:'photoJson!photoJson.action',
				data:'photoId='+hrefRef[curIndex],
				dataType:'json',
				complete:function(data){
					var jsonObj= eval("(" + data.responseText + ")");
					//照片ID
					$("#photoId").val(jsonObj.id);
					//查看原图
					$("#realPhotoUrl").attr('href',jsonObj.purl);
					//复制地址
					$("#copyUrl").attr("ref",jsonObj.purl);
					//删除照片
					$("#delPhoto").unbind("click");
					$("#delPhoto").removeAttr("onclick");
					$("#delPhoto").click(function(){
						$("#left").css("display","none");
						$("#right").css("display","none");
						$.messager.confirm('提示', '此删除操作不能撤回，是否确定删除？', function(r){
							if(r){
								MyMask.showHtml('请稍候...');
								location="dopdDel.action?photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&id="+$("#userId").val()+"&f=g";
								$("#left").css("display","block");
								$("#right").css("display","block");
							}else{
								$("#left").css("display","block");
								$("#right").css("display","block");
								return false;
							}
						});
					});
					//设置封面
					$("#cover").unbind("click");
					$("#cover").removeAttr("onclick");
					$("#cover").click(function(){
						location="dopdEdit.action?id="+$("#userId").val()+"&pid="+jsonObj.pid+"&photoId="+jsonObj.id+"&photocover=1&f=g";
					});
					//照片名称
					if(jsonObj.pname!='null'&&$.trim(jsonObj.pname)!=''){
						$("#photodetilName").val(jsonObj.pname);
					}else{
						$("#photodetilName").val("");
					}
					//照片描述
					if(jsonObj.pmemo!='null'&&$.trim(jsonObj.pmemo)!=''){
						$("#memoSpan").html(jsonObj.pmemo);
						$("#photodetilMemoValue").val(jsonObj.pmemo);
					}else{
						$("#memoSpan").html("点此输入照片描述");
						$("#photodetilMemoValue").val("");
					}
					//照片标签
					if(jsonObj.pflag!='null'&&$.trim(jsonObj.pflag)!=''){
						var tempFlag=jsonObj.pflag;
						$("#photodetilFlagText").val(tempFlag);
						var reg=new RegExp("\,","g");
						tempFlag=tempFlag.replace(reg,' ');
						$("#photoFlag").html(tempFlag);
					}else{
						$("#photoFlag").html("点此输入标签");
						$("#photodetilFlagText").val("");
					}
					//上传时间
					if(jsonObj.uploadTime!='null'&&$.trim(jsonObj.uploadTime)!=''){
						$("#uploadTime").html(jsonObj.uploadTime);
					}
					//刷新评论
					$("#mainComment").load("reloadDiv.action?id="+$("#userId").val()+"&photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&token="+document.getElementsByName("struts.token")[0].value);
				}
			});
			//样式
			var lid="li"+(imgIndex+1)
			var lis=document.getElementsByTagName("li");
			$("#"+lid).css("border","1px solid #6bc2f6");
			for(var i=0;i<lis.length;i++){
				var li=lis[i];
				if(li.id!=lid){
					$("#"+li.id).css("border","1px solid #999999");
				}
			}
			if (!isVisited[curIndex]) {
				photoContainer.appendChild(Builder.Node('div', {
							id : 'carousel_photo_shardow'
						}));
				shardow = Dom.get('carousel_photo_shardow');
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
				shardow.style.height = photoContainer.offsetHeight + 'px';
				photoContainer.appendChild(Builder.Node('img', {
							id : 'carousel_photo_loading',
							src : 'UI/images/photo/loading.gif',
							alt : 'loading',
							width:'37px',height:'37px'
						}));
				var loadImg=Dom.get("carousel_photo_loading");
				loadImg.style.width="37px";
				loadImg.style.height="37px";

			}else{
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
			}
			if (tempImage.complete) {// Mozllia
				//that.loadPhoto.call(tempImage);
				Core.widget.Carousel.autoSize.call(tempImage);
				photo.src = imgPath[curIndex];
				photoIntro.innerHTML = imgAlt[curIndex];
				isVisited[curIndex] = true;
				shardow = Dom.get('carousel_photo_shardow');
				loadingImg = Dom.get('carousel_photo_loading');
				if (shardow && loadingImg) {
					photoContainer.removeChild(shardow);
					photoContainer.removeChild(loadingImg);
				}
			} else {// IE
				Event.addListener(tempImage, 'load', function() {
							//that.loadPhoto.call(tempImage);
							Core.widget.Carousel.autoSize.call(tempImage);
							photo.src = imgPath[curIndex];
							photoIntro.innerHTML = imgAlt[curIndex];
							isVisited[curIndex] = true;
							shardow = Dom.get('carousel_photo_shardow');
							loadingImg = Dom.get('carousel_photo_loading');
							if (shardow && loadingImg) {
								photoContainer.removeChild(shardow);
								photoContainer.removeChild(loadingImg);
							}
						});
			}
		},
		nextDivs:function(e){
			var evt = e || window.event;
			imgIndex=parseInt(imgIndex);
			imgIndex=imgIndex+1;
			curIndex=imgIndex;
			
			var temp=parseInt(imgIndex/Math.ceil(oneScreenItemsNum));
			if(moveIndex!=temp){
				var tempIndex=temp-moveIndex;
				moveIndex=temp;
				movedGroups = temp;
				if (movedGroups > groups) {
					movedGroups = groups;
				}
				Core.widget.Carousel.move(oneScreenItemsNum*tempIndex);
			}
			if(curIndex>=imgCount){
				alert("已经是最后一张了");
				imgIndex=imgCount-1;
				return;
			}
			//this.focusSample;
			//this.chgPhoto;
			//Event.stopEvent(evt);
			Dom.setStyle(samplePhotos[lastIndex], 'opacity', 1);
			Dom.setStyle(samplePhotos[curIndex], 'opacity', .4);
			lastIndex = curIndex;
			var tempImage = new Image();
			var shardow = null;
			tempImage.src = imgPath[curIndex];
			jsonEntity=$.ajax({
				type:'POST',
				url:'photoJson!photoJson.action',
				data:'photoId='+hrefRef[curIndex],
				dataType:'json',
				complete:function(data){
					var jsonObj= eval("(" + data.responseText + ")");
					//照片ID
					$("#photoId").val(jsonObj.id);
					//查看原图
					$("#realPhotoUrl").attr('href',jsonObj.purl);
					//复制地址
					$("#copyUrl").attr("ref",jsonObj.purl);
					//删除照片
					$("#delPhoto").unbind("click");
					$("#delPhoto").removeAttr("onclick");
					$("#delPhoto").click(function(){
						$("#left").css("display","none");
						$("#right").css("display","none");
						$.messager.confirm('提示', '此删除操作不能撤回，是否确定删除？', function(r){
							if(r){
								MyMask.showHtml('请稍候...');
								location="dopdDel.action?photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&id="+$("#userId").val()+"&f=g";
								$("#left").css("display","block");
								$("#right").css("display","block");
							}else{
								$("#left").css("display","block");
								$("#right").css("display","block");
								return false;
							}
						});
					});
					//设置封面
					$("#cover").unbind("click");
					$("#cover").removeAttr("onclick");
					$("#cover").click(function(){
						location="dopdEdit.action?id="+$("#userId").val()+"&pid="+jsonObj.pid+"&photoId="+jsonObj.id+"&photocover=1&f=g";
					});
					//照片名称
					if(jsonObj.pname!='null'&&$.trim(jsonObj.pname)!=''){
						$("#photodetilName").val(jsonObj.pname);
					}else{
						$("#photodetilName").val("");
					}
					//照片描述
					if(jsonObj.pmemo!='null'&&$.trim(jsonObj.pmemo)!=''){
						$("#memoSpan").html(jsonObj.pmemo);
						$("#photodetilMemoValue").val(jsonObj.pmemo);
					}else{
						$("#memoSpan").html("点此输入照片描述");
						$("#photodetilMemoValue").val("");
					}
					//照片标签
					if(jsonObj.pflag!='null'&&$.trim(jsonObj.pflag)!=''){
						var tempFlag=jsonObj.pflag;
						$("#photodetilFlagText").val(tempFlag);
						var reg=new RegExp("\,","g");
						tempFlag=tempFlag.replace(reg,' ');
						$("#photoFlag").html(tempFlag);
					}else{
						$("#photoFlag").html("点此输入标签");
						$("#photodetilFlagText").val("");
					}
					//上传时间
					if(jsonObj.uploadTime!='null'&&$.trim(jsonObj.uploadTime)!=''){
						$("#uploadTime").html(jsonObj.uploadTime);
					}
					//刷新评论
					$("#mainComment").load("reloadDiv.action?id="+$("#userId").val()+"&photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&token="+document.getElementsByName("struts.token")[0].value);
				}
			});
			//样式
			var lid="li"+(imgIndex+1)
			var lis=document.getElementsByTagName("li");
			$("#"+lid).css("border","1px solid #6bc2f6");
			for(var i=0;i<lis.length;i++){
				var li=lis[i];
				if(li.id!=lid){
					$("#"+li.id).css("border","1px solid #999999");
				}
			}
			if (!isVisited[curIndex]) {
				photoContainer.appendChild(Builder.Node('div', {
							id : 'carousel_photo_shardow'
						}));
				shardow = Dom.get('carousel_photo_shardow');
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
				shardow.style.height = photoContainer.offsetHeight + 'px';
				photoContainer.appendChild(Builder.Node('img', {
							id : 'carousel_photo_loading',
							src : 'UI/images/photo/loading.gif',
							alt : 'loading',
							width:'37px',height:'37px'
						}));
				var loadImg=Dom.get("carousel_photo_loading");
				loadImg.style.width="37px";
				loadImg.style.height="37px";

			}else{
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
			}
			if (tempImage.complete) {// Mozllia
				//that.loadPhoto.call(tempImage);
				Core.widget.Carousel.autoSize.call(tempImage);
				photo.src = imgPath[curIndex];
				photoIntro.innerHTML = imgAlt[curIndex];
				isVisited[curIndex] = true;
				shardow = Dom.get('carousel_photo_shardow');
				loadingImg = Dom.get('carousel_photo_loading');
				if (shardow && loadingImg) {
					photoContainer.removeChild(shardow);
					photoContainer.removeChild(loadingImg);
				}
			} else {// IE
				Event.addListener(tempImage, 'load', function() {
							//that.loadPhoto.call(tempImage);
							Core.widget.Carousel.autoSize.call(tempImage);
							photo.src = imgPath[curIndex];
							photoIntro.innerHTML = imgAlt[curIndex];
							isVisited[curIndex] = true;
							shardow = Dom.get('carousel_photo_shardow');
							loadingImg = Dom.get('carousel_photo_loading');
							if (shardow && loadingImg) {
								photoContainer.removeChild(shardow);
								photoContainer.removeChild(loadingImg);
							}
						});
			}
		},
		move : function(moveSteps) {
			var left = 0;
			var sLeft = (samplesItemWidth * moveSteps);
			var timer = null;
			var scroll = function() {
				if (timer) {
					clearTimeout(timer);
				}
				if (sLeft > 0) {
					left += 33.6;
					if (left > sLeft) {
						if (Core.lang.isMoz) {
							samplesLeft += sLeft;
							samplesContainer.scrollLeft = samplesLeft;
						} else {
							samplesLeft -= sLeft;
							Dom.setStyle(samplesList, 'left',
									(samplesLeft + 'px'));
						}
						return false;
					} else {
						if (Core.lang.isMoz) {
							samplesContainer.scrollLeft = samplesLeft + left;
						} else {
							Dom.setStyle(samplesList, 'left', (samplesLeft
											- left + 'px'));
						}
					}
				} else {
					left -= 33.6;
					if (left < sLeft) {
						if (Core.lang.isMoz) {
							samplesLeft += sLeft;
							samplesContainer.scrollLeft = samplesLeft;
						} else {
							samplesLeft -= sLeft;
							Dom.setStyle(samplesList, 'left',
									(samplesLeft + 'px'));
						}
						return false;
					} else {
						if (Core.lang.isMoz) {
							samplesContainer.scrollLeft = samplesLeft + left;
						} else {
							Dom.setStyle(samplesList, 'left', (samplesLeft
											- left + 'px'));
						}
					}
				}
				timer = setTimeout(scroll, 5);
			};
			scroll();
		},
		focusSample : function() {
			Dom.setStyle(samplePhotos[lastIndex], 'opacity', 1);
			Dom.setStyle(samplePhotos[curIndex], 'opacity', .4);
			lastIndex = curIndex;
		},
		chgPhoto : function() {
			var that = this;
			var tempImage = new Image();
			var shardow = null;
			imgIndex=curIndex;
			tempImage.src = imgPath[curIndex];
			jsonEntity=$.ajax({
				type:'POST',
				url:'photoJson!photoJson.action',
				data:'photoId='+hrefRef[curIndex],
				dataType:'json',
				complete:function(data){
					var jsonObj= eval("(" + data.responseText + ")");
					//照片ID
					$("#photoId").val(jsonObj.id);
					//查看原图
					$("#realPhotoUrl").attr('href',jsonObj.purl);
					//复制地址
					$("#copyUrl").attr("ref",jsonObj.purl);
					//删除照片
					$("#delPhoto").unbind("click");
					$("#delPhoto").removeAttr("onclick");
					$("#delPhoto").click(function(){
						$("#left").css("display","none");
						$("#right").css("display","none");
						$.messager.confirm('提示', '此删除操作不能撤回，是否确定删除？', function(r){
							if(r){
								MyMask.showHtml('请稍候...');
								location="dopdDel.action?photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&id="+$("#userId").val()+"&f=g";
								$("#left").css("display","block");
								$("#right").css("display","block");
							}else{
								$("#left").css("display","block");
								$("#right").css("display","block");
								return false;
							}
						});
					});
					//设置封面
					$("#cover").unbind("click");
					$("#cover").removeAttr("onclick");
					$("#cover").click(function(){
						location="dopdEdit.action?id="+$("#userId").val()+"&pid="+jsonObj.pid+"&photoId="+jsonObj.id+"&photocover=1&f=g";
					});
					//照片名称
					if(jsonObj.pname!='null'&&$.trim(jsonObj.pname)!=''){
						$("#photodetilName").val(jsonObj.pname);
					}else{
						$("#photodetilName").val("");
					}
					//照片描述
					if(jsonObj.pmemo!='null'&&$.trim(jsonObj.pmemo)!=''){
						$("#memoSpan").html(jsonObj.pmemo);
						$("#photodetilMemoValue").val(jsonObj.pmemo);
					}else{
						$("#memoSpan").html("点此输入照片描述");
						$("#photodetilMemoValue").val("");
					}
					//照片标签
					if(jsonObj.pflag!='null'&&$.trim(jsonObj.pflag)!=''){
						var tempFlag=jsonObj.pflag;
						$("#photodetilFlagText").val(tempFlag);
						var reg=new RegExp("\,","g");
						tempFlag=tempFlag.replace(reg,' ');
						$("#photoFlag").html(tempFlag);
					}else{
						$("#photoFlag").html("点此输入标签");
						$("#photodetilFlagText").val("");
					}
					//上传时间
					if(jsonObj.uploadTime!='null'&&$.trim(jsonObj.uploadTime)!=''){
						$("#uploadTime").html(jsonObj.uploadTime);
					}
					//刷新评论
					$("#mainComment").load("reloadDiv.action?id="+$("#userId").val()+"&photoId="+jsonObj.id+"&pid="+jsonObj.pid+"&token="+document.getElementsByName("struts.token")[0].value);
				}
			});
			if (!isVisited[curIndex]) {
				photoContainer.appendChild(Builder.Node('div', {
							id : 'carousel_photo_shardow'
						}));
				shardow = Dom.get('carousel_photo_shardow');
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
				shardow.style.height = photoContainer.offsetHeight + 'px';
				photoContainer.appendChild(Builder.Node('img', {
							id : 'carousel_photo_loading',
							src : 'UI/images/photo/loading.gif',
							alt : 'loading',
							width:'37px',height:'37px'
						}));
				var loadImg=Dom.get("carousel_photo_loading");
				loadImg.style.width="37px";
				loadImg.style.height="37px";
			}else{
				var width = tempImage.width;
				var height = tempImage.height;
				var imgPercent = width / height;
				if (width > 800) {
					width = 800;
					height = (width / imgPercent);
				}
				if(parseInt(height)>500){
					photoContainer.style.height = height + 'px';
					lastDiv.style.height=height+'px';
					nextDiv.style.height=height+'px';
				}else{
					photoContainer.style.height = '500px';
					lastDiv.style.height='500px';
					nextDiv.style.height='500px';
				}
			}
			if (tempImage.complete) {// Mozllia
				that.loadPhoto.call(tempImage);
			} else {// IE
				Event.addListener(tempImage, 'load', function() {
							that.loadPhoto.call(tempImage);
						});
			}
		},
		loadPhoto : function() {
			Core.widget.Carousel.autoSize.call(this);
			photo.src = imgPath[curIndex];
			photoIntro.innerHTML = imgAlt[curIndex];
			isVisited[curIndex] = true;
			shardow = Dom.get('carousel_photo_shardow');
			loadingImg = Dom.get('carousel_photo_loading');
			if (shardow && loadingImg) {
				photoContainer.removeChild(shardow);
				photoContainer.removeChild(loadingImg);
			}
		},
		autoSize : function() {
			var width = this.width;
			var height = this.height;
			imgPercent = width / height;
			if (width > 800) {
				width = 800;
				height = (width / imgPercent);
			}
			Dom.setStyles(photo, {
						width : width + 'px',
						height : height + 'px'
					});
		}
	}
})();
Core.widget.Carousel.init();
// -->
