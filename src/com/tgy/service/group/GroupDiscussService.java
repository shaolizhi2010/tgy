package com.tgy.service.group;

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
import com.tgy.dao.group.GroupDiscussDao;
import com.tgy.entity.GroupDiscuss;

public class GroupDiscussService {
	GroupDiscussDao dao = new GroupDiscussDao();

	public GroupDiscuss byID(String id) {
		return dao.byID(id);
	}

	public GroupDiscuss get( 
			Map<String, Object> conditions) {
		return dao.get(GroupDiscuss.class, conditions);
	}

	public List<GroupDiscuss> list(
			Map<String, Object> conditions, String orderStr, int limit) {
		return dao.list(GroupDiscuss.class, conditions, orderStr, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<GroupDiscuss> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<GroupDiscuss> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<GroupDiscuss> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<GroupDiscuss> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<GroupDiscuss> save(GroupDiscuss entity) {
		return dao.save(entity);
	}

	public Key<GroupDiscuss> save(GroupDiscuss entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<GroupDiscuss> q,
			UpdateOperations<GroupDiscuss> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<GroupDiscuss> q,
			UpdateOperations<GroupDiscuss> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(GroupDiscuss entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(GroupDiscuss entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<GroupDiscuss> q) {
		return dao.deleteByQuery(q);
	}

	public GroupDiscuss get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<GroupDiscuss> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<GroupDiscuss> findOneId() {
		return dao.findOneId();
	}

	public Key<GroupDiscuss> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<GroupDiscuss> findOneId(Query<GroupDiscuss> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<GroupDiscuss> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<GroupDiscuss> q) {
		return dao.count(q);
	}

	public GroupDiscuss findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public GroupDiscuss findOne(Query<GroupDiscuss> q) {
		return dao.findOne(q);
	}

	public QueryResults<GroupDiscuss> find() {
		return dao.find();
	}

	public QueryResults<GroupDiscuss> find(Query<GroupDiscuss> q) {
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
