package com.pas.survey.model.security;

import java.util.LinkedHashSet;
import java.util.Set;

import com.pas.survey.model.BaseEntity;

/**
 * 角色
 */
public class Role extends BaseEntity {
	private Integer id;
	private String roleName="新角色";
	private String roleValue;
	private String roleDesc;

	//权限集合
	private Set<Right> rights = new LinkedHashSet<Right>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}
	
	
}
