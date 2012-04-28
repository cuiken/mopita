<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件编辑</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<link href="${ctx}/css/mini-web.css" rel="stylesheet"/>	
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
		
			<input type="hidden" value="${id}" name="id"/>
			<table>
				<tr>
					<td>文件名:</td>
					<td>${name}</td>
				</tr>
				<tr>
					<td>分类:</td>
					<td>
						<s:radio name="checkedCategoryId" list="allCategoryList" listKey="id" listValue="name" theme="simple"></s:radio>
					</td>
				</tr>
				<tr>
					<td>Market地址:</td>
					<td><input type="text" name="marketURL" value="${marketURL}" class="required"></td>
				</tr>
				<tr>
					<td>单价:</td>
					<td><input type="text" name="price" value="${price}" class="number"></td>
				</tr>
				<s:iterator value="fileInfo" status="info">
					<tr>
						<td colspan="2"><font color="blue">${language}</font></td>
					</tr>
					<tr>
						<td>标题</td>
						<td><input type="text" name="fileInfo[${info.index}].title" value="${title}"/></td>
					</tr>
					<tr>
						<td>描述</td>
						<td><input name="fileInfo[${info.index}].description" type="text" value="${description}"/></td>
					</tr>
				</s:iterator>

				<tr>
					<td colspan="2">
						<input type="submit" value="保存">&nbsp;
						<input class="button" type="button" value="返回" onclick="history.back();">
					</td>
					
				</tr>
			</table>		
			</div>
		</form>
		</div>
	</body>
</html>