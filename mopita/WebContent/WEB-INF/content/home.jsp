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
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	</head>
	<body>
	
		<form action="wap.action" method="get">
			<div id="container"> 
				
				<s:iterator value="recommendPage.result">
					<div class="col1">
						<a href="${ctx}/wap/wap!details.action?id=${id}">
							<img alt="${title}" src="${ctx}/image.action?path=${iconPath}">
						</a>
					</div>
				</s:iterator>
				
				<s:iterator value="newestPage.result">
					<div class="newest col2">

						<div>
							<a href="${ctx}/wap/wap!details.action?id=${id}">
								<img alt="${title}" src="${ctx}/image.action?path=${iconPath}">
							</a>
						</div>
						<div>						
							${title}				
							shortdes
						</div>
					</div>								
				</s:iterator>
				
				<s:iterator value="hottestPage.result">
					<div class="hottest col1">
						<a href="${ctx}/wap/wap!details.action?id=${id}">
							<img alt="${title}" src="${ctx}/image.action?path=${iconPath}">
						</a>
					</div>
				</s:iterator>
				
			</div>

		</form>
	
	</body>
</html>