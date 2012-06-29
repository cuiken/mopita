<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>客户端编辑</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#inputForm").validate({
					rules:{
						dtype:"required"
					},
					errorPlacement: function(error, element) {
						if (element.is(":radio") )
							error.appendTo (element.parent());
						else
							error.insertAfter( element );
					}	
				});
			
				$("#message").fadeOut(3000);
				
				
			});

		</script>
	</head>
	<body>
	<div class="container">
		<form id="inputForm" action="funlocker-client!save.action" method="post">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="notice">${actionMessages}</div>	
			</c:if>
			
			<fieldset>
				<legend>文件编辑</legend>
				<input type="hidden" name="id" value="${id}">
				<div>
					<label for="pkName" class="field">文件包名:<font class="red">*</font></label>
					<input type="text" id="pkName" name="pkName" value="${pkName}" class="required"/>
				</div>
				<div>
					<label for="dtype" class="field">客户端类型:</label>
					<s:radio id="dtype" name="dtype" list="#{'st':'标准版','jp':'日本版'}" theme="simple"></s:radio>
				</div>
				<div>
					<label for="checkedMarketIds" class="field">市场:</label>
					<s:checkboxlist id="marketList" name="checkedMarketIds" list="allMarkets" listKey="id" listValue="name" theme="simple"></s:checkboxlist>
				</div>
			</fieldset>
			<div>
				<input type="submit" class="submit" value="保存">
				<input type="button" class="btn" value="返回" onclick="javascript:history.back();">
			</div>	
			
			</div>
		</form>
		</div>
	</body>
</html>