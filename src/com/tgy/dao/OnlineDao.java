package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.Online;

public class OnlineDao extends BaseBasicDAO<Online, ObjectId> {

	public OnlineDao() {
		super(Online.class, App.getInstance().getDatastore());
	}

}
