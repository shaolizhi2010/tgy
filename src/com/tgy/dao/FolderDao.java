package com.tgy.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.BasePage;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.util.U;

public class FolderDao extends BaseBasicDAO<Folder, ObjectId> {

	public FolderDao() {
		super(Folder.class, App.getInstance().getDatastore());
	}

	public List<Folder> list(int num) {
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).order("-favScore").limit(num);
		return find(query).asList();
	}
	
	// TODO
	// public List<Folder> getPublicFoldersByUserID(String userID) {
	//
	// Query<Folder> query = App.getInstance().getDatastore()
	// .createQuery(Folder.class).filter("userID", userID)
	// .filter("pid", null).order("-createDate");
	//
	// return find(query).asList();
	//
	// }

	public List<Folder> getFoldersByUserID(String userID) {

		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("userID", userID)
				.order("-favScore");

		return find(query).asList();

	}
	
	public List<Folder> getFoldersByUserID(String userID,String sort) {
		if(StringUtils.isBlank(sort)){
			return getFoldersByUserID(userID);
		}
		
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("userID", userID)
				.order(sort);
		
		return find(query).asList();

	}

	public void saveWithRef(Folder folder) {
		folder.lastModifyDate = U.dateTime();
		save(folder);
	}

	public void deleteWithRef(Folder folder) {

		// delete all sub pages;
		PageDao pDao = new PageDao();
		if (!CollectionUtils.isEmpty(folder.pages)) {
			for (Page p : folder.pages) {
				pDao.delete(p);
			}
		}

		// delete folder
		delete(folder);

	}

	public Folder getByID(String folderID) {
		if (StringUtils.isBlank(folderID))
			return null;
		Folder folder = findOne("_id", new ObjectId(folderID));
		return folder;
	}

}
