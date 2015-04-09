package com.pas.survey.service;

import java.util.List;

import com.pas.survey.model.security.Right;
import com.pas.survey.model.security.Role;

/**
 * 角色接口
 *
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * 得到没有的right列表
	 * @param roleId
	 * @return
	 */
	List<Right> getRoleNewRights(Integer roleId);

	/**
	 * 更新权限
	 * @param model
	 */
	void updateRoleRights(Role model, String rightString);

	/**
	 * 根据ids得到对象集合
	 * @param ids
	 * @return
	 */
	public List<Role> findRolesByIds(String[] ids);

	/**
	 * 批量更新角色集合
	 * @param allRoles
	 */
	void batchUpdateRoles(List<Role> allRoles);
}
