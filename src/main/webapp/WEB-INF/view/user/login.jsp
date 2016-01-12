<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/meta.jsp"%>
<title><spring:message code="root.title" /></title>
<%@include file="../inc/jquerymobile.jsp"%>
<script>
	$(document).ready(function(){
		$("#username").focus();
		$("#login").click(function(){
			var msg="";
			var username=$("#username").val();
			var password=$("#password").val();
			if(username==''){
				msg="您还没有输入用户名！";
				popMsg(msg);
				$("#username").focus();
				return;
			}
			if(password==''){
				msg="您还没有输入密码！";
				popMsg(msg);
				$("#password").focus();
				return;
			}
			var formParam = $("#userForm").serialize();//序列化表格内容为字符串  
			$.ajax({  
				type:'post',      
				url:'robot/user/login',  
				data:formParam,  
				cache:false,  
				dataType:'json',  
				success:function(data){
					if(data.success){
						if(1==data.msg){
							popMsg("用户或密码错误！");
						}else{
							popMsg("登录成功！");
							window.location="robot/user/i/"+data.obj.username;
						}
					}else{
						popMsg("登录失败！");
					}
				}  
			}); 
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
			<form method="post" id="userForm" action="">
				<label for="username" class="ui-hidden-accessible">账号：</label>
				<input type="text" name="username" id="username" placeholder="账号..." data-clear-btn="true">
				<label for="password" class="ui-hidden-accessible">密码：</label>
				<input type="password" name="password" id="password" placeholder="密码..." data-clear-btn="true">
				<input type="reset" data-inline="true"
					value="重置">
				<input type="button" id="login" data-inline="true"
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
