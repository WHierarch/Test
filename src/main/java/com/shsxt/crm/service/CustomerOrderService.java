package com.shsxt.crm.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.db.dao.CustomerOrderDao;
import com.shsxt.crm.po.CustomerOrder;

@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {

	@Resource
	private CustomerOrderDao customerOrderDao;
	
	public Map<String, Object> queryOrderInfoByOrderId(Integer oid){
		return customerOrderDao.queryOrderInfoByOrderId(oid);
	}
}
