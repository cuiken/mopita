<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件列表</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/css/home.css" type="text/css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/table.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				
			});
			function deleteThis(id){
				if(confirm("确定要删除吗?")){
					window.location="file!delete.action?id="+id;
				}
			}
		</script>
	</head>
	<body>
		<div class="container">
			<form id="mainForm" action="file.action" method="post">
			
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
					<th>apk包名</th>		
					<th>版本</th>				
					<th><a href="javascript:sort('createTime','asc')">添加时间</a></th>
					<th><a href="javascript:sort('modifyTime','asc')">修改时间</a></th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="page.result">
					<tr>
						<td>
							<a href="file!input.action?id=${id}">${title}</a>
						</td>											
						<td>
							
							<c:choose>
								<c:when test="${fn:length(marketURL)>30}">
									${fn:substring(marketURL,0,30)}....
								</c:when>
								<c:otherwise>
									${marketURL}
								</c:otherwise>
							</c:choose>	
						</td>
						<td>${version}</td>
						<td>${createTime}</td>
						<td>${modifyTime}</td>
						<td>
							<shiro:hasPermission name="file:edit">
								<!-- <a href="file!input.action?id=${id}">修改</a> -->
								<a href="file-info.action?themeId=${id}">语言</a>
								<a href="#" onclick="deleteThis(${id})">删除</a>								
							</shiro:hasPermission>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>	
			<%@include file="/common/page.jsp" %>
		</div>
		</form>
		
	</div>
	</body>
</html>