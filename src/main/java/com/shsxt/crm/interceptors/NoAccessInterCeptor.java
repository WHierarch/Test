package com.shsxt.crm.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;

/**
 * 
 * @author lp
 *  非法请求拦截
 */
public class NoAccessInterCeptor extends HandlerInterceptorAdapter {
	@Resource
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 非法请求拦截
		 */
		Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
		AssertUtil.noLogin(null==userId||userId==0, "用户未登录!");
		AssertUtil.noLogin(null==userService.queryById(userId), "用户未登录!");
		return true;// 后续方法不执行
	}

}
