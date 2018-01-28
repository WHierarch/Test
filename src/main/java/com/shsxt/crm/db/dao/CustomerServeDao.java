package com.shsxt.crm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.dto.CustomerServeDto;
import com.shsxt.crm.po.CustomerServe;

public interface CustomerServeDao extends BaseDao<CustomerServe> {
	
	@Select("select count(1) AS total,serve_type AS serveType"
			+ " from t_customer_serve"
			+ " where is_valid=1 group by serve_type")
	public List<CustomerServeDto> queryCustomerServesGroupByType();
	

}
