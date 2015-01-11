package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.Follows;

public class FollowsDao extends BaseBasicDAO<Follows, ObjectId> {

	public FollowsDao() {
		super(Follows.class, App.getInstance().getDatastore());
	}
	
}
