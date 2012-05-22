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
		<title>商店详细</title>
  		<link rel="stylesheet" href="${ctx}/css/details.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jquery.mobile-1.1.0.min.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	</head>
	<body class="ui-mobile-viewport">
	
		<form action="home!details.action" method="get">
			<div> 
				<div class="title_bar">
					<p>${info.title}</p>
				</div>
				<div class="contents_img">
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.preWebPath}">
				</div>
				<div class="contents_txt">
					<p>${info.shortDescription}</p>
				</div>
				<div class="btnTop">
					<a href="${info.theme.downloadURL}">下载</a>
				</div>
				<div class="contents_txt">
					${info.longDescription}
				</div>
				<div>
					${info.theme.categories[0].name}系列
					<div class="icon_set">
						<s:iterator value="catePage.result">
							
							<a href="${ctx}/home!details.action?id=${theme.id}">
							<img alt="${theme.title}" src="${ctx}/image.action?path=${theme.iconPath}" width="72" height="72" class="icon">
							</a>
							
						</s:iterator>
						<a href="${ctx}/home!more.action?cid=${info.theme.categories[0].id}">							
							更多...
						</a>
						
					</div>
				</div>
			</div>

		</form>
	
	</body>
</html>