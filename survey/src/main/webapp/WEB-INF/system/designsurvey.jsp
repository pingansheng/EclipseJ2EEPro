<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设计调查页面</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){ 
	$(".delete").each(function() {
		$(this).click(function() {
			return confirm("确认删除吗？");
		})
	});
})
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold;font-size: 30px">设计调查</center>
	<div class="base-container">
		<table class="table">
			<thead>
			<c:if test="${model.logoPhotoPath!=null}">
			
				<tr>
					<th colspan="2">Logo: <img width="40px"  height="40px" src="${pageContext.request.contextPath}${model.logoPhotoPath}"  alt="Logo" /></th>
				</tr>
			</c:if>
				<tr>
				<c:forEach items="${model.pages}" var="p">
					<c:set var="count" value="${count+fn:length(p.questions)}" /> 
				</c:forEach>
					<th>${model.title }<font style="margin-left:50px;color:red;font-style: italic;font-weight: normal;">题目总数：${count }</font></th>
					<th style="text-align: right;"><a href="${pageContext.request.contextPath}/SurveyAction_toAddLogo?sid=${model.id}">Logo设置</a>&nbsp; <a
						href="${pageContext.request.contextPath}/SurveyAction_editSurvey?sid=${model.id}">编辑调查</a>&nbsp;
						<a href="${pageContext.request.contextPath}/PageAction_toAddPage?sid=${model.id}">增加页</a>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2"><c:forEach items="${model.pages}" var="p">
							<table class="table">
								<thead>
									<tr class="success">
										<th>页面：${p.title} <font style="margin-left:50px;color:red;font-style: italic;font-weight: normal;">问题数：${fn:length(p.questions) }</font></th>
										<th style="text-align: right;">
										<a href="${pageContext.request.contextPath}/PageAction_editPage?sid=${model.id}&pid=${p.id}">编辑页面</a>&nbsp;
											<a href="${pageContext.request.contextPath}/MoveOrCopyPageAction_toSelectTargetPage?srcpid=${p.id}">移动复制页</a>&nbsp; 
											<a href="${pageContext.request.contextPath}/QuestionAction_toSelectQuestionType?sid=${model.id}&pid=${p.id}">添加问题</a>&nbsp; 
											<a class="delete" href="${pageContext.request.contextPath}/PageAction_deletePage?sid=${model.id}&pid=${p.id}">删除页</a>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="2"><c:forEach items="${p.questions}" var="q">
												<table class="table">
													<thead>
														<tr class="warning">
															<th>问题：${q.title}</th>
															<th style="text-align: right;"><a href="${pageContext.request.contextPath}/QuestionAction_editQuestion?sid=${model.id}&pid=${p.id}&qid=${q.id}">编辑问题</a>&nbsp;
																<a class="delete" href="${pageContext.request.contextPath}/QuestionAction_deleteQuestion?sid=${model.id}&qid=${q.id}">删除问题</a>&nbsp;</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td colspan="2"><c:set var="qt"
																	value="${q.questionType}" /> <c:choose>
																	<c:when test="${qt<4}">
																		<c:forEach items="${q.optionArr}" var="option">
																			<label><input name="${q.id}" type="${qt<2 ? 'radio' : 'checkbox'}">${option}</label>
																			<c:if test="${qt==1||qt==3}">
																				<br>
																			</c:if>
																		</c:forEach>
																		<c:if test="${q.other}">
																		<label>
																			<input  name="${q.id}" type="${qt<2 ? 'radio' : 'checkbox'}">其他
																			<c:if test="${q.otherStyle==1}">
																				<input type="text">
																			</c:if>
																		</label>
																			<c:if test="${q.otherStyle==2}">
																				<label><select  name="${q.id}" class="form-control">
																					<c:forEach items="${q.otherSelectOptionArr}" var="other">
																						<option>${other}</option>
																					</c:forEach>
																				</select></label>
																			</c:if>
																		</c:if>
																	</c:when>
																	<c:when test="${qt==4 || qt==5}">
																		<c:if test="${qt==4}">
																			<select  name="${q.id}">
																				<c:forEach items="${q.optionArr}" var="option">
																					<option>${option}</option>
																				</c:forEach>
																			</select>
																		</c:if>
																		<c:if test="${qt==5}">
																			<input type="text" />
																		</c:if>
																	</c:when>
																	<%--矩阵问题--%>
																	<c:when test="${qt > 5}">
																		<table class="table table-striped table-hover">
																			<thead>
																				<tr>
																					<td>子问题</td>
																					<c:forEach items="${q.matrixColTitlesArr}"
																						var="ColTitle">
																						<td>${ColTitle}</td>
																					</c:forEach>
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach items="${q.matrixRowTitlesArr}"
																					var="RowTitle" varStatus="status">
																					<tr>
																						<td>${RowTitle}</td>
																						<c:forEach items="${q.matrixColTitlesArr}" 
																							var="ColTitle">
																							<td><c:if test="${qt==6}">
																									<input  name="${status.count}" type="radio">
																								</c:if>
																								<c:if test="${qt==7}">
																									<input type="checkbox">
																								</c:if>
																								<c:if test="${qt==8}">
																									<select>
																										<c:forEach items="${q.matrixSelectOptionsArr}"
																											var="SelectOption">
																											<option>${SelectOption}</option>
																										</c:forEach>
																									</select>
																								</c:if>
																							</td>
																						</c:forEach>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																	</c:when>
																</c:choose>
															</td>
														</tr>
													</tbody>
												</table>
											</c:forEach></td>
									</tr>
								</tbody>
							</table>
						</c:forEach></td>
				</tr>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
