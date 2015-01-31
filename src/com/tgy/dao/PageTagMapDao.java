package com.tgy.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.PageTagMap;
import com.tgy.entity.Tag;

public class PageTagMapDao extends BaseBasicDAO<PageTagMap, ObjectId> {

	public PageTagMapDao() {
		super(PageTagMap.class, App.getInstance().getDatastore());
	}
 

}
