<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>导航更多</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${ctx}/css/bootstrap/2.0.3/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/nav/navhome.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="nav-homepage!more.action">
		<s:iterator value="boards">
			<h2><s:property value="name"/></h2>
			<s:iterator value="tags">
				<h3><s:property value="name"/></h3>
				<s:iterator value="navigators">
					<a href="${navAddr}"><s:property value="name"/></a>
				</s:iterator>
			</s:iterator>
			<s:iterator value="navigators">
				<a href="${navAddr}"><s:property value="name"/></a>
			</s:iterator>
		</s:iterator>
	</form>
</body>
</html>