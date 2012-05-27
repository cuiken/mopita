<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="leftbar" class="span-6">

	<shiro:user>
		<shiro:hasPermission name="user:view">
			<a href="${ctx}/account/user.action">用户列表</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:edit">
			<a href="${ctx}/account/group.action">权限组列表</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="file:view">
			<a href="${ctx}/file/file.action">文件列表</a>
			<a href="${ctx}/file/file-upload.action">文件上传</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="category:view">
			<a href="${ctx}/category/category.action">文件分类</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="store:view">
			<a href="${ctx}/category/store.action">商店列表</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="shelf:view">
			<a href="${ctx}/category/shelf.action">货架列表</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="market:view">
			<a href="${ctx}/category/market.action">市场列表</a>
		</shiro:hasPermission>	
		<a href="${ctx}/logout.action">退出登录</a>
	</shiro:user>
	<a href="${ctx}/home.action?l=ZH">商店首页演示</a>
	<a href="${ctx}/home!adXml.action">广告输出演示</a>
	<a href="${ctx}/log/log!list.action">日志演示</a>
	<shiro:guest>
		<a href="${ctx}/login.action">登录</a>
		
	</shiro:guest>
</div>