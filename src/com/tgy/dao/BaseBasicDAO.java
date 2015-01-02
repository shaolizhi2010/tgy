package com.tgy.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.tgy.App;

public class BaseBasicDAO<T,K> extends BasicDAO<T, K> {
	
	public T byID(String id){
		if(StringUtils.isBlank(id)|| id.equalsIgnoreCase("null")){
			return null;
		}
		T t = findOne("_id", new ObjectId(id));
		return t;
	}
	
	//根据条件，取一个实体
	public T get(Class<T> C, Map<String,String> conditions) {
		
		Query<T> query = App.getInstance().getDatastore().createQuery(C);
		
		//filter by conditions
		if(conditions!=null && conditions.size()!=0){
			for(Map.Entry<String, String> e : conditions.entrySet()){
				query.filter(e.getKey(), e.getValue());
			}
		}
		return findOne(query);
	}
	
	//取list
	public List<T> list(Class<T> C, Map<String,String> conditions,String orderStr,  int limit) {
		
		Query<T> query = App.getInstance().getDatastore().createQuery(C);
		
		//filter by conditions
		if(conditions!=null && conditions.size()!=0){
			for(Map.Entry<String, String> e : conditions.entrySet()){
				query.filter(e.getKey(), e.getValue());
			}
		}
		//order
		if(StringUtils.isNotBlank(orderStr)){
			query.order(orderStr);
		}
		//limit
		if(limit!=0){
			query.limit(limit);
		}
		
		return find(query).asList();
	}
	
	public BaseBasicDAO(Class<T> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public BaseBasicDAO(Class<T> entityClass, MongoClient mongoClient,
			Morphia morphia, String dbName) {
		super(entityClass, mongoClient, morphia, dbName);
	}

	public BaseBasicDAO(Datastore ds) {
		super(ds);
	}

	public BaseBasicDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

 
	
	
	
}
