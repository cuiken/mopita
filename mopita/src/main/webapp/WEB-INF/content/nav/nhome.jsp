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
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/xinwen/xinwen_6x.gif" height="150" width="225">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/chuxing/chuxing_1x.gif" height="75" width="75">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/gouwu/gouwu_1x.gif" height="75" width="75">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/jiaoyou/jiaoyou_4x.gif" height="150" width="150">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/junshi/junshi_4x.gif" height="150" width="150">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/meishi/meishi_4x.gif" height="150" width="150">
					</a>
				</li>
				<li>
					<a href="#" class="thumbnail">
						<img alt="" src="${ctx}/images/nav/yuedu/yuedu_4x.gif" height="150" width="150">
					</a>
				</li>
			</ul>
		</div>
	</section>
	<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>