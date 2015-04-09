<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//声明验证的form
		$("#editForm").validation();
		
		var postChange = function(form) {
			$.ajax({
				cache : true,
				type : "POST",
				url : "LoginAction_changePass",
				data : form.serialize(),
				async : false,
				error : function(request) {
					alert("请求错误，请重试");
				},
				success : function(data) {
					var status = data.status;
					if (status != null && status == "0") {
						// 成功
						alert("修改成功,请重新登录");
						window.location.href="LoginAction_logout"; 
					} else {
						var info = data.info;
						if (null != info) {
							alert(info);
						} else {
							alert("请求数据出错，请重试");
						}
					}
				}
			});

		};
		
		
		//修改密码
		$("#editForm").submit(function(event) {
			if ($(this).valid() == false) {
				return false;
			} else {
				$("#passChange").text("正在提交……");
				$("#passChange").attr('disabled', true);
				postChange($(this));
				$("#passChange").attr('disabled', false);
				return false;
			}
		});
	});
</script>
</head>
<body style="text-align: center;">
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div style="width: 50%; margin: 0 auto; text-align: left;">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">修改密码</h3>
			</div>
			<div class="panel-body">
				<form id="editForm" method="post" role="form">
					<div class="form-group">
						<input type="password" name="oldPass" check-type="required"
							required-message="请输入原密码" class="form-control" id="oldPass"
							placeholder="请输入原密码">
					</div>
					<div class="form-group">
						<input type="password" check-type="required" name="newPass"
							class="form-control" id="newPass" required-message="请输入新密码"
							placeholder="输入新密码，至少6位" minlength="6">
					</div>
					<div class="form-group">
						<input type="password" check-type="required"
							required-message="请确认新密码" class="form-control" name="passConfirm"
							id="passConfirm" placeholder="请确认新密码" minlength="6">
					</div>
					<p style="text-align: center;">
						<button id="passChange" class="btn btn-lg btn-primary btn-block" type="submit">修改密码</button>
					</p>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>