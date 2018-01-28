package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.crm.dto.CustomerGxDto;
import com.shsxt.crm.query.CustomerGxQuery;

public interface CustomerGxDao {
	
	public List<CustomerGxDto> queryCustomerGxDtosByParams(CustomerGxQuery customerGxQuery);

}
