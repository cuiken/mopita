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
				$("#inputForm").validate({
					rules:{
						checkedCategoryIds:"required",
						longDescription:{
							required:true,
							maxlength:200
						}
					},
					errorPlacement: function(error, element) {
						if (element.is(":radio") )
							error.appendTo (element.parent());
						else
							error.insertAfter( element );
					}				
				});
				/**
				$("#upload").bind('change', function() {
								
			  					return this.files[0].size<1;
		
						});
				*/
				$("#message").fadeOut(3000);
				$("#golist").click(function(){
					window.location="file.action";
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
			<c:if test="${not empty actionMessages}">
				<div id="message" class="notice">${actionMessages}</div>	
			</c:if>
			<fieldset>
				<legend>文件上传</legend>
				<div>
					<label for="upload" class="field">上传文件:<font class="red">*</font></label>
					<input type="file" id="upload" name="upload" class="required"/>(*仅支持zip文件*)
				</div>
				
				<div>
					<label for="title" class="field">标题:<font class="red">*</font></label>
					<input type="text" id="title" name="title" size="25" maxlength="30" value="${title}" class="required">
				</div>
				
				<div>
					<label for="checkedCategoryIds" class="field">分类:<font class="red">*</font></label>
					<s:radio name="checkedCategoryIds" list="allCategoryList" listKey="id" listValue="name" theme="simple"></s:radio>
				</div>
				<div>
					<label for="marketURL" class="field">Market地址:<font class="red">*</font></label>
					<input type="text" id="marketURL" name="marketURL" value="${marketURL}" size="25" maxlength="100" class="required">
				</div>
				<div>
					<label for="shortDescription" class="field">简要描述:<font class="red">*</font></label>
					<input type="text" id="shortDescription" name="shortDescription" value="${shortDescription}" size="25" maxlength="11" class="required">				
				</div>
				<div>
					<label for="longDescription" class="field" style="vertical-align: top">详细描述:<font class="red">*</font></label>
					<textarea  id="longDescription" name="longDescription"></textarea>					
				</div>
				<div>
					<label for="author" class="field">作者:</label>
					<input type="text" id="author" name="author" value="${author}" size="25" maxlength="25" />
				</div>
				<div>
					<label for="price" class="field">单价:</label>
					<input type="text" id="price" name="price" value="${price}" size="25" maxlength="10" class="number" />
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
			<div>
				<input type="submit" class="submit" value="上传">
				<input type="button" id="golist" value="取消"/>
			</div>	
			</div>
		</form>
		</div>
	</body>
</html>