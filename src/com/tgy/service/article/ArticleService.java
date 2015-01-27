package com.tgy.service.article;

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
import com.tgy.dao.ArticleDao;
import com.tgy.entity.Article;

public class ArticleService {
	ArticleDao dao = new ArticleDao();

	public Article byID(String id) {
		return dao.byID(id);
	}

	public Article get(  Map<String, Object> conditions) {
		return dao.get(Article.class, conditions);
	}

	public long count(  Map<String, Object> conditions) {
		return dao.count(Article.class, conditions);
	}

	public Query<Article> createQuery( 
			Map<String, Object> conditions, String orderStr, int start,
			int limit) {
		return dao.createQuery(Article.class, conditions, orderStr, start, limit);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Article> list(  Map<String, Object> conditions,
			String orderStr, int limit) {
		return dao.list(Article.class, conditions, orderStr, limit);
	}

	public List<Article> list(  Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return dao.list(Article.class, conditions, orderStr, start, limit);
	}

	public DatastoreImpl getDs() {
		return dao.getDs();
	}

	public Class<Article> getEntityClazz() {
		return dao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public DBCollection getCollection() {
		return dao.getCollection();
	}

	public Query<Article> createQuery() {
		return dao.createQuery();
	}

	public UpdateOperations<Article> createUpdateOperations() {
		return dao.createUpdateOperations();
	}

	public Class<Article> getEntityClass() {
		return dao.getEntityClass();
	}

	public Key<Article> save(Article entity) {
		return dao.save(entity);
	}

	public Key<Article> save(Article entity, WriteConcern wc) {
		return dao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Article> q,
			UpdateOperations<Article> ops) {
		return dao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Article> q, UpdateOperations<Article> ops) {
		return dao.update(q, ops);
	}

	public WriteResult delete(Article entity) {
		return dao.delete(entity);
	}

	public WriteResult delete(Article entity, WriteConcern wc) {
		return dao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return dao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Article> q) {
		return dao.deleteByQuery(q);
	}

	public Article get(ObjectId id) {
		return dao.get(id);
	}

	public List<ObjectId> findIds() {
		return dao.findIds();
	}

	public List<ObjectId> findIds(Query<Article> q) {
		return dao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return dao.findIds(key, value);
	}

	public Key<Article> findOneId() {
		return dao.findOneId();
	}

	public Key<Article> findOneId(String key, Object value) {
		return dao.findOneId(key, value);
	}

	public Key<Article> findOneId(Query<Article> query) {
		return dao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return dao.exists(key, value);
	}

	public boolean exists(Query<Article> q) {
		return dao.exists(q);
	}

	public long count() {
		return dao.count();
	}

	public long count(String key, Object value) {
		return dao.count(key, value);
	}

	public long count(Query<Article> q) {
		return dao.count(q);
	}

	public Article findOne(String key, Object value) {
		return dao.findOne(key, value);
	}

	public Article findOne(Query<Article> q) {
		return dao.findOne(q);
	}

	public QueryResults<Article> find() {
		return dao.find();
	}

	public QueryResults<Article> find(Query<Article> q) {
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
