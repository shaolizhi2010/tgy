package com.tgy.service;

import java.util.ArrayList;
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
import com.tgy.dao.PanSearchCacheDao;
import com.tgy.entity.Page;
import com.tgy.entity.PanSearchCache;
import com.tgy.util.ConditionMap;

/**
 * 网盘搜索结果缓存服务类
 * @author qq
 *
 */
public class PanSearchCacheService   {
	
	PanSearchCacheDao dao = new PanSearchCacheDao();
	
	public void cacheAll(List<Page> pages,String key){
		 
		for(Page p :pages){
			PanSearchCache pc = new PanSearchCache();
			pc.name = p.name;
			pc.url = p.url;
			pc.comment = p.comment;
			pc.key = key;
			dao.save(pc);
		}
	}
	
	public List<Page> ListByKey(String key){
		List<PanSearchCache> caches =  list(new ConditionMap().add("key", key), null, 0, 0);
		List<Page> pages = new ArrayList<>();
		for(PanSearchCache c : caches){
			Page p = new Page();
			p.name = c.name;
			p.url = c.url;
			p.comment = c.comment;
			pages.add(p);
		}
		return pages;
	}

	public List<PanSearchCache> list( Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return dao.list(PanSearchCache.class, conditions, orderStr, start, limit);
	}

	public PanSearchCache byID(String id) {
		return dao.byID(id);
	}

	public PanSearchCache get(Class<PanSearchCache> C,
			Map<String, Object> conditions) {
		return dao.get(C, conditions);
	}

	public List<PanSearchCache> list(Class<PanSearchCache> C,
			Map<String, Object> conditions, String orderStr, int limit) {
		return dao.list(C, conditions, orderStr, limit);
	}

	public List<PanSearchCache> list(Class<PanSearchCache> C,
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.list(C, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<PanSearchCache> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<PanSearchCache> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<PanSearchCache> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<PanSearchCache> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<PanSearchCache> save(PanSearchCache entity) {
		return dao.save(entity);
	}

	public Key<PanSearchCache> save(PanSearchCache entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<PanSearchCache> q,
			UpdateOperations<PanSearchCache> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<PanSearchCache> q,
			UpdateOperations<PanSearchCache> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(PanSearchCache entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(PanSearchCache entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<PanSearchCache> q) {
		return dao.deleteByQuery(q);
	}

	public PanSearchCache get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<PanSearchCache> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<PanSearchCache> findOneId() {
		return dao.findOneId();
	}

	public Key<PanSearchCache> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<PanSearchCache> findOneId(Query<PanSearchCache> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<PanSearchCache> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<PanSearchCache> q) {
		return dao.count(q);
	}

	public PanSearchCache findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public PanSearchCache findOne(Query<PanSearchCache> q) {
		return dao.findOne(q);
	}

	public QueryResults<PanSearchCache> find() {
		return dao.find();
	}

	public QueryResults<PanSearchCache> find(Query<PanSearchCache> q) {
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
