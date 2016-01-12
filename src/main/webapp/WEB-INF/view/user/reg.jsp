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
		$("#reg").click(function(){
			var msg="";
			var username=$("#username").val();
			var password=$("#password").val();
			var password2=$("#password2").val();
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
			if(password2!=password){
				msg="两次输入的密码不一致！";
				popMsg(msg);
				$("#password").focus();
				return;
			}
			var formParam = $("#userForm").serialize();//序列化表格内容为字符串  
			$.ajax({  
				type:'post',      
				url:'save',  
				data:formParam,  
				cache:false,  
				dataType:'json',  
				success:function(data){
					if(data.success){
						if(1==data.msg){
							popMsg("注册失败，用户名已存在！");
						}else{
							popMsg("注册成功！");
							window.location="i/"+data.obj.username;
						}
					}else{
						popMsg("注册失败！");
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
			<h1>新用户注册</h1>
		</div>
		<div data-role="main" class="ui-content" align="center">
			<form method="post" id="userForm" action="">
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
