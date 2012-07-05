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
 
		<title>more</title>
		<link rel="stylesheet" href="${ctx}/css/jplocker/style.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/mobile.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/top.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/reset.css" media="screen"/>
  		<link rel="stylesheet" href="${ctx}/css/jplocker/layout.css" media="screen"/>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.masonry.min.js"></script>
		<script src="${ctx}/js/jquery/jquery.infinitescroll.min.js"></script>

  		<script>
		  $(function(){

				$("#${categoryId}").css("font-weight","900").css("font-size","130%");
				if('${language}'=='zh'){
					
					$("#gohome").attr("src","${ctx}/images/home.png");
					
				}else{
					
					$("#gohome").attr("src","${ctx}/images/en/home.png");
					
				};
			  
			 $("#content1").live("click",function(){ 
				$(this).css("backgroundColor","#e7e6c8");
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
		         	finishedMsg:"<s:text name='home.finishedMsg'/>",
		         	msgText: "<s:text name='home.msgText'/>",
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
	
		<form action="jplocker.action" method="get">
			
			<div id="container" class="transitions-enabled infinite-scroll clearfix"> 
				<s:iterator value="catePage.result">
					<div class="contents_info" style="height: 105px;" id="content1" onclick="location.href='jplocker!details.action?id=${theme.id}&${queryString}';">
						<div class="contents_image">						
							<img alt="${title}"  onerror="this.src='${ctx}/images/default.png'" src="${ctx}/image.action?path=${theme.iconPath}" width="72" height="72" style="margin: 3px;">							
						</div>
						<div class="contents_txt">
							<div class="content-title">
								<font color="#666666">${title}
									<s:if test="price==null">
										<span class="icon-free" id="btn_down" pay="${theme.downloadURL}">
											FREE
										</span>
									</s:if>	
								</font>
								<p><font color="#aeaea6">${shortDescription}</font></p>
							</div>
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
						<s:iterator value="cateInfos">
							<div class="col_4 navitem">
				     			<a id="${category.id}" href="jplocker!more.action?cid=${category.id}&${queryString}" >${name}</a>
					  		</div>
						</s:iterator>
						<div class="col_4 navitem" style="float: right;">
							<a href="jplocker.action?${queryString}">
								<img id="gohome" alt="gohome" src="${ctx}/images/home.png" height="28">
							</a>
						</div>	
					</div>
				</div>
			</div>
		</form>
	
	</body>
</html>