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
 		<%@include file="/common/script.jsp" %>
		<title>商店首页</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/top.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/reset.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	</head>
	<body>
	
		<form action="wap.action" method="get">
			<div id="container"> 				
				<div class="imgCenter">
					<s:if test="adFile!=null">
						<a href="${ctx}/home!details.action?id=${adFile.id}">
							<img alt="${adFile.title}" src="${ctx}/image.action?path=${adFile.adPath}" class="max-width_100">
						</a>
					</s:if><s:else>商店无内容</s:else>
				</div>
			
				<s:iterator value="newestPage.result">
					<div class="contents_info">
						<div class="contents_txt">
							<h2><a>${title}</a></h2>
							<p>${shortDescription}</p>
						</div>
						<div class="contents_image">
							<a href="${ctx}/home!details.action?id=${theme.id}">
								<img alt="${title}" src="${ctx}/image.action?path=${theme.iconPath}" width="100" height="100" class="contents_image_middle">
							</a>
						</div>
					</div>								
				</s:iterator>
				
				<div class="icon_set">
					<s:iterator value="hottestPage.result">				
						<a href="${ctx}/home!details.action?id=${theme.id}">
							<img alt="${title}" src="${ctx}/image.action?path=${theme.iconPath}" class="icon" width="72" height="72">
						</a>		
					</s:iterator>
				</div>
			</div>

		</form>
	
	</body>
</html>