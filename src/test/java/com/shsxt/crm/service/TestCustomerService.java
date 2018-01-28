package com.shsxt.crm.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

public class TestCustomerService extends BaseTest {
	@Resource
	private CustomerService customerService;

	@Test
	public void test() {
		customerService.updateCustomerLossState();
	}

}
