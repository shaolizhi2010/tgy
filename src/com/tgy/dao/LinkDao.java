package com.tgy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.tgy.App;
import com.tgy.statistic.entity.Link;
import com.tgy.util.ConditionMap;

public class LinkDao extends BaseBasicDAO<Link, ObjectId> {

	public LinkDao() {
		super(Link.class, App.getInstance().getDatastore());
	}
	
	public List<Link> list(int num){
		Query<Link> query = App.getInstance().getDatastore()
				.createQuery(Link.class).order("-favScore").limit(num);
		
		return find(query).asList();
		
	}
	//根据link url 取 link
		public List<Link> getByName(String name) {
			name = StringUtils.trim(name);
			Query<Link> query = App.getInstance().getDatastore()
					.createQuery(Link.class).filter("title", Pattern.compile(".*"+name+".*", Pattern.CASE_INSENSITIVE) )
					.order("-favScore");

			return find(query).asList();
		}
	
		
		
	public List<Link> getByTag(String tagID){
		return list(Link.class, new ConditionMap().add("tagID", tagID),null, 0);
		
 
		/*//TODO 
		Query<Link> query1 = App.getInstance().getDatastore()
				.createQuery(Link.class)
				.where(" {'tags.$id': '"+tagID+"'} ")
				///filter("tags.id", new ObjectId(tagID) )
				.order("-favScore");//order by users size
		List<Link> links = find(query1).asList();
		//System.out.println(links);
		return links;*/
//		
		//DBObject query = new BasicDBObject("tags.$id",new ObjectId(tagID) );
		//return App.getInstance().getDatastore().getDB().getCollection("Link").find(query).toArray();
		
		//  return new ArrayList<DBObject> ();
	}

	//根据link url 取 link
	/**
	 * 精确查询
	 * @param url
	 * @return
	 */
	public Link getByUrl(String url) {
		url = StringUtils.trim(url);
		Query<Link> query = App.getInstance().getDatastore()
				.createQuery(Link.class).filter("url", url)
				.order("-favScore");

		return find(query).get();
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	public List<Link> searchByUrl(String url){
		url = StringUtils.trim(url);
		Query<Link> query = App.getInstance().getDatastore()
				.createQuery(Link.class).filter("url", Pattern.compile(".*"+url+".*", Pattern.CASE_INSENSITIVE) )
				.order("-favScore");

		return find(query).asList();
	}

	public Link getByID(String linkID) {
		Link link = findOne("_id", new ObjectId(linkID));
		return link;
	}
	
	//收藏次数加一 by linkID
	public void incKeeps(String linkID){
		inc(linkID, "keeps");
	}
	
	//inceate field by linkID
	public void inc(String linkID,String filed,int num){
		Datastore ds = App.getInstance().getDatastore();
		Query<Link> query = ds.createQuery(Link.class).filter("_id", new ObjectId(linkID));
		UpdateOperations<Link> update = ds.createUpdateOperations(Link.class).inc(filed,num);
		ds.update(query, update);
		
	}
	
	//inceate field by linkID
	public void inc(String linkID,String filed){
		Datastore ds = App.getInstance().getDatastore();
		Query<Link> query = ds.createQuery(Link.class).filter("_id", new ObjectId(linkID));
		UpdateOperations<Link> update = ds.createUpdateOperations(Link.class).inc(filed);
		ds.update(query, update);
		
	}

}
