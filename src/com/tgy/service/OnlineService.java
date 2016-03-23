package com.tgy.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.tgy.dao.OnlineDao;
import com.tgy.entity.Online;

public class OnlineService {
	
	OnlineDao dao = new OnlineDao();

	public Online byID(String id) {
		return dao.byID(id);
	}

	public Online get(Class<Online> C, Map<String, Object> conditions) {
		return dao.get(C, conditions);
	}

	public long count(Class<Online> C, Map<String, Object> conditions) {
		return dao.count(C, conditions);
	}

	public Query<Online> createQuery(Class<Online> C,
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.createQuery(C, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Online> list(Class<Online> C, Map<String, Object> conditions,
			String orderStr, int limit) {
		return dao.list(C, conditions, orderStr, limit);
	}

	public List<Online> list(Class<Online> C, Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return dao.list(C, conditions, orderStr, start, limit);
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Online> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Online> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Online> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Online> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Online> save(Online entity) {
		return dao.save(entity);
	}

	public Key<Online> save(Online entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Online> q,
			UpdateOperations<Online> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Online> q, UpdateOperations<Online> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Online entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Online entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Online> q) {
		return dao.deleteByQuery(q);
	}

	public Online get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Online> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Online> findOneId() {
		return dao.findOneId();
	}

	public Key<Online> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Online> findOneId(Query<Online> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Online> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Online> q) {
		return dao.count(q);
	}

	public Online findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Online findOne(Query<Online> q) {
		return dao.findOne(q);
	}

	public QueryResults<Online> find() {
		return dao.find();
	}

	public QueryResults<Online> find(Query<Online> q) {
		return dao.find(q);
	}

	public Datastore getDatastore() {
		return dao.getDatastore();
	}

	public void ensureIndexes() {
		dao.ensureIndexes();
	}

	public String toString() {
		return dao.toString();
	}
	
	 

}
