<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>编辑文件信息</title>
		<link href="${ctx}/css/showcase.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<link href="${ctx}/css/blueprint/print.css" type="text/css" rel="stylesheet" media="print" />
		<!--[if lt IE 8]><link href="${ctx}/css/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection"><![endif]-->
	</head>
	
	<body>
		<div class="container">
		<form id="inputForm" action="file-info!save.action" method="post">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
		<div class="span-18 last prepend-top">	
		<input type="hidden" value="${id}" name="id"/>
		<input type="hidden" value="${themeId}" name="themeId"/>
			<table>
				<tr>
					<td>语言:</td>
					<td>
						
						${language}
						<s:if test="id==null">
							<select name="language">
								<option value="ZH">中文</option>
								<option value="EN">英文</option>
								<option value="JP">日文</option>
							</select>
						</s:if>
					</td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><input type="text" name="title" class="required" value="${title}"></td>
				</tr>
				<tr>
					<td>描述:</td>
					<td><input type="text" name="description" class="required" value="${description}"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存"> &nbsp;
						<input class="button" type="button" value="返回" onclick="history.back()"/>
					</td>
				</tr>
		</table>	
		</div>
		</form>
		</div>	
	</body>
	
</html>