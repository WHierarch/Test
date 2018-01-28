package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.CustomerGxQuery;
import com.shsxt.crm.service.CustomerGxService;

@Controller
@RequestMapping("customerGx")
public class CustomerGxController extends BaseController {
	@Resource
	private CustomerGxService customerGxService;
	
	@RequestMapping("queryCustomerGxDtosByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerGxDtosByParams(CustomerGxQuery customerGxQuery){
		return customerGxService.queryCustomerGxDtosByParams(customerGxQuery);
	}

}
