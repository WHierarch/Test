package com.shsxt.crm.db.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.SaleChance;

public interface SaleChanceDao extends BaseDao<SaleChance> {

	Integer updateSaleChanceDevResult(@Param("sid")Integer saleChanceId, @Param("state")Integer state);

}
