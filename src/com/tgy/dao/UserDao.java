package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.tgy.App;
import com.tgy.entity.Bookmark;
import com.tgy.entity.User;

public class UserDao extends BasicDAO<User, ObjectId> {

	public UserDao() {
		super(User.class, App.getInstance().getDatastore());
	}
	
	//根据用户名密码从数据库找用户
	public User login(User user) {
		Query<User> query = App.getInstance().getDatastore()
				.createQuery(User.class).filter("name", user.name)
				.filter("password", user.password);
		User loginUser = findOne(query);
		return loginUser;
	}

}
