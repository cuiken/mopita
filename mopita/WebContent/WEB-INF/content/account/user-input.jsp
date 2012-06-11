<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>帐号管理</title>
	<%@include file="/common/script.jsp" %>
	<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
	<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
	<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//active tab
			$("#user-tab").addClass("active");
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						remote: "${ctx}/account/user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
					},
					groupList:"required"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if ( element.is(":checkbox") )
						error.appendTo ( element.parent().next() );
					else
						error.insertAfter( element );
				}
			});
		});
	</script>
</head>

<body>
	<form id="inputForm"  action="${ctx}/account/user!save.action" method="post" class="form-horizontal">
	<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<input type="hidden" name="id" value="${id}"/>
		<div class="span-18 last prepend-top">
		<fieldset>
			<legend><small>管理用户</small></legend>
			<div id="messageBox" class="alert alert-error" style="display:none">输入有误，请先更正。</div>
	
			<div class="control-group">
				<label for="loginName" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" size="50" value="${loginName}" class="required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" size="50" value="${name}" class="required"/>
				</div>
			</div>
			<s:if test="id==null">
				<div class="control-group">
					<label for="plainPassword" class="control-label">密码:</label>
					<div class="controls">
						<input type="password" id="plainPassword" name="plainPassword" size="50" class="required" minlength="3"/>
					</div>
				</div>
				<div class="control-group">
					<label for="passwordConfirm" class="control-label">确认密码:</label>
					<div class="controls">
						<input type="password" id="passwordConfirm" name="passwordConfirm" size="50" equalTo="#plainPassword"/>
					</div>
				</div>
			</s:if><s:else>
				<div class="control-group">
					<label for="plainPassword" class="control-label">密码:</label>
					<div class="controls">
						<input type="password" id="plainPassword" name="plainPassword" size="50" minlength="3"/>
					</div>
				</div>
			</s:else>
			<div class="control-group">
				<label for="email" class="control-label">邮箱:</label>
				<div class="controls">
					<input type="text" id="email" name="email" size="50" value="${email}" class="email"/>
				</div>
			</div>
			<div class="control-group">
				<label for="groupList" class="control-label">权限组:</label>
				<div class="controls">
					<s:checkboxlist id="groupList" name="checkedGroupIds"  list="allGroups" listKey="id" listValue="name" theme="simple"></s:checkboxlist>
				</div>
			</div>	
			<div class="control-group">
				<label for="status" class="control-label">状态:</label>
				<div class="controls">
					<s:select name="status" list="#{'enabled':'enabled','disabled':'disabled'}" listKey="key" listValue="value"></s:select>				
				</div>
			</div>
			<div class="form-actions">
				<input id="submit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
		</div>
</div>

	</form>
</body>
</html>
