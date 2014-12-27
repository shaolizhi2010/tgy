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
import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.util.U;

public class FolderService {

	FolderDao fDao = new FolderDao();
	
	public List<Folder> list(int num){
		return fDao.list(num);
	}
	
	public void copy(String folderID, String userID){
		PageService pageService =   new PageService();
		Folder folder = fDao.getByID(folderID);
		
		Folder targetFolder = new Folder();
		targetFolder.name = folder.name;
		targetFolder.createDate = U.dateTime();
		targetFolder.userID = userID;
		
		fDao.save(targetFolder);
		
		for(Page p : folder.pages){
			
			Page newPage = new Page();
			newPage.name = p.name;
			newPage.description = p.description;
			newPage.url = p.url;
			newPage.createDate = U.dateTime();
			newPage.iconPath = p.iconPath;
			newPage.pid = targetFolder.id.toString();
			newPage.userID = userID;
			try {
				pageService.save(newPage);
			} catch (BaseException e) {
				System.out.println("copy page : "+newPage.name + e.getMessage());
			}
		}
	}

	public Folder ByUserIDAndName(String folderName, String userID) {

		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("name", folderName)
				.filter("userID", userID);

		return fDao.find(query).get();
	}

	public List<Folder> ByName(String folderName) {

		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("name", folderName)
				.order("-favScore");

		return fDao.find(query).asList();

	}

	public void copy(Page page, String userID) {
		// TODO save
	}

	public Folder save(Folder folder) throws BaseException {

		// check user exsit
		UserService uService = new UserService();

		folder.isRoot = true;

		folder.createDate = U.dateTime();

		folder.color = U.randomColor();

		fDao.saveWithRef(folder);

		/*
		 * TODO // save tag // TODO asyn TagDao tDao = new TagDao(); Tag tag =
		 * tDao.getByName(folder.name); if (tag == null) { // tag第一次被创建 tag =
		 * new Tag(); tag.name = folder.name; tag.createDate =
		 * folder.createDate; tag.firstCreateBy = user; tag.add(user); tag.keeps
		 * = 1; tag.favScore = Tag.keepScore; tDao.save(tag);// save tag } else
		 * { // tag已存在
		 * 
		 * if (tag.users == null || !tag.users.contains(user)) { // 该用户第一次收藏
		 * tag.keeps++; tag.favScore += Tag.keepScore; tag.add(user);
		 * tDao.save(tag); // save tag } // 用户重复收藏，不记分,也不再记录user
		 * 
		 * }
		 */
		return folder;
	}

	public Folder rootFolder(String folderID) {
		Folder folder = fDao.getByID(folderID);
		if (folder == null) {
			return null;
		}
		if (folder.isRoot) {
			return folder;
		} else {
			return rootFolder(folder.pid);
		}

	}

	public List<Folder> getFoldersByUserID(String userID) {
		return fDao.getFoldersByUserID(userID);
	}

	public List<Folder> getFoldersByUserID(String userID, String sort) {
		return fDao.getFoldersByUserID(userID, sort);
	}

	public void saveWithRef(Folder folder) {
		fDao.saveWithRef(folder);
	}

	public void deleteWithRef(Folder folder) {
		fDao.deleteWithRef(folder);
	}

	public int hashCode() {
		return fDao.hashCode();
	}

	public Folder getByID(String folderID) {
		return fDao.getByID(folderID);
	}

	public DatastoreImpl getDs() {
		return fDao.getDs();
	}

	public Class<Folder> getEntityClazz() {
		return fDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return fDao.equals(obj);
	}

	public DBCollection getCollection() {
		return fDao.getCollection();
	}

	public Query<Folder> createQuery() {
		return fDao.createQuery();
	}

	public UpdateOperations<Folder> createUpdateOperations() {
		return fDao.createUpdateOperations();
	}

	public Class<Folder> getEntityClass() {
		return fDao.getEntityClass();
	}

//	public Key<Folder> save(Folder entity) {
//		return fDao.save(entity);
//	}

	public Key<Folder> save(Folder entity, WriteConcern wc) {
		return fDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Folder> q,
			UpdateOperations<Folder> ops) {
		return fDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Folder> q, UpdateOperations<Folder> ops) {
		return fDao.update(q, ops);
	}

	public WriteResult delete(Folder entity) {
		return fDao.delete(entity);
	}

	public WriteResult delete(Folder entity, WriteConcern wc) {
		return fDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return fDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Folder> q) {
		return fDao.deleteByQuery(q);
	}

	public Folder get(ObjectId id) {
		return fDao.get(id);
	}

	public List<ObjectId> findIds() {
		return fDao.findIds();
	}

	public List<ObjectId> findIds(Query<Folder> q) {
		return fDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return fDao.findIds(key, value);
	}

	public Key<Folder> findOneId() {
		return fDao.findOneId();
	}

	public Key<Folder> findOneId(String key, Object value) {
		return fDao.findOneId(key, value);
	}

	public Key<Folder> findOneId(Query<Folder> query) {
		return fDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return fDao.exists(key, value);
	}

	public boolean exists(Query<Folder> q) {
		return fDao.exists(q);
	}

	public long count() {
		return fDao.count();
	}

	public long count(String key, Object value) {
		return fDao.count(key, value);
	}

	public long count(Query<Folder> q) {
		return fDao.count(q);
	}

	public Folder findOne(String key, Object value) {
		return fDao.findOne(key, value);
	}

	public Folder findOne(Query<Folder> q) {
		return fDao.findOne(q);
	}

	public QueryResults<Folder> find() {
		return fDao.find();
	}

	public QueryResults<Folder> find(Query<Folder> q) {
		return fDao.find(q);
	}

	public Datastore getDatastore() {
		return fDao.getDatastore();
	}

	public void ensureIndexes() {
		fDao.ensureIndexes();
	}

	public String toString() {
		return fDao.toString();
	}
	
	
}
