package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.Reply;

public class ReplyDao extends BaseBasicDAO<Reply, ObjectId> {

	public ReplyDao() {
		super(Reply.class, App.getInstance().getDatastore());
	}

}
