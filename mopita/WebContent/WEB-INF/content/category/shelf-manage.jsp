<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件上下架管理</title>
		<style type="text/css">
			@import "${ctx}/js/dojo-1.7.2/dojo/resources/dojo.css";
			@import "${ctx}/js/dojo-1.7.2/dojo/resources/dnd.css";
			@import "${ctx}/js/dojo-1.7.2/dndDefault.css";
			@import "${ctx}/js/dojo-1.7.2/dojo/robot.css";
			body {
				padding: 1em;
				background: #ededed;
			}
			.container {
				width: 150px;
				display: block;
			}

			.clear {
				clear: both;
			}
		</style>
		<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dojo/dojo.js" djConfig="parseOnLoad: true"></script>

		<script type="text/javascript">
		
			dojo.require("dojo.dnd.Source");

		</script>
	</head>
	<body>
	<table>
		<tr>
			<td>商店</td>
			<td><s:select list="allStores" listKey="id" listValue="name" theme="simple"></s:select></td>
			<td>货架</td>
			<td><select></select></td>
		</tr>
		
	</table>
	<div id="source" style="float: left; margin: 5px;">
		<h1>仓库文件</h1>
		<div dojoType="dojo.dnd.Source" class="container">

			<s:iterator value="remainFiles">
				<div class="dojoDndItem">${name}</div>
			</s:iterator>
		</div>
	</div>
	<div id="target" style="float: left; margin: 5px;">	
		<h1>货架文件</h1>
		<div dojoType="dojo.dnd.Source" class="container">
			<s:iterator value="onShelfFiles">
				<div class="dojoDndItem">${file.name}</div>
			</s:iterator>
		</div>
	</div>	
	</body>
</html>