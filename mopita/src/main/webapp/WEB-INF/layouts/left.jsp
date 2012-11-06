<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
<shiro:hasPermission name="user:view">
	<h1>系统管理</h1>
	<div class="submenu">
		<a id="account-tab"href="${ctx}/account/user.action">帐号管理</a>
		<a id="group-tab" href="${ctx}/account/group.action">角色管理</a>
	</div>
</shiro:hasPermission>
	<h1>文件管理</h1>
	<div class="submenu">
		<a id="lock-tab" href="${ctx}/file/file.action">解锁文件</a>	
		<a id="category-tab" href="${ctx}/category/category.action">解锁类别</a>
		<a id="client-tab" href="${ctx}/file/funlocker-client.action">客户端文件</a>
		<a id="ccategory-tab" href="${ctx}/category/client-type.action">客户端类别</a>
	</div>
	<h1>文件上下架管理</h1>
	<div class="submenu">
		<a id="store-tab" href="${ctx}/category/store.action">商店列表</a>
		<a id="shelf-tab" href="${ctx}/category/shelf.action">货架列表</a>
		<a id="market-tab" href="${ctx}/category/market.action">市场列表</a>
	</div>
	<h1>报表管理</h1>
	<div class="submenu">
		<a id="reportc-tab" href="${ctx}/report/report-client.action">客户端日报</a>
		<a id="reportcn-tab" href="${ctx}/report/report-content.action">内容日报</a>
		<a id="feedback-tab" href="${ctx}/report/feedback.action">用户意见反馈</a>
	</div>
	<h1>导航管理</h1>
	<div class="submenu">
		<a href="${ctx}/nav/board.action">导航相关</a>
	</div>
</div>