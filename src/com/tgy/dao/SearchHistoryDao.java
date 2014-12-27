package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.SearchHistory;

public class SearchHistoryDao extends BasicDAO<SearchHistory, ObjectId> {

	public SearchHistoryDao() {
		super(SearchHistory.class, App.getInstance().getDatastore());
	}
	
	public SearchHistory byKeyword(String keyword){
		Query<SearchHistory> query = App.getInstance().getDatastore()
				.createQuery(SearchHistory.class).filter("keyword", keyword);
		return findOne(query);
	}
	
}
