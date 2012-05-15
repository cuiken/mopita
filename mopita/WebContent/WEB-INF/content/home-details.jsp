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
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	</head>
	<body>
	
		<form action="home!details.action" method="get">
			<div id="container"> 
				<div>${info.title}</div>
				<div>
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.preWebPath}">
				</div>
				<div>
					${info.shortDescription}
				</div>
				<div><a href="${ctx}/home!down.action?path=${info.theme.apkPath}">下载</a></div>
				<div>
					${info.longDescription}
				</div>
				<div>
				${info.theme.categories[0].name}系列
				<s:iterator value="hottestPage.result">
					<div>
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.iconPath}">
				</div>
				</s:iterator>
				</div>
			</div>

		</form>
	
	</body>
</html>