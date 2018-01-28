package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerReprieve;
import com.shsxt.crm.query.CustomerReprieveQuery;
import com.shsxt.crm.service.CustomerReprieveService;

@Controller
@RequestMapping("customerReprieve")
public class CustomerRepriController extends BaseController {
	@Resource
	private CustomerReprieveService customerReprieveService;

	
	@RequestMapping("queryCustomerReprievesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerReprievesByParams(CustomerReprieveQuery customerReprieveQuery){
		return customerReprieveService.queryForPage(customerReprieveQuery);
	}
	
	@RequestMapping("saveCustomerReprieve")
	@ResponseBody
	public ResultInfo saveCustomerReprieve(CustomerReprieve customerReprieve){
		customerReprieveService.saveCustomerReprieve(customerReprieve);
		return success("暂缓措施添加成功!");
	}
	
	@RequestMapping("updateCustomerReprieve")
	@ResponseBody
	public ResultInfo updateCustomerReprieve(CustomerReprieve customerReprieve){
		customerReprieveService.update(customerReprieve);
		return success("暂缓措施更新成功");
	}
	@RequestMapping("deleteCustomerReprieve")
	@ResponseBody
	public ResultInfo deleteCustomerReprieve(Integer[] ids){
		customerReprieveService.deleteCustomerReprieveBatch(ids);
		return success("暂缓措施删除成功");
	}
}
