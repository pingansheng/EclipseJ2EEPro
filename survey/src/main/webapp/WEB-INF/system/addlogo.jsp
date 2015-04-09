<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加调查Logo</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//声明验证的form
		$("#editForm").validation();
		//编辑Survey
		$("#editForm").submit(function(event) {
			$("#err").hide(0);
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
	<center style="font-weight: bold;font-size: 30px">设置Logo</center>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
		<form role="form" id="editForm" enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/SurveyConfig_addLogo"
			namespace="/" method="post">
			<input type="hidden" name="sid" value="${sid}" />
			<div class="form-group">
				<label for="logo">调查Logo,请上传图片文件</label>
				<input class="form-control input-sm"
					check-type="required" required-message="请选择Logo文件" id="logo"
					type="file" name="logoPhoto"/>
			</div>
			<div id="err" style="color: red;"><s:fielderror fieldName="logoPhoto" /></div>
			<button id="button" type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
