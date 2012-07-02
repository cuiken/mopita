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

		<title>home</title>
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
					var id=$(this).attr("sid");
					location.href="${ctx}/store/jplocker!details.action?id="+id+"&${queryString}";
					return false;
				});
				$("#btn_down").live("click",function(){ 
					var uri=$(this).attr("pay");
					$.ajax({
						type:"POST",
						url:"${ctx}/log/log!saveDownload.action",
						dataType:"text",
						data:{queryString:uri,cs:'${queryString}'}
					});
					location.href=uri;
					return false;
				});
				
			});
		</script>
	</head>
	<body>
		<form action="jplocker.action" method="get">

			<div id="container"> 		
				<s:if test="adFile!=null">		
					 <div class="imgCenter">
						<a href="${ctx}/store/jplocker!details.action?id=${adFile.id}&${queryString}">
							<img alt="${adFile.title}" src="${ctx}/image.action?path=${adFile.adPath}" class="max-width_100">
						</a>
					</div>
				  </s:if>
		  
				<h1 class="app-title">最新アプリ</h1>		
				<s:iterator value="newestPage.result">
					<div class="contents_info" style="height: 105px;" id="content1" sid="${theme.id}">			
						<div class="contents_txt" style="height: 100px;">
							<div style="margin-top: 15px;">
								<font color="#666666">${title}</font>
								<p><font color="#aeaea6">${shortDescription}</font></p>
									<s:if test="price==null">
								 <div class="icon-paid" id="btn_down" pay="${theme.downloadURL}">
								 
								 		FREE
								 	
								 </div>
								 </s:if>
							</div>
						</div>
						<div class="contents_image" style="height: 100px;">						
							<img style="margin: 3px;margin-top: 16px;" alt="${title}" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" width="72" height="72" class="contents_image_middle">						
						</div>
					</div>								
				</s:iterator>		
				<h1 class="app-title">アプリ一覧</h1>
				<div class="icon_set">	
					<s:iterator value="hottestPage.result">				
						<a href="${ctx}/store/jplocker!details.action?id=${theme.id}&${queryString}">
							<img alt="${title}" style="padding: 1px;" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" class="icon" width="72" height="72">
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