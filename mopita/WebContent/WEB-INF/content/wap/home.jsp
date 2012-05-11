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
 
		<title>商店首页</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
  		<script>
		  $(function(){
		
		    var $container = $('#container');
		    
		  
		  });
	</script>
	</head>
	<body>
	
		<form action="wap.action" method="get">
			<div id="container"> 
				<s:iterator value="page.result">
					<div class="box col1">
						<div>
							<a href="wap!details.action?id=${id}"><img alt="${title}" src="${ctx}${previewURL}"></a>
						</div>
						<div class="themeDesc">
							${title}
						</div>
					</div>
				</s:iterator>
			</div>

		</form>
	
	</body>
</html>