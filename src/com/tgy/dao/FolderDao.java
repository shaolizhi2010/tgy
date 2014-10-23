package com.tgy.dao;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.tgy.App;
import com.tgy.entity.Bookmark;
import com.tgy.entity.Folder;
import com.tgy.entity.User;

public class FolderDao extends BasicDAO<Folder, ObjectId> {

	public FolderDao() {
		super(Folder.class, App.getInstance().getDatastore());
	}

	public void saveWithRef(Folder folder) {

		save(folder);
		
		//保存子文件夹
		if (StringUtils.isNotBlank(folder.pid)) {

			FolderDao folderDao = new FolderDao();
			Folder pFolder = folderDao.getByID(folder.pid);
			if (pFolder.folders == null) {
				pFolder.folders = new ArrayList<Folder>();
			}
			pFolder.folders.add(folder);
			folderDao.save(pFolder);
		}
		//保存一级文件夹
		else if (StringUtils.isNotBlank(folder.bookmarkID)) {

			BookmarkDao bookmarkDao = new BookmarkDao();
			Bookmark bookmark = bookmarkDao.getByID(folder.bookmarkID);
			if (bookmark.folders == null) {
				bookmark.folders = new ArrayList<Folder>();
			}
			bookmark.folders.add(folder);
			bookmarkDao.save(bookmark);

		}

	}

	public Folder getByID(String folderID) {
		 
		Folder folder = findOne("_id", new ObjectId(folderID));
		return folder;
	}

}
