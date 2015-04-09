package com.pas.survey.struts2.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.pas.survey.model.User;
import com.pas.survey.service.RightService;
import com.pas.survey.service.UserService;
import com.pas.survey.struts2.UserAware;
import com.pas.survey.util.DataUtil;
import com.pas.survey.util.ValidateUtil;

/**
 * 登录Action
 * 
 * @author pingansheng
 * 
 */
@Controller("loginaction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware,
		RequestAware {

	private static final long serialVersionUID = 1L;

	/*
	 * 接收HttpRequest与Session
	 */
	private Map<String, Object> session;
	private Map<String, Object> request;
	// 密码修改json回发数据
	private String oldPass, newPass, passConfirm;
	private Map<String, String> passChangeJson;
	@Resource
	private UserService userService;
	@Resource
	private RightService rightService;
	
	//存储用户的权限数组
	private long[] rights;
	
	public Map<String, String> getPassChangeJson() {
		return passChangeJson;
	}

	public void setPassChangeJson(Map<String, String> passChangeJson) {
		this.passChangeJson = passChangeJson;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	/**
	 * 到达登录页面
	 * 
	 * @return
	 */
	public String toLoginPage() {
		return "loginpage";
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String doLogin() {
		model.setPassword(DataUtil.sha_1(model.getPassword()));
		User user = userService.LoginCheck(model);

		if (null == user) {
			request.put("info", "用户名或密码错误,请重新登录");
			return INPUT;
		} else {
			
			//获得最高权限位
			int maxPos=rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos+1]);
			//计算用户权限总和
			user.calculateRightSum();
			session.put("user", user);
			return SUCCESS;
		}
	}

	/**
	 * 去往根目录
	 * 
	 * @return
	 */
	public String toIndex() {
		return SUCCESS;
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 */
	public String logout() {
		session.clear();
		return INPUT;
	}

	/**
	 * 去往修改密码
	 * 
	 * @return
	 */
	public String toChangePass() {
		return "changepasspage";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String changePass() {
		passChangeJson = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder();
		int statusCode = -1;
		oldPass = oldPass.trim();
		newPass = newPass.trim();
		passConfirm = passConfirm.trim();
		if (ValidateUtil.isValid(oldPass) && ValidateUtil.isValid(newPass)
				&& ValidateUtil.isValid(passConfirm)) {
			User user = (User) session.get("user");
			if (null != user) {
				if (DataUtil.sha_1(oldPass).equals(user.getPassword())) {
					if (newPass.equals(passConfirm)) {
						user.setPassword(DataUtil.sha_1(newPass));
						userService.saveOrUpdateEntity(user);
						session.put("user", model);
						statusCode = 0;
					} else {
						sb.append("密码确认不一致");
					}
				} else {
					sb.append("原密码错误");
				}
			} else {
				return logout();
			}
		} else {
			sb.append("数据有空");
		}
		passChangeJson.put("info", sb.toString());
		passChangeJson.put("status", statusCode + "");
		return "changepassinfo";
	}

	/**
	 * 注入HttpRequest
	 */
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	/**
	 * 注入Session
	 */
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
}
