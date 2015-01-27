package com.tgy.service.group;

import java.util.List;
import java.util.Map;

import org.bson.NewBSONDecoder;
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
import com.tgy.dao.group.InterestGroupFolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.group.InterestGroupFolder;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;

public class InterestGroupFolderService {

	InterestGroupFolderDao fDao = new InterestGroupFolderDao();
	
	
	
	public void copy(String folderID, String userID) throws BaseException{
		FolderService fService = new FolderService();
		PageService pageService =   new PageService();
		InterestGroupFolder folder = fDao.byID(folderID);
		
		Folder targetFolder = new Folder();
		targetFolder.name = folder.name;
		targetFolder.userID = userID;
		
		try {
			fService.save(targetFolder);
		} catch (BaseException e) {
			System.out.println("copy folder : "+targetFolder.name + e.getMessage());
			throw new BaseException(this, e.getMessage(), e);
		}
		
		for(InterestGroupPage p : folder.pages){
			
			Page newPage = new Page();
			newPage.name = p.name;
			newPage.description = p.description;
			newPage.url = p.url;
			newPage.iconPath = p.iconPath;
			newPage.pid = targetFolder.id.toString();
			newPage.folderID = targetFolder.id.toString();
			newPage.userID = userID;
			try {
				pageService.save(newPage);
			} catch (Exception e) {
				System.out.println("copy page : "+newPage.name + e.getMessage());
				//throw new BaseException(this, e.getMessage(), e);
				continue;
			}
		}
	}
	
	public InterestGroupFolder byNameAndGroupID(String name, String groupID){
		return get(new ConditionMap().add("name", name).add("groupID", groupID));
	}
	
	public List<InterestGroupFolder> ByName(String folderName) {

		Query<InterestGroupFolder> query = App.getInstance().getDatastore()
				.createQuery(InterestGroupFolder.class).filter("name", folderName)
				.order("-favScore");

		return fDao.find(query).asList();
	}

	public InterestGroupFolder byID(String id) {
		return fDao.byID(id);
	}

	public InterestGroupFolder get( 
			Map<String, Object> conditions) {
		return fDao.get(InterestGroupFolder.class, conditions);
	}

	public List<InterestGroupFolder> list( 
			Map<String, Object> conditions, String orderStr, int limit) {
		return fDao.list(InterestGroupFolder.class, conditions, orderStr, limit);
	}

	public int hashCode() {
		return fDao.hashCode();
	}

	public DatastoreImpl getDs() {
		return fDao.getDs();
	}

	public Class<InterestGroupFolder> getEntityClazz() {
		return fDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return fDao.equals(obj);
	}

	public DBCollection getCollection() {
		return fDao.getCollection();
	}


	public Query<InterestGroupFolder> createQuery() {
		return fDao.createQuery();
	}


	public UpdateOperations<InterestGroupFolder> createUpdateOperations() {
		return fDao.createUpdateOperations();
	}


	public Class<InterestGroupFolder> getEntityClass() {
		return fDao.getEntityClass();
	}


	public Key<InterestGroupFolder> save(InterestGroupFolder entity) {
		return fDao.save(entity);
	}


	public Key<InterestGroupFolder> save(InterestGroupFolder entity,
			WriteConcern wc) {
		return fDao.save(entity, wc);
	}


	public UpdateResults updateFirst(Query<InterestGroupFolder> q,
			UpdateOperations<InterestGroupFolder> ops) {
		return fDao.updateFirst(q, ops);
	}


	public UpdateResults update(Query<InterestGroupFolder> q,
			UpdateOperations<InterestGroupFolder> ops) {
		return fDao.update(q, ops);
	}


	public WriteResult delete(InterestGroupFolder entity) {
		return fDao.delete(entity);
	}


	public WriteResult delete(InterestGroupFolder entity, WriteConcern wc) {
		return fDao.delete(entity, wc);
	}


	public WriteResult deleteById(ObjectId id) {
		return fDao.deleteById(id);
	}


	public WriteResult deleteByQuery(Query<InterestGroupFolder> q) {
		return fDao.deleteByQuery(q);
	}


	public InterestGroupFolder get(ObjectId id) {
		return fDao.get(id);
	}


	public List<ObjectId> findIds() {
		return fDao.findIds();
	}


	public List<ObjectId> findIds(Query<InterestGroupFolder> q) {
		return fDao.findIds(q);
	}


	public List<ObjectId> findIds(String key, Object value) {
		return fDao.findIds(key, value);
	}


	public Key<InterestGroupFolder> findOneId() {
		return fDao.findOneId();
	}


	public Key<InterestGroupFolder> findOneId(String key, Object value) {
		return fDao.findOneId(key, value);
	}


	public Key<InterestGroupFolder> findOneId(Query<InterestGroupFolder> query) {
		return fDao.findOneId(query);
	}


	public boolean exists(String key, Object value) {
		return fDao.exists(key, value);
	}


	public boolean exists(Query<InterestGroupFolder> q) {
		return fDao.exists(q);
	}


	public long count() {
		return fDao.count();
	}


	public long count(String key, Object value) {
		return fDao.count(key, value);
	}


	public long count(Query<InterestGroupFolder> q) {
		return fDao.count(q);
	}


	public InterestGroupFolder findOne(String key, Object value) {
		return fDao.findOne(key, value);
	}


	public InterestGroupFolder findOne(Query<InterestGroupFolder> q) {
		return fDao.findOne(q);
	}


	public QueryResults<InterestGroupFolder> find() {
		return fDao.find();
	}


	public QueryResults<InterestGroupFolder> find(Query<InterestGroupFolder> q) {
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
