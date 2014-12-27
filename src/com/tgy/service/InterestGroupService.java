package com.tgy.service;

import java.util.List;

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
import com.tgy.dao.InterestGroupDao;
import com.tgy.entity.InterestGroup;

public class InterestGroupService {
	InterestGroupDao dao = new InterestGroupDao();

	public InterestGroup byID(String groupID){
		return dao.getByID(groupID);
	}
	
	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<InterestGroup> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<InterestGroup> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<InterestGroup> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<InterestGroup> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<InterestGroup> save(InterestGroup entity) {
		return dao.save(entity);
	}

	public Key<InterestGroup> save(InterestGroup entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<InterestGroup> q,
			UpdateOperations<InterestGroup> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<InterestGroup> q,
			UpdateOperations<InterestGroup> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(InterestGroup entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(InterestGroup entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<InterestGroup> q) {
		return dao.deleteByQuery(q);
	}

	public InterestGroup get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<InterestGroup> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<InterestGroup> findOneId() {
		return dao.findOneId();
	}

	public Key<InterestGroup> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<InterestGroup> findOneId(Query<InterestGroup> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<InterestGroup> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<InterestGroup> q) {
		return dao.count(q);
	}

	public InterestGroup findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public InterestGroup findOne(Query<InterestGroup> q) {
		return dao.findOne(q);
	}

	public QueryResults<InterestGroup> find() {
		return dao.find();
	}

	public QueryResults<InterestGroup> find(Query<InterestGroup> q) {
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
