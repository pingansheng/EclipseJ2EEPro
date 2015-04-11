package com.pas.survey.struts2.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.User;
import com.pas.survey.model.security.Right;
import com.pas.survey.model.security.Role;
import com.pas.survey.service.RightService;
import com.pas.survey.service.RoleService;
import com.pas.survey.service.UserService;
import com.pas.survey.util.ClassUtil;
import com.pas.survey.util.DataUtil;
import com.pas.survey.util.ValidateUtil;

/**
 * 角色Action
 * 
 * @author pingansheng
 * 
 */
@Controller("roleaction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 6520010073500627383L;

	// 增删改查针对的角色ID
	private Integer roleid;

	// 所有角色列表
	private List<Role> allRoles;

	// 提示信息
	private String info;

	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "rightService")
	private RightService rightService;

	// 已有的权限 没有的权限
	private List<Right> newRights;
	// 回发的rightid字符串
	private String rightString;

	public List<Right> getNewRights() {
		return newRights;
	}

	public void setNewRights(List<Right> newRights) {
		this.newRights = newRights;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRightString() {
		return rightString;
	}

	public void setRightString(String rightString) {
		this.rightString = rightString;
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	public String findAllRoles() {
		this.allRoles = roleService.findAllEntities();
		return "roleListPage";
	}

	/**
	 * 去往添加角色页面
	 * 
	 * @return
	 */
	public String toAddRolePage() {
		if (roleid != null) {
			this.model = roleService.getEntity(roleid);
			this.newRights = roleService.getRoleNewRights(roleid);
		} else {
			this.newRights = rightService.findAllEntities();
		}

		return "addRolePage";
	}

	/**
	 * 添加或保存角色
	 * 
	 * @return
	 */
	public String addOrUpdateRole() {
		// 更新model的right集合
		if (ValidateUtil.isValid(rightString.trim())) {
			roleService.updateRoleRights(this.model, rightString);
			return "findAllRolesAction";
		} else {
			return ERROR;
		}
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	public String deleteRole() {
		if (roleid != null) {
			roleService.deleteEntity(roleService.getEntity(roleid));
			return "findAllRolesAction";
		} else {
			return ERROR;
		}
	}
	
	/**
	 * 批量更新
	 * @return
	 */
	public String batchUpdateRoles(){
		roleService.batchUpdateRoles(this.allRoles);
		return "findAllRolesAction";
	}
}
