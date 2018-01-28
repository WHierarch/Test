package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.annotations.Pms;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
	@Resource
	private CustomerService customerService;
	
	
	@RequestMapping("index")
	public String index(){
		return "customer";
	}

	
	
	@RequestMapping("queryCustomersByParams")
	@ResponseBody
	public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery){
		return customerService.queryForPage(customerQuery);
	}
	
	
	/**
	 * 
	 * @param customer
	 * @return
	 * 前台(客户端):
	 *    1.web 页面
	 *        ajax  新闻列表（list--> []）  
	 *    2.android ios (token)
	 *        新闻客户端（新闻列表） （ui 组件）
	 *  接口地址:
	 *    生成环境: http://www.xxx.com/crm/customer/saveCutomer         (线上)
	 *    测试环境: http://192.168.2.113:8080/crm/customer/saveCutomer  (局域网)
	 *    本机环境 :http://127.0.0.1:8080/crm/customer/saveCutomer
	 *  请求类型:get|post|put|delete
	 *  参数列表:
	 *      name   客户名称
	 *      fr     法人代表
	 *      phone  联系电话
	 *      ...
	 *  结果:
	 *   success: {
	 *       code:200,
	 *       msg:"操作成功"
	 *     }
	 *   failed:{
	 *       code:300,
	 *       msg:"操作失败"
	 *   }
	 *      
	 */
	@Pms(acl="201001")
	@RequestMapping("saveCustomer")
	@ResponseBody
	public ResultInfo saveCustomer(Customer customer ){
		customerService.saveCustomer(customer);
		return success("客户信息添加成功!");
	}
	
	@RequestMapping("updateCustomer")
	@ResponseBody
	public ResultInfo updateCustomer(Customer customer ){
		customerService.updateCustomer(customer);
		return success("客户信息更新成功!");
	}
	
	
	@RequestMapping("deleteCustomer")
	@ResponseBody
	public ResultInfo deleteCustomer(Integer[] ids){
		customerService.deleteCustomer(ids);
		return success("客户信息删除成功!");
	}
	
	@RequestMapping("toCustomerInfoOtherPage")
	public String toCustomerInfoOtherPage(Integer state,Integer cid,Model model){
		Customer customer= customerService.queryById(cid);
		model.addAttribute("customer", customer);
		switch (state) {
		case 1:
			return "customer_link_user";

		case 2:
			return "customer_contact";
		case 3:
			return "customer_order";
		default:
			return "404";
		}
	}
	
	
	@RequestMapping("queryCustomersLevel")
	@ResponseBody
	public Map<String, Object> queryCustomersLevel(){
		return customerService.queryCustomersLevel();
	}
}
