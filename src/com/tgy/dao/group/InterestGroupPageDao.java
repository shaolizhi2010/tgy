package com.tgy.dao.group;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.dao.BaseBasicDAO;
import com.tgy.entity.group.InterestGroupPage;

public class InterestGroupPageDao extends
		BaseBasicDAO<InterestGroupPage, ObjectId> {
	public InterestGroupPageDao() {
		super(InterestGroupPage.class, App.getInstance().getDatastore());
	}

}
