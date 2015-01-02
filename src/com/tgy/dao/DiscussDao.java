package com.tgy.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.Discuss;

public class DiscussDao extends BaseBasicDAO<Discuss, ObjectId> {

	public DiscussDao() {
		super(Discuss.class, App.getInstance().getDatastore());
	}
	public List<Discuss> list(String sourceID,String targetID, String type, int num,String orderStr) {
		Query<Discuss> query = App.getInstance().getDatastore().createQuery(Discuss.class);
		if(StringUtils.isNotBlank(sourceID)){
			query.filter("sourceID", sourceID);
		}
		if(StringUtils.isNotBlank(targetID)){
			query.filter("targetID", targetID);
		}
		if(StringUtils.isNotBlank(type)){
			query.filter("type", type);
		}
		if(num>0){
			query.limit(num);
		}
		if(StringUtils.isNotBlank(orderStr)){
			query.order(orderStr);
		}
		return find(query).asList();
	}
	
	
	public List<Discuss> getDiscussesByUserID(String userID) {

		Query<Discuss> query = App.getInstance().getDatastore()
				.createQuery(Discuss.class).filter("userID", userID)
			 ;

		return find(query).asList();

	}
	
	public List<Discuss> getFoldersByUserID(String userID,String sort) {
		if(StringUtils.isBlank(sort)){
			return getDiscussesByUserID(userID);
		}
		
		Query<Discuss> query = App.getInstance().getDatastore()
				.createQuery(Discuss.class).filter("userID", userID)
				.order(sort);
		
		return find(query).asList();

	}

	public Discuss getByID(String discussID) {
		if (StringUtils.isBlank(discussID))
			return null;
		Discuss discuss = findOne("_id", new ObjectId(discussID));
		return discuss;
	}

}
