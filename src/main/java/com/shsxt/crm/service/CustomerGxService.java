package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.db.dao.CustomerGxDao;
import com.shsxt.crm.dto.CustomerGxDto;
import com.shsxt.crm.query.CustomerGxQuery;

@Service
public class CustomerGxService {

	@Resource
	private CustomerGxDao customerGxDao;
	
	public Map<String, Object> queryCustomerGxDtosByParams(CustomerGxQuery customerGxQuery){
		PageHelper.startPage(customerGxQuery.getPage(), customerGxQuery.getRows());
		List<CustomerGxDto> customerGxDtos=customerGxDao.queryCustomerGxDtosByParams(customerGxQuery);
		PageInfo<CustomerGxDto> pageInfo=new PageInfo<CustomerGxDto>(customerGxDtos);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
}
