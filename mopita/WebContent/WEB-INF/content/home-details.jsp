<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<% response.setHeader("remember", "true"); %>
<!DOCTYPE HTML>
<html>
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
				font-family:girl;			
			}
		</style>
		<script>
			$(function() {
				/**
				var language = navigator.language;
				if(!language){
					language = navigator.browserLanguage;
				}

				language = language.toLowerCase();
				alert(language);
				*/
				if('${language}'=='ZH'){
					$("#download").attr("src","${ctx}/images/dt.png");
					$("#gohome").attr("src","${ctx}/images/dhome.png");
					$("#more").attr("src","${ctx}/images/more.png");
				}else{
					$("#download").attr("src","${ctx}/images/en/dt.png");
					$("#gohome").attr("src","${ctx}/images/en/dhome.png");
					$("#more").attr("src","${ctx}/images/en/more.png");
				}
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
						<img id="download" alt="download" src="${ctx}/images/dt.png">
					</a>
				</div>
				<div  class="contents_txt">
					<div style="float: left; width: 50%; margin-bottom: 15px;margin-top: 15px;">
						<s:text name="home.author"/> : ${info.author}
					</div>				
					<div style="float:right;  width: 50%;margin-bottom: 15px;margin-top: 15px;">
						<s:text name="home.size"/> : ${fn:substring(info.theme.apkSize/1024/1024, 0, 4)}M
						
					</div>
				</div>
				<div class="contents_txt" id="desc" style="line-height: 130%;">
					<s:text name="home.desc"/> : ${info.longDescription}					
				</div>		
				<div style="float: right;margin: 15px;">
					<a href="home.action?${queryString}"><img id="gohome" alt="gohome" src="${ctx}/images/dhome.png"></a>
				</div>	
				<div class="category">
					<div class="title_bar">
						<s:text name="home.category" >
							<s:param name="category" value="categoryName"/>
						</s:text>
					</div>
					<div class="icon_set">
						<s:iterator value="catePage.result">
							
							<a href="${ctx}/home!details.action?id=${theme.id}&queryString=${queryString}">
								<img alt="${theme.title}" onerror="this.src='${ctx}/images/default.png'" src="${ctx}/image.action?path=${theme.iconPath}" style="margin: 2px;" width="72" height="72" class="icon"/>
							</a>
							
						</s:iterator>
						
						<a href="${ctx}/home!more.action?cid=${info.theme.categories[0].id}&queryString=${queryString}">							
							<img id="more" alt="更多" src="${ctx}/images/more.png" width="72" height="72" style="margin: 2px;" class="icon" />
						</a>
					</div>
				</div>
				
				<%@include file="/common/footer.jsp" %>

			</div>

		</form>
	
	</body>
</html>