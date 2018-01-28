package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.service.CustomerServeService;

@Controller
@RequestMapping("customerServe")
public class CustomerServeController extends BaseController {
	
	@Resource
	private CustomerServeService customerServeService;

	
	@RequestMapping("index/{state}")
	public String index(@PathVariable("state")Integer state){
		switch (state) {
		case 1:
			return "customer_serve_create";
		case 2:
			return "customer_serve_assign";
		case 3:
			return "customer_serve_proce";
		case 4:
			return "customer_serve_feed_back";
		case 5:
			return "customer_serve_archive";
		default:
			return "404";
		}
	}
	
	@RequestMapping("queryCustomerServesGroupByType")
	@ResponseBody
	public Map<String, Object> queryCustomerServesGroupByType(){
		return customerServeService.queryCustomerServesGroupByType();
	}
	
	
	
	@RequestMapping("saveCustomerServce")
	@ResponseBody
	public ResultInfo saveCustomerServce(CustomerServe customerServe){
		customerServeService.saveCustomerServe(customerServe);
		return success("服务记录创建成功!");
	}
	
	
	@RequestMapping("queryCustomerServesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){
		return customerServeService.queryForPage(customerServeQuery);
	}
	
	@RequestMapping("updateCustomerServe")
	@ResponseBody
	public ResultInfo updateCustomerServe(CustomerServe customerServe){
		customerServeService.updateCustomerServe(customerServe);
		return success("服务记录更新成功!");
	}
}
