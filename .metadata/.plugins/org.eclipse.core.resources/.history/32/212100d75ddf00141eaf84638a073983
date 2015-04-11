package com.pas.survey.service.impl;

import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.User;
import com.pas.survey.model.security.Role;
import com.pas.survey.service.RoleService;
import com.pas.survey.service.UserService;
import com.pas.survey.util.StringUtil;
import com.pas.survey.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Resource(name = "roleService")
	private RoleService roleService;

	@Override
	@Resource(name = "userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	public boolean isRegisted(String email) {
		String hql = "from User u where u.email=?";
		List<User> users = this.findEntityByHql(hql, new Object[] { email });
		return ValidateUtil.isValid(users);
	}

	public User LoginCheck(User user) {
		String hql = "from User u where u.email=? and password=?";
		List<User> users = this.findEntityByHql(hql,
				new Object[] { user.getEmail(), user.getPassword() });
		if (ValidateUtil.isValid(users)) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<Role> getUserNewRoles(Integer uid) {
		String sql = "select r.* from role r where r.id not in (select url.roleid from user_role_link url where url.userid=?)";
		return roleService.executeSqlQuery(sql, Role.class, uid);
	}

	@Override
	public void saveOrUpdateRoles(User model, String roleString) {
		// 须使用DB当中的新用户，不可以更改用户其他信息
		model = this.getEntity(model.getId());
		
		if (ValidateUtil.isValid(roleString)) {
			//有角色字符串
			String[] roleids = roleString.split(",");
			model.setRoles(new LinkedHashSet<Role>(roleService.findRolesByIds(roleids)));
			
		} else {
			// 没有角色
			model.setRoles(null);
		}
	}

	@Override
	public void clearUserAuth(Integer id) {
		User u=this.getEntity(id);
		u.setRoles(null);
	}
}
