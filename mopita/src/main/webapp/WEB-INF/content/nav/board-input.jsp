<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>一级分类管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/mini-web.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<form action="board!save.action" id="inputForm" method="post" class="form-horizontal" enctype="multipart/form-data">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span>
		           			 <span class="icon-bar"></span>
		           			 <span class="icon-bar"></span>
						</a>
						<a class="brand" href="${ctx}/file/file.action">UMS2.0</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li class="active">
									<a href="${ctx}/nav/board.action">一级分类</a>
								</li>
								<li>
									<a href="${ctx}/nav/nav-tag.action">二级分类</a>
								</li>
								<li>
									<a href="${ctx}/nav/navigator.action">具体链接</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<input type="hidden" name="id" value="${id}">
				<fieldset>
					<legend><small>管理一级分类</small></legend>
					<div class="control-group">
						<label for="name" class="control-label">名称:</label>
						<div class="controls">
							<input type="text" name="name" id="name" value="${name}" required="required" size="35">
						</div>
					</div>
					<div class="control-group">
						<label for="fileInput" class="control-label">图标:</label>
						<div class="controls">
							<input type="file" class="input-file" id="fileInput" name="upload">
						</div>
					</div>
					<div class="form-actions">
						<input id="submit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
						<input id="cancel" class="btn" type="button" value="返回" onclick="history.back()"/>
					</div>
				</fieldset>
			</div>
		</form>
	
		<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>