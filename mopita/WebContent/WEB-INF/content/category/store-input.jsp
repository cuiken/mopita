<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商店</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<link href="${ctx}/css/mini-web.css" rel="stylesheet"/>	
		<!--[if lt IE 8]><link href="${ctx}/css/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection"><![endif]-->
		<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
		<script>
			$(document).ready(function(){
				$("#inputForm").validate({
					rules:{
						name:{
							remote: "store!checkStoreName.action?oldStoreName=" + encodeURIComponent('${name}')
						}
					},
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
			<table>
				<tr>
					<td>商店名称:</td>
					<td><input type="text" id="name" name="name" size="20" value="${name}" class="required"/></td>
				</tr>
				<tr>
					<td>商店描述:</td>
					<td><input type="text" name="description" value="${description}" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存">
					</td>
					<td>
						<a href="store.action">back</a>
					</td>
				</tr>
			</table>
			</div>
		</div>
		</form>
		
	</body>
</html>