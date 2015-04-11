<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户授权列表</title>
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
		<c:choose>
			<c:when test="${fn:length(allUsers)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px">当前无任何用户</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table class="table table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>ID</th>
							<th>注册时间</th>
							<th>Email</th>
							<th>昵称</th>
							<th colspan="2">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allUsers}" var="u" varStatus="st">
							<tr>
								<td>${st.index+1}</td>
								<td>${u.id}</td>
								<td><fmt:formatDate value="${u.registTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${u.email}</td>
								<td>${u.nickname}</td>
								<td><a
									href="${pageContext.request.contextPath }/UserAuthorizeAction_toAuthModify?uid=${u.id}">修改授权</a></td>
								<td><a
									href="${pageContext.request.contextPath }/UserAuthorizeAction_clearAuth?uid=${u.id}">清除授权</a></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="10" style="height: 1px"></td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
