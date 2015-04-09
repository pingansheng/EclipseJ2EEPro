<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>移动或复制页面</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold;font-size: 30px">移动或复制页,同一调查移动，不同调查复制</center>
	<div class="base-container">
		<c:forEach items="${mySurveys}" var="s">
			<table class="table">
				<thead>
					<tr class="active">
						<th>${s.title } <c:if test="${s.logoPhotoPath!=null}">
								<img style="margin-left: 20px" width="20px" height="20px"
									src="${pageContext.request.contextPath}${s.logoPhotoPath}"
									alt="Logo" />
							</c:if></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><table class="table">
								<thead>
									<c:forEach items="${s.pages}" var="p">
										<tr class="${p.id==srcpid?'warning':'success' }">
											<form role="form" action="${pageContext.request.contextPath}/MoveOrCopyPageAction_doMoveOrCopyPage" class="form-inline" method="post">
												<th style="width: 75%">页面：${p.title}</th>
												<th><c:if test="${p.id!=srcpid}">
														<input type="hidden" name="srcpid" value="${srcpid}">
														<input type="hidden" name="targetpid" value="${p.id}">
														<%--移动完之后重新回到目标调查设计页面--%>
														<input type="hidden" name="sid" value="${s.id}">

														<div>
															<input type="radio" name="movetype" value="0">之前
															<input type="radio" name="movetype" value="1">之后
															<input type="submit" style="margin-left: 40px"
																class="btn btn-default btn-primary" value="提交">
														</div>
													</c:if></th>
											</form>
										</tr>
									</c:forEach>
								</thead>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</c:forEach>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
