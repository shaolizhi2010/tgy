package com.tgy.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.statistic.entity.Tag;

public class TagDao extends BasicDAO<Tag, ObjectId> {

	public TagDao() {
		super(Tag.class, App.getInstance().getDatastore());
	}
	
	
	//根据tag name取tag
	public Tag getByName(String name) {

		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).filter("name", name)
				.order("-createDate");

		return find(query).get();

	}

	public Tag getByID(String tagID) {
		if(tagID==null){
			return null;
		}
		Tag tag = findOne("_id", new ObjectId(tagID));
		return tag;
	}

}
