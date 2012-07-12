<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>分类多语言描述</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);		
			});
			function deleteThis(id,cid){
				if(confirm("确定要删除吗?")){
					window.location="category-info!delete.action?id="+id+"&cid="+cid;
				}
			}
		</script>
	</head>
	<body>
		<form action="category-info.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<h3>分类多语言描述</h3>
			<table>
				<thead>
				<tr>
					<th>分类名称</th>
					<th>分类描述</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="categoryInfos">
					<tr>
						<td>${name}</td>
						<td>${description}</td>
						<td>
							<shiro:hasPermission name="category:edit">
								<a href="category-info!input.action?id=${id}&cid=${cid}">编辑</a>
								<a href="#" onclick="deleteThis(${id},${cid})">删除</a>
							</shiro:hasPermission>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<shiro:hasPermission name="category:edit">
				<a class="btn" href="category-info!input.action?cid=${cid}">创建语言</a>
				<a class="btn" href="category.action">返回分类列表</a>
			</shiro:hasPermission>
			</div>
		</div>
		</form>
		
	</body>
</html>