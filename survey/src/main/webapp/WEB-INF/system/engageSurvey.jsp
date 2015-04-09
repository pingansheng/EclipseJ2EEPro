<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>参与调查页面</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var checkForm = function() {
							//总验证标示
							var flag = true;

							//========0,1,2,3类型验证========
							var inputs_type1 = $(".qt_type1 :radio,.qt_type1 :checkbox");
							var inputs_type1_NameArray = new Array();
							//取控件名称数组
							for (var i = 0; i < inputs_type1.length; i++) {
								if ($.inArray(inputs_type1[i].name,
										inputs_type1_NameArray) == -1) {
									inputs_type1_NameArray
											.push(inputs_type1[i].name);
								}
							}
							//验证
							for (var j = 0; j < inputs_type1_NameArray.length; j++) {
								var len = $("input[name="
										+ inputs_type1_NameArray[j]
										+ "]:checked").length;
								if (len < 1) {
									$("#errInfo" + inputs_type1_NameArray[j])
											.show(0);
									$("#errInfo" + inputs_type1_NameArray[j])
											.text("请至少选择一项");
									flag = false;
								} else {
									$("#errInfo" + inputs_type1_NameArray[j])
											.hide(0);
									$("#errInfo" + inputs_type1_NameArray[j])
											.text("");
								}
							}
							//========end_0,1,2,3类型验证========
							//========4，5类型=========
							var inputs_type2 = $(".qt_type2 :text");
							var inputs_type2_NameArray = new Array();
							//取控件名称数组
							for (var i = 0; i < inputs_type2.length; i++) {
								if ($.inArray(inputs_type2[i].name,
										inputs_type2_NameArray) == -1) {
									inputs_type2_NameArray
											.push(inputs_type2[i].name);
								}
							}
							//验证
							for (var j = 0; j < inputs_type2_NameArray.length; j++) {
								$("#errInfo" + inputs_type2_NameArray[j]).hide(
										0);
								var text = $.trim($(
										"input[name="
												+ inputs_type2_NameArray[j]
												+ "]").val());
								if (text == "" || text.length == 0) {
									$("#errInfo" + inputs_type2_NameArray[j])
											.show(0);
									$("#errInfo" + inputs_type2_NameArray[j])
											.text("请输入内容");
									flag = false;
								} else {
									$("#errInfo" + inputs_type2_NameArray[j])
											.hide(0);
									$("#errInfo" + inputs_type2_NameArray[j])
											.text("");
								}
							}
							//========end_4，5类型=========
							//========矩阵类型=========
								//待完善
							//========end_矩阵类型=========
							return flag;
						};

						//编辑Survey
						$("#editForm input[type='submit']").click(function(event) {
							<%--退出按钮上一页按钮，不验证--%>
							if(event.target.name==("submit_exit")
									||event.target.name==("submit_pre")){
								return true;
							}
							var checkFlag=checkForm();
							return checkFlag;
						});
					});
</script>

<style type="text/css">
body {
	padding-top: 40px;
}
</style>
</head>

