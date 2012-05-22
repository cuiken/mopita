<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>市场</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				
			});
		</script>
	</head>
	<body>
		<form action="market.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<h3>市场列表</h3>
			<table>
				<tr>
					<th>市场名称</th>
					<th>商店标识</th>
					<th>市场apk包名</th>
					<th>市场url key</th>
					<!--  <th>所有货架</th>-->
					<th>操作</th>
				</tr>
				<s:iterator value="markets">
					<tr>
						<td>${name}</td>
						<td>${value}</td>
						<td>${pkName}</td>
						<td>${marketKey}</td>
						<td>
							<a href="market!input.action?id=${id}">编辑</a>&nbsp;
							<a href="market!delete.action?id=${id}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<a href="market!input.action">创建市场</a>
			</div>
		</div>
		</form>
		
	</body>
</html>