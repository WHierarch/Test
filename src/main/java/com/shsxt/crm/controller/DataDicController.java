package com.shsxt.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.po.DataDic;
import com.shsxt.crm.service.DataDicService;

@Controller
@RequestMapping("dataDic")
public class DataDicController extends BaseController {
	
	@Resource
	private DataDicService dataDicService;
	
	@RequestMapping("queryDataDicsByDicName")
	@ResponseBody
	public List<DataDic> queryDataDicsByDicName(String dicName){
		return dataDicService.queryDataDicsByDicName(dicName);
	}
	
	
	
	
	

}
