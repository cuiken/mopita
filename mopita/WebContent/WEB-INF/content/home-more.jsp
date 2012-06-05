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
 
		<title>商店更多</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/top.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/reset.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/home.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.masonry.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.infinitescroll.min.js"></script>
		<style type="text/css">
			body{
				font-family: DFPShaoNvW;
			}
		</style>
  		<script>
		  $(function(){
			 $("#content1").live("click",function(){ 
				$(this).css("backgroundColor","#e7e6c8");
			});
		    var $container = $('#container');
		    
		    $container.imagesLoaded(function(){
		      $container.masonry({
		        itemSelector: '.contents_info',
		        columnWidth: 100
		      });
		    });
		    
		    $container.infinitescroll({
		      navSelector  : '#page-nav',    
		      nextSelector : '#page-nav a',  
		      itemSelector : '.contents_info',     
		      loading: {
		         	finishedMsg:'加载完成',
		         	msgText: "<em>加载中...</em>",
		        //  img: 'http://i.imgur.com/6RMhx.gif'
		        }
		      },
		     
		      function( newElements ) {  
		        var $newElems = $( newElements ).css({ opacity: 0 });    
		        $newElems.imagesLoaded(function(){        
		          $newElems.animate({ opacity: 1 });
		          $container.masonry( 'appended', $newElems, true ); 
		        });
		      }
		    );
		  
		  });
	</script>
	</head>
	<body>
	
		<form action="home.action" method="get">
			
			<div id="container" class="transitions-enabled infinite-scroll clearfix"> 
				<s:iterator value="catePage.result">
					<div class="contents_info" id="content1" onclick="location.href='home!details.action?id=${theme.id}&queryString=${queryString}';">
						<div class="contents_image">						
							<img alt="${title}" src="${ctx}/image.action?path=${theme.iconPath}" width="72" height="72" style="margin: 3px;">							
						</div>
						<div class="contents_txt">
							<br/>
							<font color="#666666">${title}</font>
							<p><font color="#aeaea6">${shortDescription}</font></p>
						</div>
					</div>
				</s:iterator>
			</div>
			<nav id="page-nav">
  				<a href="?catePage.pageNo=${catePage.nextPage}&cid=${categoryId}"></a>
			</nav>
			<div id="natigater" class="navigater_w">
		
			</div>
			<div class="guide" style="display: block; bottom: 0px;">
				<div>
					<div class="navigater">
						<s:iterator value="categories">
							<div class="col_4 navitem">
				     			<a href="home!more.action?cid=${id}&queryString=${queryString}" >${name}</a>
					  		</div>
						</s:iterator>
						<div class="col_4 navitem" style="float: right;">
							<a href="home.action?${queryString}">
								<img alt="gohome" src="${ctx}/images/home.png" height="28">
							</a>
						</div>	
					</div>
				</div>
			</div>
		</form>
	
	</body>
</html>