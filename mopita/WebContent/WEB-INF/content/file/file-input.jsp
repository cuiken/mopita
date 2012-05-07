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
				$("textarea").css("height","150px");
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
					<label for="upload" class="field">上传文件:</label>
					<input type="file" id="upload" name="upload" />(*仅支持zip文件*)
				</div>
				<div>
					<label for="checkedCategoryIds" class="field">文件分类:<font class="red">*</font></label>
					<s:checkboxlist name="checkedCategoryId" list="allCategoryList" listKey="id" listValue="name" theme="simple"></s:checkboxlist>
				</div>
				
				<div>
					<label for="marketURL" class="field">Market地址:<font class="red">*</font></label>
					<input type="text" id="marketURL" name="marketURL" value="${marketURL}" size="25" maxlength="100" class="required">
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
						<label for="title" class="field">标题:<font class="red">*</font></label>
						<input type="text" id="title[${info.index}]" name="fileInfo[${info.index}].title" value="${title}" size="25" maxlength="50" class="required"/>
					</div>
					<div>
						<label for="shortDescription" class="field">简要描述:<font class="red">*</font></label>
						<input id="shortDescription[${info.index}]" name="fileInfo[${info.index}].shortDescription" type="text" size="25" maxlength="11" value="${shortDescription}" class="required"/>
					</div>
					<div>
						<label for="longDescription" class="field" style="vertical-align: top">详细描述:<font class="red">*</font></label>
						<textarea  id="longDescription[${info.index}]" name="fileInfo[${info.index}].longDescription" class="required">${longDescription}</textarea>
					</div>
					<div>
						<label for="author" class="field">作者:</label>
						<input type="text" id="author" name="fileInfo[${info.index}].author" value="${author}" size="25" maxlength="25" />
					</div>
					<div>
						<label for="price" class="field">单价:</label>
						<input type="text" id="price[${info.index}]" name="fileInfo[${info.index}].price" value="${price}" size="25" maxlength="10" class="number" />
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