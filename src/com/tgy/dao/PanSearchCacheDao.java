package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.PanSearchCache;

public class PanSearchCacheDao extends BaseBasicDAO<PanSearchCache, ObjectId>  {
	public PanSearchCacheDao() {
		super(PanSearchCache.class, App.getInstance().getDatastore());
	}
	
}
