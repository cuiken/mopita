<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件多语言信息</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
			});
			function deleteThis(id,tid){
				if(confirm("确定要删除吗?")){
					window.location="file-info!delete.action?id="+id+"&themeId="+tid;
				}
			}
		</script>
	</head>
	<body>
		<div class="container">
		<form id="mainForm" action="file.action" method="get">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<div class="span-18 last prepend-top">
		<c:if test="${not empty actionMessages}">
			<div id="message" class="success">${actionMessages}</div>	
		</c:if>
		<h4 class="prepend-top">多语言信息</h4>
			<table>
				<thead>
				<tr>			
					<th>标题</th>
					<th>语言</th>
					<th>作者</th>
					<th>单价</th>			
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="allInfo">
					<tr>
						
						<td>${title}</td>
						<td>${language}</td>
						<td>${author}</td>
						<td>${price}</td>
						<td>
							<a href="file-info!input.action?id=${id}&themeId=${themeId}">修改</a>
						
							<a href="#" onclick="deleteThis(${id},${themeId})">删除</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>	
			<div>
				<a href="file-info!input.action?themeId=${themeId}">add</a>&nbsp;
				<a href="file.action">back</a>
			</div>
		</div>
			
		</form>
		
	</div>
	</body>
</html>