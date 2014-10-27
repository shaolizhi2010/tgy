package com.tgy.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.Folder;

public class FolderDao extends BasicDAO<Folder, ObjectId> {

	public FolderDao() {
		super(Folder.class, App.getInstance().getDatastore());
	}
	
	public List<Folder> getFoldersByUserID(String userID){
		
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("userID", userID).filter("pid", null)
				.order("-createDate");

		return find(query).asList();

	}

	public void saveWithRef(Folder folder) {
		
		save(folder);
		
		//保存父文件夹,目前只支持保存在根文件夹
		if (StringUtils.isNotBlank(folder.pid)) {

			FolderDao folderDao = new FolderDao();
			Folder rootFolder = folder;
			//not root and pid not null
			while(!rootFolder.isRoot && StringUtils.isNotBlank(rootFolder.pid)){
				Folder pFolder = folderDao.getByID(rootFolder.pid);
				rootFolder = pFolder;
			}
 
			rootFolder.add(folder);
			folderDao.save(rootFolder);
		}
		 

	}

	public Folder getByID(String folderID) {
		if(StringUtils.isBlank(folderID))return null;
		Folder folder = findOne("_id", new ObjectId(folderID));
		return folder;
	}
 

}
