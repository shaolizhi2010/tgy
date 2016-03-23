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
import com.tgy.dao.ReplyDao;
import com.tgy.entity.Discuss;
import com.tgy.entity.Reply;
import com.tgy.util.ConditionMap;


public class ReplyService {
	 ReplyDao dao = new ReplyDao();
	 
	 public List<Reply> byToID(String pageID,int limit){
		 return list(Reply.class, new ConditionMap().add("toID", pageID), "-createDate", limit);
	 }
	 
	public Reply byID(String id) {
		return dao.byID(id);
	}
	
	public List<Reply> byUserID(String userID){
		return dao.list( Reply.class, new ConditionMap().add("userID", userID), null, 0, 0);
	}

	public Reply get(Class<Reply> C, Map<String, Object> conditions) {
		return dao.get(C, conditions);
	}

	public long count(Class<Reply> C, Map<String, Object> conditions) {
		return dao.count(C, conditions);
	}

	public Query<Reply> createQuery(Class<Reply> C,
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.createQuery(C, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Reply> list(Class<Reply> C,
			Map<String, Object> conditions, String orderStr, int limit) {
		return dao.list(C, conditions, orderStr, limit);
	}

	public List<Reply> list(Class<Reply> C,
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.list(C, conditions, orderStr, start, limit);
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Reply> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Reply> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Reply> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Reply> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Reply> save(Reply entity) {
		return dao.save(entity);
	}

	public Key<Reply> save(Reply entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Reply> q,
			UpdateOperations<Reply> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Reply> q,
			UpdateOperations<Reply> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Reply entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Reply entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Reply> q) {
		return dao.deleteByQuery(q);
	}

	public Reply get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Reply> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Reply> findOneId() {
		return dao.findOneId();
	}

	public Key<Reply> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Reply> findOneId(Query<Reply> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Reply> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Reply> q) {
		return dao.count(q);
	}

	public Reply findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Reply findOne(Query<Reply> q) {
		return dao.findOne(q);
	}

	public QueryResults<Reply> find() {
		return dao.find();
	}

	public QueryResults<Reply> find(Query<Reply> q) {
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
