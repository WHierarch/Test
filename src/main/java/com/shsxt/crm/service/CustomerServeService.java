package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.CustomerServeDao;
import com.shsxt.crm.dto.CustomerServeDto;
import com.shsxt.crm.enums.CustomerServeState;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class CustomerServeService extends BaseService<CustomerServe> {
	
	@Resource
	private CustomerServeDao customerServeDao;
	
	
	public Map<String, Object> queryCustomerServesGroupByType(){
		List<CustomerServeDto> customerServeDtos= customerServeDao.queryCustomerServesGroupByType();
		List<String> serveTypes=new ArrayList<String>();
		List<Map<String, Object>> vals=new ArrayList<Map<String,Object>>();
		Map<String, Object> result=new HashMap<String,Object>();
		if(!CollectionUtils.isEmpty(customerServeDtos)){
			for(CustomerServeDto customerServeDto:customerServeDtos){
				serveTypes.add(customerServeDto.getServeType());
				Map<String, Object> map=new HashMap<String, Object>();	
				map.put("value", customerServeDto.getTotal());
				map.put("name", customerServeDto.getServeType());
				vals.add(map);
			}
			result.put("serveType", serveTypes);
			result.put("vals", vals);
			result.put("code", 200);
		}else{
			result.put("code",300);
		}
		return result;
	}
	
	
	public void saveCustomerServe(CustomerServe customerServe){
		checkCustomerServeParams(customerServe.getServeType(),customerServe.getCustomer(),customerServe.getOverview());
		customerServe.setIsValid(1);
		customerServe.setCreateDate(new Date());
		customerServe.setUpdateDate(new Date());
		customerServe.setState(CustomerServeState.CUSTOMER_SERVE_CREATED.getState());
		AssertUtil.isTure(customerServeDao.insert(customerServe)<1, CrmConstant.OP_FAILED_MSG);
	}


	private void checkCustomerServeParams(String serveType, String customer,
			String overview) {
		AssertUtil.isTure(StringUtils.isBlank(serveType), "服务类型不能为空!");
		AssertUtil.isTure(StringUtils.isBlank(customer), "客户名非空!");
		AssertUtil.isTure(StringUtils.isBlank(overview), "内容非空!");
	}
	

	private void checkCustomerServeParams(String serveType, String customer,
			String overview,Integer id) {
		checkCustomerServeParams(serveType,customer,overview);
		AssertUtil.isTure(null==id||null==customerServeDao.queryById(id), "待更新的服务记录不存在!");
	}
	
	public void updateCustomerServe(CustomerServe customerServe){
		checkCustomerServeParams(customerServe.getServeType(), customerServe.getCustomer(),
				customerServe.getOverview(), customerServe.getId());
		/**
		 * state
		 *    2  已分配  设置分配时间
		 *    3 已处理  设置处理时间
		 *    4 已反馈  修改state 值为5
		 */
		if(customerServe.getState().equals(CustomerServeState.CUSTOMER_SERVE_ASSIGNED.getState())){
			customerServe.setAssignTime(new Date());
		}
		
		if(customerServe.getState().equals(CustomerServeState.CUSTOMER_SERVE_PROCED.getState())){
			customerServe.setServiceProceTime(new Date());
		}
		
		if(customerServe.getState().equals(CustomerServeState.CUSTOMER_SERVE_PROCED.getState())){
			customerServe.setState(CustomerServeState.CUSTOMER_SERVE_ARCHIVE.getState());
		}
		AssertUtil.isTure(customerServeDao.update(customerServe)<1, CrmConstant.OP_FAILED_MSG);
	}

}
