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
import com.tgy.dao.TopicDao;
import com.tgy.entity.Topic;
import com.tgy.util.ConditionMap;

public class TopicService {
	TopicDao dao = new TopicDao();

	public Topic byID(String id) {
		return dao.byID(id);
	}

	public Topic byTitle(String title) {
		return get(Topic.class, new ConditionMap().add("title", title));
	}

	public Topic get(Class<Topic> C, Map<String, Object> conditions) {
		return dao.get(C, conditions);
	}

	public long count(Class<Topic> C, Map<String, Object> conditions) {
		return dao.count(C, conditions);
	}

	public Query<Topic> createQuery(Class<Topic> C,
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.createQuery(C, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Topic> list(Class<Topic> C, Map<String, Object> conditions,
			String orderStr, int limit) {
		return dao.list(C, conditions, orderStr, limit);
	}

	public List<Topic> list(Class<Topic> C, Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return dao.list(C, conditions, orderStr, start, limit);
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Topic> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Topic> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Topic> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Topic> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Topic> save(Topic entity) {
		return dao.save(entity);
	}

	public Key<Topic> save(Topic entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Topic> q, UpdateOperations<Topic> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Topic> q, UpdateOperations<Topic> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Topic entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Topic entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Topic> q) {
		return dao.deleteByQuery(q);
	}

	public Topic get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Topic> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Topic> findOneId() {
		return dao.findOneId();
	}

	public Key<Topic> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Topic> findOneId(Query<Topic> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Topic> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Topic> q) {
		return dao.count(q);
	}

	public Topic findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Topic findOne(Query<Topic> q) {
		return dao.findOne(q);
	}

	public QueryResults<Topic> find() {
		return dao.find();
	}

	public QueryResults<Topic> find(Query<Topic> q) {
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
