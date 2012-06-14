<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件上传</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#inputForm").validate();
			
				$("#message").fadeOut(3000);
				
				
			});

		</script>
	</head>
	<body>
	<div class="container">
		<form id="inputForm" action="file-upload!saveClient.action" method="post" enctype="multipart/form-data">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="notice">${actionMessages}</div>	
			</c:if>
			<fieldset>
				<legend>文件上传</legend>
				<div>
					<label for="upload" class="field">上传客户端文件:<font class="red">*</font></label>
					<input type="file" id="upload" name="upload" class="required"/>
				</div>
				
				
			</fieldset>
			<div>
				<input type="submit" class="submit" value="上传">
				
			</div>	
			</div>
		</form>
		</div>
	</body>
</html>