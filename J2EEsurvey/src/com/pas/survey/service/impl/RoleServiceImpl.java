package com.pas.survey.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.dao.impl.RoleDaoImpl;
import com.pas.survey.model.security.Right;
import com.pas.survey.model.security.Role;
import com.pas.survey.service.RightService;
import com.pas.survey.service.RoleService;
import com.pas.survey.util.StringUtil;
import com.pas.survey.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Resource(name = "rightService")
	private RightService rightService;

	private BaseDao<Role> roleDao;

	@Override
	@Resource(name = "roleDao")
	public void setDao(BaseDao<Role> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
		roleDao = dao;
	}

	@Override
	public List<Right> getRoleNewRights(Integer roleId) {
		String sql = "select r.* from rights r where r.id not in(select rrl.rightid from role_right_link rrl where rrl.roleid=?)";
		return rightService.executeSqlQuery(sql, Right.class, roleId);
	}

	@Override
	public List<Role> findRolesByIds(String[] ids) {
		if (ValidateUtil.isValid(ids)) {
			String hql = "from Role r where r.id in ("
					+ StringUtil.arrTostr(ids) + ")";
			return this.findEntityByHql(hql);
		}
		return null;
	}

	@Override
	public void updateRoleRights(Role model, String rightString) {
		String[] ids = rightString.split(",");
		if (model!=null && ValidateUtil.isValid(ids)) {
			model.setRights(new LinkedHashSet<Right>(rightService
					.findRightsByIds(ids)));
			this.saveOrUpdateEntity(model);
		} else {
			throw new RuntimeException("²ÎÊýÓÐÎó");
		}
	}

	@Override
	public void batchUpdateRoles(List<Role> allRoles) {
		if (ValidateUtil.isValid(allRoles)) {
			String hql = "update Role r set r.roleName=?,r.roleDesc=? where r.id=?";
			for (Role r : allRoles) {
				this.batchEntityByHql(hql, r.getRoleName(), r.getRoleDesc(),
						r.getId());
			}
		}
	}
}
