<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>用户反馈列表</title>
		<link href="${ctx}/css/home.css" type="text/css" rel="stylesheet">
		<script src="${ctx}/js/table.js"></script>
		<script>
			$(function(){
				$("#feedback-tab").addClass("active");
			})
		</script>
	</head>
	<body>
		<form id="mainForm" action="feedback.action" method="get">
			<h1>用户反馈列表</h1>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th>反馈内容</th>
					<th>联系方式</th>
					<th>状态</th>
					<th>反馈时间</th>
					<th>处理时间</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="page.result">
					<tr>
						<td><a href="${ctx}/report/feedback!input.action?id=${id}">
						<c:choose>
							<c:when test="${fn:length(content)>25}">
								${fn:substring(content,0,25)}...
							</c:when>
							<c:otherwise>
								${content}
							</c:otherwise>
						</c:choose>
						
						</a></td>
						<td>${contact}</td>
						<td>
							<s:if test="status==0">
								待处理
							</s:if>
							<s:elseif test="status==1">
								已处理
							</s:elseif>
						<td>${createTime}</td>
						<td>${modifyTime}</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>		
							
			<%@include file="/common/page.jsp" %>
		
		</form>
		
	</body>
</html>