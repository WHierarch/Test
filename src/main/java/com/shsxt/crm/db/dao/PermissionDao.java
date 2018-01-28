package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.crm.po.Permission;

public interface PermissionDao {
	
	public Integer queryPermissionsByRoleId(Integer rid);
	
	public Integer deleteHistoryPermissionsByRoleId(Integer rid);
	
	public Integer addNewPermissionsBatch(List<Permission> permissions);
	
	public List<Integer> queryModuleIdsByRoleId(Integer roleId);
	
	public List<String> queryAllPermissionsByRoleIds(List<Integer> roleIds);
	

}
