<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="inc/meta.jsp"%>
<%@include file="inc/common.jsp"%>
<%@include file="inc/jquerymobile.jsp"%>
<html>
<head>
<title><spring:message code="root.title" /></title>
</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1>咪喱机器人主页</h1>
		</div>

		<div data-role="main" class="ui-content">
			<h2>欢迎...最新消息</h2>
			<ul data-role="listview" data-inset="true">
				<li><a href="#">
						<h2>Google Chrome</h2>
						<p>Google Chrome 是免费的开源 web 浏览器。发布于 2008 年。</p>
				</a> <a href="#">文本信息</a></li>
				<li><a href="#">
						<h2>Mozilla Firefox</h2>
						<p>Firefox 是来自 Mozilla 的 web 浏览器。发布于 2004 年。</p>
				</a> <a href="#">文本信息</a></li>
			</ul>
		</div>

		<jsp:include page="inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>
