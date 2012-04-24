<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件列表</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen-customized.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<!--[if lt IE 8]><link href="${ctx}/css/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection"><![endif]-->
		<link href="${ctx}/css/mini-web.css" rel="stylesheet"/>	
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/table.js"></script>
	</head>
	<body>
		<div class="container">
		<form id="mainForm" action="file.action" method="get">
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.orderDir" id="order" value="${page.orderDir}"/>
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<div class="span-18 last prepend-top">
		<div id="message"><s:actionmessage cssClass="success"/></div>
		<h4 class="prepend-top">文件列表</h4>
			<table>
				<tr>
					<th><a href="javascript:sort('name','asc')">文件名</a></th>
					<th>描述</th>
					<th>Market地址</th>
					<th>分类</th>
					<th><a href="javascript:sort('createTime','asc')">上传时间</a></th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<td>${name}</td>
						<td>${description}</td>
						<td>${marketURL}</td>
						<td>${categoryNames}</td>
						<td>${createTime}</td>
						<td>
							<a href="file!input.action?id=${id}">修改</a>
							<a href="file!delete.action?id=${id}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>	
		<div>
		</div>
			第${page.pageNo}页, 共${page.totalPages}页
			<a href="?page.pageNo=1">首页</a>
			<s:if test="page.prePage"><a href="?page.pageNo=${page.prePage}">上一页</a></s:if>
			<s:if test="page.nextPage"><a href="?page.pageNo=${page.nextPage}">下一页</a></s:if>
			<a href="?page.pageNo=${page.totalPages}">末页</a>
		</div>
		</form>
		
	</div>
	</body>
</html>