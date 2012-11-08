<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商店</title>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				$("#store-tab").addClass("active");
			});
			function deleteThis(id){
				if(confirm("该操作会删除所有关联货架!确定要删除吗?")){
					window.location="store!delete.action?id="+id;
				}
			}
		</script>
	</head>
	<body>
		<form action="store.action" method="get">
			<h1>商店列表</h1>
			<c:if test="${not empty actionMessages}">
				<div id="message" class="alert alert-success">${actionMessages}</div>	
			</c:if>
			
			<table class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th>商店名称</th>
					<th>商店标识</th>
					<th>商店描述</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="stores">
					<tr>
						<td>${name}</td>
						<td>${value}</td>
						<td>${description}</td>
						
						<td>
							<shiro:hasPermission name="store:edit">
								<a href="store!input.action?copyId=${id}">复制</a>&nbsp;
								<a href="store!input.action?id=${id}">编辑</a>&nbsp;
								<a href="#" onclick="deleteThis(${id})">删除</a>
							</shiro:hasPermission>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<shiro:hasPermission name="store:edit">
				<a class="btn" href="store!input.action">创建商店</a>
			</shiro:hasPermission>
		</form>
		
	</body>
</html>