package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.tgy.App;
import com.tgy.entity.InterestGroup;
import com.tgy.entity.User;

public class InterestGroupDao extends BasicDAO<InterestGroup, ObjectId> {
	public InterestGroupDao() {
		super(InterestGroup.class, App.getInstance().getDatastore());
	}
	
	public InterestGroup getByID(String groupID) {
		if(groupID==null || groupID.equalsIgnoreCase("null")){
			return null;
		}

		InterestGroup group = findOne("_id", new ObjectId(groupID));
		return group;
	}
}
