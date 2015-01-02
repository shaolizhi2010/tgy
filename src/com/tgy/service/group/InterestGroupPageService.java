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
import com.tgy.dao.group.InterestGroupPageDao;
import com.tgy.entity.group.InterestGroupPage;

public class InterestGroupPageService {

	InterestGroupPageDao pDao = new InterestGroupPageDao();

	public InterestGroupPage byID(String id) {
		return pDao.byID(id);
	}

	public InterestGroupPage get(Map<String, String> conditions) {
		return pDao.get(InterestGroupPage.class, conditions);
	}

	public List<InterestGroupPage> list(Map<String, String> conditions,
			String orderStr, int limit) {
		return pDao.list(InterestGroupPage.class, conditions, orderStr, limit);
	}

	public int hashCode() {
		return pDao.hashCode();
	}

	public DatastoreImpl getDs() {
		return pDao.getDs();
	}

	public Class<InterestGroupPage> getEntityClazz() {
		return pDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return pDao.equals(obj);
	}

	public DBCollection getCollection() {
		return pDao.getCollection();
	}

	public Query<InterestGroupPage> createQuery() {
		return pDao.createQuery();
	}

	public UpdateOperations<InterestGroupPage> createUpdateOperations() {
		return pDao.createUpdateOperations();
	}

	public Class<InterestGroupPage> getEntityClass() {
		return pDao.getEntityClass();
	}

	public Key<InterestGroupPage> save(InterestGroupPage entity) {
		return pDao.save(entity);
	}

	public Key<InterestGroupPage> save(InterestGroupPage entity, WriteConcern wc) {
		return pDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<InterestGroupPage> q,
			UpdateOperations<InterestGroupPage> ops) {
		return pDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<InterestGroupPage> q,
			UpdateOperations<InterestGroupPage> ops) {
		return pDao.update(q, ops);
	}

	public WriteResult delete(InterestGroupPage entity) {
		return pDao.delete(entity);
	}

	public WriteResult delete(InterestGroupPage entity, WriteConcern wc) {
		return pDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return pDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<InterestGroupPage> q) {
		return pDao.deleteByQuery(q);
	}

	public InterestGroupPage get(ObjectId id) {
		return pDao.get(id);
	}

	public List<ObjectId> findIds() {
		return pDao.findIds();
	}

	public List<ObjectId> findIds(Query<InterestGroupPage> q) {
		return pDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return pDao.findIds(key, value);
	}

	public Key<InterestGroupPage> findOneId() {
		return pDao.findOneId();
	}

	public Key<InterestGroupPage> findOneId(String key, Object value) {
		return pDao.findOneId(key, value);
	}

	public Key<InterestGroupPage> findOneId(Query<InterestGroupPage> query) {
		return pDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return pDao.exists(key, value);
	}

	public boolean exists(Query<InterestGroupPage> q) {
		return pDao.exists(q);
	}

	public long count() {
		return pDao.count();
	}

	public long count(String key, Object value) {
		return pDao.count(key, value);
	}

	public long count(Query<InterestGroupPage> q) {
		return pDao.count(q);
	}

	public InterestGroupPage findOne(String key, Object value) {
		return pDao.findOne(key, value);
	}

	public InterestGroupPage findOne(Query<InterestGroupPage> q) {
		return pDao.findOne(q);
	}

	public QueryResults<InterestGroupPage> find() {
		return pDao.find();
	}

	public QueryResults<InterestGroupPage> find(Query<InterestGroupPage> q) {
		return pDao.find(q);
	}

	public Datastore getDatastore() {
		return pDao.getDatastore();
	}

	public void ensureIndexes() {
		pDao.ensureIndexes();
	}

	public String toString() {
		return pDao.toString();
	}

}
