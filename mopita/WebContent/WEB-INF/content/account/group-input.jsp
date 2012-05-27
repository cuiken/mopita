<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>帐号管理</title>
	<%@include file="/common/script.jsp" %>
	<script>
		$(document).ready(function() {
			$("#group-tab").addClass("active");
			$("#inputForm").validate();
		});
	</script>
</head>

<body>
	<form id="inputForm"  action="${ctx}/account/group!save.action" method="post" class="form-horizontal">
	<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}"/>
		<fieldset>
			<legend><small>管理权限组</small></legend>
			<div class="control-group">
				<label for="name" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" id="name" name="name" size="50" class="required" value="${name}"/>
				</div>
			</div>
			<div class="control-group">
				<label for="permissionList" class="control-label">权限列表:</label>
				<div class="controls">
					<s:checkboxlist id="permissionList" name="permissionList" list="allPermissions" listKey="value" listValue="displayName"></s:checkboxlist>
				</div>
			</div>	
			<div class="form-actions">
					<input id="submit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
					<input id="cancel" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
		</div>
	</form>
</body>
</html>
