package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.service.CustomerOrderService;
import com.shsxt.crm.service.CustomerService;

@Controller
@RequestMapping("customerOrder")
public class CustomerOrderController extends BaseController {
	@Resource
	private CustomerOrderService customerOrderService;
	
	
	@RequestMapping("queryCustomerOrdersByCid")
	@ResponseBody
	public Map<String, Object> queryCustomerOrdersByCid(CustomerOrderQuery customerOrderQuery){
		return customerOrderService.queryForPage(customerOrderQuery);
	}
	
	
	@RequestMapping("queryOrderInfoByOrderId")
	@ResponseBody
	public Map<String, Object> queryOrderInfoByOrderId(Integer oid){
		return customerOrderService.queryOrderInfoByOrderId(oid);
	}

}
