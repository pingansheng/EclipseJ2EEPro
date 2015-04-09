<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>权限列表</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".delete").each(function() {
			$(this).click(function() {
				return confirm("确认删除吗？");
			});
		});

		//其他项checkbox
		$("#checkAll").click(function() {
			if ($(this).is(":checked")) {
				$("#allrights").find("input:checkbox").prop("checked",true);
			} else {
				$("#allrights").find("input:checkbox").prop("checked",false);
			}
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
					href="${pageContext.request.contextPath }/RightAction_toAddRightPage">点击添加权限</a>
				</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${fn:length(allRights)==0}">
				<table class="table table-hover">
					<tr class="warning">
						<td align="center" style="font-size: 20px">您目前没有任何权限</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<s:form action="RightAction_batchUpdateRights" namespace="/"
					method="post" theme="simple">
					<table id="allrights" class="table table-striped table-hover table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>权限名称</th>
								<th>权限描述</th>
								<th>公共资源<input id="checkAll" type="checkbox"></th>
								<th>权限URL</th>
								<th>权限位</th>
								<th>权限码</th>
								<th colspan="2">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="allRights" status="st" var="r">
								<s:set var="rightId" value="id" />
								<tr>
									<td><s:textfield name="allRights[%{#st.index}].id"
											cssStyle="width:50px" readonly="true"></s:textfield></td>
									<td><s:textfield name="allRights[%{#st.index}].rightName"></s:textfield></td>
									<td><s:textarea name="allRights[%{#st.index}].rightDesc"
											rows="1" /></td>
									<td><s:checkbox cssClass="rightcommon" name="allRights[%{#st.index}].common"></s:checkbox></td>
									<td><s:property value="rightUrl" /></td>
									<td><s:property value="rightPos" /></td>
									<td><s:property value="rightCode" /></td>
									<td><s:a
											href="RightAction_toAddRightPage?rightid=%{#r.id}">修改</s:a></td>
									<td><s:a cssClass="delete"
											href="RightAction_deleteRight?rightid=%{#r.id}">删除</s:a></td>
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
