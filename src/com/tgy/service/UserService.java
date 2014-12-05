package com.tgy.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.U;
 
public class UserService {
	
	UserDao uDao = new UserDao();
	
	//拿到一个openid 系统已有，说明是老用户，返回user 如没有，说明新用户，创建新user并返回
	public User dealWithOpenID(String openID,String userName) throws BaseException{
		if(StringUtils.isBlank(openID )  || openID.equalsIgnoreCase("null")){
			throw new BaseException(this,"需要open ID");
		}
		User user = uDao.byOpenID(openID);
		if(user == null){
			user = new User();
			user.createDate = U.dateTime();
			user.openID = openID;
			
			int times = 1;
			while (times<6) {
				User oldUser = uDao.getByName(userName);
				if (oldUser == null) {
					break;
				}
				//重名 用户名后加随机数
				userName =  userName + U.randomNum( 1 ) ;//第一次循环 abc, 第二次 abc[0]，第三次 abc[0][0]
				times++;
			}
			user.name = userName;
			
			uDao.save(user);
		}
		return user;
	}
	
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
