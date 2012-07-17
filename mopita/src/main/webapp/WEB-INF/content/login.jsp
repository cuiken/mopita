<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>登录页</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/css/mini-web.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/jquery/validation/milk.css" rel="stylesheet">
	<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	<script src="${ctx}/js/jquery/validation/jquery.validate.min.js"></script>
	<script src="${ctx}/js/jquery/validation/messages_cn.js"></script>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>

<body>
	<div class="container">
	<div id="header" class="span12">
		<div id="menu">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="${ctx}/login.action">登陆</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="content" class="span12">
	<form id="loginForm" action="${ctx}/login.action" method="post" class="form-horizontal">
		<%
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if(error != null){
			if(error.contains("DisabledAccountException")){
		%>
			<div class="control-group">
			<div class="controls ">
			<div class="alert alert-error">
			<button class="close" data-dismiss="alert">×</button>
			用户已被屏蔽,请登录其他用户.</div>
			</div>
			</div>
		<%
			}else{
		%>	
			<div class="control-group">
			<div class="controls ">
			<div class="alert alert-error">
			<button class="close" data-dismiss="alert">×</button>
				登录失败，请重试.</div>
			</div>
			</div>
			
		<%
		}}
		%>
			<div class="control-group">
				<label for="username" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" placeholder="请输入用户名" id="username" name="username" size="50" value="${username}" class="required span2"/>
				</div>
			</div>
			<div class="control-group">
				<label for="password" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="password" placeholder="请输入密码" name="password" size="50"  class="required span2"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input type="checkbox" id="rememberMe" name="rememberMe" checked="checked"/> 记住我</label>
				<input id="submit" class="btn" type="submit" value="登录"/>
				</div>
			</div>		
	</form>
	</div>
	</div>
	<div id="footer" class="span12">
	Copyright &copy; 2012 <a href="http://www.tpadsz.com/">tpadsz.com</a>
</div>
	<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>