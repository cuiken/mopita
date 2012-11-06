<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>二级分类</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/mini-web.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<form action="nav-tag.action" id="mainForm" method="get">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span>
		           			 <span class="icon-bar"></span>
		           			 <span class="icon-bar"></span>
						</a>
						<a class="brand" href="${ctx}/file/file.action">UMS2.0</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li>
									<a href="${ctx}/nav/board.action">一级分类</a>
								</li>
								<li class="active">
									<a href="${ctx}/nav/nav-tag.action">二级分类</a>
								</li>
								<li>
									<a href="${ctx}/nav/navigator.action">具体链接</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container" style="margin-top: 60px;">
				<table class="table table-striped table-bordered table-condensed">
					<thead><tr><th>名称</th><th>标识符</th><th>描述</th><th>操作</th><tr></thead>
					<tbody>
						<c:forEach items="${tags}" var="tag">
							<tr>
								<td>${tag.name}</td>
								<td>${tag.value}</td>
								<td>${tag.description}</td>
								
								<td>
									<a href="nav-tag!input.action?id=${tag.id}">修改</a>
									<a href="#" onclick="deleteThis(${tag.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a class="btn" href="nav-tag!input.action">创建分类</a>
			</div>
		</form>
		<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
		function deleteThis(id){
			if(confirm("确定要删除吗?")){
				window.location="nav-tag!delete.action?id="+id;
			}
		}
		</script>
	</body>
</html>