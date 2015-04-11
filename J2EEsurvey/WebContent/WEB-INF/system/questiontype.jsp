<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>题型选择页面</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#quesTypeSelect").change(function(){
			if($(this).val()=="-1"){
				return false;
			}
			$("#QuestionTypeForm").submit();	
		})
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold;font-size: 30px">选择问题类型</center>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
		<form role="form" id="QuestionTypeForm"
			action="${pageContext.request.contextPath }/QuestionAction_toDesignQuestionPage"
			namespace="/" method="post">
			<input type="hidden" name="pid" value="${pid}" /> <input
				type="hidden" name="sid" value="${sid}" /> <select
				name="questionType" id="quesTypeSelect" class="form-control">
				<option selected="selected" value="-1">--请选择问题类型--</option>
				<option value="0">非矩阵式横向单选</option>
				<option value="1">非矩阵式纵向单选</option>
				<option value="2">非矩阵式横向复选</option>
				<option value="3">非矩阵式纵向复选</option>
				<option value="4">非矩阵式下拉列表</option>
				<option value="5">非矩阵式文本框</option>
				<option value="6">矩阵式单选</option>
				<option value="7">矩阵式复选</option>
				<option value="8">矩阵式下拉列表</option>
			</select>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
