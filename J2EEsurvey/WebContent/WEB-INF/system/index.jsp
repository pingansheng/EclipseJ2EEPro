<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎使用PAS问卷调查系统</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="base-container">
		<div class="jumbotron base-container">
			<h2>欢迎使用PAS问卷调查系统!</h2>
			<p>
				欢迎你，<font style="color: Blue">${user.name}</font>
			</p>
			<p>
				<a class="btn btn-primary btn-lg" role="button"
					href="${pageContext.request.contextPath }/SurveyAction_newSurvey">创建调查</a>
				<a class="btn btn-primary btn-lg"
					href="${pageContext.request.contextPath }/SurveyAction_mySurveys">我的调查</a>
			</p>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
