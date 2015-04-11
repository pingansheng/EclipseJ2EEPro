$(document).ready(function() {
	// 声明验证的form
	$("form").validation();

	var passIsvalid = function() {
		var pass1 = $.trim($("#Password").val())
		var pass2 = $.trim($("#PasswordConfirm").val())
		if (pass1 == pass2) {
			return true;
		}
		$("#error-text").text("两次密码输入不一致");
		return false;
	};

	// 去往登录界面
	var toLogin = function() {
		window.location.href ="LoginAction_toLoginPage";
	};
	// 注册用户
	var reg = function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "RegAction_doReg",
			data : $('#regForm').serialize(),
			async : false,
			error : function(request) {
				alert("请求错误，请重试");
			},
			success : function(data) {
				var info = data.info;
				if (info != null) {
					if ("OK" == info) {
						window.alert("注册成功！");
						toLogin();
					} else if ("ERR" == info) {
						var errs = data.Errors;
						var errString = "";
						for (var i = 0; i < errs.length; i++) {
							errString += "->" + errs[i] + "\n";
						}
						$("#error-text").show("fast", function() {
							$(this).text(errString);
						});
					}
				} else {
					window.alert("发生错误请重试！");
				}
			}
		});
	};

	$("#regbtn").click(function() {
		if ($("#regForm").valid() == false) {
			return false;
		} else if (passIsvalid() == false) {
			return false;
		} else {
			$(this).text("正在注册……");
			$(this).attr('disabled', true);
			// 隐藏错误信息
			if ($("#error-text").text() != "") {
				$("#error-text").hide("fast", function() {
					$(this).text("");
					reg();
				});
			} else {
				reg();
			}
			$(this).text("注册");
			$(this).attr('disabled', false);
		}
	});
	
	$("#loginbtn").click(function() {
		toLogin();
	});
});