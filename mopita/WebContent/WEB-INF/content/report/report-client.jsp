<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>客户端统计</title>
		<%@include file="/common/script.jsp" %>

	</head>
	<body>
		<form action="report!client.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<h3>客户端日报</h3>
			<table>
				<thead>
				<tr>
					<th>日期</th>
					<th>启用次数</th>
					<th>总用户量</th>
					<th>启用用户量</th>
					<th>新增用户量</th>
					<th>总下载量</th>
					<th>内容引导下载量</th>
					<th>分享引导下载量</th>
					<th>其他途径下载量</th>
					<th>商店访问次数</th>
					<th>商店访问用户量</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="cpage.result">
					<tr>
						<td>${createTime}</td>
						<td>${openCount}</td>
						<td>${totalUser}</td>
						<td>${openUser}</td>
						<td>${incrementUser}</td>
						<td>${totalDownload}</td>
						<td>${downByContent}</td>
						<td>${downByShare}</td>
						<td>${downByOther}</td>
						<td>${visitStoreCount}</td>
						<td>${visitStoreUser}</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>		
							
			<div>
				${cpage.totalItems}条记录 ,第${cpage.pageNo}页, 共${cpage.totalPages}页
				<a href="?cpage.pageNo=1">首页</a>
				<s:if test="cpage.prePage"><a href="?cpage.pageNo=${cpage.prePage}">上一页</a></s:if>
				<s:if test="cpage.nextPage"><a href="?cpage.pageNo=${cpage.nextPage}">下一页</a></s:if>
				<a href="?cpage.pageNo=${cpage.totalPages}">末页</a>
			</div>
			</div>
		</div>
		</form>
		
	</body>
</html>