<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加/编辑权限</title>
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
		$("#actionUrlSelect").change(function() {
			$("#rightUrl").val($(this).val());
		});
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold; font-size: 30px">添加/编辑权限</center>
	<div style="width: 50%; margin: 0 auto; text-align: left;">
		<form role="form" id="editForm"
			action="${pageContext.request.contextPath }/RightAction_addOrUpdate"
			namespace="/" method="post">
			<%--隐藏域 权限的id 可用于判断新建修改--%>
			<input type="hidden" name="id" value="${model.id}" />
			<div class="form-group">
				<label for="rightName">权限名称</label> <input
					class="form-control input-sm" check-type="required"
					required-message="请输入内容" id="rightName" type="text"
					name="rightName" placeholder="请输入权限名称" value="${model.rightName }" />
			</div>
			<div class="form-group">
				<label for="rightUrl">权限URL</label>
				<c:if test="${fn:length(actionNames)!=0}">
				(可以首先选择相应的Action)
					<select id="actionUrlSelect">
						<c:forEach items="${actionNames}" var="actionname">
							<option value="${actionname }">${actionname }</option>
						</c:forEach>
					</select>
				</c:if>
				<input class="form-control input-sm" check-type="required"
					required-message="请输入内容" id="rightUrl" type="text" name="rightUrl"
					placeholder="请输入权限URL" value="${model.rightUrl }" />
			</div>
			<div class="form-group">
				<label for="common">公共资源</label> <input id="common" type="checkbox"
					name="common" ${model.common==true? "checked='checked'":""} />
			</div>
			<div class="form-group">
				<label for="rightdes">权限描述</label>
				<textarea class="form-control input-sm" id="exitText"
					name="rightDesc" placeholder="请输入权限描述">${model.rightDesc}</textarea>
			</div>
			<button id="button" type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
