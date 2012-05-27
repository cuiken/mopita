<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>编辑文件信息</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#inputForm").validate({
					rules:{
						longDescription:{
							maxlength:200
						}
					}
				});
			});
		</script>
	</head>
	
	<body>
		<div class="container">
		<form id="inputForm" action="file-info!save.action" method="post">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<div class="span-18 last prepend-top">	
		<input type="hidden" value="${id}" name="id"/>
		<input type="hidden" value="${themeId}" name="themeId"/>
		<fieldset>
			<legend>多语言编辑</legend>
			<div>
				<label for="language" class="field">语言:</label>
				${language}
				<s:if test="id==null">
					<select name="language">
						<option value="ZH">中文</option>
						<option value="EN">英文</option>
						<option value="JP">日文</option>
					</select>
				</s:if>
			</div>
			<div>
				<label for="title" class="field">标题:<font class="red">*</font></label>
				<input type="text" name="title" class="required" value="${title}" size="25" maxlength="25">
			</div>
			<div>
				<label for="shortDescription" class="field">简要描述:<font class="red">*</font></label>
				<input type="text" name="shortDescription" class="required" value="${shortDescription}" size="25" maxlength="50">
			</div>
			<div>
				<label for="longDescription" class="field" style="vertical-align: top">详细描述:<font class="red">*</font></label>
				<textarea  id="longDescription" name="longDescription" class="required">${longDescription}</textarea>
			</div>
			<div>
				<label for="author" class="field">作者:</label>
				<input type="text" id="author" name="author" value="${author}" size="25" maxlength="25" />
			</div>
			<div>
				<label for="price" class="field">单价:</label>
				<input type="text" id="price" name="price" value="${price}" size="25" maxlength="10" class="number" />
			</div>
		</fieldset>
		<div>
			<input type="submit" value="保存"> &nbsp;
			<input class="button" type="button" value="返回" onclick="history.back()"/>
		</div>
			
		</div>
		</form>
		</div>	
	</body>
	
</html>