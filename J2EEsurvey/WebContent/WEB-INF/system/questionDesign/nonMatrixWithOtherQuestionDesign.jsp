<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>非矩阵型问题设计</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		//首次加载隐藏
		hideOtherArea(0);
		hideOtherOption(0);
		checkOtherOnload();
		
		//编辑问题时候判断
		function checkOtherOnload() {
			if ($("#other").is(":checked")) {
				showOtherArea(0);
				if("${model.otherStyle}"=="2"){
				showOtherOption(0);
				}
			}
		};
		//隐藏other选项部分
		function hideOtherArea(speed) {
			$("#otherStyleSelect").hide(speed);
		};
		
		function hideOtherOption(speed) {
			$("#otherSelectOptions").hide(speed);
		};
		
		function showOtherArea(speed) {
			$("#otherStyleSelect").show(speed);
		};
		
		function showOtherOption(speed) {
			$("#otherSelectOptions").show(speed);
		};

		//其他项checkbox
		$("#other").click(function() {
			if ($(this).is(":checked")) {
			$("#otherStyleSelect").val("0");
				showOtherArea("normal");
			} else {
				hideOtherArea("normal");
				hideOtherOption("normal");
			}
		});

		$("#otherStyleSelect").change(function() {
			if ($(this).val() == "0"||$(this).val() == "1") {
				hideOtherOption("normal");
			} else {
				showOtherOption("normal");
			}
		});

		//验证输入
		var checkInput = function() {
			var title = $.trim($("#title").val());
			var options = $.trim($("#questionOption").val());
			var err = "<ul>";
			if (title == "" || options == "") {
				err += "<li>标题或选项为空</li>";
			}

			if ($("#other").is(":checked")) {
				if ($("#otherStyleSelect option:selected").val() == "2") {
					if ($.trim($("#otherSelectOptions").val()) == "") {
						err += "<li>'其他'选项内容为空</li>";
					}
				}
			}
			return err += "</ul>";
		};

		$("#editForm").submit(function(event) {
			var errInfo = checkInput();
			if (errInfo != "<ul></ul>") {
				$("#errInfo").html(errInfo);
				return false;
			} else {
				$("#errInfo").text("");
				$("#button").val("正在提交……");
				$("#button").attr("disabled", true);
				return true;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
		<div style="text-align: center;font-weight: bold;">
			<c:if test="${model.questionType==0}">
				非矩阵式横向单选问题
			</c:if>
			<c:if test="${model.questionType==1}">
				非矩阵式纵向单选问题
			</c:if>
			<c:if test="${model.questionType==2}">
				非矩阵式横向复选问题
			</c:if>
			<c:if test="${model.questionType==3}">
				非矩阵式纵向复选问题
			</c:if>
		</div>
		<form role="form" id="editForm"
			action="${requestContext.request.contextPath }QuestionAction_saveOrUpdateQuestion"
			namespace="/" method="post">
			<input type="hidden" name="id" value="${model.id}" /> <input
				type="hidden" name="questionType" value="${model.questionType}" />
			<input type="hidden" name="pid" value="${pid}" /> <input
				type="hidden" name="sid" value="${sid}" />

			<div class="form-group">
				<label for="title">问题标题:</label> <input
					class="form-control input-sm" id="title" type="text" name="title"
					placeholder="请输入问题标题" value="${model.title}" />
			</div>
			<div class="form-group">
				<label for="questionOption">问题选项:</label>
				<textarea id="questionOption" class="form-control" rows="3"
					placeholder="请输入问题选项" name="options">${model.options}</textarea>
			</div>
			<div class="form-group">
				<label> <input id="other" name="otherStr" type="checkbox"
					value="other" ${model.other==true? "checked='checked'":""}>
					是否含有其他选项</input> </label> <label> <select class="form-control"
					id="otherStyleSelect" name="otherStyle">
						<option value="0" ${model.otherStyle==0? "selected='selected'":""}>无</option>
						<option value="1" ${model.otherStyle==1? "selected='selected'":""}>文本框</option>
						<option value="2" ${model.otherStyle==2? "selected='selected'":""}>下拉列表框</option>
				</select> </label>
				<textarea id="otherSelectOptions" class="form-control" rows="3"
					placeholder="请输入'其它'项内容或下拉列表选项"
					name="otherSelectOptions">${model.otherSelectOptions}</textarea>
			</div>
			<div>
				<span id="errInfo" style="color: red;"></span>
			</div>
			<input id="button" type="submit" class="btn btn-primary btn-block"
				value="提交" />
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>