package com.tgy.dao.group;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.dao.BaseBasicDAO;
import com.tgy.entity.group.InterestGroupFolder;

public class InterestGroupFolderDao extends BaseBasicDAO<InterestGroupFolder, ObjectId> {
	
	public InterestGroupFolderDao() {
		super(InterestGroupFolder.class, App.getInstance().getDatastore());
	}
	
}
