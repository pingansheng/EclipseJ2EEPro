package com.pas.survey.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pas.survey.service.RightService;
import com.pas.survey.util.ValidateUtil;

public class CatchURL extends AbstractInterceptor {

	private static final long serialVersionUID = 6316641953787572143L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy actionProxy = invocation.getProxy();
		String namespace = actionProxy.getNamespace();
		String actionName = actionProxy.getActionName();
		if (!ValidateUtil.isValid(namespace) || namespace.equals("/")) {
			namespace = "";
		}
		String url = namespace + "/" + actionName;

		//È¡µÃSpringÈÝÆ÷
//		ApplicationContext ac=(ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ServletContext sc=ServletActionContext.getServletContext();
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(sc);
		RightService rs = (RightService) ac.getBean("rightService");// null
		rs.appendRightUrl(url);

		return invocation.invoke();
	}

}
