<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>分析页面</title>
<style type="text/css">
.box {
	padding: 10px;
	height: 400px;
	width: 800px;
	display: none;
	height: 400px;
}
</style>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript" src="js/echarts/esl.js"></script>
<script type="text/javascript" src="js/echarts/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/surveyAna.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold; font-size: 30px">分析调查</center>
	<div class="base-container">
		<table class="table">
			<thead>
				<tr class="active">
					<th>${model.title }<c:if test="${model.logoPhotoPath!=null}">
							<img style="margin-left: 20px" width="20px" height="20px"
								src="${pageContext.request.contextPath}${model.logoPhotoPath}"
								alt="Logo" />
						</c:if></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${model.pages}" var="p">
					<tr class="warning">
						<td>页面:${p.title}</td>
					</tr>
					<tr>
						<td>
							<table class="table">
								<c:forEach items="${p.questions}" var="q">
									<%--文本类型不统计 --%>
									<c:if test="${q.questionType!=5 }">

										<tr class="success">
											<td style="width: 60%">${q.title}</td>
											<td>
												<form name="anaForm" id="${q.id}" method="post">
													<div>
														<input type="hidden" name="qid" value="${q.id}">
														<input type="hidden" name="qtype" value="${q.questionType}">
														<c:if test="${q.questionType<6}">
															<select name="analyzeType">
																<option value="0">饼图</option>
																<option value="1">柱状/折线图</option>
															</select>
															<input type="submit"
																style="margin-left: 100px"
																class="btn btn-default btn-primary" value="分析">
														</c:if>
														<%--矩阵类型特殊处理--%>
														<c:if test="${q.questionType>=6}">
															<select name="analyzeType" ${q.questionType==8?'disabled':''}>
																<option value="0">数量图</option>
																<option value="1">比例图</option>
															</select>
															<input type="submit" style="margin-left: 100px"
																class="btn btn-default btn-primary" value="矩阵问题分析">
														</c:if>
													</div>
												</form>
											</td>
										</tr>

									</c:if>
								</c:forEach>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="main" class="box"></div>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
