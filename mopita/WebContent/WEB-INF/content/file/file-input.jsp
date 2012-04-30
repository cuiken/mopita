<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件编辑</title>
		<%@include file="/common/script.jsp" %>
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
			<fieldset>
				<legend>文件基础属性</legend>
				<div>
					<label for="name" class="field">文件名:</label>
					<input type="text" id="name" name="name" value="${name}" size="25" readonly="readonly">
				</div>
				
				<div>
					<label for="checkedCategoryIds" class="field">文件分类:</label>
					<s:checkboxlist name="checkedCategoryId" list="allCategoryList" listKey="id" listValue="name" theme="simple"></s:checkboxlist>
				</div>
				
				<div>
					<label for="marketURL" class="field">Market地址:</label>
					<input type="text" id="marketURL" name="marketURL" value="${marketURL}" size="25" maxlength="100" class="required">
				</div>
				<div>
					<label for="price" class="field">单价:</label>
					<input type="text" id="price" name="price" value="${price}" size="25" maxlength="10" class="number">
				</div>
				<div>
					<label for="availMachine" class="field">可用机型:</label>
					<input type="text" id="availMachine" name="availMachine" value="${availMachine}" size="25" maxlength="255"/>
				</div>
				<div>
					<label for="unavailMachine" class="field">不可用机型:</label>
					<input type="text" id="unavailMachine" name="unavailMachine" value="${unavailMachine}" size="25" maxlength="255"/>
				</div>
				
			</fieldset>
			<fieldset>
				<legend>多语言描述信息</legend>
				<s:iterator value="fileInfo" status="info">
					<div>
						<font color="blue">${language}</font>
					</div>
					<div>
						<label for="title" class="field">文件标题:</label>
						<input type="text" id="title" name="fileInfo[${info.index}].title" value="${title}" size="25" maxlength="50"/>
					</div>
					<div>
						<label for="description" class="field">文件描述:</label>
						<input id="description" name="fileInfo[${info.index}].description" type="text" size="25" maxlength="255" value="${description}"/>
					</div>
				</s:iterator>
			</fieldset>
			<div>
				<input type="submit" value="保存">&nbsp;
				<input class="button" type="button" value="返回" onclick="history.back();">
			</div>

			</div>
		</form>
		</div>
	</body>
</html>