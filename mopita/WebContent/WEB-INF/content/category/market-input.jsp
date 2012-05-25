<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>市场编辑</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#name").focus();
				$("#inputForm").validate({
					rules:{
						name:{
							remote: "market!checkMarketName.action?oldName=" + encodeURIComponent('${name}')
						}
					},
					errorContainer: "#messageBox",
					messages:{
						name:{
							remote:"市场已存在"
						}
					}
				
				});
			});
		</script>
	</head>
	<body>
		<form id="inputForm" action="market!save.action" method="post">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}">
		<div class="span-18 last prepend-top">
			<fieldset>
				<legend>编辑市场</legend>
				<div id="messageBox" class="error" style="display:none">输入有误，请先更正。</div>
				<div>
					<label for="name" class="field">市场名称:</label>
					<input type="text" id="name" name="name" size="25" maxlength="20" value="${name}" class="required"/>
				</div>
				<div>
					<label for="value" class="field">市场标识:</label>
					<input type="text" id="value" name="value" size="25" maxlength="20" value="${value}" class="required"/>
				</div>
				<div>
					<label for="pkName" class="field">市场apk包名:</label>
					<input type="text" id="pkName" name="pkName" size="25" maxlength="50" value="${pkName}" class="required"/>
				</div>
				<div>
					<label for="marketKey" class="field">市场market key:</label>
					<input type="text" id="marketKey" name="marketKey" size="25" maxlength="100" value="${marketKey}" />
				</div>
				<!--  
				<div>
					<label for="dtype" class="field">下载方式:</label>
					<input type="radio" value="market" name="dntype">market
					<input type="radio" value="http" name="dntype">http
				</div>
				-->
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