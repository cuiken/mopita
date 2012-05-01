<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>货架文件管理</title>
	<link rel="stylesheet" href="${ctx}/js/jquery/themes/base/jquery.ui.all.css">
	<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.core.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.droppable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.sortable.js"></script>
	<script src="${ctx}/js/jquery/ui/jquery.ui.accordion.js"></script>
	<link rel="stylesheet" href="${ctx}/css/demos.css">
	<style>
	h1 { padding: .2em; margin: 0; }
	#products { float:left; width: 500px; margin-right: 2em; }
	#cart { width: 200px; float: left; }
	/* style the list to maximize the droppable hitarea */
	#cart ol { margin: 0; padding: 1em 0 1em 3em; }
	</style>
	<script>
	$(function() {
		
		$( "#products li" ).draggable({
			revert: "invalid",
			containment: $( "#demo-frame" ).length ? "#demo-frame" : "document",
			appendTo: "body",
			helper: "clone",
			cursor: "move"
		});
		$( "#cart ol" ).droppable({
			activeClass: "ui-state-default",
			hoverClass: "ui-state-hover",
			accept: ":not(.ui-sortable-helper)",
			drop: function( event, ui ) {
				$( this ).find( ".placeholder" ).remove();
				//$(ui.draggable).remove();
			//	$( "<li></li>" ).text( ui.draggable.text() ).appendTo( this );
				recyleItem(ui.draggable);
			}
		}).sortable({
			items: "li:not(.placeholder)",
			sort: function() {
				// gets added unintentionally by droppable interacting with sortable
				// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
				$( this ).removeClass( "ui-state-default" );
			}
		});

		$("#products ol").droppable({
			accept:"#cart li",		
			drop:function(event,ui){
				deleteItem(ui.draggable);
			//	$(ui.draggable).remove();
			//	$( "<li class='ui-draggable'></li>" ).text(ui.draggable.text()).appendTo(this);
			}
		});

		function deleteItem($item){
			$item.appendTo("#products ol");
		}
		function recyleItem($item){
			$item.appendTo("#cart ol");
		}
	});
	</script>
</head>
<body>

<div class="demo">
	
<div id="products">
	<h1 class="ui-widget-header">仓库文件</h1>	
	<div class="ui-widget-content">
		<ol>
			<li>Lolcat Shirt</li>
			<li>Cheezeburger Shirt</li>
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
	<h1 class="ui-widget-header">已上架文件</h1>
	<div class="ui-widget-content">
		<ol>
			<li class="placeholder">文件拖放之这里</li>
		</ol>
	</div>
</div>

</div><!-- End demo -->



<div class="demo-description">
<p>请将仓库内的文件拖放之货架，货架内文件可上下拖动排序.</p>
</div><!-- End demo-description -->

</body>
</html>
