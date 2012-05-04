<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>管理商店</title>
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
							remote: "store!checkStoreName.action?oldStoreName=" + encodeURIComponent('${name}')
						}
					},
					errorContainer: "#messageBox",
					messages:{
						name:{
							remote:"商店名称已存在"
						}
					}
				
				});
			});
		</script>
	</head>
	<body>
		<form id="inputForm" action="store!save.action" method="post">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="copyId" value="${copyId}">
		<div class="span-18 last prepend-top">
		<h4 class="prepend-top">
			<s:if test="copyId!=null">复制</s:if><s:elseif test="id!=null">编辑</s:elseif>
			<s:else>新增</s:else>商店
		</h4>
			<fieldset>
				<legend>管理商店</legend>
				<div id="messageBox" class="error" style="display:none">输入有误，请先更正。</div>
				<div>
					<label for="name" class="field">商店名称:</label>
					<input type="text" id="name" name="name" size="25" maxlength="20" value="${name}" class="required"/>
				</div>
				
				<div>
					<label for="description" class="field">商店描述:</label>
					<input type="text" id="description" name="description" size="25" maxlength="50" value="${description}" />
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