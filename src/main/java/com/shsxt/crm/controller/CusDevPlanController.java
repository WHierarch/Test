package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.query.CusDevPlanQUery;
import com.shsxt.crm.service.CusDevPlanService;

@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {
	
	@Resource
	private CusDevPlanService cusDevPlanService;
	
	@RequestMapping("queryCusDevPlansByParams")
	@ResponseBody
	public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQUery cusDevPlanQUery){
		return cusDevPlanService.queryForPage(cusDevPlanQUery);
	}
	
	
	@RequestMapping("saveCusDevPlan")
	@ResponseBody
	public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
		cusDevPlanService.saveCusDevPlan(cusDevPlan);
		return success("计划项数据添加成功!");
	}

	
	@RequestMapping("updateCusDevPlan")
	@ResponseBody
	public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
		cusDevPlanService.updateCusDevPlan(cusDevPlan);
		return success("计划项数据更新成功!");
	}


	@RequestMapping("deleteCusDevPlan")
	@ResponseBody
	public ResultInfo deleteCusDevPlan(Integer[] ids){
		cusDevPlanService.deleteCusDevPlanBatch(ids);
		return success("计划项数据删除成功!");
	}

}
