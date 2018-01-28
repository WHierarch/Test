package com.shsxt.crm.db.dao;

import java.util.Map;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.CustomerOrder;

public interface CustomerOrderDao  extends BaseDao<CustomerOrder>{
	
	Map<String, Object> queryOrderInfoByOrderId(Integer oid);
	
	
	CustomerOrder queryLastOrderByCusId(Integer cusId);
   
}