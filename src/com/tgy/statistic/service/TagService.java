package com.tgy.statistic.service;

import java.util.Iterator;
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
import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.dao.TagDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.service.PageTagMapService;
import com.tgy.util.PageType;

public class TagService {

	FolderService fService = new FolderService();
	PageTagMapService pts = new PageTagMapService();
	
	TagDao tagDao = new TagDao();
	
	public List<Tag> list(int num) {
		return tagDao.list(num);
	}

	public List<Tag> list(  Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return tagDao.list(Tag.class, conditions, orderStr, start, limit);
	}



	public Tag byID(String id) {
		return tagDao.byID(id);
	}

	public Tag getByName(String name, PageType type) {
		return tagDao.getByName(name, type);
	}

	public Tag getByName(String name) {
		return tagDao.getByName(name);
	}

	public Tag getByID(String tagID) {
		return tagDao.getByID(tagID);
	}

	public int hashCode() {
		return tagDao.hashCode();
	}

	public DatastoreImpl getDs() {
		return tagDao.getDs();
	}

	public Class<Tag> getEntityClazz() {
		return tagDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return tagDao.equals(obj);
	}

	public DBCollection getCollection() {
		return tagDao.getCollection();
	}

	public Query<Tag> createQuery() {
		return tagDao.createQuery();
	}

	public UpdateOperations<Tag> createUpdateOperations() {
		return tagDao.createUpdateOperations();
	}

	public Class<Tag> getEntityClass() {
		return tagDao.getEntityClass();
	}

	public Key<Tag> save(Tag entity) {
		return tagDao.save(entity);
	}

	public Key<Tag> save(Tag entity, WriteConcern wc) {
		return tagDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Tag> q, UpdateOperations<Tag> ops) {
		return tagDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Tag> q, UpdateOperations<Tag> ops) {
		return tagDao.update(q, ops);
	}

	public WriteResult delete(Tag entity) {
		return tagDao.delete(entity);
	}

	public WriteResult delete(Tag entity, WriteConcern wc) {
		return tagDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return tagDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Tag> q) {
		return tagDao.deleteByQuery(q);
	}

	public Tag get(ObjectId id) {
		return tagDao.get(id);
	}

	public List<ObjectId> findIds() {
		return tagDao.findIds();
	}

	public List<ObjectId> findIds(Query<Tag> q) {
		return tagDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return tagDao.findIds(key, value);
	}

	public Key<Tag> findOneId() {
		return tagDao.findOneId();
	}

	public Key<Tag> findOneId(String key, Object value) {
		return tagDao.findOneId(key, value);
	}

	public Key<Tag> findOneId(Query<Tag> query) {
		return tagDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return tagDao.exists(key, value);
	}

	public boolean exists(Query<Tag> q) {
		return tagDao.exists(q);
	}

	public long count() {
		return tagDao.count();
	}

	public long count(String key, Object value) {
		return tagDao.count(key, value);
	}

	public long count(Query<Tag> q) {
		return tagDao.count(q);
	}

	public Tag findOne(String key, Object value) {
		return tagDao.findOne(key, value);
	}

	public Tag findOne(Query<Tag> q) {
		return tagDao.findOne(q);
	}

	public QueryResults<Tag> find() {
		return tagDao.find();
	}

	public QueryResults<Tag> find(Query<Tag> q) {
		return tagDao.find(q);
	}

	public Datastore getDatastore() {
		return tagDao.getDatastore();
	}

	public void ensureIndexes() {
		tagDao.ensureIndexes();
	}

	public String toString() {
		return tagDao.toString();
	}

	public Tag searchByName(String name) {

		TagDao tDao = new TagDao();

		Tag tag = tDao.getByName(name);
		return tag;

	}

	//扫描 folder表， 并声称Tag
	public void scan() {
//		FolderDao fDao = new FolderDao();
//		UserDao uDao = new UserDao();
//		
//		
//		//找到从来没处理过的
//		Query<Folder> query = App.getInstance().getDatastore()
//				.createQuery(Folder.class).filter("scanTimes",0);
//		
//		Iterator<Folder> it = fDao.find(query).iterator();
//		while (it.hasNext()) {
//			Folder folder = it.next();
//			folder.scanTimes ++;
//			fDao.save(folder);
//			
//			User user = uDao.getByID(folder.userID);
//
//			TagDao tDao = new TagDao();
//			Tag tag = tDao.getByName(folder.name);
//			if (tag == null) { // tag第一次被创建
//				tag = new Tag();
//				tag.name = folder.name;
//				tag.createDate = folder.createDate;
//				tag.keeps = 1;
//				tag.favScore = Tag.keepScore;
//
//			} else { // tag已存在,计数加一
//				tag.keeps++;
//				tag.favScore += Tag.keepScore;
//
//			}
//			//tag.add(user);
//			tDao.save(tag);// save tag
//		}

	}
	
	public List<Tag> list(){
		
		TagDao tDao = new TagDao();
		
		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).order("-favScore").limit(50);
		
		return tDao.find(query).asList();
		
	}

	/*
	 * public List<Link> searchByName(String name){
	 * 
	 * TagDao tDao = new TagDao(); LinkDao lDao = new LinkDao();
	 * 
	 * Tag tag = tDao.getByName(name); if(tag==null){ return new ArrayList<>();
	 * } return tag.links;
	 * 
	 * }
	 */
}
