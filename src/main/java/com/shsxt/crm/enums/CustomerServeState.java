package com.shsxt.crm.enums;

public enum CustomerServeState {

	CUSTOMER_SERVE_CREATED("1"),// 已创建
	CUSTOMER_SERVE_ASSIGNED("2"),//已分配
	CUSTOMER_SERVE_PROCED("3"),//已处理
	CUSTOMER_SERVE_FEED_BACK("4"),// 已反馈
	CUSTOMER_SERVE_ARCHIVE("5");// 已归档
	
	
	
	
	private CustomerServeState(String state) {
		this.state = state;
	}

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
