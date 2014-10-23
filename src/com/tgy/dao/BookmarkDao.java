package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.tgy.App;
import com.tgy.entity.Bookmark;

public class BookmarkDao extends BasicDAO<Bookmark, ObjectId> {

	public BookmarkDao() {
		super(Bookmark.class, App.getInstance().getDatastore());
	}

	public Bookmark getByID(String bookmarkID){
//		Query query = App.getInstance().getDatastore().createQuery(Bookmark.class).filter("_id", bookmarkID);
		Bookmark bookmark = findOne("_id", new ObjectId(bookmarkID) );
		return bookmark;
	}
	
}
