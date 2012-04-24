<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件编辑</title>
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
				$("#inputForm").validate({
					rules:{
						checkedCategoryIds:"required"
					}
				
				});
			});
		
		</script>
	</head>
	<body>
	<div class="container">
		<form id="inputForm" action="file!save.action" method="post">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<div id="message"><s:actionmessage cssClass="error"/></div>
			<input type="hidden" value="${id}" name="id"/>
			<table>
				<tr>
					<td>标题:</td>
					<td><input type="text" name="title" class="required" value="${title}"></td>
				</tr>
				<tr>
					<td>分类:</td>
					<td>
						<s:checkboxlist name="checkedCategoryIds" list="allCategoryList" listKey="id" listValue="name" theme="simple" />
					</td>
				</tr>
				<tr>
					<td>描述:</td>
					<td><input type="text" name="description" class="required" value="${description}"></td>
				</tr>
				<tr>
					<td>Market地址:</td>
					<td><input type="text" name="marketURL" value="${marketURL}" class="required"></td>
				</tr>
				<tr>
					<td>单价:</td>
					<td><input type="text" name="price" value="${price}"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="submit" value="保存"></td>
					<td><a href="file.action">返回</a></td>
				</tr>
			</table>		
			</div>
		</form>
		</div>
	</body>
</html>