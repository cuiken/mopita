<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>链接列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/mini-web.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/home.css" type="text/css" rel="stylesheet">
		<script src="${ctx}/static/jquery/1.7.2/jquery.min.js"></script>
		<script src="${ctx}/js/table.js"></script>
	</head>
	<body>
		<form action="navigator.action" id="mainForm" method="post" class="form-horizontal">
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
								<li>
									<a href="${ctx}/nav/nav-tag.action">二级分类</a>
								</li>
								<li class="active">
									<a href="${ctx}/nav/navigator.action">具体链接</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container" style="margin-top: 60px;">
				<div id="filter" style="margin-bottom:5px;">
					链接名称: <input type="text" class="input-medium" name="filter_LIKES_name"
					value="${param['filter_LIKES_name']}" size="20" /> <input
					type="button" id="submit_btn" class="btn" value="搜索"
					onclick="search();" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead><tr><th>链接名称</th><th>链接地址</th>
					
					<th>操作</th><tr></thead>
					<tbody>
						<c:forEach items="${page.result}" var="nav">
							<tr>
								<td>${nav.name}</td>
								<td>${nav.navAddr}</td>
								<td>
									<a href="navigator!input.action?id=${nav.id}">修改</a>
									<a href="#" onclick="deleteThis(${nav.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@include file="/common/page.jsp"%>
				<a class="btn" href="navigator!input.action">创建链接</a>
			</div>
		</form>
		<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
		function deleteThis(id){
			if(confirm("该删除为逻辑删除，可恢复！确定要删除吗?")){
				window.location="navigator!delete.action?id="+id;
			}
		}
		</script>
	</body>
</html>