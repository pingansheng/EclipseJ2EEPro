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
 * ��¼Action
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
	 * ����HttpRequest��Session
	 */
	private Map<String, Object> session;
	private Map<String, Object> request;
	// �����޸�json�ط�����
	private String oldPass, newPass, passConfirm;
	private Map<String, String> passChangeJson;
	@Resource
	private UserService userService;
	@Resource
	private RightService rightService;
	
	//�洢�û���Ȩ������
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
	 * �����¼ҳ��
	 * 
	 * @return
	 */
	public String toLoginPage() {
		return "loginpage";
	}

	/**
	 * ��¼
	 * 
	 * @return
	 */
	public String doLogin() {
		model.setPassword(DataUtil.sha_1(model.getPassword()));
		User user = userService.LoginCheck(model);

		if (null == user) {
			request.put("info", "�û������������,�����µ�¼");
			return INPUT;
		} else {
			
			//������Ȩ��λ
			int maxPos=rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos+1]);
			//�����û�Ȩ���ܺ�
			user.calculateRightSum();
			session.put("user", user);
			return SUCCESS;
		}
	}

	/**
	 * ȥ����Ŀ¼
	 * 
	 * @return
	 */
	public String toIndex() {
		return SUCCESS;
	}

	/**
	 * �˳�ϵͳ
	 * 
	 * @return
	 */
	public String logout() {
		session.clear();
		return INPUT;
	}

	/**
	 * ȥ���޸�����
	 * 
	 * @return
	 */
	public String toChangePass() {
		return "changepasspage";
	}

	/**
	 * �޸�����
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
						sb.append("����ȷ�ϲ�һ��");
					}
				} else {
					sb.append("ԭ�������");
				}
			} else {
				return logout();
			}
		} else {
			sb.append("�����п�");
		}
		passChangeJson.put("info", sb.toString());
		passChangeJson.put("status", statusCode + "");
		return "changepassinfo";
	}

	/**
	 * ע��HttpRequest
	 */
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	/**
	 * ע��Session
	 */
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
}
