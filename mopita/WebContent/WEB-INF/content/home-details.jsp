<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<% response.setHeader("remember", "true"); %>
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
  		<link rel="stylesheet" href="${ctx}/css/home.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<style type="text/css">
			body{
				margin: 0;
				padding: 0;
				font-family:DFPShaoNvW;			
			}
		</style>
		<script>
		$(function() {
            var $link = $("#desc a");
            var $hide = $("#more");
            var $symbol= $("#symbol");
            $link.click(function() {
                if ($("#silder").attr("alt") == "更多") {
                    $(this).html("<img id='silder' alt='收起' src='${ctx}/images/up.png'>");
                    $hide.show();
                    $symbol.hide();
                } else {
                    $(this).html("<img id='silder' alt='更多' src='${ctx}/images/down.png'>");
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
					${info.title}
				</div>
				<div class="contents_img">
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.preWebPath}" width="180" height="300">
				</div>
				<div class="short_des">
					<p>${info.shortDescription}</p>
				</div>
				<div align="center">
					<a href="${info.theme.downloadURL}">
						<img alt="下载" src="${ctx}/images/dt.png">
					</a>
				</div>
				<div  class="contents_txt">
					<div style="float: left; width: 50%; margin-bottom: 15px;margin-top: 15px;">
						设计师:${info.author}
					</div>				
					<div style="float:right;  width: 50%;margin-bottom: 15px;margin-top: 15px;">
						大小: ${fn:substring(info.theme.apkSize/1024/1024, 0, 4)}M
						
					</div>
				</div>
				<div class="contents_txt" id="desc" style="line-height: 120%;">
					简介:${info.longDescription}					
				</div>		
				<div style="float: right;margin: 10px;">
					<a href="home.action?${queryString}"><img alt="gohome" src="${ctx}/images/dhome.png"></a>
				</div>	
				<div class="category">
					<div class="title_bar">${info.theme.categories[0].name}系列</div>
					<div class="icon_set">
						<s:iterator value="catePage.result">
							
							<a href="${ctx}/home!details.action?id=${theme.id}&queryString=${queryString}">
								<img alt="${theme.title}" src="${ctx}/image.action?path=${theme.iconPath}" style="margin: 2px;" width="72" height="72" class="icon"/>
							</a>
							
						</s:iterator>
						
						<a href="${ctx}/home!more.action?cid=${info.theme.categories[0].id}&queryString=${queryString}">							
							<img alt="更多" src="${ctx}/images/more.png" width="72" height="72" style="margin: 2px;" class="icon" />
						</a>
					</div>
				</div>
				<%@include file="/common/footer.jsp" %>
			</div>

		</form>
	
	</body>
</html>