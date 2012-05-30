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
		<title>商店详细</title>
  		<link rel="stylesheet" href="${ctx}/css/details.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
		$(function() {
            var $link = $("#desc a");
            var $hide = $("#more");
            var $symbol= $("#symbol");
            $link.click(function() {
                if ($(this).html() == "更多") {
                    $(this).html("收起");
                    $hide.show();
                    $symbol.hide();
                } else {
                    $(this).html("更多");
                    $hide.hide();
                    $symbol.show();
                }
            })
        })

		</script>
	</head>
	<body>
	
		<form action="home!details.action" method="get">
			<div> 
				<div class="title_bar">
					<p>${info.title}</p>
				</div>
				<div class="contents_img">
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.preWebPath}" width="180" height="256">
				</div>
				<div class="short_des">
					<p>${info.shortDescription}</p>
				</div>
				<div class="btnTop">
					<a href="${info.theme.downloadURL}">下载</a>
				</div>
				<div class="contents_txt">
					设计师:${info.author}
				</div>
				<div class="contents_txt">
					大小: ${fn:substring(info.theme.apkSize/1024/1024, 0, 4)}M
					
				</div>
				<div class="contents_txt" id="desc">
					简介:
					<c:choose>
						<c:when test="${fn:length(info.longDescription)>70}">
							<span>${fn:substring(info.longDescription,0,70)}</span>
							<span id="more" style="display: none;">${fn:substring(info.longDescription,70,-1)}</span>
							<span id="symbol">....</span>
							 &nbsp;<a href="javascript:void(0)">更多</a>							
						</c:when>
						<c:otherwise>
							<span>${info.longDescription}</span>
						</c:otherwise>
					</c:choose>	
				</div>
				<div>
					<div class="title_bar">${info.theme.categories[0].name}系列</div>
					<div class="icon_set">
						<s:iterator value="catePage.result">
							
							<a href="${ctx}/home!details.action?id=${theme.id}">
								<img alt="${theme.title}" src="${ctx}/image.action?path=${theme.iconPath}" width="72" height="72" class="icon"/>
							</a>
							
						</s:iterator>
						
						<a href="${ctx}/home!more.action?cid=${info.theme.categories[0].id}">							
							<img alt="更多" src="${ctx}/images/more.png" width="72" height="72" class="icon" />
						</a>
					</div>
				</div>
			</div>

		</form>
	
	</body>
</html>