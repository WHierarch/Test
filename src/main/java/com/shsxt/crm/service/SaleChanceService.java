package com.shsxt.crm.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.SaleChanceDao;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class SaleChanceService extends BaseService<SaleChance> {
	
	@Resource
	private SaleChanceDao saleChanceDao;
	
	public void saveSaleChance(SaleChance saleChance){
		/**
		 * 1.参数校验
		 *     客户名称  联系人  联系电话非空
		 * 2.字段值设置
		 *    开发结果0
		 *    创建时间  更新时间  new date()
		 *    state 0 (默认值) 如果分配人存在  state=1
		 * 3.执行添加 判断结果
		 */
		checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
		saleChance.setIsValid(1);
		saleChance.setCreateDate(new Date());
		saleChance.setUpdateDate(new Date());
		saleChance.setDevResult(0);
		saleChance.setState(0);
		if(StringUtils.isNoneBlank(saleChance.getAssignMan())){
			saleChance.setState(1);
			saleChance.setAssignTime(new Date());
		}
		AssertUtil.isTure(saleChanceDao.insert(saleChance)<1, CrmConstant.OP_FAILED_MSG);
	}

	private void checkSaleChanceParams(String customerName, String linkMan,
			String linkPhone) {
		AssertUtil.isTure(StringUtils.isBlank(customerName), "客户名称非空!");
		AssertUtil.isTure(StringUtils.isBlank(linkMan), "联系人非空!");
		AssertUtil.isTure(StringUtils.isBlank(linkPhone), "联系电话非空!");
	}
	
	private void checkSaleChanceParams(String customerName, String linkMan,
			String linkPhone,Integer id) {
		checkSaleChanceParams(customerName,linkMan,linkPhone);
		AssertUtil.isTure(null==id||null==saleChanceDao.queryById(id), "待更新记录不存在!");
	}
	
	
	public void updateSaleChancce(SaleChance saleChance){
		/**
		 * 1.参数校验
		 *     客户名称  联系人  联系电话非空 id 记录校验
		 * 2.字段值设置
		 *    updateDate new date()
		 *      state  1-0
		 *         assignTime
		 *  3.更新  判断结果
		 */
		checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone(),saleChance.getId());
		saleChance.setUpdateDate(new Date());
		saleChance.setState(0);
		if(StringUtils.isNoneBlank(saleChance.getAssignMan())){
			saleChance.setState(1);
			saleChance.setAssignTime(new Date());
		}
		AssertUtil.isTure(saleChanceDao.update(saleChance)<1, CrmConstant.OP_FAILED_MSG);
	}
	
	public void deleteSaleChanceBatch(Integer[] ids){
		AssertUtil.isTure(null==ids||ids.length==0, "请选择待删除记录!");
		AssertUtil.isTure(saleChanceDao.deleteBatch(ids)<ids.length, CrmConstant.OP_FAILED_MSG);
	}

	public void updateSaleChanceDevResult(Integer saleChanceId, Integer state) {
		AssertUtil.isTure(saleChanceDao.updateSaleChanceDevResult(saleChanceId,state)<1, CrmConstant.OP_FAILED_MSG);;
		
	}

}
