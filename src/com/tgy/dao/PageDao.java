package com.tgy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;

public class PageDao extends BasicDAO<Page, ObjectId> {

	public PageDao() {
		super(Page.class, App.getInstance().getDatastore());
	}

	public void saveWithRef(Page page) {
		save(page);

		// 文件夹中保存的网址
		if (StringUtils.isNotBlank(page.pid)) {

			FolderDao folderDao = new FolderDao();
			Folder pFolder = folderDao.getByID(page.pid);
			if (pFolder != null) {
				pFolder.add(page);
				folderDao.save(pFolder);
				return;
			}
		}

		System.out.println("page : " + page.name
				+ " can not find parent folder [pid=" + page.pid
				+ "], ignore saving parent folder.");

	}
	public void deleteWithRef(Page page) {
		delete(page);

		// 文件夹中保存的网址
		if (StringUtils.isNotBlank(page.pid)) {

			FolderDao folderDao = new FolderDao();
			Folder pFolder = folderDao.getByID(page.pid);
			if (pFolder != null) {
				pFolder.remove(page);
				folderDao.save(pFolder);
				return;
			}
		}

	}

	public void saveWithRef(Page page, Folder folder) {
		if (folder == null) {
			save(page);
			return;
		}
		page.pid = folder.id.toString();
		save(page);

		folder.add(page);
		FolderDao fDao = new FolderDao();
		fDao.save(folder);
	}

	public Page getByID(String id) {
		if (StringUtils.isBlank(id))
			return null;
		Page page = findOne("_id", new ObjectId(id));
		return page;
	}

	public List<Page> getByUserID(String userID,String orderBy) {
		if (StringUtils.isBlank(userID))
			return new ArrayList<>();
		
		if(orderBy == null){
			orderBy = "-clicks";
		}

		Query<Page> query = App.getInstance().getDatastore()
				.createQuery(Page.class).filter("userID", userID).order(orderBy);

		List<Page> pages = find(query).asList();
		return pages;
	}
}
