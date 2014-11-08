package com.tgy.service;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
 
public class UserService {
	
	public User checkAndGetUser(String userID) throws BaseException{
		// check user exsit
		if (StringUtils.isBlank(userID)) {
			throw new BaseException(this, "操作失败,需要用户");
		}
		UserDao uDao = new UserDao();
		User user = uDao.getByID(userID);
		if (user == null) {
			throw new BaseException(this, "操作失败,需要用户");
		}
		return user;
	}
}
