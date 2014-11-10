package com.tgy.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;

public class FolderDao extends BasicDAO<Folder, ObjectId> {

	public FolderDao() {
		super(Folder.class, App.getInstance().getDatastore());
	}

	//TODO
//	public List<Folder> getPublicFoldersByUserID(String userID) {
//
//		Query<Folder> query = App.getInstance().getDatastore()
//				.createQuery(Folder.class).filter("userID", userID)
//				.filter("pid", null).order("-createDate");
//
//		return find(query).asList();
//
//	}

	public List<Folder> getFoldersByUserID(String userID) {

		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("userID", userID)
				.filter("pid", null).order("-createDate");

		return find(query).asList();

	}

	public void saveWithRef(Folder folder) {

		// 保存父文件夹,目前只支持保存在根文件夹
		if (StringUtils.isNotBlank(folder.pid)) {

			Folder rootFolder = folder;
			// not root and pid not null
			while (!rootFolder.isRoot && StringUtils.isNotBlank(rootFolder.pid)) {
				Folder pFolder = getByID(rootFolder.pid);
				rootFolder = pFolder;
			}
			folder.pid = rootFolder.id.toString();
			save(folder);
			
			rootFolder.add(folder);
			save(rootFolder);
		}
		else{ //not pid, then it is a root folder
			folder.isRoot = true;
			save(folder);
		}

	}
	
	public void deleteWithRef(Folder folder) {
		
		//delete all sub pages;
		PageDao pDao = new PageDao();
		if(!CollectionUtils.isEmpty(folder.pages)){
			for(Page p : folder.pages){
				pDao.delete(p);
			}
		}

		//delete all sub folders
		if(!CollectionUtils.isEmpty(folder.folders)){
			for(Folder f : folder.folders){
				 deleteWithRef(f);
			}	
		}
		
		//delete folder
		delete(folder);

		// delete ref from root folder
		if (StringUtils.isNotBlank(folder.pid)) {

			Folder pFolder = getByID(folder.pid);
			pFolder.remove(folder);
			save(pFolder);
		}
	}

	public Folder getByID(String folderID) {
		if (StringUtils.isBlank(folderID))
			return null;
		Folder folder = findOne("_id", new ObjectId(folderID));
		return folder;
	}

}
