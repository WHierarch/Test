package com.shsxt.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.base.BaseController;
import com.shsxt.crm.db.dao.UserDao;
import com.shsxt.crm.po.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.utils.UserIDBase64;

@Controller
public class IndexController extends BaseController{
	@Resource
	private UserService userService;
	
	
	@RequestMapping("index")
	public String inex(){
		return "index";
	}

	@RequestMapping("main")
	public String main(HttpServletRequest request){
		Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
		User user=userService.queryUserById(userId);
		
		request.setAttribute("user", user);
		return "main";
	}
}
