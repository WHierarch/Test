package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.Role;

public interface RoleDao extends BaseDao<Role> {
	
	List<Role> queryAllRoles();
	
	Role queryRoleByRoleName(String roleName);
	
	
	
	String queryRoleNameByUserId(Integer userId);
    
}