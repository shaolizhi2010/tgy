package com.tgy.dao.group;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.dao.BaseBasicDAO;
import com.tgy.entity.GroupDiscuss;

public class GroupDiscussDao extends BaseBasicDAO<GroupDiscuss, ObjectId> {

	public GroupDiscussDao() {
		super(GroupDiscuss.class, App.getInstance().getDatastore());
	}
	    
}
