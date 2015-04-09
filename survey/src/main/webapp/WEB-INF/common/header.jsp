<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#newSurvey").click(function() {
			var flag = window.confirm("继续吗？这将自动添加一个默认的未命名调查。");
			return flag;
		});
	});
</script>

<div id="nav" class="navbar navbar-default" role="navigation">
	<div class="base-container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target=".bs-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<span class="navbar-brand">PAS Soft</span>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a
					href="${pageContext.request.contextPath}/LoginAction_toIndex">首页</a>
				</li>
				<li><a id="newSurvey"
					href="${pageContext.request.contextPath}/SurveyAction_newSurvey">新建调查</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/SurveyAction_mySurveys">我的调查</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/RightAction_findAllRights">权限管理</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/RoleAction_findAllRoles">角色管理</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/UserAuthorizeAction_findAllUsers">用户授权</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/EngageAction_findAllOpenedSurveys">参与调查</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath }/LogAction_findAllLogs">日志管理</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<p class="navbar-text">当前用户：${sessionScope.user.name }</p>
				</li>
				<li><a href="${pageContext.request.contextPath}/LoginAction_toChangePass">修改密码</a></li>
				<li><a href="${pageContext.request.contextPath}/LoginAction_logout">退出系统</a></li>
			</ul>
		</div>
	</div>
</div>
