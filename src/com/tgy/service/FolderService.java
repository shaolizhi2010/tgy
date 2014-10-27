package com.tgy.service;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Link;
import com.tgy.entity.Page;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.U;

public class FolderService {

	public void copy(Page page, String userID) {

		// TODO save
	}

	public Folder save(Folder folder) throws BaseException {
		
		// check user exsit
		UserService uService = new UserService();
		User user = uService.checkAndGetUser(folder.userID);
		
		boolean isRoot = folder.isRoot;
		FolderDao fDao = new FolderDao();
		folder.createDate = U.dateTime();
		
		if(isRoot){ //root folder
			fDao.saveWithRef(folder);
		}
		else{ //不是Root folder
			if(StringUtils.isNoneBlank(folder.pid)){//有pid
				fDao.saveWithRef(folder);
			}
			else{ //不是root folder 又没有pid，说明用户没有创建书签，直接点击的创建分类按钮
				//这种情况为用户创建一个 ‘默认收藏夹’，然后把默认收藏夹佐夫 root folder
				
				Folder defaultRootFolder = new Folder();
				defaultRootFolder.name = "未命名";
				defaultRootFolder.isRoot = true;
				defaultRootFolder.createDate = U.dateTime();
				defaultRootFolder.userID = folder.userID;
				fDao.save(defaultRootFolder); //save root folder
				if(defaultRootFolder.id == null){ 
					folder.pid = null; //创建root folder 失败，把folder作为 root folder
				}
				else{
					folder.pid = defaultRootFolder.id.toString();
				}
				fDao.saveWithRef(folder);
				//系统自动创建的root folder 不建立相应的Tag，
			}
		}

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

		return folder;
	}
}
