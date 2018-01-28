package com.shsxt.crm.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

public class TestModuleService extends BaseTest {
	@Resource
	private ModuleService moduleService;

	@Test
	public void test() {
		moduleService.deleteModule(1);
	}

}
