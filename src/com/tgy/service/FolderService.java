package com.tgy.service;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.util.U;

public class FolderService {

	FolderDao fDao = new FolderDao();
	
	public   Folder  ByUserIDAndName(String folderName, String userID){
		
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("name", folderName).filter("userID", userID);
		
		return fDao.find(query).get();
	}
	
	public List<Folder> ByName(String folderName){
		
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("name", folderName).order("-favScore");
		
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
		
		
		
/*TODO
		// save tag
		// TODO asyn
		TagDao tDao = new TagDao();
		Tag tag = tDao.getByName(folder.name);
		if (tag == null) { // tag第一次被创建
			tag = new Tag();
			tag.name = folder.name;
			tag.createDate = folder.createDate;
			tag.firstCreateBy = user;
			tag.add(user);
			tag.keeps = 1;
			tag.favScore = Tag.keepScore;
			tDao.save(tag);// save tag
		} else { // tag已存在

			if (tag.users == null || !tag.users.contains(user)) { // 该用户第一次收藏
				tag.keeps++;
				tag.favScore += Tag.keepScore;
				tag.add(user);
				tDao.save(tag); // save tag
			}
			// 用户重复收藏，不记分,也不再记录user

		}
*/
		return folder;
	}
	
	public Folder rootFolder(String folderID){
		Folder folder  = fDao.getByID(folderID);
		if(folder == null){
			return null;
		}
		if(folder.isRoot){
			return folder;
		}
		else{
			return rootFolder(folder.pid);
		}
		
		
	}
}
