package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;

@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {
	@Resource
	private CustomerLossService customerLossService;
	
	@RequestMapping("index")
	public String index(){
		return "customer_loss";
	}
	
	@RequestMapping("queryCustomerLossesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery){
		return customerLossService.queryForPage(customerLossQuery);
	}
	
	
	@RequestMapping("queryCustomerLossByLossId")
	public String queryCustomerLossByLossId(Integer lossId,Model model){
		CustomerLoss customerLoss=customerLossService.queryById(lossId);
		model.addAttribute("customerLoss",customerLoss);
		return "customer_reprieve";
	}

	@RequestMapping("confirmCustomerLossState")
	@ResponseBody
	public ResultInfo confirmCustomerLossState(Integer lossId,String msg){
		customerLossService.updateCustomerLossState(lossId,msg);
		return success("客户流失成功!");
	}
}
