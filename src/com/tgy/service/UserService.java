package com.tgy.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
 
public class UserService {
	
	UserDao uDao = new UserDao();
	
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
	
	public List<User> list(){
		
		Query<User> query = App.getInstance().getDatastore()
				.createQuery(User.class).order("-score").limit(50);
		
		return uDao.find(query).asList();
		
	}
}
