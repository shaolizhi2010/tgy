package com.tgy.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.tgy.exception.BaseException;
import com.tgy.util.C;

public class CommonValidator implements Validator{
	
	public CommonValidator isLogin(HttpServletRequest req,String errMsg) throws BaseException{ 
		if(req==null ||
				req.getSession(false)==null||
				req.getSession(false).getAttribute(C.user)==null){
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "需要登陆");
			}
			
		}
		return this;
	}
	
	public CommonValidator isShorter(String s,int length,String errMsg) throws BaseException{
		if(s==null){
			return this;
		}
		if(s.length()>=length){
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "长度应小于"+length);
			}
			
		}
		return this;
	}

	public CommonValidator isLonger(String s,int length,String errMsg) throws BaseException{
		if(s==null){
			return this;
		}
		if(s.length()<=length){
			
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "长度应大于"+length);
			}
		}
		return this;
	}

	
	public CommonValidator isNotNull(Object o,String errMsg) throws BaseException{ 
		if(o==null){
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "不能为空");
			}
			
		}
		return this;
	}
}
