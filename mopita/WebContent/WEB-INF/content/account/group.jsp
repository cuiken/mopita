<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>帐号管理</title>
	<%@include file="/common/script.jsp" %>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#group-tab").addClass("active");
		});
	</script>
</head>

<body>
	<form action="group.action" method="get">
	<div class="container">
	<%@include file="/common/header.jsp" %>
	<%@include file="/common/left.jsp" %>
	<div class="span-18 last prepend-top">
	<h4>权限组列表</h4>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>	
	</c:if>
	
	<table>
		<thead><tr><th>名称</th><th>授权</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${groups}" var="group">
			<tr>
				<td>${group.name}</td>
				<td>${group.permissionNames}</td>
				<td>
					<shiro:hasPermission name="group:edit">
						<a href="group!input.action?id=${group.id}">修改</a> <a href="group!delete.action?id=${group.id}">删除</a>
					</shiro:hasPermission>	
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<shiro:hasPermission name="group:edit">
		<a class="btn" href="group!input.action">创建权限组</a>
	</shiro:hasPermission>
	</div>
	</div>
</form>	
</body>
</html>
