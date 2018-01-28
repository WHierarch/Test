package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.service.ModuleService;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
	@Resource
	private ModuleService moduleService;

	/*@RequestMapping("queryAllModules")
	@ResponseBody
	public List<TreeDto> queryAllModules(){
		return moduleService.queryAllModules();
	}*/

	@RequestMapping("index")
	public String index(){
		return "module";
	}

	@RequestMapping("queryAllModules")
	@ResponseBody
	public List<TreeDto> queryAllModules(Integer roleId){
		return moduleService.queryAllModules(roleId);
	}


	@RequestMapping("queryModulesByParams")
	@ResponseBody
	public Map<String, Object> queryModulesByParams(ModuleQuery moduleQuery){
		return moduleService.queryForPage(moduleQuery);
	}


	@RequestMapping("saveModule")
	@ResponseBody
	public ResultInfo saveModule(Module module){
		moduleService.saveModule(module);
		return success("模块添加成功!");
	}

	@RequestMapping("updateModule")
	@ResponseBody
	public ResultInfo updateModule(Module module){
		moduleService.updateModule(module);
		return success("模块更新成功!");
	}




	@RequestMapping("deleteModule")
	@ResponseBody
	public ResultInfo deleteModule(Integer[] ids){
		moduleService.deleteModule(ids[0]);
		return success("模块删除成功!");
	}

	@RequestMapping("queryModulesByGrade")
	@ResponseBody
	public List<Module> queryModulesByGrade(Integer grade){
      return  moduleService.queryModulesByGrade(grade);
	}
}
