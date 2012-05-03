<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>货架</title>
		<%@include file="/common/script.jsp" %>
		<script src="${ctx}/js/jquery/jquery-1.7.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#message").fadeOut(3000);
				var sid=$("#store").val();
				
				var getData=function(sid){
						$.ajax({
							dataType:"json",
							url:"shelf!filterShelf.action?checkedStoreId="+sid,
							success:function(data){
								
								var html="";
								$.each(data,function(i,val){		
									html+="<tr><td>"+val.name+"</td><td>"
									+val.description
									+"</td><td><a href=shelf!manage.action?id="
									+val.id+">管理</a></td></tr>";
								
								});
								$("#content tr").after(html);
								
							}
						});
				};
				
				getData(sid);
				$("#store").change(function(){
					$("#content tr:not(:first)").remove();
					getData($(this).children('option:selected').val());
				});
			});
		</script>
	</head>
	<body>
		<form action="shelf.action" method="get">
		<div class="container">
		<%@include file="/common/header.jsp" %>
		<%@include file="/common/left.jsp" %>
			<div class="span-18 last prepend-top">
			<c:if test="${not empty actionMessages}">
				<div id="message" class="success">${actionMessages}</div>	
			</c:if>
			<h3>货架列表</h3>
			商店:<s:select list="allStores" listKey="id" listValue="name" name="" id="store"/>
			<table id="content">
				<tr>
					<th>货架名称</th>
					<th>货架描述</th>
					<th>操作</th>
				</tr>	
				<!--  		
				<tr>
						<td>${name}</td>
						<td>${description}</td>
						<td>
							<a href="shelf!manage.action?id=${id}">管理</a>
							<a href="shelf!input.action?id=${id}">修改</a>
							<a href="shelf!delete.action?id=${id}">删除</a>
						</td>
				</tr>
				-->
			</table>		
			<a href="shelf!input.action">创建货架</a>			
			</div>
		</div>
		</form>
		
	</body>
</html>