package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.po.Module;

public interface ModuleDao extends BaseDao<Module> {
	
	List<TreeDto> queryAllModules();
	
	public Module queryModuleByModuleName(String moduleName);
	
	public Module queryModuleByOptValue(String optValue);
	
	
	public List<Module> querySubModulesByPid(Integer pid);
	
	public Integer deleteModulesBatch(List<Integer> ids);

	List<Module> queryModulesByGrade(Integer grade);
    
}