<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件列表</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/table.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				
			});
		</script>
	</head>
	<body>
		<div class="container">
			<form id="mainForm" action="file.action" method="post">
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.orderDir" id="order" value="${page.orderDir}"/>
			<%@include file="/common/header.jsp" %>
			<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
		
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<h3>文件列表</h3>
			<div id="filter">
				文件名: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="20"/>
				<input type="button" value="搜索" onclick="search();"/>
			</div>
			<table>
				<thead>
				<tr>
					<th><a href="javascript:sort('title','asc')">标题</a></th>
					<th><a href="javascript:sort('name','asc')">文件名</a></th>					
					<th><a>apk包名</a></th>					
					<th><a href="javascript:sort('createTime','asc')">添加时间</a></th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="page.result">
					<tr>
						<td>${title}</td>
						<td>${name}</td>						
						<td>${marketURL}</td>
						<td>${createTime}</td>
						<td>
							<shiro:hasPermission name="file:edit">
								<a href="file!input.action?id=${id}">修改</a>
								<a href="file!delete.action?id=${id}">删除</a>
								<a href="file-info.action?themeId=${id}">语言</a>
							</shiro:hasPermission>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>	
			<div>
				第${page.pageNo}页, 共${page.totalPages}页
				<a href="?page.pageNo=1">首页</a>
				<s:if test="page.prePage"><a href="?page.pageNo=${page.prePage}">上一页</a></s:if>
				<s:if test="page.nextPage"><a href="?page.pageNo=${page.nextPage}">下一页</a></s:if>
				<a href="?page.pageNo=${page.totalPages}">末页</a>
			</div>
		</div>
		</form>
		
	</div>
	</body>
</html>