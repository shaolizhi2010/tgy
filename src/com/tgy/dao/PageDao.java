package com.tgy.dao;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.tgy.App;
import com.tgy.entity.Bookmark;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;

public class PageDao extends BasicDAO<Page, ObjectId> {

	public PageDao() {
		super(Page.class, App.getInstance().getDatastore());
	}

	public void saveWithRef(Page page) {

		save(page);

		//文件夹中保存的网址
		if (StringUtils.isNotBlank(page.pid)) {

			FolderDao folderDao = new FolderDao();
			Folder pFolder = folderDao.getByID(page.pid);
			if (pFolder.pages == null) {
				pFolder.pages = new ArrayList<Page>();
			}
			pFolder.pages.add(page);
			folderDao.save(pFolder);
		}
		//书签直接保存的网址
		else if (StringUtils.isNotBlank(page.bookmarkID)) {

			BookmarkDao bookmarkDao = new BookmarkDao();
			 
			Bookmark bookmark = bookmarkDao.getByID(page.bookmarkID);
			if (bookmark.pages == null) {
				bookmark.pages = new ArrayList<Page>();
			}
			bookmark.pages.add(page);
			bookmarkDao.save(bookmark);
		}
	}
}
