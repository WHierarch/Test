package com.shsxt.crm.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.PseudoColumnUsage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.ResultInfo;

@Component
public class GlobalExceptionReslover implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		/**
		 * 1.视图
		 * 2.json
		 */
		if(handler instanceof HandlerMethod){
			if(ex instanceof NoLoginException) {
				NoLoginException ne=(NoLoginException) ex;
				ModelAndView modelAndView=getDefaultModelAndView(request);
				modelAndView.addObject("errorMsg", ne.getErrorMsg());
				return modelAndView;
			}
			
			HandlerMethod handlerMethod=(HandlerMethod) handler;
			/**
			 * controller 响应的内容类型判断  view json
			 */
			Method method= handlerMethod.getMethod();
			ResponseBody responseBody= method.getAnnotation(ResponseBody.class);
			
			if(null!=responseBody){
				/**
				 * 响应为json
				 */
				ResultInfo resultInfo=new ResultInfo();
				resultInfo.setCode(CrmConstant.OP_FAILED_CODE);
				resultInfo.setMsg(CrmConstant.OP_FAILED_MSG);
				if(ex instanceof ParamsExcetion){
					ParamsExcetion pe=(ParamsExcetion) ex;
					resultInfo.setCode(pe.getErrorCode());
					resultInfo.setMsg(pe.getErrorMsg());
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				PrintWriter pw=null;
				try {
					pw=response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(null!=pw){
						pw.write(JSON.toJSONString(resultInfo));
						pw.flush();
						pw.close();
					}
				}
				return null;
			}else{
				/**
				 * 响应的为视图
				 */
				ModelAndView mv=getDefaultModelAndView(request);
				if(ex instanceof ParamsExcetion){
					ParamsExcetion pe=(ParamsExcetion) ex;
					//mv.setViewName(viewName);
					mv.addObject("errorMsg", pe.getErrorMsg());
				}
				return mv;
			}
		}else{
			return getDefaultModelAndView(request);
		}
	}
	private ModelAndView getDefaultModelAndView(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("error");
		mv.addObject("ctx", request.getContextPath());
		mv.addObject("uri", request.getRequestURI());
		mv.addObject("errorMsg", CrmConstant.OP_FAILED_MSG);
		return mv;
	}

}
