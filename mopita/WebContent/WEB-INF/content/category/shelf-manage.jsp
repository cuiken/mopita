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
	function openwindow(id){
		window.open("../file/file-store-info.action?themeId="+id+"&storeId="+$("#store").val(),'newwindow');
	}
	
	$(function() {
		$("#message").fadeOut(3000);
		$( "#sortable1, #sortable2" ).sortable({
			connectWith: ".connectedSortable"
		}).disableSelection();
		$("#btn").click(function(){
			var checked=$("#sortable2").children();
			for(var i=0;i<checked.length;i++){
				$("#cart").append("<input type='hidden' name='checkedFileIds' value="+checked[i].id+">");
			}
			$("#cart").append("<input type='hidden' name='selectId' value="+$("#shelf").val()+">");
			$("#manageForm").submit();
		});
		
		$("#store option[value="+${store.id}+"]").attr("selected",true);
		var sid=$("#store").val();
			
		var getData=function(sid){
			$.ajax({
				dataType:"json",
				url:"shelf!filterShelf.action?checkedStoreId="+sid,
				success:function(data){	
					var opt="<option value=0>--请选择--</option>";						
					$.each(data,function(i,val){
							
						opt+="<option value="+val.id+">"+val.name+"</option>";
								
					});
					
					$("#shelf").append(opt);
					$("#shelf option[value="+${id}+"]").attr("selected", true);
				}
			});
		};
			
		
		var getRemainFileData=function(sfid){
			$.ajax({
				dataType:"json",
				url:"shelf!getRemainFile.action?id="+sfid,
				success:function(data){
					var li="";
					$.each(data,function(i,val){
						li+="<li id="+val.id+">"+val.name+"</li>";
					});
					$("#sortable1").append(li);
				}
			});
		};
		var getShelfFileData=function(sfid){
			$.ajax({
				dataType:"json",
				url:"shelf!getOnShelfFile.action?id="+sfid,
				success:function(data){
					var li="";
					
					$.each(data,function(i,val){
						li+="<li id="+val.id+" onclick=openwindow("+val.id+");><a>"+val.name+"</a></li>";
					});
					$("#sortable2").append(li);
				}		
			});
		};
		
		getData(sid);
		
		getRemainFileData(${id});
		getShelfFileData(${id});
		$("#store").change(function(){
			$("#shelf option").remove();
			getData($(this).children('option:selected').val());
			$("#sortable1 li").remove();
			$("#sortable2 li").remove();
		});
		
		$("#shelf").change(function(){
			$("#sortable1 li").remove();
			$("#sortable2 li").remove();
			getRemainFileData($(this).children('option:selected').val());
			getShelfFileData($(this).children('option:selected').val());
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
<c:if test="${not empty actionMessages}">
	<div id="message" class="success">${actionMessages}</div>	
</c:if>
<div>
商店名称:<s:select list="allStores" listKey="id" listValue="name" name="" id="store" /> &nbsp;
货架名称:<select id="shelf"></select>
</div>
<div id="products">
	<h3>仓库文件</h3>	
	<div class="ui-widget-content">
		<ol id="sortable1" class="connectedSortable" style="min-height: 300px;">

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


</form>

</body>
</html>
