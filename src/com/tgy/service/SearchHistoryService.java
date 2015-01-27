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
import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.SearchHistory;


public class SearchHistoryService {
	 SearchHistoryDao dao = new SearchHistoryDao();

	public SearchHistory byID(String id) {
		return dao.byID(id);
	}

	public SearchHistory get( 
			Map<String, Object> conditions) {
		return dao.get(SearchHistory.class, conditions);
	}

	public List<SearchHistory> list( 
			Map<String, Object> conditions, String orderStr, int limit) {
		return dao.list(SearchHistory.class, conditions, orderStr, limit);
	}

	public List<SearchHistory> list( 
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.list(SearchHistory.class, conditions, orderStr, start, limit);
	}

	public SearchHistory byKeyword(String keyword) {
		return dao.byKeyword(keyword);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<SearchHistory> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<SearchHistory> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<SearchHistory> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<SearchHistory> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<SearchHistory> save(SearchHistory entity) {
		return dao.save(entity);
	}

	public Key<SearchHistory> save(SearchHistory entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<SearchHistory> q,
			UpdateOperations<SearchHistory> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<SearchHistory> q,
			UpdateOperations<SearchHistory> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(SearchHistory entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(SearchHistory entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<SearchHistory> q) {
		return dao.deleteByQuery(q);
	}

	public SearchHistory get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<SearchHistory> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<SearchHistory> findOneId() {
		return dao.findOneId();
	}

	public Key<SearchHistory> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<SearchHistory> findOneId(Query<SearchHistory> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<SearchHistory> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<SearchHistory> q) {
		return dao.count(q);
	}

	public SearchHistory findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public SearchHistory findOne(Query<SearchHistory> q) {
		return dao.findOne(q);
	}

	public QueryResults<SearchHistory> find() {
		return dao.find();
	}

	public QueryResults<SearchHistory> find(Query<SearchHistory> q) {
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
