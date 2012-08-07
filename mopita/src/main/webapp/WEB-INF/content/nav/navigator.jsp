<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>链接列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<form action="navigator.action" id="mainForm" method="get">
			<div class="container">
				<table class="table table-striped table-bordered table-condensed">
					<thead><tr><th>链接名称</th><th>链接地址</th><th>分类标签</th><th>所属面版</th><th>操作</th><tr></thead>
					<tbody>
						<c:forEach items="${navigators}" var="nav">
							<tr>
								<td>${nav.name}</td>
								<td>${nav.navAddr}</td>
								<td>${nav.tagNames}</td>
								<td>${nav.boardNames}</td>
								<td>
									<a href="navigator!input.action?id=${nav.id}">修改</a>
									<a href="navigator!delete.action?id=${nav.id}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a class="btn" href="navigator!input.action">创建链接</a>
			</div>
		</form>
		<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>