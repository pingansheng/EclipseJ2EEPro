package com.pas.survey.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.security.Right;
import com.pas.survey.model.security.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role>{
}