<body>

	<div class="base-container">
		<%--头部 --%>
		<div
			style="text-align: center; color: gray; font-weight: bold; font-size: 30px">
			${currentSurvey.title }
			<c:if test="${currentSurvey.logoPhotoPath!=null}">
				<img width="40px" height="40px"
					src="${pageContext.request.contextPath}${currentSurvey.logoPhotoPath}"
					alt="Logo" />
			</c:if>
		</div>
		<%--end_头部 --%>
		<%--Page为空 --%>
		<c:if test="${currentPage==null}">
			<div
				style="text-align: center; color: red; font-weight: bold; font-size: 20px">
				本调查无页面 <a href="#" onclick="window.close()">点击关闭</a>
			</div>

		</c:if>
		<%--endPage为空 --%>
		<c:if test="${currentPage!=null}">
			<form id="editForm"
				action="${pageContext.request.contextPath}/EngageAction_doEnageSurvey"
				method="post">
				<%--当前Pageid --%>
				<input type="hidden" name="currentPid" value="${currentPage.id}">
				<%--Page --%>
				<table class="table">
					<thead>
						<tr class="success">
							<th>${currentPage.title}</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(currentPage.questions)<=0}">
								<tr>
									<td><div
											style="text-align: center; color: red; font-weight: bold; font-size: 20px">
											当前页面为空，请进入下一页或退出</div></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><c:forEach items="${currentPage.questions}" var="q">
											${q.id }<table class="table">
												<thead>
													<tr class="warning">
														<th>问题：${q.title}</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><c:set var="qt" value="${q.questionType}" /> <c:choose>
																<%--0,1,2,3 --%>
																<c:when test="${qt<4}">
																	<div class="qt_type1">
																		<%--  用于答案回显的key--%>
																		<c:set var="key" value="q${q.id }"></c:set>
																		<c:forEach items="${q.optionArr}" var="option"
																			varStatus="index">
																			<label><input name="${key}"
																				value="${index.count-1}"
																				type="${qt<2 ? 'radio' : 'checkbox'}"
																				${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),(index.count-1))>=0?'checked="checked"':''}>${option}</label>
																			<c:if test="${qt==1||qt==3}">
																				<br>
																			</c:if>
																		</c:forEach>
																		<c:if test="${q.other}">
																			<%--  用于答案回显的key Other部分--%>
																			<c:set var="otherkey" value="q${q.id }other"></c:set>
																			<label> <input name="${key}" value="other"
																				${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),'other')>=0?'checked="checked"':''}
																				type="${qt<2 ? 'radio' : 'checkbox'}">其他 <%--其他类型的分类设置--%>
																				<%--文本框--%> <c:if test="${q.otherStyle==1}">
																					<input name="${otherkey}" type="text"
																						value="${sessionScope.all_answer_map[currentPage.id][otherkey][0]}">
																				</c:if>
																			</label>
																			<%--下拉框--%>
																			<c:if test="${q.otherStyle==2}">
																				<label><select name="${otherkey}"
																					class="form-control">
																						<c:forEach items="${q.otherSelectOptionArr}"
																							var="other" varStatus="otherindex">
																							<option value="${otherindex.count-1}"
																								${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][otherkey],''),(otherindex.count-1))>=0?'selected="selected"':''}>${other}</option>
																						</c:forEach>
																				</select> </label>
																			</c:if>
																		</c:if>
																		<div>
																			<span id="errInfoq${q.id}" style="color: red;"></span>
																		</div>
																	</div>
																</c:when>
																<%--0,1,2,3_end --%>
																<%--4,5 --%>
																<c:when test="${qt==4 || qt==5}">
																	<div class="qt_type2">
																		<%--用于答案回显的Key--%>
																		<c:set var="key" value="q${q.id }"></c:set>
																		<label><c:if test="${qt==4}">
																				<select name="${key}">
																					<c:forEach items="${q.optionArr}" var="option"
																						varStatus="index">
																						<option value="${index.count-1}"
																							${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),(index.count-1))>=0?'selected="selected"':''}>${option}</option>
																					</c:forEach>
																				</select>
																			</c:if> <c:if test="${qt==5}">
																				<input name="${key }" type="text"
																					value="${sessionScope.all_answer_map[currentPage.id][key][0]}" />
																			</c:if></label>
																		<div>
																			<span id="errInfoq${q.id}" style="color: red;"></span>
																		</div>
																	</div>
																</c:when>
																<%--4,5_end --%>
																<%--矩阵问题--%>
																<c:when test="${qt > 5}">
																	<%--用于答案回显的Key--%>
																	<c:set var="key" value="q${q.id}" />
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
																				var="RowTitle" varStatus="rowIndex">
																				<tr>
																					<td>${RowTitle}</td>
																					<c:forEach items="${q.matrixColTitlesArr}"
																						varStatus="colIndex" var="ColTitle">
																						<td>
																							<%--矩阵单选--%> <c:if test="${qt==6}">
																								<%--用于答案回显的Key--%>
																								<c:set var="key"
																									value="q${q.id}_${rowIndex.count-1}" />
																								<c:set var="value"
																									value="${rowIndex.count-1}_${colIndex.count-1}" />
																								<input name="${key}" value="${value}"
																									${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),value)>=0?'checked="checked"':''}
																									type="radio">
																							</c:if> <%--矩阵复选--%> <c:if test="${qt==7}">
																								<c:set var="value"
																									value="${rowIndex.count-1}_${colIndex.count-1}" />
																								<input name="${key}" type="checkbox"
																									${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),value)>=0?'checked="checked"':''}
																									value="${value}">
																							</c:if> <%--矩阵下拉列表--%> <c:if test="${qt==8}">
																								<select name="${key}">
																									<c:forEach items="${q.matrixSelectOptionsArr}"
																										var="SelectOption" varStatus="selectIndex">
																										<c:set var="value"
																											value="${rowIndex.count-1}_${colIndex.count-1}_${selectIndex.count-1}" />
																										<option
																											${fn:indexOf(fn:join(sessionScope.all_answer_map[currentPage.id][key],''),value)>=0?'selected="selected"':''}
																											value="${value}">${SelectOption}</option>
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
																<%--矩阵问题_end--%>
															</c:choose></td>
													</tr>
												</tbody>
											</table>
										</c:forEach></td>
								</tr>
							</c:otherwise>
						</c:choose>
						<tr>
							<td>
								<div style="text-align: center;">
									<%--页面数大于1--%>
									<c:if test="${currentSurvey.minOrderno!=currentSurvey.maxOrderno }">
										<%--上一页 --%>
										<c:if test="${currentPage.orderno!=currentSurvey.minOrderno }">
											<input type="submit" style="width: 200px" name="submit_pre"
												class="btn btn-primary" value="${currentSurvey.preText}">
										</c:if>
										<%--下一页 --%>
										<c:if test="${currentPage.orderno!=currentSurvey.maxOrderno }">
											<input type="submit" style="width: 200px" name="submit_next"
												class="btn btn-primary" value="${currentSurvey.nextText}">
										</c:if>

									</c:if>
									<%--完成 --%>
									<c:if test="${currentPage.orderno==currentSurvey.maxOrderno }">
										<input type="submit" style="width: 200px" name="submit_done"
											class="btn btn-primary" value="${currentSurvey.doneText}">
									</c:if>
									<%--退出--%>
									<input type="submit" style="width: 200px" name="submit_exit"
										class="btn btn-primary" value="${currentSurvey.exitText}">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</c:if>
		<%--Page_end--%>
	</div>
</body>
</html>
