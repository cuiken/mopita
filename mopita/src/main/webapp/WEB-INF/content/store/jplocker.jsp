<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html lang="ja">
	<head>
    	<meta charset="utf-8">
	 	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	 	<meta name="apple-mobile-web-app-capable" content="yes"> 
	  	<meta name="apple-mobile-web-app-status-bar-style" content="black">  
	  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

		<title>home</title>
		<link rel="stylesheet" href="${ctx}/css/jplocker/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/top.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/reset.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/mobile.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/layout.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.lazyload.min.js"></script>

		<script>
			$(document).ready(function(){
				$("img").lazyload();
				$("#content1").live("click",function(){ 
					$(this).css("backgroundColor","#e7e6c8");
					var uri=$(this).attr("pay");
					location.href=uri;
					$.ajax({
						type:"POST",
						url:"${ctx}/log/log!saveDownload.action",
						dataType:"text",
						data:{queryString:uri,cs:'${queryString}'}
					});				
				});
				
			});
		</script>
	</head>
	<body>
		<form action="jplocker.action" method="get">

			<div id="container"> 		
				<s:if test="recommendPage.result.size()>0">		
					 <div class="imgCenter">
						<a href="${recommendPage.result[0].theme.downloadURL}">
							<img alt="${recommendPage.result[0].theme.title}" src="${ctx}/image.action?path=${recommendPage.result[0].theme.adPath}" class="max-width_100">
						</a>
					</div>
				  </s:if>
		  
				<h1 class="app-title">おすすめロック画面アプリ</h1>		
				<s:iterator value="newestPage.result">
					<div class="contents_info" style="height: 80px;" id="content1" pay="${theme.downloadURL}">			
						<div class="contents_txt">
							<div class="content-title">
								<font color="#666666">${title}
									<s:if test="price==null">
										<span class="icon-free" id="btn_down">														 
											 FREE
										</span>
									</s:if>
								</font>
								<p><font color="#aeaea6">${shortDescription}</font></p>
							</div>
						</div>
						<div class="contents_image">						
							<img style="margin: 3px;" alt="${title}" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" width="72" height="72" class="contents_image_middle">						
						</div>
					</div>								
				</s:iterator>		
				<h1 class="app-title">ロック画面アプリ一覧</h1>
				<div class="icon_set">	
					<s:iterator value="hottestPage.result">				
						<a href="${theme.downloadURL}">
							<img alt="${title}" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" class="icon">
						</a>		
					</s:iterator>
				</div>						

				<div style="font-size: 85%;">
					<%@include file="/common/jp-footer.jsp" %>
				</div>
			</div>
		</form>
	</body>

</html>