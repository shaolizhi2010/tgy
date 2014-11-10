package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.User;
import com.tgy.statistic.entity.Tag;

public class UserDao extends BasicDAO<User, ObjectId> {

	public UserDao() {
		super(User.class, App.getInstance().getDatastore());
	}

	// 根据用户名密码从数据库找用户
	public User login(User user) {
		Query<User> query = App.getInstance().getDatastore()
				.createQuery(User.class).filter("name", user.name)
				.filter("password", user.password);
		User loginUser = findOne(query);
		if(loginUser!=null){ //TODO 异步
			loginUser.loginTimes++;
			loginUser.score+=10;
			save(loginUser);
		}
		return loginUser;
	}
	public User getByID(String userID) {
		if(userID==null || userID.equalsIgnoreCase("null")){
			return null;
		}

		User user = findOne("_id", new ObjectId(userID));
		return user;
	}
	
	public User getByName(String userName) {
		if(userName==null || userName.equalsIgnoreCase("null")){
			return null;
		}

		Query<User> query = App.getInstance().getDatastore()
				.createQuery(User.class).filter("name", userName);
		User user = findOne(query);
		return user;
	}
	 
}
