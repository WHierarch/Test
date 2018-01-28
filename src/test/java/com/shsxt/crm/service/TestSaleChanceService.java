package com.shsxt.crm.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.SaleChanceQuery;

public class TestSaleChanceService extends BaseTest{
	@Resource
	private SaleChanceService saleChanceService;
	
	
	@Test
	public void test01(){
		SaleChanceQuery saleChanceQuery=new SaleChanceQuery();
		saleChanceQuery.setCustomerName("百度");
		saleChanceQuery.setCreateMan("admin");
		saleChanceQuery.setState(0);
		Map<String, Object> map= saleChanceService.queryForPage(saleChanceQuery);
		
		System.err.println(map.get("total"));
		List<SaleChance> saleChances=(List<SaleChance>) map.get("rows");
		if(saleChances!=null && saleChances.size()>0 ){
			for(SaleChance saleChance:saleChances){
				System.err.println(saleChance);
			}
		}
		
	}
	

}
