<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件上传</title>
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
		<form id="inputForm" action="file-upload!upload.action" method="post" enctype="multipart/form-data">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<div id="message"><s:actionmessage cssClass="error"/></div>
			<table>
				<tr>
					<td>上传文件:</td>
					<td>
					
						<input type="file" name="upload" class="required"/>
						(*仅支持zip)
					</td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><input type="text" name="title" class="required"></td>
				</tr>
				<tr>
					<td>分类:</td>
					<td>
						<s:radio name="checkedCategoryIds" list="allCategoryList" listKey="id" listValue="name" theme="simple"></s:radio>
					</td>
				</tr>
				<tr>
					<td>描述:</td>
					<td><input type="text" name="description" class="required"></td>
				</tr>
				<tr>
					<td>Market地址:</td>
					<td><input type="text" name="marketURL" value="${marketURL}" class="required"></td>
				</tr>
				<tr>
					<td>单价:</td>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td>可用机型:</td>
					<td><input name="availMachine"/></td>
				</tr>
				<tr>
					<td>不可用机型:</td>
					<td><input name="unavailMachine"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="上传"></td>
					<td><input type="reset" value="取消"/></td>
				</tr>
			</table>		
			</div>
		</form>
		</div>
	</body>
</html>