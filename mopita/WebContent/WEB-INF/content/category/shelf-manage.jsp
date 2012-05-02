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
	
	#sortable1 li, #sortable2 li { margin: 0 5px 0px 15px; padding: 0px; font-size: 1.2em; width: 150px; }
	
	</style>

	<script>
	
	$(function() {
		$( "#sortable1, #sortable2" ).sortable({
			connectWith: ".connectedSortable"
		}).disableSelection();
		$("#btn").click(function(){
			var checked=$("#sortable2").children();
			for(var i=0;i<checked.length;i++){
				$("#cart").append("<input type='hidden' name='checkedFileIds' value="+checked[i].id+">");
			}
			$("#manageForm").submit();
		});
		
	});
	</script>
</head>
<body>
<form id="manageForm" action="shelf!saveFile.action" method="post">
<div class="container">
<input type="hidden" name="id" value="${id}"/>
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
			<s:iterator value="remainFiles">
				<li id="${id}">${name}</li>
			</s:iterator>
		</ol>
	</div>
</div>

<div id="cart">
	<h3>已上架文件</h3>
	<div class="ui-widget-content" >
		<ol id="sortable2" class="connectedSortable" style="min-height: 300px;">
			<s:iterator value="onShelfFiles">
				<li id="${id}">${name}</li>
			</s:iterator>
		</ol>
	</div>
</div>
<div>
<input type="button" value="保存" id="btn"/>
</div>
</div>

</div>


</form>

</body>
</html>
