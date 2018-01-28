package com.shsxt.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.db.dao.DataDicDao;
import com.shsxt.crm.po.DataDic;

@Service
public class DataDicService extends BaseService<DataDic> {
	
	@Resource
	private DataDicDao dataDicDao;
	
	public List<DataDic> queryDataDicsByDicName(String dicName){
		return dataDicDao.queryDataDicsByDicName(dicName);
	}

}
