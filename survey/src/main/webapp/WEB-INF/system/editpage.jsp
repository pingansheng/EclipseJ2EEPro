<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑页面</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//声明验证的form
		$("#editForm").validation();
		//编辑Survey
		$("#editForm").submit(function(event) {
			if ($(this).valid() == false) {
				return false;
			} else {
				$("#button").text("正在提交……");
				$("#button").attr('disabled', true);
				return true;
			}
		});
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold;font-size: 30px">编辑页面</center>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
		<form role="form" id="editForm"
			action="${pageContext.request.contextPath }/PageAction_saveOrUpdatePage"
			namespace="/" method="post">
			<input type="hidden" name="id" value="${model.id}" />
			<input type="hidden" name="sid" value="${sid}" />
			<div class="form-group">
				<label for="title">页面标题</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="title"
					type="text" name="title" placeholder="请输入页面标题"
					value="${model.title }" />
			</div>
			<div class="form-group">
				<label for="nextText">页面描述</label> <input
					class="form-control input-sm" check-type="required"
					required-message="请输入内容" id="nextText" type="text" name="description"
					placeholder="请输入页面描述" value="${model.description}" />
			</div>
			<button id="button" type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
