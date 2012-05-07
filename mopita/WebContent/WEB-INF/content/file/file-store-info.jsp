<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件-市场-修改</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#submitBtn").click(function(){
					
					$("#inputForm").submit();
					window.close();
				});
			
			})
		</script>
	</head>
	<body>
	<form id="inputForm" action="file-store-info!save.action" method="post">
		<fieldset>
			<legend>多语言描述信息</legend>
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<s:iterator value="fileInfo" status="info">
				<input type="hidden" name="fileInfo[${info.index}].id" value="${id}"/>
				<input type="hidden" name="fileInfo[${info.index}].theme.id" value="${theme.id}"/>
				<input type="hidden" name="fileInfo[${info.index}].store.id" value="${store.id}"/>
				<input type="hidden" name="fileInfo[${info.index}].language" value="${language}"/>
				<input type="hidden" name="fileInfo[${info.index}].price" value="${price}"/>
				<div>
					<font color="blue">${language}</font>
				</div>
				<div>
					<label for="title" class="field">文件标题:</label>
					<input type="text" id="title" name="fileInfo[${info.index}].title" value="${title}" size="25" maxlength="50"/>
				</div>
				<div>
					<label for="description" class="field">文件描述:</label>
					<input id="description" name="fileInfo[${info.index}].description" type="text" size="25" maxlength="255" value="${description}"/>
				</div>
			</s:iterator>
		</fieldset>
		<div>
			<input type="button" id="submitBtn" value="保存">&nbsp;
			<input class="button" type="button" value="返回" onclick="window.close();">
		</div>
	</form>		
	</body>

</html>	
		