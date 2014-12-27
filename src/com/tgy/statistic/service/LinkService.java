package com.tgy.statistic.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.entity.Tag;

public class LinkService {
	
	public List<Link> getByName(String name) {
		return linkDao.getByName(name);
	}

	public List<Link> getByTag(String tagID) {
		return linkDao.getByTag(tagID);
	}

	public int hashCode() {
		return linkDao.hashCode();
	}

	public Link getByUrl(String url) {
		return linkDao.getByUrl(url);
	}

	public List<Link> searchByUrl(String url) {
		return linkDao.searchByUrl(url);
	}

	public Link getByID(String linkID) {
		return linkDao.getByID(linkID);
	}

	public void incKeeps(String linkID) {
		linkDao.incKeeps(linkID);
	}

	public void inc(String linkID, String filed, int num) {
		linkDao.inc(linkID, filed, num);
	}

	public DatastoreImpl getDs() {
		return linkDao.getDs();
	}

	public void inc(String linkID, String filed) {
		linkDao.inc(linkID, filed);
	}

	public Class<Link> getEntityClazz() {
		return linkDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return linkDao.equals(obj);
	}

	public DBCollection getCollection() {
		return linkDao.getCollection();
	}

	public Query<Link> createQuery() {
		return linkDao.createQuery();
	}

	public UpdateOperations<Link> createUpdateOperations() {
		return linkDao.createUpdateOperations();
	}

	public Class<Link> getEntityClass() {
		return linkDao.getEntityClass();
	}

	public Key<Link> save(Link entity) {
		return linkDao.save(entity);
	}

	public Key<Link> save(Link entity, WriteConcern wc) {
		return linkDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<Link> q, UpdateOperations<Link> ops) {
		return linkDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<Link> q, UpdateOperations<Link> ops) {
		return linkDao.update(q, ops);
	}

	public WriteResult delete(Link entity) {
		return linkDao.delete(entity);
	}

	public WriteResult delete(Link entity, WriteConcern wc) {
		return linkDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return linkDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<Link> q) {
		return linkDao.deleteByQuery(q);
	}

	public Link get(ObjectId id) {
		return linkDao.get(id);
	}

	public List<ObjectId> findIds() {
		return linkDao.findIds();
	}

	public List<ObjectId> findIds(Query<Link> q) {
		return linkDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return linkDao.findIds(key, value);
	}

	public Key<Link> findOneId() {
		return linkDao.findOneId();
	}

	public Key<Link> findOneId(String key, Object value) {
		return linkDao.findOneId(key, value);
	}

	public Key<Link> findOneId(Query<Link> query) {
		return linkDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return linkDao.exists(key, value);
	}

	public boolean exists(Query<Link> q) {
		return linkDao.exists(q);
	}

	public long count() {
		return linkDao.count();
	}

	public long count(String key, Object value) {
		return linkDao.count(key, value);
	}

	public long count(Query<Link> q) {
		return linkDao.count(q);
	}

	public Link findOne(String key, Object value) {
		return linkDao.findOne(key, value);
	}

	public Link findOne(Query<Link> q) {
		return linkDao.findOne(q);
	}

	public QueryResults<Link> find() {
		return linkDao.find();
	}

	public QueryResults<Link> find(Query<Link> q) {
		return linkDao.find(q);
	}

	public Datastore getDatastore() {
		return linkDao.getDatastore();
	}

	public void ensureIndexes() {
		linkDao.ensureIndexes();
	}

	public String toString() {
		return linkDao.toString();
	}

	LinkDao linkDao = new LinkDao();
	TagDao tagDao = new TagDao();
	
	public List<Link> list(int num){
		return linkDao.list(num);
	}

	public List<Link> searchByTagName(String tagName) {
		
		if(StringUtils.isBlank(tagName)){
			
		}

		Tag tag = tagDao.getByName(tagName);
		if (tag == null || tag.links == null) {
			return new ArrayList<>();
		}

		// LinkDao lDao = new LinkDao();

		// sort TODO may cause performance issue
		List<Link> links = tag.links;
		Collections.sort(links);
		Collections.reverse(links);
		if (tag.links.size() > 20) {
			tag.links.subList(0, 20);
		}
		return tag.links;

	}
	
	public List<Link> list(){
		
		LinkDao lDao = new LinkDao();
		
		Query<Link> query = App.getInstance().getDatastore()
				.createQuery(Link.class).order("-favScore").limit(50);
		
		return lDao.find(query).asList();
		
	}

	/**
	 * 扫描page表所有的网页，生成对应的信息。
	 */
	public void scan() {

		PageDao pDao = new PageDao();
		FolderDao fDao = new FolderDao();
		LinkDao lDao = new LinkDao();
		TagDao tDao = new TagDao();
		UserDao uDao = new UserDao();
		
		// TODO 分组 -> mapreduce
		//只统计处理新增数据，TODO 更改或删除也应该纳入处理
		
		// 找到从来没处理过的
		Query<Page> query = App.getInstance().getDatastore()
				.createQuery(Page.class).filter("scanTimes", 0);
		Iterator<Page> it = pDao.find(query).iterator();
		
		while (it.hasNext()) {
			
			try{
				Page page = it.next();
				page.scanTimes++;
				pDao.save(page);
				
				User user = uDao.getByID(page.userID);
				
				// save link
				boolean newLinkFlag = false;
				Link link = lDao.getByUrl(page.url);
				if (link == null) { // first create
					newLinkFlag = true;
					link = new Link();
					link.url = StringUtils.trim(page.url);
					link.title = page.name;// TODO title should be html title.
					link.createDate = page.createDate;
					link.clicks = 0;
					link.keeps = 1;
					link.favScore = Link.keepScore;
				} else {
					link.keeps++;
					link.favScore += Link.keepScore;
				}
				link.add(user);

				// pre get tag

				// get folder name
				Folder folder = fDao.getByID(page.pid);
				Tag tag = null;
				if (folder != null) {
					// get tab by folder name
					tag = tDao.getByName(folder.name);

					if (tag != null) {
						if (link.tags == null || !link.tags.contains(tag)) {
							// add tag into link
							link.add(tag);
						}
					}
				}
				// save link
				lDao.save(link);
				// add link into tag and save tag
				if (tag != null && newLinkFlag) {// 只有是新的link才add到Tag里，避免重复
					tag.add(link);
					tDao.save(tag);
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				continue;
			}
		}

	}
}
