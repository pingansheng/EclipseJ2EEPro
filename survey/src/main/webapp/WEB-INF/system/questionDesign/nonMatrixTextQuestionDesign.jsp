<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>非矩阵式文本框问题设计</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//验证输入
		var checkInput=function(){
			var title=$.trim($("#title").val());
			var err="<ul>";
			if(title==""){
				err+="<li>标题为空</li>";
			}
			return err+="</ul>";
		};
		
		$("#editForm").submit(function(event) {
			var errInfo=checkInput();
			if (errInfo!="<ul></ul>") {
				$("#errInfo").html(errInfo);
				return false;
			} else {
				$("#errInfo").text("");
				$("#button").text("正在提交……");
				$("#button").attr("disabled", true);
				return true;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
	<div style="text-align: center;font-weight: bold;">非矩阵式文本框问题</div>
		<form role="form" id="editForm"
			action="${requestContext.request.contextPath }QuestionAction_saveOrUpdateQuestion"
			namespace="/" method="post">
			<input type="hidden" name="id" value="${model.id}" /> <input
				type="hidden" name="questionType" value="${model.questionType}" />
			<input type="hidden" name="pid" value="${pid}" /> <input
				type="hidden" name="sid" value="${sid}" />
			<div class="form-group">
				<label for="title">问题标题:</label> <input
					class="form-control input-sm" id="title" type="text" name="title"
					placeholder="请输入问题标题" value="${model.title}" />
			</div>
			<div>
				<span id="errInfo" style="color: red;"></span>
			</div>
			<button id="button" type="submit" class="btn btn-primary btn-block" >提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>