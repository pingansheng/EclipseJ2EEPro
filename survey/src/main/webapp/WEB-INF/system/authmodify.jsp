<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户授权页面</title>
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
				var left = $("#left>option");
				//提交时候自动每项都选中
				left.each(function() {
					$(this).attr("selected", true);
				});
				$("#button").text("正在提交……");
				$("#button").attr('disabled', true);
				return true;
			}
		});

		//列表操作按钮
		//>
		$("#remove").click(function() {
			var leftSize = $("#left>option:selected").size();
			if (leftSize <= 0) {
				alert("您未选中任何项");
			} else {
				$('#left > option:selected').appendTo($('#right'));
			}
		});
		//<
		$("#add").click(function() {
			var rightSize = $("#right>option:selected").size();
			if (rightSize <= 0) {
				alert("您未选中任何项");
			} else {
				$('#right > option:selected').appendTo($('#left'));
			}
		});

		//>>
		$("#removeAll").click(function() {
			var leftSize = $("#left>option").size();
			if (leftSize <= 0) {
				alert("当前项目数目为0");
			} else {
				$('#left > option').appendTo($('#right'));
			}
		});
		//<
		$("#addAll").click(function() {
			var rightSize = $("#right>option").size();
			if (rightSize <= 0) {
				alert("当前项目数目为0");
			} else {
				$('#right > option').appendTo($('#left'));
			}
		});
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold; font-size: 30px">用户授权</center>
	<div style="width: 50%; margin: 0 auto; text-align: left;">
		<form role="form" id="editForm"
			action="${pageContext.request.contextPath }/UserAuthorizeAction_modifyAuth"
			namespace="/" method="post">
			<%--隐藏域 当前用户的id--%>
			<input type="hidden" name="id" value="${model.id}" />
			<div class="form-group">
				<label>Email</label> <input class="form-control input-sm"
					name="email" type="text" value="${model.email}" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>昵称</label> <input class="form-control input-sm" type="text"
					name="nickname" value="${model.nickname}" readonly="readonly" />
			</div>

			<div class="form-group">
				<label>权限授予</label>
				<div>
					<div style="float: left; width: 40%">
						已拥有的角色 <select size="12" multiple class="form-control" id="left"
							name="roleString">
							<c:forEach items="${model.roles}" var="r">
								<option value="${r.id}">${r.roleName}</option>
							</c:forEach>
						</select>
					</div>
					<%--操控部分 --%>
					<div
						style="float: left; width: 20%; text-align: center; padding-top: 40px">
						<input class="btn btn-success" id="remove"
							style="margin-bottom: 10px; width: 100%" type="button" value=">"><br>
						<input class="btn btn-success" id="add"
							style="margin-bottom: 10px; width: 100%" type="button" value="<"><br>
						<input class="btn btn-success" id="removeAll"
							style="margin-bottom: 10px; width: 100%" type="button" value=">>"><br>
						<input class="btn btn-success" id="addAll"
							style="margin-bottom: 10px; width: 100%" type="button" value="<<">

					</div>
					<div style="float: left; width: 40%">
						未拥有的角色 <select size="12" id="right" multiple class="form-control">
							<c:forEach items="${newRoles}" var="r">
								<option value="${r.id}">${r.roleName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<button id="button" type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
