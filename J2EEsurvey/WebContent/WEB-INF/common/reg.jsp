<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册页面</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-theme.min.css"
	type="text/css">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="js/bootstrap3-validation.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
</head>
<body style="text-align: center;">
	<div style="width: 50%; margin: 0 auto; text-align: left;">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">新用户注册</h3>
			</div>
			<div class="panel-body">
				<form id="regForm" method="post" role="form">
					<div class="form-group">
						<input type="text" name="name" check-type="required"
							required-message="请输入姓名" class="form-control" id="Name"
							placeholder="请输入姓名">
					</div>
					<div class="form-group">
						<input type="text" name="email" check-type="mail required"
							required-message="请输入邮箱" class="form-control" id="Email"
							placeholder="请输入电子邮箱地址">
					</div>
					<div class="form-group">
						<input type="password" check-type="required" name="password"
							class="form-control" id="Password" required-message="请输入密码"
							placeholder="请输入密码,至少六位，首尾请勿输入空格" minlength="6">
					</div>
					<div class="form-group">
						<input type="password" check-type="required"
							required-message="请确认密码" class="form-control"
							id="PasswordConfirm" placeholder="请再次输入密码" minlength="6">
					</div>
					<div class="form-group">
						<input type="text" check-type="required" name="nickname"
							class="form-control" id="Password" required-message="请输入昵称"
							placeholder="请输入昵称">
					</div>
					<div class="form-group">
						<span id="error-text" style="color: #FF0000;"></span>
					</div>
					<p style="text-align: center;">
						<button id="regbtn" type="button" style="width: 49%"
							class="btn  btn-lg btn-primary">注册</button>
						<button id="loginbtn" style="width: 49%"
							class="btn btn-lg btn-primary" type="button">登录</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>