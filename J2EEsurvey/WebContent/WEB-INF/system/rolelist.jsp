<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色列表</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".delete").each(function() {
			$(this).click(function() {
				return confirm("确认删除吗？");
			});
		});
	});
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="base-container">
		<table class="table table-hover">
			<tr>
				<td align="center"><a
					href="${pageContext.request.contextPath }/RoleAction_toAddRolePage">点击添加角色</a>
				</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${fn:length(allRoles)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px">您目前没有任何角色</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<s:form action="RoleAction_batchUpdateRoles" namespace="/"
					method="post" theme="simple">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>角色名称</th>
								<th>角色描述</th>
								<th colspan="2">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="allRoles" status="st" var="ro">
								<s:set var="roleId" value="id" />
								<tr>
									<td><s:textfield name="allRoles[%{#st.index}].id"
											cssStyle="width:50px" readonly="true"></s:textfield></td>
									<td><s:textfield name="allRoles[%{#st.index}].roleName"></s:textfield></td>
									<td><s:textarea name="allRoles[%{#st.index}].roleDesc"
											rows="1" /></td>
									<td><s:a
											href="RoleAction_toAddRolePage?roleid=%{#ro.id}">修改</s:a></td>
									<td><s:a cssClass="delete"
											href="RoleAction_deleteRole?roleid=%{#ro.id}">删除</s:a></td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="10" style="text-align: center;"><input
									class="btn btn-default btn-primary btn-block" type="submit"
									value="提交"></td>
							</tr>
						</tbody>
					</table>
				</s:form>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
