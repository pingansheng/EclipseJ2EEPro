package com.pas.survey.struts2.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pas.survey.model.User;
import com.pas.survey.model.security.Right;
import com.pas.survey.struts2.UserAware;
import com.pas.survey.struts2.action.BaseAction;
import com.pas.survey.util.ValidateUtil;

public class AnthCheck extends AbstractInterceptor {

	private static final long serialVersionUID = 6316641953787572143L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		ActionProxy actionProxy = invocation.getProxy();
		String namespace = actionProxy.getNamespace();
		String actionName = actionProxy.getActionName();
		if (!ValidateUtil.isValid(namespace) || namespace.equals("/")) {
			namespace = "";
		}
		String url = namespace + "/" + actionName;
		// 得到权限对象
		ServletContext sc=ServletActionContext.getServletContext();
		Right right = ((Map<String, Right>)sc.getAttribute("all rights map")).get(url);
		if (right != null && right.isCommon()) {
			return invocation.invoke();
		} else {
			// 获取session中User的引用
			User user = (User) session.get("user");
			if (user == null) {
				// 回首页
				return Action.LOGIN;
			} else {
				BaseAction action = (BaseAction) invocation.getAction();
				if (user.isSuperAdmin()) {
					if (action instanceof UserAware) {
						((UserAware) action).setUser(user);
					}
					return invocation.invoke();
				} else {
					if (user.hasRight(right)) {
						if (action instanceof UserAware) {
							((UserAware) action).setUser(user);
						}
						return invocation.invoke();
					} else {
						return "err_noright";
					}
				}
			}
		}
	}
}
