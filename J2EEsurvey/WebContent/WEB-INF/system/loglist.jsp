<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>日志列表</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#start_date").click(function() {
			WdatePicker();
		});
		$("#end_date").click(function() {
			WdatePicker();
		});
		
		$("#search").submit(function() {
			var startDate=$("#start_date").val();
			var endDate=$("#end_date").val();
			if(startDate=="" || endDate==""){
				alert("请选择开始与结束日期");
				return false;
			}
			if(startDate>endDate){
				alert("日期错误");
				return false;
			}
		});
	});
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="base-container">
		<c:choose>
			<c:when test="${fn:length(alllogs)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px">当前无日志</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<form id="search" class="form-inline" method="post" action="${pageContext.request.contextPath}/LogAction_findLogsByDate" role="form">
					<div class="form-group">
						<input class="form-control input-sm" style="cursor: pointer;" id="start_date" type="text"
							placeholder="开始日期" name="start_date" value="<fmt:formatDate value="${start_date}"
										pattern="yyyy-MM-dd" />" readonly>
					</div>
					<div class="form-group">
						<input class="form-control input-sm" style="cursor: pointer;" id="end_date" type="text"
							placeholder="结束日期" name="end_date" value="<fmt:formatDate value="${end_date}"
										pattern="yyyy-MM-dd" />" readonly>
					</div>
					<button type="submit" class="btn btn-default btn-sm">检索</button>
				</form>
				<table class="table table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th>操作用户</th>
							<th>操作名称</th>
							<th>参数</th>
							<th>操作结果</th>
							<th>消息</th>
							<th>时间戳</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="alllogs" var="log">
							<tr>
								<td>${log.operator}</td>
								<td>${log.operName}</td>
								<%--注意添加允许静态方法调用 --%>
								<td><span title='<s:property value="operParams"/>'><s:property
											value="@com.pas.survey.util.StringUtil@getDescString(operParams)" /></span>
								</td>
								<td><span title='<s:property value="operResult"/>'><s:property
											value="@com.pas.survey.util.StringUtil@getDescString(operResult)" /></span></td>
								<td><span title='<s:property value="resultMsg"/>'><s:property
											value="@com.pas.survey.util.StringUtil@getDescString(resultMsg)" /></span></td>
								<td><fmt:formatDate value="${log.operTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
