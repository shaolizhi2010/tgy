package com.tgy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.tgy.App;
import com.tgy.dao.VisitHistoryDao;
import com.tgy.entity.User;
import com.tgy.entity.VisitHistory;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;

public class VisitHistoryService {
	VisitHistoryDao dao = new VisitHistoryDao();

	public List<VisitHistory> getByUser(String requestUserID,
			String responseUserID, String orderStr) {

		if(StringUtils.isBlank(requestUserID) && StringUtils.isBlank(responseUserID)){
			return new ArrayList<VisitHistory>();
		}
		
		return list(new ConditionMap().add("requestUserID", requestUserID).add("responseUserID", responseUserID), orderStr, 0);
	}
	public Key<VisitHistory> save(VisitHistory entity) {
		
		List<VisitHistory> list = getByUser(entity.requestUserID, entity.responseUserID, null);
		if(!CollectionUtils.isEmpty(list)){
			entity= list.get(0);
			entity.lastVisitDateTime = U.dateTime();
		}
		if(StringUtils.equals(entity.requestUserID, entity.responseUserID)){
			entity.timesByOther ++;
		}
		entity.showTimes++;
		return dao.save(entity);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<VisitHistory> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<VisitHistory> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<VisitHistory> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<VisitHistory> getEntityClass() {
		return dao.getEntityClass();
	}

	

	public Key<VisitHistory> save(VisitHistory entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<VisitHistory> q,
			UpdateOperations<VisitHistory> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<VisitHistory> q,
			UpdateOperations<VisitHistory> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(VisitHistory entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(VisitHistory entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<VisitHistory> q) {
		return dao.deleteByQuery(q);
	}

	public VisitHistory get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<VisitHistory> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<VisitHistory> findOneId() {
		return dao.findOneId();
	}

	public Key<VisitHistory> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<VisitHistory> findOneId(Query<VisitHistory> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<VisitHistory> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<VisitHistory> q) {
		return dao.count(q);
	}

	public VisitHistory findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public VisitHistory findOne(Query<VisitHistory> q) {
		return dao.findOne(q);
	}

	public QueryResults<VisitHistory> find() {
		return dao.find();
	}

	public QueryResults<VisitHistory> find(Query<VisitHistory> q) {
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
	public VisitHistory byID(String id) {
		return dao.byID(id);
	}
	public VisitHistory get( 
			Map<String, String> conditions) {
		return dao.get(VisitHistory.class, conditions);
	}
	public List<VisitHistory> list( 
			Map<String, String> conditions, String orderStr, int limit) {
		return dao.list(VisitHistory.class, conditions, orderStr, limit);
	}

}
