<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="leftbar" class="span-6">
	<a href="${ctx}/">首页</a>
	<a href="${ctx}/file/file.action">文件列表</a>
	<a href="${ctx}/file/file-upload.action">文件上传</a>
	<a href="${ctx}/category/category.action">文件分类</a>
	<a href="${ctx}/category/store.action">商店列表</a>
	<a href="${ctx}/category/shelf.action">货架列表</a>
	<a href="${ctx}/category/market.action">市场列表</a>
	<a href="${ctx}/home.action?l=ZH">商店首页</a>
	<a href="${ctx}/home!adXml.action">广告输出演示</a>
	<a href="${ctx}/log/log!list.action">日志演示</a>
</div>