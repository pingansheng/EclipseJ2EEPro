package com.pas.survey.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.pas.survey.model.security.Right;
import com.pas.survey.model.security.Role;

/**
 * Bean：用户类
 * 
 * @author pingansheng
 * 
 */
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4795661624715664761L;
	private Integer id;
	private String email;
	private String name;
	private String password;
	private String nickname;

	private Date registTime = new Date();

	// 角色集合
	private Set<Role> roles = new HashSet<Role>();

	// 权限总和
	private long[] rightSum;

	// 是否是超级管理员
	private boolean superAdmin;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	/**
	 * 计算权限总和
	 */
	public void calculateRightSum() {
		int pos = 0;
		long code = 0;
		for (Role role : this.roles) {
			// 超级管理员判断
			if ("-1".equals(role.getRoleValue())) {
				this.superAdmin=true;
				//释放资源
				roles = null;
				return;
			} else {
				for (Right r : role.getRights()) {
					pos = r.getRightPos();// 0,1,2,3,4
					code = r.getRightCode();// 1,2,4,8
					rightSum[pos] = rightSum[pos] | code;
				}
			}
		}
		// 释放资源
		roles = null;
	}

	/**
	 * 判断是否含有该权限
	 * @param right
	 * @return
	 */
	public boolean hasRight(Right right) {
		int pos=right.getRightPos();
		long code=right.getRightCode();
		return !((rightSum[pos]& code)==0);
	}

}
