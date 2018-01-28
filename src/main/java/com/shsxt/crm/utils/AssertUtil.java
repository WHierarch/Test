package com.shsxt.crm.utils;

import com.shsxt.crm.exceptions.NoLoginException;
import com.shsxt.crm.exceptions.ParamsExcetion;


public class AssertUtil {
	
	public static void isTure(Boolean flag,String errorMsg) {
		if(flag){
			throw new ParamsExcetion(errorMsg);
		}
	}
	
	
	public static void isTure(Boolean flag,String errorMsg,Integer errorCode) {
		if(flag){
			throw new ParamsExcetion(errorMsg,errorCode);
		}
	}
	
	public static void noLogin(Boolean flag,String errorMsg){
		if(flag){
			throw new NoLoginException(errorMsg);
		}
	}
	
}
