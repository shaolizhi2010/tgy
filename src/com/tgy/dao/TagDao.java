package com.tgy.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.Tag;
import com.tgy.util.PageType;

public class TagDao extends BaseBasicDAO<Tag, ObjectId> {

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
		return getByName(name,PageType.link);
	}
	
	// 根据tag name取tag
	public Tag getByName(String name,PageType type) {
		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).filter("name", name).filter("type", type).order("-favScore");
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
