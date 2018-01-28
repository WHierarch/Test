package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.ModuleDao;
import com.shsxt.crm.db.dao.PermissionDao;
import com.shsxt.crm.db.dao.RoleDao;
import com.shsxt.crm.db.dao.UserRoleDao;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.po.Permission;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class RoleService extends BaseService<Role> {
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Resource
	private ModuleDao moduleDao;
	public List<Role> queryAllRoles(){
		return roleDao.queryAllRoles();
	}
	
	
	public void saveRole(Role role){
		AssertUtil.isTure(StringUtils.isBlank(role.getRoleName()), "角色名称非空!");
		AssertUtil.isTure(null!=roleDao.queryRoleByRoleName(role.getRoleName()), "角色名称不能重复!");
		role.setIsValid(1);
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
	    AssertUtil.isTure(roleDao.insert(role)<1,CrmConstant.OP_FAILED_MSG);
	}
	
	public void updateRole(Role role){
		AssertUtil.isTure(StringUtils.isBlank(role.getRoleName()), "角色名称非空!");
		Role temp=roleDao.queryRoleByRoleName(role.getRoleName());
		AssertUtil.isTure(null!=temp&&!temp.getId().equals(role.getId()), "角色名称不能重复!");
		role.setUpdateDate(new Date());
	    AssertUtil.isTure(roleDao.update(role)<1,CrmConstant.OP_FAILED_MSG);
	}

	
	public void deleteRole(Integer id){
		AssertUtil.isTure(null==id||null==queryById(id), "请选择待删除记录!");
		int total=userRoleDao.queryUserRolesByRoleId(id);
		if(total>0){
			AssertUtil.isTure(userRoleDao.deleteUserRolesByRoleId(id)<total, CrmConstant.OP_FAILED_MSG);
		}
		AssertUtil.isTure(roleDao.delete(id)<1, CrmConstant.OP_FAILED_MSG);
	}
	
	
	public void addGrant(Integer rid,Integer[] moduleIds){
		/**
		 * 1.角色记录存在校验
		 * 2.非空校验
		 *    not null
		 *       批量添加
		 *         首先删除原始权限  根据角色id 删除
		 *         然后再执行批量添加
		 *    null 
		 *       执行后批量删除  删除角色拥有的全部权限
		 *        
		 */
		AssertUtil.isTure(null==rid||null==roleDao.queryById(rid), "待授权角色不存在!");
		int total=permissionDao.queryPermissionsByRoleId(rid);
		if(total>0){
			AssertUtil.isTure(permissionDao.deleteHistoryPermissionsByRoleId(rid)<total, CrmConstant.OP_FAILED_MSG);
		}
		if(null!=moduleIds&&moduleIds.length>0){
			/**
			 * 权限添加
			 *  删除历史权限
			 */
			List<Permission> permissions=new ArrayList<Permission>();
			for(Integer moduleId:moduleIds){
				Permission permission=new Permission();
				permission.setRoleId(rid);
				permission.setModuleId(moduleId);
			 	Module module= moduleDao.queryById(moduleId);
			 	permission.setAclValue(module.getOptValue());
			 	permission.setCreateDate(new Date());
			 	permission.setUpdateDate(new Date());
			 	permissions.add(permission);
			}
			AssertUtil.isTure(permissionDao.addNewPermissionsBatch(permissions)<moduleIds.length, CrmConstant.OP_FAILED_MSG);
		}
	}
}
