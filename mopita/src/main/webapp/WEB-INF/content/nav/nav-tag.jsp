<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>分类列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<form action="nav-tag.action" id="mainForm" method="get">
			<div class="container">
				<table class="table table-striped table-bordered table-condensed">
					<thead><tr><th>名称</th><th>操作</th><tr></thead>
					<tbody>
						<c:forEach items="${tags}" var="tag">
							<tr>
								<td>${tag.name}</td>
								<td>
									<a href="nav-tag!input.action?id=${tag.id}">修改</a>
									<a href="nav-tag!delete.action?id=${tag.id}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a class="btn" href="nav-tag!input.action">创建分类</a>
			</div>
		</form>
		<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>