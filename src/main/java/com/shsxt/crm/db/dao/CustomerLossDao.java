package com.shsxt.crm.db.dao;

import org.apache.ibatis.annotations.Param;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.CustomerLoss;

public interface CustomerLossDao extends BaseDao<CustomerLoss> {

	Integer updateCustomerLossState(@Param("lossId")Integer lossId, @Param("msg")String msg);
   
}