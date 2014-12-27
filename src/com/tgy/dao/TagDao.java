package com.tgy.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.entity.Tag;

public class TagDao extends BasicDAO<Tag, ObjectId> {

	public TagDao() {
		super(Tag.class, App.getInstance().getDatastore());
	}

	public List<Tag> list(int num) {
		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).order("-favScore").limit(num);
		return find(query).asList();
	}

	// 根据tag name取tag
	public Tag getByName(String name) {
		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).filter("name", name).order("-favScore");
		return find(query).get();
	}

	public Tag getByID(String tagID) {
		if (tagID == null) {
			return null;
		}
		Tag tag = findOne("_id", new ObjectId(tagID));
		return tag;
	}

}
