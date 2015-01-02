package com.tgy.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.StateFulBaseEntity;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.AuthManager;
import com.tgy.util.C;

public class CommonValidator implements Validator{
	
	public CommonValidator isLogin(HttpServletRequest req,String errMsg) throws BaseException{ 
		if(req==null ||
				req.getSession(false)==null||
				req.getSession(false).getAttribute(C.user)==null ||
				((User)req.getSession(false).getAttribute(C.user)).id == null
				){
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
	
	public CommonValidator isLength(String s,int length,String errMsg) throws BaseException{
		if(s==null){
			return this;
		}
		if(s.length()!=length){
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "长度应等于"+length);
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

	public CommonValidator eq(String s1,String s2, String errMsg) throws BaseException{ 
		if(StringUtils.equals(s1, s2)){
			return this;
		}
		else{
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "数据不匹配");
			}
		}
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
	
	 
	public CommonValidator isSameUser(User user, StateFulBaseEntity e2,String errMsg) throws BaseException{ 
		
		boolean isSameUser = AuthManager.checkOwner(user, e2);
		
		if(!isSameUser){ //不是owner
			if(StringUtils.isNotBlank(errMsg)){
				throw new BaseException(this, errMsg);
			}
			else{
				throw new BaseException(this, "无权限进行此操作");
			}
		}
		 
		return this;
	} 
	
}
