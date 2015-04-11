<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>系统登录</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<script type="text/javascript" src="js/bootstrap3-validation.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>

	<div class="container">
		<form style="width: 30%; margin: 0 auto"
			action="${pageContext.request.contextPath }/LoginAction_doLogin"
			method="post" role="form" id="loginform">
			<h2 class="form-signin-heading">请登录系统</h2>

			<div class="form-group">
				<input type="text" class="form-control" name="email"
					check-type="mail required" required-message="请输入邮箱"
					value="pingansheng_90@126.com" placeholder="您的邮箱地址">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="password"
					check-type="required" placeholder="您的密码" value="38383386">
			</div>

			<div class="form-group">
				<span id="error-text" style="color: #FF0000;">${info}</span>
			</div>
			<p style="text-align: center;">
				<button id="button" style="width: 49%" class="btn btn-lg btn-primary"
					type="submit">登录</button>
				<button id="regbutton" style="width: 49%" class="btn btn-lg btn-primary"
					type="button">注册</button>
			</p>
		</form>
	</div>
	<!-- /container -->
</body>
</html>

