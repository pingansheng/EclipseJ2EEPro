package com.pas.survey.service;

import java.util.List;

import com.pas.survey.model.User;
import com.pas.survey.model.security.Role;

public interface UserService extends BaseService<User>{

	/**
	 * 判断Email是否占用
	 * @param email
	 * @return
	 */
	public boolean isRegisted(String email);
	
	public User LoginCheck(User user);

	/**
	 * 取得用户未拥有的角色
	 * @param uid
	 * @return
	 */
	public List<Role> getUserNewRoles(Integer uid);

	/**
	 * 更新用户授权信息
	 * @param model
	 * @param roleString
	 */
	public void saveOrUpdateRoles(User model, String roleString);

	/**
	 * 清除用户授权
	 * @param id
	 */
	public void clearUserAuth(Integer id);
	
}
