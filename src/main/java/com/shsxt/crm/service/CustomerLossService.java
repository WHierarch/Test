package com.shsxt.crm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.CustomerLossDao;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class CustomerLossService extends BaseService<CustomerLoss> {

	@Resource
	private CustomerLossDao customerLossDao;
	

	public void updateCustomerLossState(Integer lossId, String msg) {
		AssertUtil.isTure(customerLossDao.updateCustomerLossState(lossId,msg)<1, CrmConstant.OP_FAILED_MSG);
	}

}
