package com.pas.survey.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.Page;
import com.pas.survey.model.Survey;
import com.pas.survey.model.User;
import com.pas.survey.model.security.Role;
import com.pas.survey.service.RoleService;
import com.pas.survey.service.SurveyService;
import com.pas.survey.service.UserService;

/**
 * PageAction
 * 
 * @author pingansheng
 * 
 */
@Controller("userauthorizeaction")
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = 7032765744767711547L;

	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "roleService")
	private RoleService roleService;
	// ��ǰ��Ե��û�id
	private Integer uid;
	private List<User> allUsers;

	// �ط��Ľ�ɫ�б��ַ���
	private String roleString;
	//δӵ�еĽ�ɫ
	private List<Role> newRoles;

	public List<Role> getNewRoles() {
		return newRoles;
	}

	public void setNewRoles(List<Role> newRoles) {
		this.newRoles = newRoles;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}

	/**
	 * �����û���Ȩҳ��
	 * 
	 * @return
	 */
	public String findAllUsers() {
		this.allUsers = userService.findAllEntities();
		return "userAuthorizeListPage";
	}

	/**
	 * �޸���Ȩҳ��
	 */
	public String toAuthModify() {
		if (null != this.uid) {
			this.model = userService.getEntity(uid);
			this.newRoles = userService.getUserNewRoles(uid);
			return "authModifyPage";
		}
		return ERROR;
	}

	/**
	 * �޸���Ȩ
	 * 
	 * @return
	 */
	public String modifyAuth() {
		userService.saveOrUpdateRoles(this.model, this.roleString.trim());
		return "userAuthorizeListAction";
	}
	
	/**
	 * �����Ȩ
	 * @return
	 */
	public String clearAuth(){
		userService.clearUserAuth(this.uid);
		return "userAuthorizeListAction";
	}
}
