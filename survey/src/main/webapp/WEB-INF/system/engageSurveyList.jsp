<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>参与调查</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="base-container">
		<c:choose>
			<c:when test="${fn:length(openedSurveys)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px;color: red;">您当前暂无任何开放调查</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table class="table">
				<fmt:formatNumber type="number" value="${(fn:length(openedSurveys)-fn:length(openedSurveys)%5)/5 }" var="rowCount" maxFractionDigits="0" pattern="#"/>
					<tbody>
					<c:forEach var="r" begin="0" step="1" end="${rowCount}" >
						<tr>
							<c:forEach items="${openedSurveys}" var="s" begin="${r*5}" step="1" end="${r*5+4}">
								<td style="width: 200px;height: 150px;padding:20px 20px 20px 20px;text-align: center;">
										<a target="_blank" href="${pageContext.request.contextPath }/EngageAction_entry?sid=${s.id}"><img width="100px" height="100px"
											src="${pageContext.request.contextPath }${(s.logoPhotoPath==null || s.logoPhotoPath=='')? '/upload/imgs/404.jpg':s.logoPhotoPath}"
											alt="Logo" /></a>
									 <br> 
									 <a target="_blank" href="${pageContext.request.contextPath }/EngageAction_entry?sid=${s.id}">${s.title}</a></td>
							</c:forEach>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
