package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.CustomerDao;
import com.shsxt.crm.db.dao.CustomerLossDao;
import com.shsxt.crm.db.dao.CustomerOrderDao;
import com.shsxt.crm.dto.CustomerLevelDto;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.po.CustomerOrder;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import com.sun.org.apache.bcel.internal.generic.ISTORE;

@Service
public class CustomerService extends BaseService<Customer>{
	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private CustomerOrderDao customerOrderDao;
	
	@Resource
	private CustomerLossDao customerLossDao;



	public void saveCustomer(Customer customer){
		/**
		 * 1.参数校验
		 *     客户名称非空  法人代表  联系电话
		 * 2.设置额外字段值
		 *      客户编号设置   isValid  state create_date update_date
		 * 3.执行添加  判断添加结果
		 */
		checkCustomerParams(customer.getFr(),customer.getName(),customer.getPhone());
		customer.setIsValid(1);
		customer.setState(0);
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		customer.setKhno(MathUtil.genereateKhCode());
		AssertUtil.isTure(insert(customer)<1, CrmConstant.OP_FAILED_MSG);
	}



	private void checkCustomerParams(String fr, String name, String phone) {

		AssertUtil.isTure(StringUtils.isBlank(fr), "法人代表 不能为空!");
		AssertUtil.isTure(StringUtils.isBlank(name), "客户名称非空!");
		AssertUtil.isTure(StringUtils.isBlank(phone), "电话非空!");
	}
	private void checkCustomerParams(String fr, String name, String phone,Integer id) {
		checkCustomerParams(fr, name, phone);
		AssertUtil.isTure(null==id||null==queryById(id), "客户信息不存在!");

	}

	public void updateCustomer(Customer customer){
		/**
		 * 1.参数校验
		 *     客户名称非空  法人代表  联系电话  id
		 * 2.设置额外字段值
		 *       update_date
		 * 3.执行更新 判断添加结果
		 */
		checkCustomerParams(customer.getFr(),customer.getName(), customer.getPhone(), customer.getId());
		customer.setUpdateDate(new Date());
		AssertUtil.isTure(update(customer)<1, CrmConstant.OP_FAILED_MSG);
	}
	
	
	public void deleteCustomer(Integer[] ids){
		AssertUtil.isTure(null==ids||ids.length==0, "请选择带删除记录!");
		AssertUtil.isTure(deleteBatch(ids)<ids.length, CrmConstant.OP_FAILED_MSG);
	}

	
	/**
	 * 更新客户流失状态业务处理
	 */
	public void updateCustomerLossState(){
		List<Customer> customers=customerDao.queryLossCustomers();
		if(null!=customers&&customers.size()>0){
			/**
			 * 查询流失客户
			 *  批量添加到客户流失表中
			 *  批量更新客户流失状态
			 */
			List<CustomerLoss> customerLosses=new ArrayList<CustomerLoss>();
			Integer[] ids=new Integer[customers.size()];
			for(int i=0;i<customers.size();i++){
				Customer customer= customers.get(i);
				ids[i]=customer.getId();
				CustomerLoss customerLoss=new CustomerLoss();
				customerLoss.setCreateDate(new Date());
				customerLoss.setCusManager(customer.getCusManager());
				customerLoss.setCusName(customer.getName());
				customerLoss.setCusNo(customer.getKhno());
				customerLoss.setIsValid(1);
				CustomerOrder customerOrder=customerOrderDao.queryLastOrderByCusId(customer.getId());
				if(null!=customerOrder){
					customerLoss.setLastOrderTime(customerOrder.getOrderDate());
				}
				customerLoss.setState(0);
				customerLoss.setUpdateDate(new Date());
				customerLosses.add(customerLoss);
			}
			AssertUtil.isTure(customerLossDao.insertBatch(customerLosses)<customerLosses.size(), CrmConstant.OP_FAILED_MSG);
			AssertUtil.isTure(customerDao.updateCustomerStateById(ids)<ids.length, CrmConstant.OP_FAILED_MSG);
		}
		
	}
	
	
	public Map<String, Object> queryCustomersLevel(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<String> levels=new ArrayList<String>();
		List<Integer> totals=new ArrayList<Integer>();
		List<CustomerLevelDto> customerLevelDtos= customerDao.queryCustomersLevel();
		if(null!=customerLevelDtos&&customerLevelDtos.size()>0){
			for(CustomerLevelDto customerLevelDto:customerLevelDtos){
				levels.add(customerLevelDto.getLevel());
				totals.add(customerLevelDto.getTotal());
			}
			map.put("levels", levels);
			map.put("totals", totals);
			map.put("code", 200);
		}else{
			map.put("code", 300);
		}
		return map;
		
	}


}
