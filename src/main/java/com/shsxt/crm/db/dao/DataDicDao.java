package com.shsxt.crm.db.dao;

import java.util.List;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.DataDic;

public interface DataDicDao extends BaseDao<DataDic> {
	
	List<DataDic> queryDataDicsByDicName(String dicName);
   
}