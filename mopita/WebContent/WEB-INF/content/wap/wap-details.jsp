<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
	<head>
	 	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=2.0; user-scalable=0;">
	 	<meta name="apple-mobile-web-app-capable" content="yes"> 
	  	<meta name="apple-mobile-web-app-status-bar-style" content="black">  
	  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
 
		<title>主题下载</title>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/application.js"></script>
		<script src="${ctx}/js/jquery/jquery.masonry.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.infinitescroll.min.js"></script>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen" />
  		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen" />
	</head>
	<body>
		<div id="natigater" class="navigater">
			<s:iterator value="categories">
				<div class="col_4 navitem ">
	     			<a href="wap.action?categoryId=${id}" >${name}</a>
		  		</div>
			</s:iterator>
		</div>
		<div id="divallimg">
			<s:iterator value="previews">
				<div>${ctx}<s:property/></div>
			</s:iterator>
		</div>
		
		<div style="border-top:0px; line-height:30px">
   			<div class="divthemeitem"></div>
		</div>
		
		<div class="theme_img">
          <img src="${ctx}${previews[0]}" id="themeimg" onload="fixImage(this);">
		</div>
		<div id="divimginfo">
          <div id="divimgsize"></div>
          <div style="position:absolute; z-index:2; text-align:center;width:100%;">
          	<div class="divthemeitem">
            	<div id="divimgpageplayprev" class="navitem2" style="width:50px" onclick="playimgprev();">pre</div>
                <div id="divimgpageplaynext" class="navitem2" style="width:50px;margin-left:15px" onclick="playimgnext();">next</div>
            </div>
          </div>
    	</div>
    	<div class="divthemeitem" style="line-height:30px; ">
		<div class="down navitem" style="width:200px">
		<a href="${theme.marketURL}">Market Download</a>      </div> 
		</div>
		<div id="divinfo" class="">
		        <div class="theme" style="BORDER-TOP: #ddd 1px solid;"> <span class="theme_span"> Author：</span>yu_wings</div>
		        <div class="theme"><span class="theme_span">Catalog：</span>
		        <s:iterator value="theme.categories">
		        	<a href="wap.action?categoryId=${id}">${name}</a>
		        </s:iterator>
		</div>		
		  <div class="theme"> 
		  	<span class="theme_span">Desc: </span>
		  		
		  </div>
		
		</div>
	</body>
	<script>
		if (imglink == null) {
		    imglink = new Array();
		    var j=0;
		    var imgs = document.getElementById("divallimg").childNodes;
		    for (var i = 0; i < imgs.length; i++) {
		    	if(imgs[i].nodeType==1){
		    		
		    		imglink[j++] = imgs.item(i).innerHTML;
		    	}
		    }
		}
		document.getElementById("divimgsize").innerHTML = "Preview(" + imglink.length + "-" + (imgnowindex + 1) + ")";
	</script>
	<script>
	function fixImage(image) {
                $image = $(image);
                var fixWidth = 138;
                var fixHeight = 230;
                var flag = image.offsetWidth / image.offsetHeight > fixWidth / fixHeight;
                var width;
                var height;
                if (flag) {
                        width = image.offsetWidth * fixHeight / image.offsetHeight;
                        height = fixHeight;
                } else {
                        width = fixWidth;
                        height = image.offsetHeight * fixWidth / image.offsetWidth;
                }
                image.style.width = width + "px";
                image.style.height = height + "px";
                if (flag) {
                        image.parentNode.scrollLeft = (width - fixWidth) / 2;
                } else {
                        image.parentNode.scrollTop = (height - fixHeight) / 2;
                }
        }
</script>
</html>	