<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商店</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				
			});
		</script>
	</head>
	<body>
		<form action="category.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<h3>商店列表</h3>
			<table>
				<tr>
					<th>商店名称</th>
					<th>商店描述</th>
					<th>所有货架</th>
					<th>操作</th>
				</tr>
				<s:iterator value="stores">
					<tr>
						<td>${name}</td>
						<td>${description}</td>
						<td>${categoryNames}</td>
						<td>
							<a href="store!input.action?copyId=${id}">复制</a>&nbsp;
							<a href="store!input.action?id=${id}">编辑</a>&nbsp;
							<a href="store!delete.action?id=${id}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<a href="store!input.action">创建商店</a>
			</div>
		</div>
		</form>
		
	</body>
</html>