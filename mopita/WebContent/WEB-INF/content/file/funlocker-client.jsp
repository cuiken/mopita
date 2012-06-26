<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>客户端列表</title>
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
			
			<div>
				<h3>客户端文件列表</h3>
				<table>
					<thead>
						<tr>
							<th>文件名</th>
							<th>大小</th>
							<th>版本号</th>
							<th>上传时间</th>
							<th>更新时间</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="clientFiles">
							<tr>
								<td>${name}</td>
								<td>${size}</td>
								<td>${version}</td>
								<td>${createTime}</td>
								<td>${modifyTime}</td>
								<td>
									<a href="funlocker-client!input.action?id=${id}">编辑</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			</div>
		</form>
		</div>
	</body>
</html>