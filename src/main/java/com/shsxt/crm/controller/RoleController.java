package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;
	

	@RequestMapping("queryAllRoles")
	@ResponseBody
	public List<Role> queryAllRoles(){
		return roleService.queryAllRoles();
	}
	@RequestMapping("index")
	public String index(){
		return "role";
	}
	
	@RequestMapping("queryRolesByParams")
	@ResponseBody
	public Map<String, Object> queryRolesByParams(RoleQuery roleQuery){
		return roleService.queryForPage(roleQuery);
	}
	
	@RequestMapping("saveRole")
	@ResponseBody
	public ResultInfo saveRole(Role role){
		roleService.saveRole(role);
		return success("角色记录添加成功!");
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public ResultInfo updateRole(Role role){
		roleService.updateRole(role);
		return success("角色记录添加成功!");
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public ResultInfo deleteRole(Integer[] ids){
		roleService.deleteRole(ids[0]);
		return success("角色记录删除成功!");
	}
	
	@RequestMapping("addGrant")
	@ResponseBody
	public ResultInfo addGrant(Integer rid,Integer[] moduleIds){
		roleService.addGrant(rid, moduleIds);
		return success("授权成功!");
	}

	
}
