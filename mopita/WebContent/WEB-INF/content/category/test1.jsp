<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>货架文件管理</title>
	<%@include file="/common/script.jsp" %>
	<link rel="stylesheet" href="${ctx}/js/jquery/themes/base/jquery.ui.all.css">
	<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.core.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.droppable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.sortable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.accordion.js"></script>

	<style>
	<style>
	h1 { padding: .2em; margin: 0; }
	#products { float:left; width: 200px; margin-right: 2em; }
	#cart { width: 200px; float: left;}
	/* style the list to maximize the droppable hitarea */
	#cart ol { margin: 0; padding: 1em 0 1em 3em; }
	#sortable1 li, #sortable2 li { margin: 0 5px 5px 5px; padding: 2px; font-size: 1.2em; width: 200px; }
	</style>

	<script>
	
	$(function() {
		$( "#sortable1, #sortable2" ).sortable({
			connectWith: ".connectedSortable"
		}).disableSelection();
		$("#btn").click(function(){
			var aa=$("#sortable2").children();
			alert(aa[0].id);
		});
		
	});
	</script>
</head>
<body>

<div class="container">
<%@include file="/common/header.jsp" %>
<%@include file="/common/left.jsp" %>
<div class="span-18 last prepend-top">
<div>
商店名称:<select><option>----</option></select> &nbsp;
货架名称:<select><option>----</option></select>
</div>
<div id="products">
	<h3>仓库文件</h3>	
	<div class="ui-widget-content">
		<ol id="sortable1" class="connectedSortable" style="min-height: 300px;">
			<li id="1">小狗的天空</li>
			<li id="2">Cheezeburger Shirt</li>
			<li>Buckit Shirt</li>
			<li>Zebra Striped</li>
			<li>Black Leather</li>
			<li>Alligator Leather</li>
			<li>iPhone</li>
			<li>iPod</li>
			<li>iPad</li>
		</ol>
	</div>
</div>

<div id="cart">
	<h3>已上架文件</h3>
	<div class="ui-widget-content" >
		<ol id="sortable2" class="connectedSortable" style="min-height: 300px;">
		
		</ol>
	</div>
</div>
<div>
<input type="button" value="保存" id="btn"/>
</div>
</div>

</div>




</body>
</html>
