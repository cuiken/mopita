<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>分类语言维护</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#name").focus();
				$("#inputForm").validate();
			});
		</script>
	</head>
	<body>
		<form id="inputForm" action="category-info!save.action" method="post">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="cid" value="${cid}">
		<div class="span-18 last prepend-top">
			<fieldset>
				<legend>文件语言管理</legend>
				<div id="messageBox" class="error" style="display:none">输入有误，请先更正。</div>
				<div>
					<label for="name" class="field">分类名称:</label>
					<input type="text" id="name" name="name" size="25" maxlength="20" value="${name}" class="required"/>
				</div>
				
				<div>
					<label for="description" class="field">分类描述:</label>
					<input type="text" id="description" name="description" size="25" maxlength="50" value="${description}" class="required" />
				</div>
				
			</fieldset>
			
			<div>
				<input type="submit" value="保存">&nbsp;
				<input type="button" value="返回" onclick="history.back();">
			</div>
			
			</div>
		</div>
		</form>
		
	</body>
</html>