package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.tgy.App;
import com.tgy.entity.VisitHistory;

public class VisitHistoryDao extends BasicDAO<VisitHistory, ObjectId> {

	public VisitHistoryDao() {
		super(VisitHistory.class, App.getInstance().getDatastore());
	}
	
}
