<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>内容统计</title>
		<%@include file="/common/script.jsp" %>
		<link href="${ctx}/css/home.css" type="text/css" rel="stylesheet">
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script src="${ctx}/js/table.js"></script>
	</head>
	<body>
		<form id="mainForm" action="report!content.action" method="post">
		<input type="hidden" name="ctpage.pageNo" id="pageNo" value="${ctpage.pageNo}"/>
		<input type="hidden" name="ctpage.orderBy" id="orderBy" value="${ctpage.orderBy}"/>
		<input type="hidden" name="ctpage.orderDir" id="order" value="${ctpage.orderDir}"/>
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<h3>内容日报</h3>
			<div id="filter">
				内容: <input type="text" name="filter_LIKES_themeName" value="${param['filter_LIKES_themeName']}" size="20"/>
				&nbsp;日期<input type="text" name="filter_LIKES_logDate" value="${param['filter_LIKES_logDate']}" size="20"/>
				<input type="button" value="搜索" onclick="search();"/>
			</div>
			<table>
				<thead>
				<tr>
					<th><a href="javascript:sort('createTime','desc')">日期</a></th>
					<th><a href="javascript:sort('themeName','desc')">内容</a></th>
					<th><a href="javascript:sort('totalVisit','desc')">访问总量</a></th>
					<th><a href="javascript:sort('visitByAd','desc')">广告访问量</a></th>
					<th><a href="javascript:sort('visitByStore','desc')">商店访问量</a></th>
					<th><a href="javascript:sort('totalDown','desc')">下载总量</a></th>
					<th>市场下载量</th>
					<th><a href="javascript:sort('downByStore','desc')">自有下载量</a></th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="ctpage.result">
					<tr>
						<td>${logDate}</td>
						<td>${themeName}</td>
						<td>${totalVisit}</td>
						<td>${visitByAd}</td>
						<td>${visitByStore}</td>
						<td>${totalDown}</td>
						<td>
							<div>
								<s:iterator value="downByPerMarket">
									
									<li>${marketName} &nbsp; ${totalDown}</li>								
									
								</s:iterator>
							</div>
						</td>
						<td>${downByStore}</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>		
							
			<div class="pagination">
				${ctpage.totalItems}条记录 , 第${ctpage.pageNo}页, 共${ctpage.totalPages}页
				<a href="javascript:jumpPage(1)">首页</a>
				<s:iterator value="sliders" id="num">	
					<s:if test="ctpage.pageNo==#num">
						<span class="current">${num}</span>
					</s:if><s:else>		
					<a href="javascript:jumpPage(${num})"><s:property/></a>
					</s:else>
				</s:iterator>
				<a href="javascript:jumpPage(${ctpage.totalPages})">末页</a>		
			</div>
			</div>
		</div>
		</form>
		
	</body>
</html>