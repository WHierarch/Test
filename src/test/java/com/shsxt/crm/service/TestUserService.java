package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.po.User;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.utils.Md5Util;



public class TestUserService extends BaseTest{
	@Resource
	private UserService userService;
	
	@Test
	public void testUserLoginCheck(){
		UserModel userModel= userService.userLoginCheck("admin", "123456");
		System.out.println(userModel);
	}
	
	
	@Test
	public void  testAddUser() {
		User user=new User();
		user.setUserName("test01");
		user.setUserPwd(Md5Util.encode("123456"));
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		userService.insert(user);
	}
	
	@Test
	public void testQueryUserByParams(){
		UserQuery userQuery=new UserQuery();
		userQuery.setUserName("admin");
		userQuery.setTrueName("admin");
		userQuery.setTime("2017-09-10");
		Map<String, Object> map=userService.queryForPage(userQuery);
		/*for(Entry<String, Object> entry:map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}*/
		Long total= (Long) map.get("total");
		List<User> users=(List<User>) map.get("rows");
		System.out.println("总数:"+total);
		if(null!=users&&users.size()>0){
			for(User user:users){
				System.out.println(user);
			}
		}
	}
	
	
	@Test
	public void testSaveUser(){
		User user=new User();
		user.setUserName("老裴");
		user.setUserPwd("123456");
		user.setTrueName("admin");
		userService.saveUser(user);
	}
	
	@Test
	public void testSaveUser02(){
		User user=new User();
		user.setUserName("老高");
		user.setUserPwd("123456");
		user.setTrueName("admin");
		List<Integer> roleIds=new ArrayList<Integer>();
		roleIds.add(1);
		roleIds.add(2);
		user.setRoleIds(roleIds);
		userService.saveUser(user);
	}

	
	
	@Test
	public void testUpdateUser(){
		User user=new User();
		user.setId(48);
		user.setUserName("老裴");
		//user.setUserPwd("111111");
		user.setTrueName("老裴");
		/*List<Integer> roleIds=new ArrayList<Integer>();
		roleIds.add(1);
		roleIds.add(2);*/
		//user.setRoleIds(roleIds);
		userService.updateUser(user);
	}
	
	@Test
	public void testDeleteUser(){
		userService.deleteUser(10);
	}

}
