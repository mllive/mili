<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../inc/meta.jsp"%>
<%@include file="../inc/common.jsp"%>
<%@include file="../inc/jquerymobile.jsp"%>
<html>
<head>
<title><spring:message code="root.title" /></title>
<script>
	$(document).ready(function(){
		$("#username").focus();
		$("#save").click(function(){
			var msg="";
			var username=$("#username").val();
			var password=$("#password").val();
			var password2=$("#password2").val();
			if(username==''){
				msg="您还没有输入用户名！";
				popMsg(msg);
				return;
			}
			if(password==''){
				msg="您还没有输入密码！";
				popMsg(msg);
				return;
			}
			if(password2!=password){
				msg="两次输入的密码不一致！";
				popMsg(msg);
				return;
			}
			
			$.post(
				"demo_ajax_gethint.asp",
				{suggest:txt},
				function(result){
					$("span").html(result);
				}
			);
		});
		
		function popMsg(msg){
			$("#msgContent").text(msg);
			$("#popupMsg").popup('open');
		}
	});
</script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>用户登录</h1>
		</div>
		<div data-role="main" class="ui-content" align="center">
			<form method="post" action="save">
				<label for="username" class="ui-hidden-accessible">账号：</label>
				<input type="text" name="username" id="username" placeholder="账号..." data-clear-btn="true">
				<label for="password" class="ui-hidden-accessible">密码：</label>
				<input type="password" name="password" id="password" placeholder="密码..." data-clear-btn="true">
				<label for="password2" class="ui-hidden-accessible">确认密码：</label>
				<input type="password" name="password2" id="password2" placeholder="确认密码..." data-clear-btn="true">
				<input type="reset" data-inline="true"
					value="重置">
				<input type="button" id="save" data-inline="true"
					value="提交">
			</form>
		</div>
		<div data-role="popup" id="popupMsg" class="ui-content">
			<p id="msgContent"></p>
	    </div>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>
