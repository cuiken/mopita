<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件市场编辑</title>
		<%@include file="/common/script.jsp" %>
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
	<div class="container">
		<form id="inputForm" action="file-market!save.action" method="post">

			<div class="span-18 last prepend-top">
		
			<input type="hidden" value="${id}" name="id"/>
			<input type="hidden" value="${themeId}" name="themeId"/>
			<input type="hidden" value="${marketId}" name="marketId"/>
			<fieldset>
				<legend>文件市场属性</legend>
				<div>
					<label for="keyName" class="field">字段名:<font class="red">*</font></label>
					<input type="text" name="keyName" value="${keyName}" size="25" class="required">
				</div>

				<div>
					<label for="keyValue" class="field">字段值:<font class="red">*</font></label>
				<input type="text" name="keyValue" value="${keyValue}" size="25" class="required">
				</div>
				

				
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