<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>导航首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<style>
			.thumbnails {	
				margin-left: 0;
				list-style: none;
			}
			.thumbnails > li {
			    float: left;
			    margin-bottom: 0;
				margin-left: 0;
				display:inline;
			}
			li {
    			line-height: 18px;
			}
			ul, ol {
			    margin: 0 0 0 25px;
			    padding: 0;
			}
			.thumbnail {
			    border: 1px solid #DDDDDD;
			    border-radius: 4px 4px 4px 4px;
			    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
			    display: block;
			    line-height: 1;
			    padding:0;
			}
			
			.thumbnail > img {
			    display: block;
			    margin-left: auto;
			    margin-right: auto;
			    max-width: 100%;
			}
			img {
			    border: 0 none;
			    max-width: 100%;
			    vertical-align: middle;
			}
		</style>
	</head>
	<body>
	<section id="thumbnails">
		<div>
			<ul class="thumbnails">
				<li style="width: 75%;">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/xinwen/xinwen_6x.gif" >
					</a>
				</li>
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/junshi/junshi_1x.gif">
					</a>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/tiyu/tiyu_1x.gif">
					</a>
				</li>
				
			</ul>	
		</div>
		<div>		
			<ul class="thumbnails">
				<li style="width: 50%;">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/gouwu/gouwu_4x.gif">
					</a>
				</li>
				<li style="width: 50%;">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/yuedu/yuedu_4x.gif">
					</a>
				</li>
			</ul>
		</div>
		<div>
			<ul class="thumbnails">		
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/meishi/meishi_1x.gif">
					</a>
				</li>
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/chuxing/chuxing_1x.gif">
					</a>
				</li>
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/youxiang/youjian_1x.gif">
					</a>
				</li>
				<li style=" width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/ditu/ditu_1x.gif">
					</a>
				</li>
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/qiche/qiche_1x.gif">
					</a>
				</li>
				<li style="width: 25%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/jiaoyou/jiaoyou_1x.gif">
					</a>
				</li>
				<li style="width: 50%">
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/tianqi/tianqi_2x.gif">
					</a>
				</li>
			</ul>
		</div>
	</section>
	<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>