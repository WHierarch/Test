package com.shsxt.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.ResultInfo;

public class BaseController {
	
	@ModelAttribute
	public void preMethod(HttpServletRequest request){
		request.setAttribute("ctx", request.getContextPath());
	}
	
	
	public ResultInfo success(String msg){
		ResultInfo result=new ResultInfo();
		result.setMsg(msg);
		result.setCode(CrmConstant.OP_SUCCESS_CODE);
		return result;
	}
	
	public ResultInfo success(String msg,Object rst){
		ResultInfo result=new ResultInfo();
		result.setMsg(msg);
		result.setResult(rst);
		result.setCode(CrmConstant.OP_SUCCESS_CODE);
		return result;
	}

}
