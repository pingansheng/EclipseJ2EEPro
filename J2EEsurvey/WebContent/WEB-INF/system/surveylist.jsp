<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>调查列表</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){ 
	$(".delete").each(function() {
		$(this).click(function() {
			return confirm("确认删除吗？");
		})
	});
	$(".clear").each(function() {
		$(this).click(function() {
			return confirm("确认清空吗？");
		})
	});
})
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="base-container">
		<c:choose>
			<c:when test="${fn:length(mySurveys)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px">您当前暂无任何调查</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table class="table table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th>ID</th>
							<th>调查标题</th>
							<th>LOGO</th>
							<th>创建时间</th>
							<th>状态</th>
							<th colspan="6">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mySurveys}" var="s">
							<tr>
								<td>${s.id}</td>
								<td>${s.title}</td>
								<td>
									<c:if test="${s.logoPhotoPath!=null}">
										<img width="30px"  height="30px" src="${pageContext.request.contextPath}${s.logoPhotoPath}"  alt="Logo" />
									</c:if>
									<c:if test="${s.logoPhotoPath==null}">
										<a href="${pageContext.request.contextPath}/SurveyAction_toAddLogo?sid=${s.id}">设置</a>
									</c:if>
								</td>
								<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${s.closed==true?'<font color="red">关闭中':'<font color="green">开放中' }</font></td>
								<td><a
									href="${pageContext.request.contextPath }/SurveyAction_designSurvey?sid=${s.id}">设计</a>
								</td>
								<td><a href="${pageContext.request.contextPath }/CollectionSurveyAction_collectData?sid=${s.id}">收集信息</a></td>
								<td><a href="${pageContext.request.contextPath }/SurveyAction_analyzeSurvey?sid=${s.id}">分析</a></td>
								<td><a href="${pageContext.request.contextPath }/SurveyAction_toggleStatus?sid=${s.id}">打开/关闭</a></td>
								<td><a class="clear"
									href="${pageContext.request.contextPath }/SurveyAction_clearAnswer?sid=${s.id}">清除调查</a></td>
								<td><a class="delete"
									href="${pageContext.request.contextPath }/SurveyAction_deleteSurvey?sid=${s.id}">删除</a></td>
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
