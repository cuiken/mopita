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
 
		<title>商店更多</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/mobile.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.masonry.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.infinitescroll.min.js"></script>
  		<script>
		  $(function(){
		
		    var $container = $('#container');
		    
		    $container.imagesLoaded(function(){
		      $container.masonry({
		        itemSelector: '.box',
		        columnWidth: 100
		      });
		    });
		    
		    $container.infinitescroll({
		      navSelector  : '#page-nav',    
		      nextSelector : '#page-nav a',  
		      itemSelector : '.box',     
		      loading: {
		          finishedMsg: 'No more pages to load.',
		          img: 'http://i.imgur.com/6RMhx.gif'
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
		<div id="navigater" class="navigater">
			<s:iterator value="categories">
				<div class="col_4 navitem ">
	     			<a href="home!more.action?cid=${id}" >${name}</a>
		  		</div>
			</s:iterator>
	   	</div>		
		<div class="float_clear"></div>
		<div class="lineBottom"></div>

		
		<form action="home.action" method="get">
			<div id="container" class="transitions-enabled infinite-scroll clearfix"> 
				<s:iterator value="hottestPage.result">
					<div class="box col1">
						<div>
							<a href="home!details.action?id=${id}">
								<img alt="${title}" src="${ctx}/image.action?path=${iconPath}">
							</a>
						</div>
						<div class="themeDesc">
							${title}
						</div>
					</div>
				</s:iterator>
			</div>
			<nav id="page-nav">
  				<a href="?hottestPage.pageNo=${hottestPage.nextPage}&cid=${categoryId}"></a>
			</nav>
		</form>
	
	</body>
</html>