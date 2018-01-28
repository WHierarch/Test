package com.shsxt.crm.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.CtMember;
import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.po.CustomerReprieve;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve> {
	
	
	public void saveCustomerReprieve(CustomerReprieve customerReprieve){
		AssertUtil.isTure(StringUtils.isBlank(customerReprieve.getMeasure()), "措施内容非空!");
		customerReprieve.setCreateDate(new Date());
		customerReprieve.setUpdateDate(new Date());
		customerReprieve.setIsValid(1);
		AssertUtil.isTure(insert(customerReprieve)<1,CrmConstant.OP_FAILED_MSG);
	}
	
	
	public void updateCustomerReprieve(CustomerReprieve customerReprieve){
		AssertUtil.isTure(StringUtils.isBlank(customerReprieve.getMeasure()), "措施内容非空!");
		customerReprieve.setUpdateDate(new Date());
		AssertUtil.isTure(update(customerReprieve)<1,CrmConstant.OP_FAILED_MSG);
	}
	
	public void deleteCustomerReprieveBatch(Integer[] ids){
		AssertUtil.isTure(null==ids||ids.length==0, "请选择待删除记录!");
		AssertUtil.isTure(deleteBatch(ids)<ids.length,CrmConstant.OP_FAILED_MSG);
	}
	
	

}
