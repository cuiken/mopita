<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>文件上下架管理</title>
		
		<style type="text/css">
			@import "http://ajax.googleapis.com/ajax/libs/dojo/1.5/dojo/resources/dojo.css";
			@import "http://ajax.googleapis.com/ajax/libs/dojo/1.5/dijit/themes/claro/claro.css";
			ul{
				border: 3px solid #ccc;
				padding: 2em;
				margin: 5em;
				float: left;
				cursor: default;
			}
			.dojoDndItemOver{
				background: #ededed;
				cursor: pointer;
			}
			.dojoDndItemSelected {
				background: #ccf; 
			}
			.dojoDndItemAnchor{
				background: #ccf;
			}
		</style>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.5/dojo/dojo.xd.js" djConfig="parseOnLoad: true"></script>
		<script type="text/javascript">
			dojo.require("dojo.dnd.Source");
		</script>
	</head>
	<body class="claro">
		<ul dojoType="dojo.dnd.Source">
			<li class="dojoDndItem">Item 1</li>
			<li class="dojoDndItem">Item 2</li>
			<li class="dojoDndItem">Item 3</li>
			<li class="dojoDndItem">Item 4</li>
			<li class="dojoDndItem">Item 5</li>
			<s:iterator value="remainFiles">
				<li class="dojoDndItem">${name}</li>
			</s:iterator>
		</ul>
		<ul dojoType="dojo.dnd.Source">
			<li class="dojoDndItem">Item A</li>
			<li class="dojoDndItem">Item B</li>
			<li class="dojoDndItem">Item C</li>
			<li class="dojoDndItem">Item D</li>
			<li class="dojoDndItem">Item E</li>
			<s:iterator value="onShelfFiles">
				<li class="dojoDndItem">${file.name}</li>
			</s:iterator>
		</ul>
	</body>
</html>