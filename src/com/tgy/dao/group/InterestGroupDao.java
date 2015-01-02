package com.tgy.dao.group;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.dao.BaseBasicDAO;
import com.tgy.entity.group.InterestGroup;

public class InterestGroupDao extends BaseBasicDAO<InterestGroup, ObjectId> {
	public InterestGroupDao() {
		super(InterestGroup.class, App.getInstance().getDatastore());
	}
	 
}
