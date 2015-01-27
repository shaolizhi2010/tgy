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
import com.tgy.dao.FollowsDao;
import com.tgy.entity.Follows;


public class FollowsService {
	FollowsDao dao = new FollowsDao();

	public Follows byID(String id) {
		return dao.byID(id);
	}

	public Follows get(  Map<String, Object> conditions) {
		return dao.get(Follows.class, conditions);
	}

	public List<Follows> list(  Map<String, Object> conditions,
			String orderStr, int limit) {
		return dao.list(Follows.class, conditions, orderStr, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Follows> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Follows> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Follows> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Follows> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Follows> save(Follows entity) {
		return dao.save(entity);
	}

	public Key<Follows> save(Follows entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Follows> q,
			UpdateOperations<Follows> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Follows> q, UpdateOperations<Follows> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Follows entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Follows entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Follows> q) {
		return dao.deleteByQuery(q);
	}

	public Follows get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Follows> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Follows> findOneId() {
		return dao.findOneId();
	}

	public Key<Follows> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Follows> findOneId(Query<Follows> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Follows> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Follows> q) {
		return dao.count(q);
	}

	public Follows findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Follows findOne(Query<Follows> q) {
		return dao.findOne(q);
	}

	public QueryResults<Follows> find() {
		return dao.find();
	}

	public QueryResults<Follows> find(Query<Follows> q) {
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
