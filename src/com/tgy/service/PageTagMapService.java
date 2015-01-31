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
import com.tgy.dao.PageTagMapDao;
import com.tgy.entity.PageTagMap;

public class PageTagMapService {
	PageTagMapDao dao = new PageTagMapDao();

	public PageTagMap byID(String id) {
		return dao.byID(id);
	}

	public PageTagMap get( Map<String, Object> conditions) {
		return dao.get(PageTagMap.class, conditions);
	}

	public long count( Map<String, Object> conditions) {
		return dao.count(PageTagMap.class, conditions);
	}

	public Query<PageTagMap> createQuery(
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.createQuery(PageTagMap.class, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<PageTagMap> list(
			Map<String, Object> conditions, String orderStr, int limit) {
		return dao.list(PageTagMap.class, conditions, orderStr, limit);
	}

	public List<PageTagMap> list(
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.list(PageTagMap.class, conditions, orderStr, start, limit);
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<PageTagMap> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<PageTagMap> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<PageTagMap> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<PageTagMap> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<PageTagMap> save(PageTagMap entity) {
		return dao.save(entity);
	}

	public Key<PageTagMap> save(PageTagMap entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<PageTagMap> q,
			UpdateOperations<PageTagMap> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<PageTagMap> q,
			UpdateOperations<PageTagMap> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(PageTagMap entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(PageTagMap entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<PageTagMap> q) {
		return dao.deleteByQuery(q);
	}

	public PageTagMap get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<PageTagMap> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<PageTagMap> findOneId() {
		return dao.findOneId();
	}

	public Key<PageTagMap> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<PageTagMap> findOneId(Query<PageTagMap> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<PageTagMap> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<PageTagMap> q) {
		return dao.count(q);
	}

	public PageTagMap findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public PageTagMap findOne(Query<PageTagMap> q) {
		return dao.findOne(q);
	}

	public QueryResults<PageTagMap> find() {
		return dao.find();
	}

	public QueryResults<PageTagMap> find(Query<PageTagMap> q) {
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
