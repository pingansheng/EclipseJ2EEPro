<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加/编辑角色</title>
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
				var left=$("#left>option");
				if($("#left>option").size()<1){
					$("#left").hide("50");
					$("#left").show("50");
					$("#left").css("border-color","red");
					return false;
				}else{
					left.each(function() {
						$(this).attr("selected",true);
					});
					$("#button").text("正在提交……");
					$("#button").attr('disabled', true);
					return true;
				}
			
			}
		});
		
		//列表操作按钮
		//>
		$("#remove").click(function() {
			var leftSize=$("#left>option:selected").size();
			if(leftSize<=0){
				alert("您未选中任何项");
			}else{
				$('#left > option:selected').appendTo($('#right'));
			}
		});
		//<
		$("#add").click(function() {
			var rightSize=$("#right>option:selected").size();
			if(rightSize<=0){
				alert("您未选中任何项");
			}else{
				$('#right > option:selected').appendTo($('#left'));
			}
		});
		
		
		//>>
		$("#removeAll").click(function() {
			var leftSize=$("#left>option").size();
			if(leftSize<=0){
				alert("当前项目数目为0");
			}else{
				$('#left > option').appendTo($('#right'));
			}
		});
		//<
		$("#addAll").click(function() {
			var rightSize=$("#right>option").size();
			if(rightSize<=0){
				alert("当前项目数目为0");
			}else{
				$('#right > option').appendTo($('#left'));
			}
		});
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold; font-size: 30px">添加/编辑角色</center>
	<div style="width: 50%; margin: 0 auto; text-align: left;">
		<form role="form" id="editForm"
			action="${pageContext.request.contextPath }/RoleAction_addOrUpdateRole"
			namespace="/" method="post">
			<%--隐藏域 角色的id 可用于判断新建修改--%>
			<input type="hidden" name="id" value="${model.id}" />
			<div class="form-group">
				<label>角色名称</label> <input
					class="form-control input-sm" check-type="required"
					required-message="请输入内容" id="roleName" type="text" name="roleName"
					placeholder="请输入角色名称" value="${model.roleName }" />
			</div>
			<div class="form-group">
				<label>角色值</label> <input
					class="form-control input-sm" check-type="required"
					required-message="请输入内容" id="roleValue" type="text"
					name="roleValue" placeholder="请输入角色值" value="${model.roleValue }" />
			</div>
			<div class="form-group">
				<label>角色描述</label>
				<textarea type="text" class="form-control input-sm" id="roleDesc"
					name="roleDesc" placeholder="请输入角色描述">${model.roleDesc}</textarea>
			</div>

			<div class="form-group">
				<label>角色权限</label>
				<div>
					<div style="float: left; width: 40%">
						已拥有的权限 <select size="12" multiple class="form-control" id="left" name="rightString">
						<c:forEach items="${model.rights}" var="r">
							<option value="${r.id}">${r.rightName} ${r.rightUrl}</option>
						</c:forEach>
						</select>
					</div>
					<%--操控部分 --%>
					<div style="float: left; width: 20%; text-align: center;padding-top: 40px">
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
						未拥有的权限 <select size="12" multiple class="form-control" id="right">
							<c:forEach items="${newRights}" var="r">
							<option value="${r.id}">${r.rightName} ${r.rightUrl }</option>
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
