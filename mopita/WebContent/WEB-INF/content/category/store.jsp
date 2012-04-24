<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商店</title>
		<%@include file="/common/script.jsp" %>
	</head>
	<body>
		<form action="category.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<div id="message"><s:actionmessage cssClass="success"/></div>
			<h4 class="prepend-top">商店列表</h4>
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
			<div>
				<a href="store!input.action">add</a>
			</div>
			</div>
		</div>
		</form>
		
	</body>
</html>