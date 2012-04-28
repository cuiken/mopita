<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>货架</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				//alert($("#store").val());
				$("#store").change(function(){
					//alert($(this).children('option:selected').val());
				});
			});
		</script>
	</head>
	<body>
		<form action="shelf.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<div id="message"><s:actionmessage cssClass="success"/></div>
			商店:<s:select list="allStores" listKey="id" listValue="name" name="" id="store"/>
			<table>
				<tr>
					<th>货架名称</th>
					<th>货架描述</th>
					<th>所属商店</th>
					<th>操作</th>
				</tr>
				<s:iterator value="shelfs">
					<tr>
						<td>${name}</td>
						<td>${description}</td>
						<td>${store.name}</td>
						<td>
							<a href="shelf!file.action?id=${id}">管理</a>
							<a href="shelf!input.action?id=${id}">编辑</a>
							<a href="shelf!delete.action?id=${id}">删除</a>
						</td>
					</tr>
				</s:iterator>
				
			</table>
			<div>
				<a href="shelf!input.action">add</a>
			</div>
			</div>
		</div>
		</form>
		
	</body>
</html>