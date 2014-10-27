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

public class PageService {

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

		// check user exsit
		UserService uService = new UserService();
		User user = uService.checkAndGetUser(page.userID);

		PageDao pDao = new PageDao();
		FolderDao fDao = new FolderDao();
		FolderService fService = new FolderService();

		if (StringUtils.isBlank(page.pid)) { // 没有pid，说明用户没有创建书签，直接点击的创建网页按钮
			// 这种情况为用户创建一个 默认收藏夹，然后把默认收藏夹佐夫 root folder

			Folder defaultRootFolder = new Folder();
			defaultRootFolder.name = "未命名";
			defaultRootFolder.isRoot = true;
			defaultRootFolder.createDate = U.dateTime();
			defaultRootFolder.userID = page.userID;
			fDao.save(defaultRootFolder); // save root folder
			if (defaultRootFolder.id == null) {
				throw new BaseException(this, "需要先创建一个网页分类");
			} else {
				page.pid = defaultRootFolder.id.toString();
			}

		}
		//save page
		pDao.saveWithRef(page);

		// 下边的处理可以异步执行，尽快返回保存结果给用户 TODO

		// save link
		boolean newLinkFlag = false;
		LinkDao lDao = new LinkDao();
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
			link.firstCreateBy = user;
			link.add(user);
		} else {

			if (link.users == null || !link.users.contains(user)) { // 该用户第一次收藏
				link.keeps++;
				link.favScore += Link.keepScore;
				link.add(user);
			}
			// 用户重复收藏，不记分,也不再记录user
		}

		// pre get tag
		TagDao tDao = new TagDao();
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
		if (tag != null && newLinkFlag) {
			tag.add(link);
			tDao.save(tag);
		}

		return page;

	}
	/*
	 * public List<Link> searchByName(String name){
	 * 
	 * PageDao tDao = new PageDao(); LinkDao lDao = new LinkDao();
	 * 
	 * Page page = tDao.getByName(name); if(page==null){ return new
	 * ArrayList<>(); } return page.links;
	 * 
	 * }
	 */
}
