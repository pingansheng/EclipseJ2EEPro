<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		//验证输入
		var checkInput=function(){
			var title=$.trim($("#title").val());
			var rowTitles=$.trim($("#matrixRowTitles").val());
			var colTitles=$.trim($("#matrixColTitles").val());
			var options=$.trim($("#matrixSelectOptions").val());
			
			var err="";
			if(title==""){
				err+="#标题为空&nbsp;";
			}
			if(rowTitles==""){
				err+="#行标签为空&nbsp;";
			}
			if(colTitles==""){
				err+="#列标签为空&nbsp;";
			}
			if(options==""){
				err+="#下拉列表选项为空";
			}
			return err;
		};
		
		$("#editForm").submit(function(event) {
			var errInfo=checkInput();
			if (errInfo!="") {
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
				矩阵式下拉列表问题
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
				<label for="matrixRowTitles">行标题组:</label>
				<textarea id="matrixRowTitles" class="form-control" rows="3"
					placeholder="请输入行标题组" name="matrixRowTitles">${model.matrixRowTitles}</textarea>
			</div>
			<div class="form-group">
				<label for="matrixColTitles">列标题组:</label>
				<textarea id="matrixColTitles" class="form-control" rows="3"
					placeholder="请输入列标题组" name="matrixColTitles">${model.matrixColTitles}</textarea>
			</div>
			<div class="form-group">
				<label for="matrixSelectOptions">下拉列表选项集合:</label>
				<textarea id="matrixSelectOptions" class="form-control" rows="4"
					placeholder="请输入下拉列表选项集合" name="matrixSelectOptions">${model.matrixSelectOptions}</textarea>
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