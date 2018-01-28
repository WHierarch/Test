package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.annotations.Pms;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {
	
	@Resource
	private SaleChanceService saleChanceService;
	@Resource
	private UserService userService;
	@RequestMapping("index/{state}")
	public String index(@PathVariable("state")Integer state){
		switch (state) {
		case 1:
			return "sale_chance";
		case 2:
			return "cus_dev_plan";
		default:
			return "404";
		}
	}
	
	
	@RequestMapping("querySaleChanceByParams")
	@ResponseBody
	public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){
		return saleChanceService.queryForPage(saleChanceQuery);
	}
	
	
	@Pms(acl="101001")
	@RequestMapping("saveSaleChance")
	@ResponseBody
	public ResultInfo saveSaleChance(SaleChance saleChance,HttpServletRequest request){
		Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
		saleChance.setCreateMan(userService.queryById(userId).getUserName()); 
		saleChanceService.saveSaleChance(saleChance);
		return success("营销机会记录添加成功!");
	}
	
	@RequestMapping("updateSaleChance")
	@ResponseBody
	public ResultInfo updateSaleChance(SaleChance saleChance){
		
		saleChanceService.updateSaleChancce(saleChance);
		return success("营销机会记录更新成功!");
	}
	
	
	@RequestMapping("deleteSaleChance")
	@ResponseBody
	public ResultInfo deleteSaleChanceBatch(Integer[] ids){
		saleChanceService.deleteSaleChanceBatch(ids);
		return success("营销机会记录删除成功!");
	}
	
	@RequestMapping("querySaleChanceBySid")
	public String querySaleChanceBySid(Integer sid,Model model){
		 SaleChance saleChance= saleChanceService.queryById(sid);
		 model.addAttribute("saleChance", saleChance);
		 return "cus_dev_plan_detail";
	}
	
	@RequestMapping("updateSaleChanceDevResult")
	@ResponseBody
	public ResultInfo updateSaleChanceDevResult(Integer saleChanceId,Integer state){
		saleChanceService.updateSaleChanceDevResult(saleChanceId,state);
		return success("状态更新成功!");
	}
}
