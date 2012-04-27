<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>多语言列表</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<!--[if lt IE 8]><link href="${ctx}/css/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection"><![endif]-->
		<link href="${ctx}/css/mini-web.css" rel="stylesheet"/>	
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
			});
		
		</script>
	</head>
	<body>
		<div class="container">
		<form id="mainForm" action="file.action" method="get">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<div class="span-18 last prepend-top">
		<div id="message"><s:actionmessage cssClass="success"/></div>
		<h4 class="prepend-top">语言列表</h4>
			<table>
				<tr>			
					<th>名称</th>
					<th>描述</th>
					<th>语言</th>
					<th>操作</th>
				</tr>
				<s:iterator value="allInfo">
					<tr>
						
						<td>${title}</td>
						<td>${description}</td>
						<td>${language}</td>
						<td>
							<a href="file-info!input.action?id=${id}&themeId=${themeId}">修改</a>
						
							<a href="file-info!delete.action?id=${id}&themeId=${themeId}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>	
			<div>
				<a href="file-info!input.action?themeId=${themeId}">add</a>&nbsp;
				<a href="file.action">back</a>
			</div>
		</div>
			
		</form>
		
	</div>
	</body>
</html>