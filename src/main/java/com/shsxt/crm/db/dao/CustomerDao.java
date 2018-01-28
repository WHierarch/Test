package com.shsxt.crm.db.dao;

import java.util.List;
import java.util.Map;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.dto.CustomerLevelDto;
import com.shsxt.crm.po.Customer;

public interface CustomerDao extends BaseDao<Customer> {
	
	List<Customer> queryLossCustomers();
	
	Integer updateCustomerStateById(Integer[] ids);
	
	List<CustomerLevelDto> queryCustomersLevel();
	
    
}