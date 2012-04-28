<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>上架文件管理</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<!--[if lt IE 8]><link href="${ctx}/css/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection"><![endif]-->
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#inputForm").validate();
			});
		</script>
	</head>
	<body>
		<form id="inputForm" action="shelf!saveFile.action" method="post">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}">
		<div class="span-18 last prepend-top">
			<table>
				<tr>
					<td>货架名称:</td>
					<td>${name}</td>
				</tr>
				
				<tr>
					<td>仓库文件:</td>
					<td><s:checkboxlist name="checkedFileIds" list="allFiles" listKey="id" listValue="name" theme="simple"></s:checkboxlist></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存">&nbsp;
						<input class="button" type="button" value="返回" onclick="history.back();">
					</td>
				</tr>
			</table>
			</div>
		</div>
		</form>
		
	</body>
</html>