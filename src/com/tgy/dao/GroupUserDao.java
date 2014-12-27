package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.tgy.App;
import com.tgy.entity.GroupUser;

public class GroupUserDao extends BasicDAO<GroupUser, ObjectId> {
	public GroupUserDao() {
		super(GroupUser.class, App.getInstance().getDatastore());
	}
}
