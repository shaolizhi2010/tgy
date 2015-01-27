package com.tgy.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.tgy.asyn.GetPageInfoThread;
import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Discuss;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.statistic.entity.Link;
import com.tgy.util.U;

public class PageService {
	
	PageDao pDao = new PageDao();
	
	public Query<Page> createQuery( 
			Map<String, Object> conditions,String orderStr, int start, int limit) {
		return pDao.createQuery(Page.class, conditions,orderStr,start,limit);
	}

	
	public Page byID(String id) {
		return pDao.byID(id);
	}

	public List<Page> list(  Map<String, Object> conditions,
			String orderStr, int start, int limit) {
		return pDao.list(Page.class, conditions, orderStr, start, limit);
	}

	public List<Page> list(String userID, String sortStr, int limit) {
		return pDao.list(userID, sortStr, limit);
	}

	public List<Page> search(String userID, String name, String url, String orderStr){
		Query<Page> query = App.getInstance().getDatastore()
				.createQuery(Page.class);
		if(StringUtils.isNotBlank(userID)){
			query.filter("userID", userID);
		}
		if(StringUtils.isNotBlank(url)){
			url = url.trim();
			query.filter("url", Pattern.compile(".*"+url+".*", Pattern.CASE_INSENSITIVE));
		}	
		if(StringUtils.isNotBlank(name)){
			name = name.trim();
			query.filter("name", Pattern.compile(".*"+name+".*", Pattern.CASE_INSENSITIVE));
		}	
		if(StringUtils.isNotBlank(orderStr)){
			query.order(orderStr);
		}
		else{
			query.order("-favScore");
		}
		
		return find(query).asList();
		
	}
	
	public void copy(Page page, String userID) {
		Page newPage = new Page();
		newPage.name = page.name;
		newPage.url = page.url;
		newPage.createDate = U.dateTime();
		newPage.userID = userID;

		// TODO save
	}

	public Page save(Page page) throws BaseException {

		page.createDate = U.dateTime();

		PageDao pDao = new PageDao();
		FolderDao fDao = new FolderDao();
		FolderService fService = new FolderService();

		if (StringUtils.isBlank(page.pid)) { // 没有pid，说明用户没有创建书签，直接点击的创建网页按钮
			// 这种情况为用户创建一个 默认收藏夹，然后把默认收藏夹佐夫 root folder

			Folder defaultRootFolder = fService.ByUserIDAndName("未命名", page.userID);
			
			if(defaultRootFolder==null){
				 defaultRootFolder = new Folder();
					defaultRootFolder.name = "未命名";
					defaultRootFolder.createDate = U.dateTime();
					defaultRootFolder.userID = page.userID;
					fDao.save(defaultRootFolder); // save root folder
			}
			 
			
			if (defaultRootFolder.id == null) {
				throw new BaseException(this, "需要先创建一个网页分类");
			} else {
				page.pid = defaultRootFolder.id.toString();
			}

		}
		//save page
		pDao.saveWithRef(page);
		
		if(StringUtils.isBlank(page.name)){
			
			LinkDao linkDao = new LinkDao();
			
			Link link = linkDao.getByUrl(page.url);
			if(link == null || StringUtils.isBlank(link.title) ){
				new Thread(new GetPageInfoThread(page.url, page.id.toString())).start();
				try { //wait second
					new Thread().sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			else{
				page.name = link.title;
				pDao.save(page);
			}
		
		}

		return page;

	}

	public void saveWithRef(Page page) {
		pDao.saveWithRef(page);
	}

	public void deleteWithRef(Page page) {
		pDao.deleteWithRef(page);
	}

	public void saveWithRef(Page page, Folder folder) {
		pDao.saveWithRef(page, folder);
	}

	public int hashCode() {
		return pDao.hashCode();
	}

//	public Page getByID(String id) {
//		return pDao.getByID(id);
//	}

	public List<Page> getByUserID(String userID, String orderBy) {
		return pDao.getByUserID(userID, orderBy);
	}

	public List<Page> getByUrl(String url) {
		return pDao.getByUrl(url);
	}

	public DatastoreImpl getDs() {
		return pDao.getDs();
	}

	public Class<Page> getEntityClazz() {
		return pDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return pDao.equals(obj);
	}

	public DBCollection getCollection() {
		return pDao.getCollection();
	}

	public Query<Page> createQuery() {
		return pDao.createQuery();
	}

	public UpdateOperations<Page> createUpdateOperations() {
		return pDao.createUpdateOperations();
	}

	public Class<Page> getEntityClass() {
		return pDao.getEntityClass();
	}
 
	public Key<Page> save(Page entity, WriteConcern wc) {
		return pDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Page> q, UpdateOperations<Page> ops) {
		return pDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Page> q, UpdateOperations<Page> ops) {
		return pDao.update(q, ops);
	}

	public WriteResult delete(Page entity) {
		return pDao.delete(entity);
	}

	public WriteResult delete(Page entity, WriteConcern wc) {
		return pDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return pDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Page> q) {
		return pDao.deleteByQuery(q);
	}

	public Page get(ObjectId id) {
		return pDao.get(id);
	}

	public List<ObjectId> findIds() {
		return pDao.findIds();
	}

	public List<ObjectId> findIds(Query<Page> q) {
		return pDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return pDao.findIds(key, value);
	}

	public Key<Page> findOneId() {
		return pDao.findOneId();
	}

	public Key<Page> findOneId(String key, Object value) {
		return pDao.findOneId(key, value);
	}

	public Key<Page> findOneId(Query<Page> query) {
		return pDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return pDao.exists(key, value);
	}

	public boolean exists(Query<Page> q) {
		return pDao.exists(q);
	}

	public long count() {
		return pDao.count();
	}

	public long count(String key, Object value) {
		return pDao.count(key, value);
	}

	public long count(Query<Page> q) {
		return pDao.count(q);
	}

	public Page findOne(String key, Object value) {
		return pDao.findOne(key, value);
	}

	public Page findOne(Query<Page> q) {
		return pDao.findOne(q);
	}

	public QueryResults<Page> find() {
		return pDao.find();
	}

	public QueryResults<Page> find(Query<Page> q) {
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
