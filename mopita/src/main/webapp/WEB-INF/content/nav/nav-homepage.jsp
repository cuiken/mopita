<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>导航首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<style>
.thumbnails {
	margin-left: 0;
	list-style: none;
}

.thumbnails>li {
	float: left;
	margin-bottom: 0;
	margin-left: 0;
	display: inline;
}

li {
	line-height: 18px;
}

ul,ol {
	margin: 0 0 0 25px;
	padding: 0;
}

.thumbnail {
	border: 0 solid #FFFFFF;
	border-radius: 4px 4px 4px 4px;
	box-shadow: 0 0 0 rgba(0, 0, 0, 0.075);
	display: block;
	line-height: 1;
	padding: 0;
}

.thumbnail>img {
	display: block;
	margin-left: auto;
	margin-right: auto;
	max-width: 100%;
}

img {
	border: 0 none;
	max-width: 100%;
	vertical-align: middle;
}

.footer {
	margin-top: 45px;
	padding: 35px 0 36px;
	border-top: 1px solid #E5E5E5;
}
</style>
</head>
<body>
	<section id="thumbnails">
		<div id="top">
			<ul class="thumbnails">
				<s:if test="tops.template==1">
					<%@include file="/common/nav-top/nav-top-temp.jsp"%>
				</s:if>
				<s:if test="tops.template==2">
					<%@include file="/common/nav-top/nav-top-temp2.jsp"%>
				</s:if>
				<s:if test="tops.template==3">
					<%@include file="/common/nav-top/nav-top-temp3.jsp"%>
				</s:if>
				<s:if test="tops.template==4">
					<%@include file="/common/nav-top/nav-top-temp4.jsp"%>
				</s:if>
			</ul>
		</div>
		<div id="center">
			<ul class="thumbnails">
				<s:if test="centerLeft.template==1">
					<%@include file="/common/nav-cl/nav-temp1.jsp"%>
				</s:if>
				<s:if test="centerLeft.template==2">
					<%@include file="/common/nav-cl/nav-temp2.jsp"%>
				</s:if>
				<s:if test="centerLeft.template==3">
					<%@include file="/common/nav-cl/nav-temp3-top2.jsp"%>
				</s:if>
				<s:if test="centerLeft.template==4">
					<%@include file="/common/nav-cl/nav-temp4.jsp"%>
				</s:if>
				
				<s:if test="centerRight.template==1">
					<%@include file="/common/nav-cr/nav-temp1.jsp"%>
				</s:if>
				<s:if test="centerRight.template==2">
					<%@include file="/common/nav-cr/nav-temp2.jsp"%>
				</s:if>
				<s:if test="centerRight.template==3">
					<%@include file="/common/nav-cr/nav-temp3-top2.jsp"%>
				</s:if>
				<s:if test="centerRight.template==4">
					<%@include file="/common/nav-cr/nav-temp4.jsp"%>
				</s:if>
			</ul>
		</div>
		<div id="bottom">
			<ul class="thumbnails">
				<%@include file="/common/nav-bottom/nav-temp4.jsp"%>
				
				<%@include file="/common/nav-bottom/nav-temp3-top2.jsp"%>
			</ul>
		</div>
	</section>
	<script src="${ctx}/css/bootstrap/2.0.3/js/bootstrap.min.js"
		type="text/javascript"></script>
</body>
</html>