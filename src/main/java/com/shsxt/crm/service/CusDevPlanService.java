package com.shsxt.crm.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.utils.AssertUtil;
import com.sun.org.apache.bcel.internal.generic.ISTORE;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {
	
	
	public void saveCusDevPlan(CusDevPlan cusDevPlan){
		checkCusDevPlanParams(cusDevPlan.getPlanItem(),cusDevPlan.getPlanDate(),cusDevPlan.getExeAffect());
		cusDevPlan.setCreateDate(new Date());
		cusDevPlan.setIsValid(1);
		cusDevPlan.setUpdateDate(new Date());
		AssertUtil.isTure(insert(cusDevPlan)<1, CrmConstant.OP_FAILED_MSG);
	}

	private void checkCusDevPlanParams(String planItem, Date planDate,
			String exeAffect) {
		AssertUtil.isTure(StringUtils.isBlank(planItem), "计划项内容非空!");
		AssertUtil.isTure(null==planDate, "日期必须填写!");
		AssertUtil.isTure(StringUtils.isBlank(exeAffect), "执行效果非空!");
	}
	private void checkCusDevPlanParams(String planItem, Date planDate,
			String exeAffect,Integer id) {
		checkCusDevPlanParams(planItem,planDate,exeAffect);
		AssertUtil.isTure(null==id||null==queryById(id), "待更新记录不存在!");
	}
	
	public void updateCusDevPlan(CusDevPlan cusDevPlan){
		checkCusDevPlanParams(cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate(),
				cusDevPlan.getExeAffect(), cusDevPlan.getId());
		cusDevPlan.setUpdateDate(new Date());
		AssertUtil.isTure(update(cusDevPlan)<1, CrmConstant.OP_FAILED_MSG);
	}
	
	public void deleteCusDevPlanBatch(Integer[] ids){
		AssertUtil.isTure(null==ids||ids.length==0, "请选择删除记录!");
		AssertUtil.isTure(deleteBatch(ids)<ids.length, "记录删除失败!");
	}

}
