<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>链接管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("input[name='checkedBoardIds']").each(function(){
					var checkedIds=${checkedBoardIds};				
					for(var i=0;i<checkedIds.length;i++){
						if(this.value==checkedIds[i]){
							$(this).attr("checked","checked");
						}
					}
				});
				
				$("input[name='checkedTagIds']").each(function(){		
					var checkedIds=${checkedTagIds};
					for(var i=0;i<checkedIds.length;i++){
						if(this.value==checkedIds[i]){
							$(this).attr("checked","checked");
						}
					}
				});
				
			});
		</script>
	</head>
	<body>
		<form action="navigator!save.action" id="inputForm" method="post" enctype="multipart/form-data" class="form-horizontal">
			<div class="container">
				<input type="hidden" name="id" value="${id}">
				<fieldset>
					<legend><small>管理链接</small></legend>
					<div class="control-group">
						<label for="name" class="control-label">链接名称:</label>
						<div class="controls">
							<input type="text" name="name" id="name" value="${name}" required="required" size="35">
						</div>
					</div>
					
					<div class="control-group">
						<label for="navAddr" class="control-label">链接地址:</label>
						<div class="controls">
							<input type="url" name="navAddr" id="navAddr" value="${navAddr}" required="required" size="35">
						</div>
					</div>
					
					<div class="control-group">
						<label for="fileInput" class="control-label">链接图标:</label>
						<div class="controls">
							<input type="file" class="input-file" id="fileInput" name="upload">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">所属板块:</label>
						<div class="controls">
							<c:forEach var="board" items="${allBoards}">
								<label class="radio">
									<input type="radio" name="checkedBoardIds" value="${board.id}">${board.name}
								</label>
							</c:forEach>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">分类标签:</label>
						<div class="controls">
							<c:forEach items="${allTags}" var="tag">
								<label class="checkbox">
									<input type="checkbox" name="checkedTagIds" value="${tag.id}">${tag.name}
								</label>
							</c:forEach>
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