<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html lang="ja">
	<head>
    	<meta charset="utf-8">
	 	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=2.0; user-scalable=0;">
	 	<meta name="apple-mobile-web-app-capable" content="yes"> 
	  	<meta name="apple-mobile-web-app-status-bar-style" content="black">  
	  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

		<title>商店首页</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/top.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/reset.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/layout.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.lazyload.min.js"></script>

		<script>

			$(document).ready(function(){

				$("img").lazyload();

				$("#content1").live("click",function(){ 

					$(this).css("backgroundColor","#e7e6c8");

				});

			});

		</script>

	</head>

	<body>

		<form action="home.action" method="get">

			<div id="container"> 		
				<s:if test="adFile!=null">		
					 <div class="imgCenter">
						<s:if test="adFile!=null">
							<a href="${ctx}/home!details.action?id=${adFile.id}&${queryString}">
								<img alt="${adFile.title}" src="${ctx}/image.action?path=${adFile.adPath}" class="max-width_100">
							</a>
						</s:if><s:else>商店无内容</s:else>
					</div>
				  </s:if>
		  
				<h1 class="app-title">最新アプリ</h1>		
				<s:iterator value="newestPage.result">
					<div class="contents_info" id="content1" onclick="location.href='${ctx}/home!details.action?id=${theme.id}&${queryString}';">			
						<div class="contents_txt">
							<div style="margin-top: 10px;">
								<font color="#666666">${title}</font>
								<p><font color="#aeaea6">${shortDescription}</font></p>
								 <div class="icon-paid">FREE</div>
							</div>
						</div>
						<div class="contents_image">						
							<img style="margin: 3px;" alt="${title}" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" width="72" height="72" class="contents_image_middle">						
						</div>
					</div>								
				</s:iterator>		
				<div class="icon_set">
				<h1 class="app-title">アプリ一覧</h1>	
					<s:iterator value="hottestPage.result">				
						<a href="${ctx}/home!details.action?id=${theme.id}&${queryString}">
							<img alt="${title}" style="padding: 1px;" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" class="icon" width="72" height="72">
						</a>		
					</s:iterator>
				</div>			

				</div>

				<div style="font-size: 85%;">

					



	<div style="text-align: center; width: 98%;line-height: 130% ;margin-bottom: 20px; background:rgb(255,93,177);">

		<br>

		&copy; Funlocker

		<br>

		<font>Copyright 2012 </font>

		<br/>

		&nbsp;&nbsp;&nbsp;<font>by TPAD.</font>

	</div>

				 </div>

			</div>



		</form>

	

	</body>

</html>