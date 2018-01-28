//package com.shsxt.crm.proxy;
//
//import java.lang.reflect.Method;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.shsxt.crm.annotations.Pms;
//import com.shsxt.crm.constant.CrmConstant;
//import com.shsxt.crm.exceptions.ParamsExcetion;
//import com.shsxt.crm.service.UserService;
//import com.shsxt.crm.utils.AssertUtil;
//import com.shsxt.crm.utils.LoginUserUtil;
//
///**
// * 
// * @author lp
// * 权限aop 切面定义
// */
//@Component
//@Aspect
//public class PermissionProxy {
//	
//	@Autowired
//	private HttpServletRequest request;
//	
//	@Autowired
//	private HttpSession session;
//     
//	@Resource
//	private UserService userService;
//	
//	@Before("execution (* com.*.*.controller.*.*(..) )")
//	public void before(JoinPoint joinPoint){
//		/**
//		 * 获取方法注解
//		 */
//		MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
//		Method method= methodSignature.getMethod();
//		Pms pms= method.getAnnotation(Pms.class);
//		if(null!=pms){
//			Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
//			AssertUtil.noLogin(null==userId||userId==0||null==userService.queryById(userId), "用户未登录!");
//			// 获取session中权限值
//			List<String> permissions=(List<String>) session.getAttribute(CrmConstant.USER_PERMISSION_KEY);
//			/*if(null!=permissions&&permissions.size()>0){
//				AssertUtil.isTure(!permissions.contains(pms.acl()), "没有操作权限!");
//			}else{
//				AssertUtil.isTure(!permissions.contains(pms.acl()), "没有操作权限!");
//			}*/
//			if(null!=permissions&&permissions.size()>0&&permissions.contains(pms.acl())){
//				System.out.println("用户拥有操作权限...");
//				return;
//			}
//			throw new ParamsExcetion("没有操作权限!");
//		}
//	}
//	
//	
//}
