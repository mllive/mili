<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/meta.jsp"%>
<title><spring:message code="root.title" /></title>
<%@include file="../inc/jquerymobile.jsp"%>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<a href="/robot"
				class="ui-btn ui-corner-all ui-shadow ui-icon-home ui-btn-icon-left">主页</a>
			<h1>个人主页</h1>
			<a href="javascript:void(0)" onclick="window.location.reload();"
				class="ui-btn ui-corner-all ui-shadow ui-icon-refresh ui-btn-icon-left">刷新</a>
		</div>
		<div data-role="main" class="ui-content" align="center">
			<p>欢迎${user.username}</p>
			<div align="center">
				<a href="/robot/stock/list" class="ui-btn ui-btn-inline ui-shadow">我的股票</a>
				<a href="#" class="ui-btn ui-btn-inline ui-shadow">退出登录</a>
			</div>
		</div>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>
