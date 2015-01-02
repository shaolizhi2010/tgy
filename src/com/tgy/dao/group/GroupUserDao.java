package com.tgy.dao.group;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.dao.BaseBasicDAO;
import com.tgy.entity.group.GroupUser;

public class GroupUserDao extends BaseBasicDAO<GroupUser, ObjectId> {
	public GroupUserDao() {
		super(GroupUser.class, App.getInstance().getDatastore());
	}
}
