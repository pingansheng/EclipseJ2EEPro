<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑调查</title>
<jsp:include page="/WEB-INF/common/scriptset.html"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	//声明验证的form
	 $("#editForm").validation();
	 //编辑Survey
    $("#editForm").submit(function(event){
      if ($(this).valid()==false){
        return false;
      }else{
    	  $("#button").text("正在提交……");
    	  $("#button").attr('disabled', true);
    	  return true;
      }
    });
});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<center style="font-weight: bold;font-size: 30px">编辑调查</center>
	<div style="width: 50%;margin: 0 auto;text-align: left;">
		<form role="form" id="editForm"
			action="${pageContext.request.contextPath }/SurveyAction_updateSurvey"
			namespace="/" method="post">
			<input type="hidden" name="id" value="${model.id }" />
			<div class="form-group">
				<label for="title">调查标题</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="title"
					type="text" name="title" placeholder="请输入调查标题" value="${model.title }" />
			</div>
			<div class="form-group">
				<label for="nextText">"下一步"提示文本</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="nextText"
					type="text" name="nextText" placeholder="请输入'下一步'提示" value="${model.nextText }"/>
			</div>
			<div class="form-group">
				<label for="preText">"上一步"提示文本</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="preText"
					type="text" name="preText" placeholder="请输入'上一步'提示" value="${model.preText }"/>
			</div>
			<div class="form-group">
				<label for="doneText">"完成"提示文本</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="doneText"
					type="text" name="doneText" placeholder="请输入'完成'提示" value="${model.doneText }"/>
			</div>
			<div class="form-group">
				<label for="exitText">"退出"提示文本</label> <input class="form-control input-sm"
					check-type="required" required-message="请输入内容" id="exitText"
					type="text" name="exitText" placeholder="请输入'退出'提示" value="${model.exitText }"/>
			</div>
			<button id="button" type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>
