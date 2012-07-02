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
		<title>details</title>
  		<link rel="stylesheet" href="${ctx}/css/details.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/home.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.lazyload.min.js"></script>
		<style type="text/css">
			body{
				margin: 0;
				padding: 0;
						
			}
		</style>
		<script>
			$(function() {
				$("img").lazyload();
				
				if('${language}'=='zh'){
					
					$("#gohome").attr("src","${ctx}/images/dhome.png");
					$("#more").attr("src","${ctx}/images/more.png");
				}else{
				
					$("#gohome").attr("src","${ctx}/images/en/dhome.png");
					$("#more").attr("src","${ctx}/images/en/more.png");
				}
				$("#downfree").click(function(){
					$.ajax({
						type:"POST",
						url:"${ctx}/log/log!saveDownload.action",
						dataType:"text",
						data:{queryString:'${info.theme.downloadURL}',cs:'${queryString}'}
					});
					location.href='${info.theme.downloadURL}';
					
				});
	        })

		</script>
	</head>
	<body>
	
		<form action="jplocker!details.action" method="get">
			<div> 
				<div class="title_bar">
					${info.title}
				</div>
				<div class="contents_img">
					<img alt="${info.title}" src="${ctx}/image.action?path=${info.theme.preWebPath}" width="180" height="300">
				</div>
				
				<div class="btnTop">
					<a href="#" id="downfree">
						<s:if test="info.price==null">
							FREE
						</s:if>
						<s:else>
							PAID
						</s:else>
					</a>
				</div>
				<div  class="contents_txt">
					<div style="width: 50%; margin-bottom: 15px;margin-top: 15px;">
						<s:text name="home.author"/> : ${info.author}
					</div>				
					
				</div>
				<div class="contents_txt" id="desc" style="line-height: 130%;">
					<s:text name="home.desc"/> : ${info.longDescription}					
				</div>		
				<div style="float: right;margin: 15px;">
					<a href="${ctx}/store/jplocker.action?${queryString}"><img id="gohome" alt="gohome" src="${ctx}/images/dhome.png"></a>
				</div>	
				<div class="category">
					<div class="title_bar">
						<s:text name="home.category" >
							<s:param name="category" value="categoryName"/>
						</s:text>
					</div>
					<div class="icon_set">
						<s:iterator value="catePage.result">
							
							<a href="${ctx}/store/jplocker!details.action?id=${theme.id}&${queryString}">
								<img alt="${theme.title}" data-original="${ctx}/image.action?path=${theme.iconPath}" src="${ctx}/images/default.png" style="margin: 2px;" width="72" height="72" class="icon"/>
							</a>
							
						</s:iterator>
						
						<a href="${ctx}/store/jplocker!more.action?cid=${info.theme.categories[0].id}&${queryString}">							
							<img id="more" alt="更多" src="${ctx}/images/more.png" width="72" height="72" style="margin: 2px;" class="icon" />
						</a>
					</div>
				</div>
				
				<%@include file="/common/jp-footer.jsp" %>

			</div>

		</form>
	
	</body>
</html>