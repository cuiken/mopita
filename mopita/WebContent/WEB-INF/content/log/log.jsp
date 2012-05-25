<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>日志列表</title>
		<%@include file="/common/script.jsp" %>

	</head>
	<body>
		<form action="log!list.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<h3>日志演示</h3>
			<table>
				<tr>
					<th>日期</th>
					<th>IMEI</th>
					<th>IMSI</th>
					<th>商店类型</th>
					<th>下载方式</th>
					<th>环境语言</th>
					<th>客户端版本</th>
					<th>分辨率</th>
					<th>市场来源</th>
				</tr>
				<s:iterator value="logs">
					<tr>
						<td>${createTime}</td>
						<td>${imei}</td>
						<td>${imsi}</td>
						<td>${storeType}</td>
						<td>${downType}</td>
						<td>${language}</td>
						<td>${clientVersion}</td>
						<td>${resolution}</td>
						<td>${fromMarket}</td>
					</tr>
				</s:iterator>
				
			</table>
		
			
		
			</div>
		</div>
		</form>
		
	</body>
</html>