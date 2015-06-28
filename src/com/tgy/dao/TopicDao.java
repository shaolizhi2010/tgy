package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.Topic;

public class TopicDao extends BaseBasicDAO<Topic, ObjectId> {

	public TopicDao() {
		super(Topic.class, App.getInstance().getDatastore());
	}

}
