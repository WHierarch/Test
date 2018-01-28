package com.shsxt.crm.tasks;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shsxt.crm.service.CustomerService;

@Component
public class CustomerTask {
	
	@Resource
	private CustomerService customerService;
	
	//@Scheduled(cron="0/5 * * * * ?")
	public void job01(){
		System.err.println("客户流失定时任务处理。。。");
		customerService.updateCustomerLossState();
	}
	
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring.xml","servlet-context.xml");
	}

}
