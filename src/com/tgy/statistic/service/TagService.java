package com.tgy.statistic.service;

import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.dao.TagDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.statistic.entity.Tag;

public class TagService {

	FolderService fService = new FolderService();

	public Tag searchByName(String name) {

		TagDao tDao = new TagDao();

		Tag tag = tDao.getByName(name);
		return tag;

	}

	//扫描 folder表， 并声称Tag
	public void scan() {
		FolderDao fDao = new FolderDao();
		UserDao uDao = new UserDao();
		
		//TODO 分组 -> mapreduce
		
		//找到从来没处理过的
		Query<Folder> query = App.getInstance().getDatastore()
				.createQuery(Folder.class).filter("scanTimes",0);
		
		Iterator<Folder> it = fDao.find(query).iterator();
		while (it.hasNext()) {
			Folder folder = it.next();
			folder.scanTimes ++;
			fDao.save(folder);
			
			User user = uDao.getByID(folder.userID);

			TagDao tDao = new TagDao();
			Tag tag = tDao.getByName(folder.name);
			if (tag == null) { // tag第一次被创建
				tag = new Tag();
				tag.name = folder.name;
				tag.createDate = folder.createDate;
				tag.keeps = 1;
				tag.favScore = Tag.keepScore;

			} else { // tag已存在,计数加一
				tag.keeps++;
				tag.favScore += Tag.keepScore;

			}
			tag.add(user);
			tDao.save(tag);// save tag
		}

	}
	
	public List<Tag> list(){
		
		TagDao tDao = new TagDao();
		
		Query<Tag> query = App.getInstance().getDatastore()
				.createQuery(Tag.class).order("-favScore").limit(50);
		
		return tDao.find(query).asList();
		
	}

	/*
	 * public List<Link> searchByName(String name){
	 * 
	 * TagDao tDao = new TagDao(); LinkDao lDao = new LinkDao();
	 * 
	 * Tag tag = tDao.getByName(name); if(tag==null){ return new ArrayList<>();
	 * } return tag.links;
	 * 
	 * }
	 */
}
