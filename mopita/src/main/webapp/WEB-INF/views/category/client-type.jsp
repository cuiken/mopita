<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>客户端分类</title>
<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);	
				$("#ccategory-tab").addClass("active");
			});
			function deleteThis(id){
				if(confirm("确定要删除吗?")){
					window.location="client-type!delete.action?id="+id;
				}
			}
		</script>
</head>
<body>
	<form action="category.action" method="get">
		<h1>客户端分类</h1>
		<c:if test="${not empty actionMessages}">
			<div id="message" class="alert alert-success">${actionMessages}</div>
		</c:if>

		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>分类名称</th>
					<th>分类键值</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="cts">
					<tr>
						<td>${name}</td>
						<td>${value}</td>
						<td>
								<a href="client-type!input.action?id=${id}">编辑</a>
								<a href="#" onclick="deleteThis(${id})">删除</a>
							</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	
			<a class="btn" href="client-type!input.action">创建分类</a>

	</form>

</body>
</html>