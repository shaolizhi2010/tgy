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
import com.tgy.dao.DiscussDao;
import com.tgy.entity.Discuss;
import com.tgy.util.U;

public class DiscussService {
	DiscussDao dao = new DiscussDao();

	public Discuss byID(String id) {
		return dao.byID(id);
	}

	public Discuss get(Map<String, Object> conditions) {
		return dao.get(Discuss.class, conditions);
	}

	public List<Discuss> list(  Map<String, Object> conditions,
			String orderStr, int limit) {
		return dao.list(Discuss.class, conditions, orderStr, limit);
	}

	public List<Discuss> list(  Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return dao.list(Discuss.class, conditions, orderStr, start, limit);
	}
	
	public long count(  Map<String, Object> conditions) {
		return dao.count(Discuss.class, conditions);
	}

	public Query<Discuss> createQuery( 
			Map<String, Object> conditions,String orderStr, int start, int limit) {
		return dao.createQuery(Discuss.class, conditions,orderStr,start,limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Discuss> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Discuss> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Discuss> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Discuss> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Discuss> save(Discuss entity) {
		entity.lastModifyDate = U.dateTime();
		return dao.save(entity);
	}

	public Key<Discuss> save(Discuss entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Discuss> q,
			UpdateOperations<Discuss> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Discuss> q, UpdateOperations<Discuss> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Discuss entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Discuss entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Discuss> q) {
		return dao.deleteByQuery(q);
	}

	public Discuss get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Discuss> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Discuss> findOneId() {
		return dao.findOneId();
	}

	public Key<Discuss> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Discuss> findOneId(Query<Discuss> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Discuss> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Discuss> q) {
		return dao.count(q);
	}

	public Discuss findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Discuss findOne(Query<Discuss> q) {
		return dao.findOne(q);
	}

	public QueryResults<Discuss> find() {
		return dao.find();
	}

	public QueryResults<Discuss> find(Query<Discuss> q) {
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
