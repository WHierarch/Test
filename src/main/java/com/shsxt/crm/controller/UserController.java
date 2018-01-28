package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.exceptions.ParamsExcetion;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.po.User;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;
	
	@RequestMapping("userLogin")
	@ResponseBody
	public ResultInfo userLogin(String userName,String userPwd,HttpSession session){
		UserModel userModel=userService.userLoginCheck(userName, userPwd);
		/**
		 * 获取用户 拥有的权限 存入session 
		 */
		session.setAttribute(CrmConstant.USER_PERMISSION_KEY, userModel.getPermissions());
		return success("用户登录成功", userModel);
	}
	
	
	@RequestMapping("updateUserPassword")
	@ResponseBody
	public ResultInfo updateUserPassword(String oldPassword,
			String newPassword, String confirmPassword,HttpServletRequest request){
		userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request),
				oldPassword, newPassword, confirmPassword);
		return success("密码更新成功!");
	}
	
	
	@RequestMapping("index")
	public String index(){
		return "user";
	}
	
	@RequestMapping("queryUsersByParams")
	@ResponseBody
	public Map<String, Object> queryUsersByParams(UserQuery userQuery){
		return userService.queryForPage(userQuery);
	}
	
	
	@RequestMapping("saveUser")
	@ResponseBody
	public ResultInfo saveUser(User user){
		userService.saveUser(user);
		return success("用户记录添加成功!");
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public ResultInfo updateUser(User user){
		userService.updateUser(user);
		return success("用户记录更新成功!");
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public ResultInfo deleteUser(Integer[] ids){
		userService.deleteUser(ids[0]);
		return success("用户记录删除成功!");
	}
	
	@RequestMapping("queryCustomerManager")
	@ResponseBody
	public List<User> queryCUstomerManager(){
		return userService.queryCUstomerManager();
	}

}
