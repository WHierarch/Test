package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerLossQuery extends BaseQuery{
	private String cusNo;
	private String cusName;
	private Integer state;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	

}
