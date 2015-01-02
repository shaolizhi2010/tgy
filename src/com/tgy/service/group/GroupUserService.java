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
import com.tgy.dao.group.GroupUserDao;
import com.tgy.entity.group.GroupUser;

public class GroupUserService {
	GroupUserDao dao = new GroupUserDao();
	
	public List<GroupUser> list( 
			Map<String, String> conditions, String orderStr, int limit) {
		return dao.list(GroupUser.class, conditions, orderStr, limit);
	}

	public GroupUser get( Map<String, String> conditions) {
		return dao.get(GroupUser.class, conditions);
	}

	public GroupUser byID(String id) {
		return dao.byID(id);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<GroupUser> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<GroupUser> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<GroupUser> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<GroupUser> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<GroupUser> save(GroupUser entity) {
		return dao.save(entity);
	}

	public Key<GroupUser> save(GroupUser entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<GroupUser> q,
			UpdateOperations<GroupUser> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<GroupUser> q,
			UpdateOperations<GroupUser> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(GroupUser entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(GroupUser entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<GroupUser> q) {
		return dao.deleteByQuery(q);
	}

	public GroupUser get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<GroupUser> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<GroupUser> findOneId() {
		return dao.findOneId();
	}

	public Key<GroupUser> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<GroupUser> findOneId(Query<GroupUser> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<GroupUser> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<GroupUser> q) {
		return dao.count(q);
	}

	public GroupUser findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public GroupUser findOne(Query<GroupUser> q) {
		return dao.findOne(q);
	}

	public QueryResults<GroupUser> find() {
		return dao.find();
	}

	public QueryResults<GroupUser> find(Query<GroupUser> q) {
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
