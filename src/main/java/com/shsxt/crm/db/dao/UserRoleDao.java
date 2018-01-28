package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.UserRole;

public interface UserRoleDao extends BaseDao<UserRole>{
	
	public Integer queryUserRolesByUserId(Integer userId);
	
	public Integer deleteUserRolesByUserId(Integer userId);
	
	public Integer queryUserRolesByRoleId(Integer roleId);
	
	public Integer deleteUserRolesByRoleId(Integer roleId);
	
	
	public List<Integer> queryRoleIdsByUserId(Integer userId);
}
